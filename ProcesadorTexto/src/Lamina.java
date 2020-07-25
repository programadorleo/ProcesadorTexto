
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;
import java.awt.Font;

public class Lamina extends JPanel {

	private JScrollPane scrollTexto;
	private JTextArea areaTexto;
	private JPanel laminaTexto, laminaBarra;

	public Lamina() {

		laminaTexto = new LaminaTexto();
		laminaBarra = new LaminaBarra();

		setLayout(new BorderLayout());

		add(laminaTexto, BorderLayout.CENTER);
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

	private class LaminaBarra extends JPanel {

		private JMenuBar Barra;
		private JMenu Archivo, Edicion, Fuente, Ver, Ayuda;
		private JMenuItem archivoNuevo, archivoAbrir, archivoGuardar, archivoGuardarComo, archivoConfigurarPagina,
				archivoImprimir, salir, edicionDeshacer, edicionCortar, edicionCopiar, edicionPegar, edicionEliminar,
				edicionBuscar, edicionBuscarSiguiente, edicionReemplazar, edicionIrA, edicionSeleccionarTodo,
				HoraFecha,
				fuenteLetra, fuenteEstiloLetra,fuenteTamano;

		public LaminaBarra() {

			laminaBarra = new JPanel();

			laminaBarra.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));

			Barra = new JMenuBar();

			Archivo = new JMenu("Archivo");
			Edicion = new JMenu("Edición");
			Fuente = new JMenu("Fuente");
			Ver = new JMenu("Ver");
			Ayuda = new JMenu("Ayuda");

			archivoNuevo = new JMenuItem("Nuevo");
			archivoAbrir = new JMenuItem("Abrir");
			archivoGuardar = new JMenuItem("Guardar");
			archivoGuardarComo = new JMenuItem("Guardar Como");
			archivoConfigurarPagina = new JMenuItem("Configurar Pagina");
			archivoImprimir = new JMenuItem("Imprimir");
			salir = new JMenuItem("Salir");

			edicionDeshacer = new JMenuItem("Deshacer");
			edicionCortar = new JMenuItem("Cortar");
			edicionCopiar = new JMenuItem("Copiar");
			edicionPegar = new JMenuItem("Pegar");
			edicionEliminar = new JMenuItem("Eliminar");
			edicionBuscar = new JMenuItem("Buscar");
			edicionBuscarSiguiente = new JMenuItem("Buscar Siguiente");
			edicionReemplazar = new JMenuItem("Reemplazar");
			edicionIrA = new JMenuItem("Ir a");
			edicionSeleccionarTodo = new JMenuItem("Seleccionar Todo");
			HoraFecha = new JMenuItem("Hora y Fehca");
			
			fuenteLetra = new JMenuItem("Letra");
			fuenteEstiloLetra= new JMenuItem("Estilo de Letra");
			fuenteTamano = new JMenuItem("Tamaño");

			Archivo.add(archivoNuevo);
			Archivo.add(archivoAbrir);
			Archivo.add(archivoGuardar);
			Archivo.add(archivoGuardarComo);
			Archivo.add(archivoConfigurarPagina);
			Archivo.add(archivoImprimir);
			Archivo.add(salir);

			Edicion.add(edicionDeshacer);
			Edicion.add(edicionCortar);
			Edicion.add(edicionCopiar);
			Edicion.add(edicionPegar);
			Edicion.add(edicionEliminar);
			Edicion.add(edicionEliminar);
			Edicion.add(edicionBuscar);
			Edicion.add(edicionBuscarSiguiente);
			Edicion.add(edicionReemplazar);
			Edicion.add(edicionIrA);
			Edicion.add(edicionSeleccionarTodo);
			Edicion.add(HoraFecha);
					
			Fuente.add(fuenteLetra);
			Fuente.add(fuenteEstiloLetra);
			Fuente.add(fuenteTamano);
			
			
			
			salir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					System.exit(0);
				}

			});

			HoraFecha.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {

					Date objDate = new Date();

					String texto = areaTexto.getText();

					texto += objDate.toString();

					areaTexto.setText(texto);

				}
			});

			Barra.add(Archivo);
			Barra.add(Edicion);
			Barra.add(Fuente);
			Barra.add(Ver);
			Barra.add(Ayuda);

			add(Barra);
		}

	}
}

/*
 * Inicio = new JMenu("INICIO"); COP_PEG = new JMenu("COP & PEG"); tIPO_LETRA =
 * new JMenu("TIPO LETRA"); TAMAÑO_LETRA = new JMenu("TAMAÑO LETRA"); VISTA =
 * new JMenu("VISTA"); COPIAR = new JMenuItem("COPIAR"); PEGAR = new
 * JMenuItem("PEGAR"); GUARDAR = new JMenuItem("GUARDAR"); GUARDAR_COMO = new
 * JMenuItem("GUARDAR COMO"); NEGRITA = new JMenuItem("NEGRITA"); CURSIVA = new
 * JMenuItem("CURSIVA"); SIN_FORMATO = new JMenuItem("SIN FORMATO"); POR2 = new
 * JMenuItem("X 2"); POR10 = new JMenuItem("X 10"); POR100 = new
 * JMenuItem("X 100"); CINCUENTA = new JMenuItem("50 %"); CIEN = new
 * JMenuItem("100 %"); DOSCIENTOS = new JMenuItem("200 %"); TRESCIENTOS = new
 * JMenuItem("300 %");
 * 
 * BARRA.add(Inicio); Inicio.add(GUARDAR); Inicio.add(GUARDAR_COMO);
 * Inicio.addSeparator(); Inicio.add(COP_PEG); COP_PEG.add(COPIAR);
 * COP_PEG.add(PEGAR); BARRA.add(tIPO_LETRA);
 *//*
	 * NEGRITA.addActionListener(new ActionListener() {
	 * 
	 * public void actionPerformed(ActionEvent e) { String Tex_ = (String)
	 * tipoLetra.getSelectedItem(); areaTexto.setFont(new Font(Tex_, Font.BOLD,
	 * 20)); } });
	 * 
	 * CURSIVA.addActionListener(new ActionListener() {
	 * 
	 * public void actionPerformed(ActionEvent e) { String Tex_ = (String)
	 * tipoLetra.getSelectedItem(); areaTexto.setFont(new Font(Tex_, Font.ITALIC,
	 * 20)); } });
	 * 
	 * SIN_FORMATO.addActionListener(new ActionListener() {
	 * 
	 * public void actionPerformed(ActionEvent e) { String Tex_ = (String)
	 * tipoLetra.getSelectedItem(); areaTexto.setFont(new Font(Tex_, Font.PLAIN,
	 * 20)); } });
	 * 
	 * tIPO_LETRA.add(SIN_FORMATO); tIPO_LETRA.add(NEGRITA);
	 * tIPO_LETRA.add(CURSIVA); BARRA.add(TAMAÑO_LETRA); TAMAÑO_LETRA.add(POR2);
	 * TAMAÑO_LETRA.add(POR10); TAMAÑO_LETRA.add(POR100);
	 * 
	 * BARRA.add(VISTA); VISTA.add(CINCUENTA); VISTA.add(CIEN);
	 * VISTA.add(DOSCIENTOS); VISTA.add(TRESCIENTOS); laminaBarra.add(BARRA);
	 * 
	 * add(laminaBarra);
	 */

/*
 * private class LaminaBotones extends JPanel {
 * 
 * public LaminaBotones() {
 * 
 * String Fuentes_de_mi_pc[] =
 * GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames
 * (); tipoLetra = new JComboBox(Fuentes_de_mi_pc); tipoLetra.setSize(new
 * Dimension(10, 10));
 * 
 * tipoLetra.addActionListener(new ActionListener() {
 * 
 * public void actionPerformed(ActionEvent e) {
 * 
 * String Tex_ = (String) tipoLetra.getSelectedItem(); areaTexto.setFont(new
 * Font(Tex_, 10, 20)); } });
 * 
 * add(tipoLetra);
 * 
 * }
 * 
 * }
 */
