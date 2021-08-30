package uy.edu.ort.colas.mensaje;

public
class Paso2SinColasPeor
{


    public static void main(String [] args) throws Exception{
        //Noten que es peor si tengo 5 liebres
        //Y ade+ no termina antes aun cuando la cantidad de manzanas es menor

        Thread liebre=new Thread(new LiebreSinColaMensajes("1",10));
        liebre.start();
        liebre=new Thread(new LiebreSinColaMensajes("2",10));
        liebre.start();
        liebre=new Thread(new LiebreSinColaMensajes("3",10));
        liebre.start();
        liebre=new Thread(new LiebreSinColaMensajes("4",10));
        liebre.start();

        liebre=new Thread(new LiebreSinColaMensajes("5",10));
        liebre.start();
        


    }



    private static class LiebreSinColaMensajes implements Runnable{


        private final int cantidadARecoger;
        private final String nombre;

        public
        LiebreSinColaMensajes(String nombre,int cant)
        {
            this.nombre=nombre;
            this.cantidadARecoger=cant;
        }

        @Override
        public
        void run()
        {
            int i=0;
            long inicio=System.currentTimeMillis();
            while (i<cantidadARecoger){

                //Esto representa un proceso que funciona muy rapido, produce datos cada 100ms
                TortugaSinColaMensajes.getInstance().datoNuevo();
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
