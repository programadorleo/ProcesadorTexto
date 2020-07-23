
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;

public class Lamina extends JPanel {

	private JScrollPane scrollTexto;
	private JTextArea areaTexto;
	private JComboBox tipoLetra;
	private JPanel laminaTexto, laminaBotones, laminaBarra;

	public Lamina() {

		laminaTexto = new LaminaTexto();
		laminaBotones = new LaminaBotones();
		laminaBarra = new LaminaBarra();

		setLayout(new BorderLayout());

		add(laminaTexto, BorderLayout.CENTER);
		add(laminaBotones, BorderLayout.WEST);
		add(laminaBarra, BorderLayout.NORTH);

	}

	private class LaminaTexto extends JPanel {

		private int largoAreaTexto = 25;
		private int altoAreaTexto = 45;

		public LaminaTexto() {

			areaTexto = new JTextArea("", largoAreaTexto, altoAreaTexto);
			areaTexto.setLineWrap(true);
			scrollTexto = new JScrollPane(areaTexto);
			add(scrollTexto);

		}
	}

	private class LaminaBotones extends JPanel {

		public LaminaBotones() {

			String Fuentes_de_mi_pc[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			tipoLetra = new JComboBox(Fuentes_de_mi_pc);
			tipoLetra.setSize(new Dimension(10, 10));

			tipoLetra.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					String Tex_ = (String) tipoLetra.getSelectedItem();
					areaTexto.setFont(new Font(Tex_, 10, 20));
				}
			});

			add(tipoLetra);

		}

	}

	
	private class LaminaBarra extends JPanel {

		private JMenuBar BARRA;
		private JMenu Inicio, tIPO_LETRA, TAMAÑO_LETRA, VISTA, COP_PEG;
		private JMenuItem COPIAR, PEGAR, GUARDAR, GUARDAR_COMO, NEGRITA, CURSIVA, SIN_FORMATO, POR2, POR10, POR100,
				CIEN, DOSCIENTOS, TRESCIENTOS, CINCUENTA;

		public LaminaBarra() {

			laminaBarra = new JPanel();

			laminaBarra.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));
			BARRA = new JMenuBar();
			Inicio = new JMenu("INICIO");
			COP_PEG = new JMenu("COP & PEG");
			tIPO_LETRA = new JMenu("TIPO LETRA");
			TAMAÑO_LETRA = new JMenu("TAMAÑO LETRA");
			VISTA = new JMenu("VISTA");
			COPIAR = new JMenuItem("COPIAR");
			PEGAR = new JMenuItem("PEGAR");
			GUARDAR = new JMenuItem("GUARDAR");
			GUARDAR_COMO = new JMenuItem("GUARDAR COMO");
			NEGRITA = new JMenuItem("NEGRITA");
			CURSIVA = new JMenuItem("CURSIVA");
			SIN_FORMATO = new JMenuItem("SIN FORMATO");
			POR2 = new JMenuItem("X 2");
			POR10 = new JMenuItem("X 10");
			POR100 = new JMenuItem("X 100");
			CINCUENTA = new JMenuItem("50 %");
			CIEN = new JMenuItem("100 %");
			DOSCIENTOS = new JMenuItem("200 %");
			TRESCIENTOS = new JMenuItem("300 %");

			BARRA.add(Inicio);
			Inicio.add(GUARDAR);
			Inicio.add(GUARDAR_COMO);
			Inicio.addSeparator();
			Inicio.add(COP_PEG);
			COP_PEG.add(COPIAR);
			COP_PEG.add(PEGAR);
			BARRA.add(tIPO_LETRA);

			NEGRITA.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String Tex_ = (String) tipoLetra.getSelectedItem();
					areaTexto.setFont(new Font(Tex_, Font.BOLD, 20));
				}
			});

			CURSIVA.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String Tex_ = (String) tipoLetra.getSelectedItem();
					areaTexto.setFont(new Font(Tex_, Font.ITALIC, 20));
				}
			});

			SIN_FORMATO.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String Tex_ = (String) tipoLetra.getSelectedItem();
					areaTexto.setFont(new Font(Tex_, Font.PLAIN, 20));
				}
			});

			tIPO_LETRA.add(SIN_FORMATO);
			tIPO_LETRA.add(NEGRITA);
			tIPO_LETRA.add(CURSIVA);
			BARRA.add(TAMAÑO_LETRA);
			TAMAÑO_LETRA.add(POR2);
			TAMAÑO_LETRA.add(POR10);
			TAMAÑO_LETRA.add(POR100);

			BARRA.add(VISTA);
			VISTA.add(CINCUENTA);
			VISTA.add(CIEN);
			VISTA.add(DOSCIENTOS);
			VISTA.add(TRESCIENTOS);
			laminaBarra.add(BARRA);

			add(laminaBarra);
		}

	}

}
