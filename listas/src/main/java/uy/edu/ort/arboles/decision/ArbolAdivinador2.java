package uy.edu.ort.arboles.decision;

import uy.edu.ort.listas.Lista;
import uy.edu.ort.listas.ListaDobleCircularClase;

import uy.edu.ort.util.AGraphViz;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public
class ArbolAdivinador2
{

    private class NodoArbol{
        Lista<Persona> candidatos=new ListaDobleCircularClase<>();
        private Pregunta p;
        private String respuestaElegida;

        private NodoArbol respuesta1;
        private NodoArbol respuesta2;
        private NodoArbol respuesta3;
        public NodoArbol(){

        }
        public NodoArbol(Lista<Persona> candidatos){
            this.candidatos=candidatos;

        }

        void filtrarPorRespuesta(Lista<Persona> cand,int preguntaIdx,String resp){
            this.respuestaElegida=resp;
            for (var c:cand)
            {
                if(c.getRespuesta(preguntaIdx).equalsIgnoreCase(resp)){
                    this.candidatos.agregarFin(c);
                }

            }
        }
        public
        void split(Pregunta pregunta){

            if(this.respuesta1!=null){
                this.respuesta1.split(pregunta);
                if(this.respuesta2!=null)
                {
                    this.respuesta2.split(pregunta);
                }
                if(this.respuesta3!=null){
                    this.respuesta3.split(pregunta);

                }
            }else if(candidatos.getLargo()>1){
                this.p=pregunta;


                respuesta1=new NodoArbol();
                respuesta1.filtrarPorRespuesta(candidatos,pregunta.idx,p.respuestas.get(0));
                if(p.respuestas.getLargo()==2)
                {
                    respuesta2 = new NodoArbol();
                    respuesta2.filtrarPorRespuesta(candidatos, pregunta.idx, p.respuestas.get(1));
                }
                if(p.respuestas.getLargo()==3){
                    respuesta3=new NodoArbol();
                    respuesta3.filtrarPorRespuesta(candidatos,pregunta.idx,p.respuestas.get(2));
                }

            }
        }

        public
        boolean matchesRespuesta(final String respuesta)
        {
            return respuesta.equalsIgnoreCase(this.respuestaElegida);
        }
    }


    public  Pregunta siguientePregunta(){
        return nodoActualFlujo.p;
    }

    public void responder(String respuesta){
        if(nodoActualFlujo.respuesta1.matchesRespuesta(respuesta)){
            this.nodoActualFlujo=nodoActualFlujo.respuesta1;
        }else if(nodoActualFlujo.respuesta2.matchesRespuesta(respuesta)){
            this.nodoActualFlujo=nodoActualFlujo.respuesta2;
        }else if(nodoActualFlujo.respuesta3!=null &&nodoActualFlujo.respuesta3.matchesRespuesta(respuesta) ){
            this.nodoActualFlujo=nodoActualFlujo.respuesta3;
        }

    }

    public
    Lista<Persona> candidatosHastaAhora(){
        return nodoActualFlujo.candidatos;
    }
    NodoArbol raiz=new NodoArbol();
    NodoArbol nodoActualFlujo=new NodoArbol();
    public
    void construirArbol(final Respuestas respuestas)
    {
        int preguntaIdx=0;

        raiz=new NodoArbol();
        raiz.candidatos=respuestas.personas;
        for (int i=0;i<respuestas.preguntas.length;i++){
            var pregunta=respuestas.preguntas[i];
            raiz.split(pregunta);
        }
        AGraphViz.imprimirArbol(this.raiz,
                                n->String.format("%s/%s",n.respuestaElegida!=null?n.respuestaElegida:"",(n.p!=null?n.p.pregunta:"")),
                                n-> Stream.of(n.respuesta1, n.respuesta2,n.respuesta3).collect(Collectors.toList()),5);
    }



    public
    void iniciarFlujo()
    {
        nodoActualFlujo=raiz;

    }
}
