package principal;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Marco extends JFrame {

	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private int inicioX = pantalla.width / 4;
	private int inicioY = pantalla.height / 4;
	private int largoVentana = pantalla.width / 2;
	private int altoVentana = pantalla.height / 2;

	public Marco() {

		setBounds(inicioX, inicioY, largoVentana, altoVentana);

		add(new Lamina());

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
