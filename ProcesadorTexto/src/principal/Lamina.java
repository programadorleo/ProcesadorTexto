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

	private class LaminaMenu extends JPanel {

		private JMenuBar barra;

		private JMenu archivo, edicion, fuente, ayuda;
		private JMenu fuenteLetra, fuenteEstiloLetra, fuenteTamano, fuenteColor;

		private JMenuItem archivoNuevo, archivoAbrir, archivoGuardar, archivoGuardarComo, archivoCerrar,
				archivoConfigurarPagina, archivoImprimir, salir;
		private JMenuItem edicionDeshacer, edicionCortar, edicionCopiar, edicionPegar, edicionEliminar, edicionBuscar,
				edicionBuscarSiguiente, edicionReemplazar, edicionIrA, edicionSeleccionarTodo, HoraFecha;
		private JMenuItem arial, courier, verdana, normal, negrita, cursiva, tam_8, tam_10, tam_14, tam_20, tam_28,
				rojo, verde, azul, negro;

		public LaminaMenu() {

			instanciarObjetos();

			configurarMenu();

			gestionMenu();

		}

		public void instanciarObjetos() {

			barra = new JMenuBar();

			archivo = new JMenu("Archivo");
			edicion = new JMenu("Edición");
			fuente = new JMenu("Fuente");
			ayuda = new JMenu("Ayuda");

			archivoNuevo = new JMenuItem("Nuevo");
			archivoAbrir = new JMenuItem("Abrir");
			archivoGuardar = new JMenuItem("Guardar");
			archivoCerrar = new JMenuItem("Cerrar");
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

			fuenteLetra = new JMenu("Letra");
			fuenteEstiloLetra = new JMenu("Estilo de Letra");
			fuenteTamano = new JMenu("Tamaño");
			fuenteColor = new JMenu("Color");

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
			rojo = new JMenuItem("Rojo");
			verde = new JMenuItem("Verde");
			azul = new JMenuItem("Azul");
			negro = new JMenuItem("Negro");

		}

		public void configurarMenu() {

			archivo.add(archivoNuevo);
			archivo.add(archivoAbrir);
			archivo.add(archivoGuardar);
			archivo.add(archivoCerrar);
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
			fuente.add(fuenteColor);

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
			fuenteColor.add(rojo);
			fuenteColor.add(verde);
			fuenteColor.add(azul);
			fuenteColor.add(negro);

			barra.add(archivo);
			barra.add(edicion);
			barra.add(fuente);
			barra.add(ayuda);

			add(barra);

		}

		public void gestionMenu() {

			gestionArchivo();
			gestionEdicion();
			gestionFuente();
			gestionAyuda();
		}

		public void gestionArchivo() {

			GestionArchivo gestion = new GestionArchivo();

			JFileChooser seleccionado = new JFileChooser();
			
			
			
			archivoNuevo.addActionListener(new ActionListener() {
				
				File archivo;

				@Override
				public void actionPerformed(ActionEvent e) {
					
					if (seleccionado.showDialog(archivoNuevo, "Archivo nuevo") == JFileChooser.APPROVE_OPTION) {

						archivo = seleccionado.getSelectedFile();

						gestion.setArchivo(archivo);

						if (archivo.canRead()) {

							if (archivo.getName().endsWith("txt")) {

								String contenido = gestion.abrirArchivoTexto();

								areaTexto.setText(contenido);

							}
						}
					}
					
					
				}
				
				
			});
			
			

			archivoAbrir.addActionListener(new ActionListener() {

				File archivo;

				@Override
				public void actionPerformed(ActionEvent arg0) {

					if (seleccionado.showDialog(archivoAbrir, "Abrir archivo") == JFileChooser.APPROVE_OPTION) {

						archivo = seleccionado.getSelectedFile();

						gestion.setArchivo(archivo);

						if (archivo.canRead()) {

							if (archivo.getName().endsWith("txt")) {

								String contenido = gestion.abrirArchivoTexto();

								areaTexto.setText(contenido);

							}
						}
					}

				}

			});

			archivoGuardar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					gestion.guardarArchivoTexto(areaTexto.getText());

				}

			});

			archivoCerrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					int opcion = JOptionPane.showConfirmDialog(null, "Desea guardar los cambios");
					// 0=si 1=no 2=cancelar

					if (opcion == 0) {

						gestion.guardarArchivoTexto(areaTexto.getText());

					}

					areaTexto.setText("");

				}

			});

		}
		
		

		public void gestionEdicion() {
		}

		public void gestionFuente() {

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

			rojo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					areaTexto.setForeground(Color.red);
				}
			});

			verde.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					areaTexto.setForeground(Color.green);
				}
			});

			azul.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					areaTexto.setForeground(Color.blue);
				}
			});

			negro.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					areaTexto.setForeground(Color.black);
				}
			});

		}

		public void gestionAyuda() {
		}

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
