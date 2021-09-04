package uy.edu.ort.ae2.practico;

import uy.edu.ort.ae2.huffman.ArbolHuffman;
import uy.edu.ort.ae2.huffman.IteradorHuffman;
import uy.edu.ort.ae2.io.EscritorComprimido;
import uy.edu.ort.ae2.io.LectorArchivo;
import uy.edu.ort.ae2.util.AGraphViz;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public
class Main
{

    //Para pasar de byttes a kilobytes es 1/1024, para pasar de kb a mb es 1 /1024
    //Entonces para pasar de bytes a mb, es 1/(1024*1024)
    private static final float BYTES_TO_MB = 1f / (1024 * 1024);

    public static
    void main(String[] args)
        throws
        URISyntaxException,
        IOException
    {
        //Instanciamos las variables.
        Paso1ContadorDeFrecuencia contarFrecuencias = new Paso1ContadorDeFrecuencia();
        Paso2ConstruccionDelArbol construccionDelArbol = new Paso2ConstruccionDelArbol();
        Paso3CodificarElTexto codificarElTexto = new Paso3CodificarElTexto();

        LectorArchivo lectorArchivo = new LectorArchivo();
        //Actualizamos las frecuencias de los caracteres.
        lectorArchivo.leerResource("/shakespeare.txt", contarFrecuencias);

        //Construimos el arbol de huffman desde abajo a partir de las frecuencias
        ArbolHuffman arbolHuffman = construccionDelArbol.construirArbolDesdeAbajo(contarFrecuencias.getFrecuenciasDeCaracter());

        //Imprimimos algunas metricas
        System.out.println("El codigo del caracter mas largo es la altura (A implementar):" + arbolHuffman.altura());
        System.out.println("La cantidad de caracteres del arbol es la cantidad de hojas:" + arbolHuffman.cantidadDeHojas());
       /* Si queres imprimir el arbol descomentar el siguiente codigo

        AGraphViz.imprimirArbol(arbolHuffman.getRaiz(),
                                v->v.esHoja()?
                                    v.getCaracterConFrecuencia().getCaracter()+"-"+v.getCaracterConFrecuencia().getFrecuencia():
                                    v.getFrecuencia()+"",
                                v-> Stream.of(v.getIzq(),v.getDer()).collect(Collectors.toList()),
                                20);

        */
        // Hacemos una pequeña prueba chequeando que el inicio se descomprime bien.
        String original = "This is the 100th Etext file presented by Project Gutenberg";
        String comprimido = codificarElTexto.textoCodificado(arbolHuffman, original);
        System.out.printf("El texto simple comprimido es la cadena de bits: %s. En total lo comprimimos un %2f \n",
                          comprimido,
                          comprimido.length() * 100. / (8 * original.length()));

        StringBuilder descomp = new StringBuilder();
        //Descomprimir un texto, es basicamente recorrer el arbol yendo a la izquierda o derecha de acuerdo al bit que tengamos
        IteradorHuffman hit = arbolHuffman.iterador();
        for (int i = 0; i < comprimido.length(); i++)
        {
            hit.siguiente(comprimido.charAt(i));
            if (hit.resolviCaracter())
            {
                descomp.append(hit.getCaracter());
                hit.reiniciar();
            }
        }
        //La idea es que sea igual que el original
        System.out.println("El texto descomprimido es: " + descomp.toString());

        //Ahora vamos a probarlo de con el archivo del código
        //Obtenemos el largo del archivo
        long largoArchivoOriginal = Files.size(Path.of(Main.class.getResource("/shakespeare.txt")
                                                                 .toURI()));

        //Creamos una archivo comprimido
        File archivoComprimido = File.createTempFile("shakespeare_comprimido", ".bin");
        System.out.println("El archivo comprimido se guardara en: ");
        System.out.println(archivoComprimido.toPath()
                                            .toAbsolutePath()
                                            .toString());

        comprimirArchivo(codificarElTexto, lectorArchivo, arbolHuffman, largoArchivoOriginal, archivoComprimido);

        File archivoDesComprimido = File.createTempFile("shakespeare_descomprimido", ".txt");

        //Descomprimimos el archivo
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(archivoComprimido)))
        {

            StringBuilder bits = leerBitsDesdeElArchivo(inputStream);

            IteradorHuffman iteradorHuffman = arbolHuffman.iterador();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoDesComprimido)))
            {
                for (int i = 0; i < bits.length(); i++)
                {

                    iteradorHuffman.siguiente(bits.charAt(i));
                    if (iteradorHuffman.resolviCaracter())
                    {
                        writer.write("" + iteradorHuffman.getCaracter());
                        iteradorHuffman.reiniciar();
                    }
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("TERMINO EL PROGRAMA DE DESCOMPRIMIR");
        System.out.println("El archivo descomprimido se guardó en: ");
        System.out.println(archivoDesComprimido.toPath()
                                               .toAbsolutePath()
                                               .toString());
    }

    private static
    StringBuilder leerBitsDesdeElArchivo(final BufferedInputStream inputStream)
        throws
        IOException
    {
        byte[] allBytes = inputStream.readAllBytes();
        StringBuilder bits = new StringBuilder(allBytes.length * 8);
        for (int i = 0; i < allBytes.length; i++)
        {
            String representacionBits = String.format("%8s", Integer.toBinaryString(allBytes[i]))
                                              .replace(" ", "0");
            bits.append(representacionBits.substring(representacionBits.length() - 8, representacionBits.length()));
        }
        return bits;
    }

    private static
    void comprimirArchivo(final Paso3CodificarElTexto codificarElTexto,
                          final LectorArchivo lectorArchivo,
                          final ArbolHuffman arbolHuffman,
                          final long largoArchivoOriginal,
                          final File archivoComprimido)
    {
        System.out.println("COMPRIMIENDO");
        try (BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(archivoComprimido)))
        {
            EscritorComprimido escritorComprimido = new EscritorComprimido(arbolHuffman, codificarElTexto, bw);
            lectorArchivo.leerResource("/shakespeare.txt", escritorComprimido);

            long largoArchivoNuevo = Files.size(archivoComprimido.toPath());
            System.out.printf("Termino la compresion el largo original es de %2f, largo del nuevo es de %2f (%2f).",
                              largoArchivoOriginal * BYTES_TO_MB,
                              largoArchivoNuevo * BYTES_TO_MB,
                              largoArchivoNuevo * 100.0 / largoArchivoOriginal
                             );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
