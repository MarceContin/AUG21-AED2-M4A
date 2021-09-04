package uy.edu.ort.ae2.practico;

import uy.edu.ort.ae2.huffman.ArbolHuffman;

public
class Paso3CodificarElTexto
{
    //Devuelve un string que representa un binario.
    public
    String textoCodificado(ArbolHuffman arbol,
                           String texto)
    {

        StringBuilder textoCodificado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++)
        {
            textoCodificado.append(arbol.codificarCaracter(texto.charAt(i)));
        }

        return textoCodificado.toString();
    }
}
