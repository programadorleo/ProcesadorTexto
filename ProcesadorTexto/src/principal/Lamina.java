package principal;

import java.awt.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import javax.swing.*;
import java.awt.Font;

public class Lamina extends JPanel { // Lamina

	private JTextPane areaTexto;

	private Font letras;

	public Lamina() {

		JPanel laminaMenu = new LaminaMenu();

		setLayout(new BorderLayout());

		add(laminaMenu, BorderLayout.NORTH);

		areaTexto = new JTextPane();

		add(areaTexto, BorderLayout.CENTER);

	}

	private class LaminaMenu extends JPanel { // clase lamina menu

		private JMenuBar barra;

		private JMenu archivo, edicion, fuente, ayuda, fuenteLetra, fuenteEstiloLetra, fuenteTamano;

		private JMenuItem archivoNuevo, archivoAbrir, archivoGuardar, archivoGuardarComo, archivoConfigurarPagina,
				archivoImprimir, salir, edicionDeshacer, edicionCortar, edicionCopiar, edicionPegar, edicionEliminar,
				edicionBuscar, edicionBuscarSiguiente, edicionReemplazar, edicionIrA, edicionSeleccionarTodo, HoraFecha,
				arial, courier, verdana, normal, negrita, cursiva, tam_8, tam_10, tam_14, tam_20, tam_28;

		public LaminaMenu() { // constructor laminaMenu

			barra = new JMenuBar();

			archivo = new JMenu("Archivo");
			edicion = new JMenu("Edición");
			fuente = new JMenu("Fuente");
			ayuda = new JMenu("Ayuda");

			fuenteLetra = new JMenu("Letra");
			fuenteEstiloLetra = new JMenu("Estilo de Letra");
			fuenteTamano = new JMenu("Tamaño");

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

			arial = new JMenuItem("Arial");
			courier = new JMenuItem("Courier");
			verdana = new JMenuItem("Verdana");

			normal = new JMenuItem("Normal");
			negrita = new JMenuItem("Negrita");
			cursiva = new JMenuItem("Cursiva");

			tam_8 = new JMenuItem("8");
			tam_10 = new JMenuItem("10");
			tam_14 = new JMenuItem("14");
			tam_20 = new JMenuItem("20");
			tam_28 = new JMenuItem("28");

			archivo.add(archivoNuevo);
			archivo.add(archivoAbrir);
			archivo.add(archivoGuardar);
			archivo.add(archivoGuardarComo);
			archivo.add(archivoConfigurarPagina);
			archivo.add(archivoImprimir);
			archivo.add(salir);

			edicion.add(edicionDeshacer);
			edicion.add(edicionCortar);
			edicion.add(edicionCopiar);
			edicion.add(edicionPegar);
			edicion.add(edicionEliminar);
			edicion.add(edicionEliminar);
			edicion.add(edicionBuscar);
			edicion.add(edicionBuscarSiguiente);
			edicion.add(edicionReemplazar);
			edicion.add(edicionIrA);
			edicion.add(edicionSeleccionarTodo);
			edicion.add(HoraFecha);

			fuente.add(fuenteLetra);
			fuente.add(fuenteEstiloLetra);
			fuente.add(fuenteTamano);

			fuenteLetra.add(arial);
			fuenteLetra.add(courier);
			fuenteLetra.add(verdana);

			fuenteEstiloLetra.add(normal);
			fuenteEstiloLetra.add(negrita);
			fuenteEstiloLetra.add(cursiva);

			fuenteTamano.add(tam_8);
			fuenteTamano.add(tam_10);
			fuenteTamano.add(tam_14);
			fuenteTamano.add(tam_20);
			fuenteTamano.add(tam_28);

			arial.addActionListener(new GestionaEventos("letra", "Arial", 9, 10));
			courier.addActionListener(new GestionaEventos("letra", "Courier", 9, 10));
			verdana.addActionListener(new GestionaEventos("letra", "Verdana", 9, 10));

			normal.addActionListener(new GestionaEventos("fuente", "", Font.PLAIN, 1));
			negrita.addActionListener(new GestionaEventos("fuente", "", Font.BOLD, 1));
			cursiva.addActionListener(new GestionaEventos("fuente", "", Font.ITALIC, 1));

			tam_8.addActionListener(new GestionaEventos("tamano", "", Font.PLAIN, 8));
			tam_10.addActionListener(new GestionaEventos("tamano", "", Font.PLAIN, 10));
			tam_14.addActionListener(new GestionaEventos("tamano", "", Font.PLAIN, 14));
			tam_20.addActionListener(new GestionaEventos("tamano", "", Font.PLAIN, 20));
			tam_28.addActionListener(new GestionaEventos("tamano", "", Font.PLAIN, 28));

			salir.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					System.exit(0);
				}

			});

			archivoAbrir.addActionListener(new ActionListener() {
				JFileChooser seleccionado = new JFileChooser();
				File archivo;
				byte[] bytesImg;
				GestionArchivo gestion = new GestionArchivo();

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (seleccionado.showDialog(archivoAbrir, "Abrir archivo") == JFileChooser.APPROVE_OPTION) {
						archivo = seleccionado.getSelectedFile();
						if (archivo.canRead()) {
							if (archivo.getName().endsWith("txt")) {
								String contenido = gestion.abrirArchivoTexto(archivo);
								areaTexto.setText(contenido);
							} else {
								if (archivo.getName().endsWith("jpg") || archivo.getName().endsWith("png")
										|| archivo.getName().endsWith("gif")) {
									bytesImg = gestion.abrirImagen(archivo);
									// lblImagen.setIcon(new ImagenIcon(bytesImg));
								} else {
									JOptionPane.showMessageDialog(null, "Por favor seleccione un archivo de texto");
								}
							}
						}
					}

				}

			});

			barra.add(archivo);
			barra.add(edicion);
			barra.add(fuente);
			barra.add(ayuda);

			add(barra);

		} // constructor laminamenu

	} // fin clase laminamenu

	private class GestionaEventos implements ActionListener {

		String tipoTexto, menu;

		int estiloLetra, tamanoLetra;

		GestionaEventos(String elemento, String text, int estLetra, int tamLetra) {

			tipoTexto = text;
			estiloLetra = estLetra;
			tamanoLetra = tamLetra;
			menu = elemento;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

			letras = areaTexto.getFont();

			if (menu.equals("letra")) {

				estiloLetra = letras.getStyle();

				tamanoLetra = letras.getSize();

			} else if (menu.equals("fuente")) {

				tipoTexto = letras.getFontName();

				tamanoLetra = letras.getSize();

			} else if (menu.equals("tamano")) {

				estiloLetra = letras.getStyle();

				tipoTexto = letras.getFontName();

			}

			areaTexto.setFont(new Font(tipoTexto, estiloLetra, tamanoLetra));

		}

	}

} // fin clase lamina principal
