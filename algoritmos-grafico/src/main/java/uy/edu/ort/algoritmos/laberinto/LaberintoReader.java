package uy.edu.ort.algoritmos.laberinto;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public
class LaberintoReader
{

	public static
	int[][] leerLaberinto(InputStream is)
		throws
		IOException
	{

		BufferedImage image = ImageIO.read(is);

		int tamanioCelda = 1;
		int filas = image.getHeight() / tamanioCelda;
		int columnas = image.getWidth() / tamanioCelda;

		int[][] resultado = new int[filas][columnas];
		for (int fila = 0; fila < filas; fila++)
		{
			for (int col = 0; col < columnas; col++)
			{

				resultado[fila][col] = image.getRGB(col * tamanioCelda, fila * tamanioCelda) == -1 ? 0 : 1;
			}
		}

		return resultado;
	}
}
