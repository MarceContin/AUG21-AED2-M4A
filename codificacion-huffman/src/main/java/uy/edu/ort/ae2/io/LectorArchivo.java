package uy.edu.ort.ae2.io;

import uy.edu.ort.ae2.util.LectorLineas;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public
class LectorArchivo
{

    public
    void leerResource(String path,
                      LectorLineas onLinea)
    {
        leerArchivo(getClass().getResourceAsStream(path), onLinea);
    }

    public
    void leerArchivo(InputStream is,
                     LectorLineas onLinea)
    {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is)))
        {

            String linea = null;
            while ((linea = reader.readLine()) != null)
            {
                onLinea.leerLinea(linea + "\n");//Leemos el archivo linea a linea
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
