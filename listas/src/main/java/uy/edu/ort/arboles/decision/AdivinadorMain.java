package uy.edu.ort.arboles.decision;

import uy.edu.ort.util.AGraphViz;

import java.io.InputStream;
import java.util.Scanner;

public
class AdivinadorMain
{

    public static void main(String[] args){

        AdivinadorMain adivinador=new AdivinadorMain();
        adivinador.adivinar("/respuestas.csv");

    }
    public void adivinar(String  archivo){
        LectorRespuestas lec=new LectorRespuestas();
       Respuestas respuestas= lec.leerArchivo(getClass().getResourceAsStream(archivo));

        ArbolAdivinador adivinador=new ArbolAdivinador();
        adivinador.construirArbol(respuestas);
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.print("Empezamos s para salir, cualquier otra cosa para comenzar");
            String entrada=scanner.nextLine();
            if("s".equalsIgnoreCase(entrada)){
                break;
            }

            adivinador.iniciarFlujo();
            while (adivinador.siguientePregunta()!=null && adivinador.candidatosHastaAhora().getLargo()>1){

                System.out.println(adivinador.siguientePregunta().pregunta+"? ("+adivinador.candidatosHastaAhora().getLargo()+")");
                for(int i=0;i<adivinador.siguientePregunta().respuestas.getLargo();i++){

                    System.out.printf("%s.%s", i, adivinador.siguientePregunta().respuestas.get(i));
                    System.out.println();
                }

                int opcion=-1;
                do
                {
                    try
                    {
                        opcion = Integer.parseInt(scanner.nextLine());
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error leyendo respuesta");
                    }
                }while (opcion<0 || opcion>=adivinador.siguientePregunta().respuestas.getLargo());
                adivinador.responder(adivinador.siguientePregunta().respuestas.get(opcion));

            }
            System.out.print("La persona/s es:");
            for(Persona p:adivinador.candidatosHastaAhora()){
                System.out.print(p.nombre+",");
            }
            System.out.println();


        }

        //Construimos el arbol


    }
}
