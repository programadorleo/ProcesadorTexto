package principal;

import java.io.*;

import javax.swing.JOptionPane;

public class GestionArchivo {

	private FileInputStream entrada;
	private FileOutputStream salida;
	private File archivo;

	public GestionArchivo() {
	
	}

	public String abrirArchivoTexto() {

		String contenido="";

		try {

			entrada = new FileInputStream(archivo);

			int ascii = entrada.read();

			while (ascii != -1) {

				char caracter = (char) ascii;

				contenido += caracter;

				ascii = entrada.read();

			}

			entrada.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");

		}

		return contenido;
	}

	


	public String guardarArchivoTexto(String contenido) {

		String respuesta = null;

		try {

			salida = new FileOutputStream(archivo);

			byte[] bytesTxt = contenido.getBytes();

			salida.write(bytesTxt);

			respuesta = "Se guardo con exito el archivo";

		} catch (Exception e) {

		}

		return respuesta;
	}
	
	
	
	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

}
