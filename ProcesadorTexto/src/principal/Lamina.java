package principal;

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
		private JMenu Archivo, Edicion, Fuente,Ayuda;
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
			Barra.add(Ayuda);

			add(Barra);
		}

	}
}

