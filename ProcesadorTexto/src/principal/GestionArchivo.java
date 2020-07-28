package principal;

import java.io.*;

import javax.swing.JOptionPane;


public class GestionArchivo {
	
	
	FileInputStream entrada;
	FileOutputStream salida;
	File archivo;
	
	public GestionArchivo() {
		
	}
	
	public String abrirArchivoTexto(File archivo) {
		
		String contenido=null;
				
		try {
			
			entrada = new FileInputStream(archivo);
			
			int ascii = entrada.read();
			
			while(ascii !=-1) {
				
				char caracter = (char)ascii;
				
				contenido+=caracter;
				
				ascii = entrada.read();
														
			}
			
			entrada.close();
					
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
			
		}
		
		return contenido;
	}
	
	
	public String guardarArchivoTexto(File archivo, String contenido) {
		
		String respuesta =null;
		
		try {
			
			salida = new FileOutputStream(archivo);
			
			byte[] bytesTxt = contenido.getBytes();
			
			salida.write(bytesTxt);
			
			respuesta = "Se guardo con exito el archivo";
			
			
		}catch(Exception e) {
			
		}
		
		return respuesta;
	}
	
	public byte[] abrirImagen(File archivo) {
		
		byte[] bytesImg = new byte[1024*100];
		
		try {
			
			entrada = new FileInputStream(archivo);
			entrada.read(bytesImg);
			
		}catch(Exception e) {
			
		}
		
		return bytesImg;
	}
	
	
	public String guardarArchivoImagen(File archivo, byte[] bytesImg) {
		
		String respuesta=null;
		
		
		try {
			
			salida = new FileOutputStream(archivo);
			
			salida.write(bytesImg);
			
			respuesta = "La imagen se guardo con exito";
		}
		
		catch(Exception e) {}
		
		
		return respuesta;
	}
	

}
