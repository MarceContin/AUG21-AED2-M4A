package uy.edu.ort.colas.mensaje;

import uy.edu.ort.listas.Cola;
import uy.edu.ort.listas.ColaListaEnlazada;

import java.util.List;

public
class Paso3ConColas
{
    public static void main(String [] args){
        Cola<String> manzanas=new ColaListaEnlazada<>();
        //Naturalmente el ejemplo va a tardar lo mismo en correr que el paso dos
        // Pero las liebres se liberan mucho mas rápido dando no solo la ilusión de que el sistema es más rapido
        //Sino que es mas eficiente a lo que no tenemos los hilos abiertos para cada una de las liebres

        Thread liebre=new Thread(new LiebreColaMensajes("1", 10,manzanas));
        liebre.start();
        liebre=new Thread(new LiebreColaMensajes("2", 10,manzanas));
        liebre.start();
        liebre=new Thread(new LiebreColaMensajes("3", 10,manzanas));
        liebre.start();
        liebre=new Thread(new LiebreColaMensajes("4", 10,manzanas));
        liebre.start();
        liebre=new Thread(new LiebreColaMensajes("5", 10,manzanas));
        liebre.start();

        Thread t=new Thread(new TortugaColaMensajes(manzanas));
        t.start();




    }
    private static class TortugaColaMensajes implements Runnable{

        private final Cola<String> manzanas;

        public
        TortugaColaMensajes(final Cola<String> manzanas)
        {
            this.manzanas = manzanas;
        }

        @Override
        public
        void run()
        {
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("Empezando a organizar manzanas");
            while (!manzanas.esVacia()){
                System.out.printf("Tortuga procesando manzana %s \n", manzanas.pop());
                //ElProceso lento
                try
                {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("Tortuga: Proceso manzana");
            }
            System.out.println("Termine con las manzanas");

        }
    }

    private static class LiebreColaMensajes implements Runnable{


        private final int cantidadARecoger;
        private final String nombre;
        private final Cola<String> manzanas;


        public
        LiebreColaMensajes(String nombre,int cant,Cola<String> manzanas)
        {
            this.nombre=nombre;
            this.cantidadARecoger=cant;
            this.manzanas=manzanas;
        }

        @Override
        public
        void run()
        {
            int i=0;
            long inicio=System.currentTimeMillis();
            while (i<cantidadARecoger){

                this.manzanas.push(nombre+"--"+i);
                i++;
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println("Liebre "+nombre+":Ya traje todas las manzanas y me llevo : "+(System.currentTimeMillis()-inicio)/1000f);
        }
    }
}
