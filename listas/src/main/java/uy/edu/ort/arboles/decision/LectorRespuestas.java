package uy.edu.ort.arboles.decision;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public
class LectorRespuestas
{


    public Respuestas leerArchivo(InputStream is){

        try(BufferedReader reader=new BufferedReader(new InputStreamReader(is))){
            String linea=reader.readLine();
            String[] cabezal=linea.split(",");
            int nombreIdx=1;
            int preguntasCount=cabezal.length-2;//nombre y timestamp
            Respuestas resultado=new Respuestas();
            Pregunta[] preguntas=new Pregunta[preguntasCount];
            while ((linea=reader.readLine())!=null){
                String[] respuestas=linea.split(",");
                Persona p=new Persona();
                p.nombre=respuestas[nombreIdx];
                for(int i=0;i<preguntasCount;i++){
                    Respuesta r=new Respuesta();
                    r.pregunta=cabezal[i+2];
                    r.respuesta=respuestas[i+2];
                    r.preguntaIdx=i;
                    if(preguntas[i]==null){
                        preguntas[i]=new Pregunta();
                        preguntas[i].idx=i;
                    }

                    preguntas[i].pregunta=r.pregunta;
                    boolean existeRespuesta=false;
                    for(var respuestaRegistrada : preguntas[i].respuestas){
                        existeRespuesta=existeRespuesta|| respuestaRegistrada.equalsIgnoreCase(r.respuesta);
                    }
                    if(!existeRespuesta){
                        preguntas[i].respuestas.agregarFin(r.respuesta);
                    }


                    p.respuestas.agregarFin(r);
                }
                resultado.personas.agregarFin(p);
            }
            resultado.preguntas=preguntas;

            return resultado;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
