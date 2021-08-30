package uy.edu.ort.colas.mensaje;

public
class TortugaSinColaMensajes
{


        private static TortugaSinColaMensajes instancia;
        public  static synchronized   TortugaSinColaMensajes getInstance(){
            if(instancia==null)instancia=new TortugaSinColaMensajes();
            return instancia;
        }

        //Esto representa un proceso muy lento, que lleva mucho trabajo.
    //La tortuga recibe la manzana de la liebre y la gurada
        //En particular dura dos segundos en procesar
        public synchronized
        void datoNuevo(){
            System.out.print("Manzana recibida-->");
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("Manzana guardada");
        }

}
