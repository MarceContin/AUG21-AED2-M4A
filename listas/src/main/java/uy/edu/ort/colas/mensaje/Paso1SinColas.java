package uy.edu.ort.colas.mensaje;

public
class Paso1SinColas
{


    public static void main(String [] args) throws Exception{
        //En este ejemplo vemos ocmo la liebre queda bloqueada esperando por las manzanas
        // Esto por ejemplo podria ser un usuario esperando que una wwweb le responda
        //Vemos a la liebre en total le lleva un minuto hacer su trabajo

        Thread liebre=new Thread(new LiebreSinColaMensajes(30));
        liebre.start();




    }



    private static class LiebreSinColaMensajes implements Runnable{


        private final int cantidadARecoger;

        public
        LiebreSinColaMensajes(int cant)
        {this.cantidadARecoger=cant;
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
            System.out.println("Ya traje todas las manzanas y me llevo : "+(System.currentTimeMillis()-inicio)/1000f);
        }
    }


}
