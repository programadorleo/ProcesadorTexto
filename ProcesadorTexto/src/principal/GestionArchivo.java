package principal;
import java.io.*;


public class GestionArchivo {
	
	
	FileInputStream entrada;
	FileOutputStream salida;
	File archivo;
	
	public GestionArchivo() {
		
	}
	
	public String abrirArchivoTexto(File archivo) {
		
		String contenido="";
		
		try {
			
			entrada = new FileInputStream(archivo);
			int ascii;
			while((ascii = entrada.read())!=-1) {
				
				char caracter = (char)ascii;
				contenido+=caracter;
								
			}
					
			
		}catch(Exception e) {
			
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
