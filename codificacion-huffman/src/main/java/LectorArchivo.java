import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public
class LectorArchivo
{

    public void leerResource(String path,
                             Consumer<String> onLinea){
        leerArchivo(getClass().getResourceAsStream(path),onLinea);
    }

    public void leerArchivo(InputStream is,
                            Consumer<String> onLinea){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is)))
        {

            String linea = null;
            while ((linea = reader.readLine()) != null)
            {
                onLinea.accept(linea);//Leemos el archivo linea a linea
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
