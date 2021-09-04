package uy.edu.ort.ae2.io;

import uy.edu.ort.ae2.huffman.ArbolHuffman;
import uy.edu.ort.ae2.practico.Paso3CodificarElTexto;
import uy.edu.ort.ae2.util.LectorLineas;

import java.io.IOException;
import java.io.OutputStream;

public
class EscritorComprimido
    implements LectorLineas
{

    private final ArbolHuffman huffman;
    private final Paso3CodificarElTexto codificarElTexto;
    private final OutputStream stream;
    private final StringBuilder sb = new StringBuilder();

    private byte[] buffer = new byte[10000];

    public
    EscritorComprimido(final ArbolHuffman huffman,
                       final Paso3CodificarElTexto codificarElTexto,
                       final OutputStream stream)
    {
        this.huffman = huffman;
        this.codificarElTexto = codificarElTexto;
        this.stream = stream;
    }

    public
    void escribirComprimido(String texto)
        throws
        IOException
    {

        String textoBinario = codificarElTexto.textoCodificado(huffman, texto);
        this.sb.append(textoBinario);
        final int BYTE_LENGTH = 8;

        int siguienteElementoBuffer = 0;

        while (this.sb.length() >= BYTE_LENGTH)
        {
            buffer[siguienteElementoBuffer] =
                Integer.valueOf(Integer.parseInt(sb.substring(0, BYTE_LENGTH), 2))
                       .byteValue();
            siguienteElementoBuffer++;

            sb.delete(0, BYTE_LENGTH);
        }
        stream.write(buffer, 0, siguienteElementoBuffer);
    }

    @Override
    public
    void leerLinea(final String linea)
        throws
        Exception
    {
        escribirComprimido(linea);
    }
}
