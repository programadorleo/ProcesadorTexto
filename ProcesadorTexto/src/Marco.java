import javax.swing.JFrame;


public class Marco extends JFrame{
	
	private int inicioX = 400;
	private int inicioY = 100;
	private int largoVentana = 800;
	private int altoVentana = 500;
	
	public Marco() {
		
		  setBounds(inicioX,inicioY,largoVentana,altoVentana);
		  add(new Lamina());
		  
		 }

}
