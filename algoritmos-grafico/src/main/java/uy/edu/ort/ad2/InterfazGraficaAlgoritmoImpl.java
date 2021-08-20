package uy.edu.ort.ad2;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public
class InterfazGraficaAlgoritmoImpl
    implements InterfazGraficaAlgoritmo
{

    private static final double PADDING = 10;
    private static final double ALTO_ARRAY = 100;
    private static final long SECONDS_TO_NANOS = 1000 * 1000 * 100;
    private final GraphicsContext ctx;

    private final Map<String, int[]> arrays = new HashMap<>();

    private
    double getAnchoTotal()
    {
        return ctx.getCanvas()
                  .getWidth();
    }

    public
    InterfazGraficaAlgoritmoImpl(final GraphicsContext context)
    {
        this.ctx = context;
        ctx.setFont(Font.font("Arial", 12));
    }

    private
    Bounds getArrayBounds()
    {
        double anchoArrayEnPantalla = getAnchoTotal() - 2 * PADDING;
        double inicioArrayX = PADDING, inicioArrayY = PADDING;
        return new BoundingBox(inicioArrayX, inicioArrayY, anchoArrayEnPantalla, ALTO_ARRAY);
    }

    private
    Bounds dibujarBordeArray()
    {
        ctx.setFill(Color.WHITE);
        ctx.setTextAlign(TextAlignment.CENTER);
        ctx.setTextBaseline(VPos.CENTER);
        double anchoArrayEnPantalla = getAnchoTotal() - 2 * PADDING;
        double inicioArrayX = PADDING, inicioArrayY = PADDING;
        ctx.fillRect(inicioArrayX, inicioArrayY, anchoArrayEnPantalla, ALTO_ARRAY);
        ctx.setFill(Color.BLACK);
        ctx.strokeRect(inicioArrayX, inicioArrayY, anchoArrayEnPantalla, ALTO_ARRAY);
        return new BoundingBox(inicioArrayX, inicioArrayY, anchoArrayEnPantalla, ALTO_ARRAY);
    }

    @Override
    public
    void dibujarArray(final String nombre,
                      final int[] valores)
    {

        this.arrays.put(nombre, Arrays.copyOf(valores, valores.length));
        Platform.runLater(() ->
                          {

                              Bounds b = this.dibujarBordeArray();

                              ctx.setFill(Color.BLACK);
                              for (int i = 0; i < valores.length; i++)
                              {
                                  dibujarCelda(valores[i], b, valores.length, i, i, 0, Color.WHITE);
                              }
                          });
    }

    @Override
    public
    void dibujarCeldaArray(final String nombre,
                           final int[] datos,
                           final int posicion,
                           final Color color)
    {
        Platform.runLater(() ->
                          {
                              Bounds b = this.getArrayBounds();

                              this.dibujarCelda(datos[posicion], b, datos.length, posicion, posicion, 0, color);
                          });
    }

    private
    void dibujarCelda(final int valor,
                      final Bounds b,
                      int length,
                      final int iStart,
                      final int iEnd,
                      double interpolation,
                      final Color color)
    {
        double anchoCelda = b.getWidth() / length;
        double inicioCeldaX = b.getMinX() + anchoCelda * (iStart + (iEnd - iStart) * interpolation);
        ctx.strokeRect(inicioCeldaX, b.getMinY(), anchoCelda, ALTO_ARRAY);
        if (color != null)
        {
            var previousFill = ctx.getFill();
            ctx.setFill(color);
            ctx.fillRect(inicioCeldaX, b.getMinY(), anchoCelda, ALTO_ARRAY);
            ctx.setFill(previousFill);
        }
        ctx.fillText(valor + "", inicioCeldaX + anchoCelda / 2, b.getMinY() + b.getHeight() / 2);
    }

    public
    void animarArray(final String nombre,
                     final int[] valores,
                     double tiempo)
    {

        if (!arrays.containsKey(nombre))
        {
            this.dibujarArray(nombre, valores);
        }
        else
        {
            final Map<Integer, Integer> movimientos = new HashMap<>();
            int[] valoresPrevios = this.arrays.get(nombre);
            for (int i = 0; i < valoresPrevios.length; i++)
            {
                if (valores[i] != valoresPrevios[i])
                {
                    for (int j = 0; j < valoresPrevios.length; j++)
                    {
                        if (valores[j] == valoresPrevios[i])
                        {
                            movimientos.put(i, j);
                        }
                    }
                }
                else
                {
                    movimientos.put(i, i);
                }
            }

            AnimationTimer t = new AnimationTimer()
            {
                private long started = -1;

                @Override
                public
                void handle(final long now)
                {
                    if (started < 0)
                    {
                        started = now;
                    }
                    Bounds b = dibujarBordeArray();
                    double ellapsedTimeInSeconds = (now - started) * 1. / SECONDS_TO_NANOS;
                    double interpolation = Math.min(ellapsedTimeInSeconds / tiempo, 1);
                    for (int i = 0; i < valores.length; i++)
                    {
                        dibujarCelda(valoresPrevios[i], b, valores.length, i, movimientos.get(i), interpolation, Color.WHITE);
                    }

                    if (ellapsedTimeInSeconds > tiempo)
                    {
                        System.out.println("Stoped");
                        this.stop();
                    }
                }
            };
            t.start();
        }
    }

    private
    class NodoArbolExtendido<T>
    {
        NodoArbolExtendido<T> padre;
        T nodo;
        List<NodoArbolExtendido<T>> hijos;
        int nivel;

        int cantidadNodosSubArbol;

        double startX;
        double startY;
        boolean dibujar = true;

        void extenderInfoArbol(T raiz,
                               Function<T, Collection<T>> getHijos,
                               List<List<NodoArbolExtendido<T>>> nodoPorNivel,
                               final int cantHijos,
                               final int nivelMaximo)
        {
            this.nodo = raiz;

            if (raiz != null)
            {
                this.hijos = getHijos
                    .apply(raiz)
                    .stream()
                    .map(v ->
                         {
                             NodoArbolExtendido<T> h = new NodoArbolExtendido<>();
                             h.padre = this;
                             h.nivel = this.nivel + 1;
                             h.extenderInfoArbol(v, getHijos, nodoPorNivel, cantHijos, nivelMaximo);
                             return h;
                         })
                    .collect(Collectors.toList());
            }
            else
            {
                this.hijos = new ArrayList<>();
            }
            if (nivelMaximo > this.nivel)
            {
                while ((cantHijos > 0 && this.hijos.size() < cantHijos) || this.hijos.isEmpty())
                {
                    NodoArbolExtendido<T> h = new NodoArbolExtendido<>();
                    h.padre = this;
                    h.nivel = this.nivel + 1;
                    h.dibujar = cantHijos < 0;
                    h.extenderInfoArbol(null, getHijos, nodoPorNivel, cantHijos, nivelMaximo);
                    this.hijos.add(h);
                }
            }
            while (nodoPorNivel.size() <= this.nivel)
            {
                nodoPorNivel.add(new ArrayList<>());
            }
            nodoPorNivel.get(this.nivel)
                        .add(this);
            this.cantidadNodosSubArbol = this.hijos.stream()
                                                   .map(v -> v.cantidadNodosSubArbol)
                                                   .reduce(0, Integer::sum) + 1;
        }

        @Override
        public
        String toString()
        {
            return this.nodo.toString();
        }
    }

    @Override
    public
    <T> void dibujarArbol(final String nombre,
                          final T nodoRaiz,
                          final Function<T, Collection<T>> obtenerHijos)
    {
        dibujarArbolNArio(nombre, nodoRaiz, obtenerHijos, -1);
    }

    @Override
    public
    <T> void dibujarArbolNArio(final String nombre,
                               final T nodoRaiz,
                               final Function<T, Collection<T>> obtenerHijos,
                               int nArio)
    {
        final double RADIO_NODO = 20;
        final double ALTO_NIVEL_ARBOL = 70;
        final double ESPACIO_NODOS = 5;

        Platform.runLater(() ->
                          {
                              int nivelMaximo = getNivelMaximo(nodoRaiz, obtenerHijos);

                              NodoArbolExtendido<T> raiz = new NodoArbolExtendido<>();

                              final List<List<NodoArbolExtendido<T>>> nodosPorNivel = new ArrayList<>();
                              raiz.extenderInfoArbol(nodoRaiz, obtenerHijos, nodosPorNivel, nArio, nivelMaximo);

                              NodoArbolExtendido<T> padre = null;

                              ctx.setFill(Color.BLACK);
                              ctx.setTextAlign(TextAlignment.CENTER);
                              ctx.setTextBaseline(VPos.CENTER);

                              for (int nivel = nodosPorNivel.size() - 1; nivel >= 0; nivel--)
                              {
                                  List<NodoArbolExtendido<T>> nodosNivel = nodosPorNivel.get(nivel);

                                  for (int posicionEnNivel = 0; posicionEnNivel < nodosNivel.size(); posicionEnNivel++)
                                  {
                                      NodoArbolExtendido<T> nodo = nodosNivel.get(posicionEnNivel);
                                      double xNodo = posicionEnNivel * (RADIO_NODO + ESPACIO_NODOS);
                                      double yNodo = nivel * ALTO_NIVEL_ARBOL + PADDING;
                                      if (!nodo.hijos.isEmpty())
                                      {
                                          xNodo = nodo.hijos.stream()
                                                            .map(hijo -> hijo.startX)
                                                            .reduce(0., Double::sum) / nodo.hijos.size();
                                          for (final NodoArbolExtendido<T> hijo : nodo.hijos)
                                          {
                                              if (hijo.dibujar)
                                              {
                                                  ctx.strokeLine(xNodo + RADIO_NODO, yNodo + RADIO_NODO * 2, hijo.startX + RADIO_NODO, hijo.startY);
                                              }
                                          }
                                      }

                                      nodo.startX = xNodo;
                                      nodo.startY = yNodo;

                                      if (nodo.nodo != null)
                                      {
                                          ctx.strokeOval(xNodo, yNodo, RADIO_NODO * 2, RADIO_NODO * 2);
                                          ctx.fillText(nodo.toString(), xNodo + RADIO_NODO, yNodo + RADIO_NODO);
                                      }
                                      else if (nodo.dibujar)
                                      {
                                          ctx.setLineDashes(10, 3);
                                          ctx.strokeOval(xNodo, yNodo, RADIO_NODO * 2, RADIO_NODO * 2);
                                          ctx.setLineDashes(1);
                                          ctx.fillText("<>", xNodo + RADIO_NODO, yNodo + RADIO_NODO);
                                      }
                                  }
                              }
                          });
    }

    private
    <T>
    int getNivelMaximo(final T nodoRaiz,
                       final Function<T, Collection<T>> obtenerHijos)
    {
        if (nodoRaiz == null)
        {
            return 0;
        }
        return 1 + obtenerHijos.apply(nodoRaiz)
                               .stream()
                               .map(n -> getNivelMaximo(n, obtenerHijos))
                               .max(Integer::compare)
                               .orElse(0);
    }

    @Override
    public
    void imprimirMatriz(int[][] valores)
    {
        for (int i = 0; i < valores.length; i++)
        {
            for (int j = 0; j < valores[i].length; j++)
            {
                System.out.print(valores[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private
    Bounds dibujarBordeMatriz()
    {
        ctx.setFill(Color.WHITE);
        ctx.setTextAlign(TextAlignment.CENTER);
        ctx.setTextBaseline(VPos.CENTER);
        double anchoArrayEnPantalla = getAnchoTotal() - 2 * PADDING;
        double inicioArrayX = PADDING, inicioArrayY = PADDING;
        double altoTotal = ctx.getCanvas()
                              .getHeight() - 2 * PADDING;

        ctx.fillRect(inicioArrayX, inicioArrayY, anchoArrayEnPantalla, altoTotal);
        ctx.setFill(Color.BLACK);
        ctx.strokeRect(inicioArrayX, inicioArrayY, anchoArrayEnPantalla, altoTotal);

        return new BoundingBox(inicioArrayX, inicioArrayY, anchoArrayEnPantalla, altoTotal);
    }

    @Override
    public
    void dibujarMatrizBinaria(String nombre,
                              int[][] valores,
                              Color color0,
                              Color color1)
    {
        Platform.runLater(() ->
                          {

                              Bounds b = this.dibujarBordeMatriz();

                              ctx.setFill(Color.BLACK);
                              for (int i = 0; i < valores.length; i++)
                              {
                                  for (int j = 0; j < valores[i].length; j++)
                                  {
                                      dibujarCeldaMatriz(b.getHeight() / valores.length,
                                                         b.getWidth() / valores[i].length,
                                                         i,
                                                         j,
                                                         valores[i][j] == 0 ? color0 : color1);
                                  }
                              }
                          });
    }

    private
    Bounds getMatrizBounds()
    {
        double anchoArrayEnPantalla = getAnchoTotal() - 2 * PADDING;
        double inicioArrayX = PADDING, inicioArrayY = PADDING;
        double altoTotal = ctx.getCanvas()
                              .getHeight() - 2 * PADDING;

        return new BoundingBox(inicioArrayX, inicioArrayY, anchoArrayEnPantalla, altoTotal);
    }

    private
    void dibujarCeldaMatriz(double altoCelda,
                            double anchoCelda,
                            int fila,
                            int columna,
                            Color color)
    {
        // TODO Auto-generated method stub
        ctx.setFill(color);
        ctx.fillRect(anchoCelda * columna + PADDING, fila * altoCelda + PADDING, anchoCelda, altoCelda);
    }

    @Override
    public
    void dibujarCeldaDeMatriz(String nombre,
                              int[][] valores,
                              int i,
                              int j,
                              Color color)
    {
        Platform.runLater(() ->
                          {
                              var b = getMatrizBounds();
                              dibujarCeldaMatriz(b.getHeight() / valores.length,
                                                 b.getWidth() / valores[i].length,
                                                 i,
                                                 j,
                                                 color);
                          });
    }

    @Override
    public
    void sleep(int i)
    {
        try
        {
            Thread.sleep(i);
        }
        catch (Exception e)
        {

        }
    }
}
