import javax.swing.JTextArea;

/*
public class Principal {

}*/



import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Caret;
import javax.swing.text.DefaultEditorKit;

/**
 *
 * @author Julian Delgado Castillo
 * @carnet 1690-07-9127
 */
public class Principal extends javax.swing.JFrame {

    //Variables para los Combos de formato de letra
    JComboBox TipoLetra;
    JComboBox TamanoLetra;
    String nombreFuentes[];
    String tamanoFuente[];
    
    public Principal() {
        initComponents();
        
        /*PROPIEDADES DE FORMULARIO */
        
        this.setLocationRelativeTo(null);                  //Centrar Formulario
        this.setTitle("JD Text Editor vr. 1.0-alpha");     //Agregar un título al formulario
        
       
        /*
         AREA DE TEXTO: Se declara un margen para el área de texto, de manera que
        el texto baje un renglón automáticamente cuando llegue al final del área y 
        no siga de corrido. Además se agregó una barra de desplazamiento para textos
        muy largos.
        */
        
        AreaTexto.setLineWrap(true);       // Parte las líneas y las baja un renglón
        AreaTexto.setWrapStyleWord(true);  // Respetar y no partir palabras completas
        AreaTexto.requestFocus();                          //Selecciona el JTextArea como principal
        
       // JScrollPane scroll = new JScrollPane(jTextArea1);
       // v.getContetPane().add(scroll, BorderLayout.CENTER);
        
       
        
        /* ACCIONES - COPIAR,CORTAR Y PEGAR */
 
        ActionMap acciones = AreaTexto.getActionMap();  //Se implementan las acciones del JTextArea llamando a su método getActionMap()
        
        Action accionCopiar = acciones.get(DefaultEditorKit.copyAction);    //Se declara la acción copiar
        Action accionPegar = acciones.get(DefaultEditorKit.pasteAction);    //Se declara la acción pegar
        Action accionCortar = acciones.get(DefaultEditorKit.cutAction);     //Se declara la acción cortar
        
       //Se le asigana nombre y un botón a la función Copiar y un método abreviado de teclado
        accionCopiar.putValue(Action.NAME, "Copiar");
        accionCopiar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('C', Event.CTRL_MASK));
       
       //Se le asigana nombre y un botón a la función Cortar y un método abreviado de teclado 
        accionCortar.putValue(Action.NAME, "Cortar");
        accionCortar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('X', Event.CTRL_MASK));
        
       //Se le asigana nombre y un botón a la función Pegar y un método abreviado de teclado
        accionPegar.putValue(Action.NAME, "Pegar");
        accionPegar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getAWTKeyStroke('V', Event.CTRL_MASK));
        
        //Se asignan las acciones al menú Editar de la barra de Menú
        MenuEditar.add(accionCopiar);
        MenuEditar.add(accionCortar);
        MenuEditar.add(accionPegar);
        
        /*
        ActionMap Botones = AreaTexto.getActionMap();
        
        Action botonCopiar = Botones.get(DefaultEditorKit.copyAction);
        botonCopiar.putValue(Action.SMALL_ICON, new ImageIcon("C:\\Users\\Julian Delgado\\Documents\\NetBeansProjects\\TexEditor\\src\\Images\\copy.png"));
        BarraHerramientas.add(botonCopiar);
        */
       
        
        //Inicialización de variable para ComboBox TipoLetra
        nombreFuentes = Toolkit.getDefaultToolkit().getFontList();
        
        //Inicialización de variable para ComboBox TamanoLetra
        tamanoFuente = new String[37];
        for(int i=11;i<48;i++){
            tamanoFuente[i-11]= "" + (i + 1);
        }
        
        
        //COMBO PARA TIPO DE LETRA
        
        TipoLetra = new JComboBox(nombreFuentes);
        TipoLetra.addItem("Arial");
        TipoLetra.addItem("Calibri");
        TipoLetra.addItem("Comic Sans MS");
        TipoLetra.addItem("Impact");
        TipoLetra.addItem("Monotype Corsiva");
        TipoLetra.addItem("Tahoma");
        TipoLetra.addItem("Times New Roman");
        TipoLetra.addItem("Verdana");
        TipoLetra.setSelectedItem("Arial");
        TipoLetra.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
                AreaTexto.setFont(new Font(""+ TipoLetra.getItemAt(TipoLetra.getSelectedIndex()), Font.PLAIN, Integer.parseInt(tamanoFuente[TamanoLetra.getSelectedIndex()])));
        
            }
          }
        );
        
          //COMBO PARA TAMAÑNO DE LETRA
            
        TamanoLetra = new JComboBox(tamanoFuente);
        TamanoLetra.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           
                AreaTexto.setFont(new Font(""+ TipoLetra.getItemAt(TipoLetra.getSelectedIndex()), Font.PLAIN, Integer.parseInt(tamanoFuente[TamanoLetra.getSelectedIndex()])));
            }
        });
        BarraHerramientas.setFloatable(false);
        BarraHerramientas2.setFloatable(false);
        BarraHerramientas.add(TipoLetra);
        BarraHerramientas.add(TamanoLetra);
    }
    
    //Método para Abrir Ficheros en el editor
    
    public void AbrirFichero(){
        
         FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto", "doc", "docx", "txt"); //Crea un filtro para especificar que sólo se trabajaran archivos de texto
        
        
        JFileChooser fileChooser = new JFileChooser();              //Muestra una ventana que permite navegar por los directorios
        fileChooser.setDialogTitle("Abrir");                        //Agrega título al cuadro de diálogo
        fileChooser.setFileFilter(filtro);                          //Se agrega el filtro de tipo de archivo al cuadro de diálogo
        int seleccion = fileChooser.showOpenDialog(AreaTexto);      //En "int seleccion" se guarda la selección que hizo el usario "Abir" o "Cancelar".

        if (seleccion == JFileChooser.APPROVE_OPTION)
        {
            File fichero = fileChooser.getSelectedFile();           //Selecciona el fichero

            try {
                BufferedReader leer = new BufferedReader(new FileReader(fichero));  //Construye un BufferedReader que va leyendo línea por línea. No lee retornos

                //Bucle para leer líenas y retornos.
                String linea = leer.readLine();
                while (linea != null)
                {
                    AreaTexto.append(linea);
                    AreaTexto.append(System.getProperty("line.separtor"));  //Retorno de carro
                    linea = leer.readLine();

                }

                // AreaTexto.setText(AreaTexto.toString());
                leer.close(); //Se cierra lectura del fichero
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); //Toda va dentro de un Try Catch por cualquier error en la apertur y cierre del fichero.
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //Método para Guardar los ficheros creados en el editor
    public void GuardarFichero(){
             FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto", "doc", "txt"); //Crea un filtro para especificar que sólo se trabajaran archivos de texto
       
        JFileChooser fileChooser = new JFileChooser();              //Muestra una ventana que permite navegar por los directorios
        fileChooser.setDialogTitle("Guardar");                      //Agrega el título al cuadro de diálogo
        fileChooser.setApproveButtonText("Guardar");                //Cambia el texto del botón de aceptación
        fileChooser.setFileFilter(filtro);                          //Se agrega el filtro al cuadro de diálogo
        int seleccion = fileChooser.showOpenDialog(AreaTexto);      //En "int seleccion" se guarda la selección que hizo el usario "Abir" o "Cancelar".

        if (seleccion == JFileChooser.APPROVE_OPTION)
        {
            File fichero = fileChooser.getSelectedFile();           //Selecciona el fichero

            try {
                PrintWriter escritor = new PrintWriter(fichero);    //Se abre el fichero
                escritor.print(AreaTexto.getText());                //Se le escribe el texto contenido en AreaTexto
                escritor.close();                                   //Se cierra el fichero.
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex); //Toda va dentro de un Try Catch por cualquier error en la apertur y cierre del fichero.
            }
        }
        }

    /**
     * A partir de este punto se encuentra el código generado por el IDE de forma autómatica
     * cuando se crean los objetos.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        BarraHerramientas = new javax.swing.JToolBar();
        BotonNuevo = new javax.swing.JButton();
        BotonAbrir = new javax.swing.JButton();
        BotonGuardar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        BotonCopiar = new javax.swing.JButton();
        BotonCortar = new javax.swing.JButton();
        BotonPegar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jScrollPane1 = new javax.swing.JScrollPane();
        AreaTexto = new javax.swing.JTextArea();
        BarraHerramientas2 = new javax.swing.JToolBar();
        BotonNegrita = new javax.swing.JButton();
        BotonCursiva = new javax.swing.JButton();
        BotonSubrayado = new javax.swing.JButton();
        ColorFuente = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuArchivo = new javax.swing.JMenu();
        Abrir = new javax.swing.JMenuItem();
        Guardar = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenuItem();
        MenuEditar = new javax.swing.JMenu();
        Buscar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BarraHerramientas.setRollover(true);

        BotonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nuevo.png"))); // NOI18N
        BotonNuevo.setFocusable(false);
        BotonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BotonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonNuevoActionPerformed(evt);
            }
        });
        BarraHerramientas.add(BotonNuevo);

        BotonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/openfile.png"))); // NOI18N
        BotonAbrir.setFocusable(false);
        BotonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BotonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAbrirActionPerformed(evt);
            }
        });
        BarraHerramientas.add(BotonAbrir);

        BotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        BotonGuardar.setFocusable(false);
        BotonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BotonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGuardarActionPerformed(evt);
            }
        });
        BarraHerramientas.add(BotonGuardar);
        BarraHerramientas.add(jSeparator2);

        BotonCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/copy.png"))); // NOI18N
        BotonCopiar.setFocusable(false);
        BotonCopiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonCopiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BotonCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCopiarActionPerformed(evt);
            }
        });
        BarraHerramientas.add(BotonCopiar);

        BotonCortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cut.png"))); // NOI18N
        BotonCortar.setFocusable(false);
        BotonCortar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonCortar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BotonCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCortarActionPerformed(evt);
            }
        });
        BarraHerramientas.add(BotonCortar);

        BotonPegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/paste.png"))); // NOI18N
        BotonPegar.setFocusable(false);
        BotonPegar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonPegar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BotonPegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonPegarActionPerformed(evt);
            }
        });
        BarraHerramientas.add(BotonPegar);
        BarraHerramientas.add(jSeparator3);

        AreaTexto.setColumns(20);
        AreaTexto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        AreaTexto.setRows(5);
        jScrollPane1.setViewportView(AreaTexto);

        BarraHerramientas2.setRollover(true);

        BotonNegrita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bold.png"))); // NOI18N
        BotonNegrita.setFocusable(false);
        BotonNegrita.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonNegrita.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BotonNegrita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonNegritaActionPerformed(evt);
            }
        });
        BarraHerramientas2.add(BotonNegrita);

        BotonCursiva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/italic.png"))); // NOI18N
        BotonCursiva.setFocusable(false);
        BotonCursiva.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonCursiva.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BotonCursiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCursivaActionPerformed(evt);
            }
        });
        BarraHerramientas2.add(BotonCursiva);

        BotonSubrayado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/underline.png"))); // NOI18N
        BotonSubrayado.setFocusable(false);
        BotonSubrayado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BotonSubrayado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BotonSubrayado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSubrayadoActionPerformed(evt);
            }
        });
        BarraHerramientas2.add(BotonSubrayado);

        ColorFuente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fontcolor.png"))); // NOI18N
        ColorFuente.setFocusable(false);
        ColorFuente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ColorFuente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ColorFuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorFuenteActionPerformed(evt);
            }
        });
        BarraHerramientas2.add(ColorFuente);

        MenuArchivo.setText("Archivo");

        Abrir.setText("Abrir");
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        MenuArchivo.add(Abrir);

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        MenuArchivo.add(Guardar);

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        MenuArchivo.add(Salir);

        jMenuBar1.add(MenuArchivo);

        MenuEditar.setText("Editar");

        Buscar.setText("Buscar...");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        MenuEditar.add(Buscar);
        MenuEditar.add(jSeparator1);

        jMenuBar1.add(MenuEditar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(BarraHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BarraHerramientas2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BarraHerramientas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BarraHerramientas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    /**
     * MENU ARCHIVO
     */
    
    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // BOTON SALIR DEL MENÚ ARCHIVO

        System.exit(0); //método salir

    }                                     

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // BOTÓN GUARDAR DE MENÚ ARCHIBO

       GuardarFichero(); //se llama al método GuardarFichero

    }                                       

    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // BOTÓN ABRIR DE MENÚ ARCHIVO

       AbrirFichero(); //Se llema el método abrir fechero

    }                                     

    /**
     * MENU EDITAR
     */
    
    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // FUNCION BUSCAR DEL MENU EDITAR

        //String Buscar = JOptionPane.showInputDialog(AreaTexto, "Buscar: ", "Escriba su texto");

        //Variable tipo String que almacena el texto que está en el AreaTexto
        String textoInicialDeBusqueda = AreaTexto.getSelectedText();
        
        //Sentencia que determina si AreaTexto contiene texto o está vacia
        if(textoInicialDeBusqueda == null)
        textoInicialDeBusqueda = "";
        
        //Muestra un Input para ingresar el texto que se buscará
        String TextoBuscar = JOptionPane.showInputDialog(AreaTexto, "Buscar", textoInicialDeBusqueda);

       //Se crea un objeto tipo Carte que sirve para seleccionar el texto de la busqueda
        Caret seleccionado = AreaTexto.getCaret();

        /*
        Esto sirve para saber la posición del texto seleccionado. De esta forma se podrá hacer la búsqueda
        a partir de la posición en la que encontramos el texto previamente.
        */
        int posicionInicial = 0;
        if(seleccionado.getDot() != seleccionado.getMark())
        {
            posicionInicial = seleccionado.getDot(); //Hay algo seleccioando

        }

        //La búsqueda se realiza usando el método indexOf() que además de buscar el texto admite la posición inical de búsqueda.
        String textoTotal = AreaTexto.getText();
        int posicion = textoTotal.indexOf(TextoBuscar, posicionInicial);

        /*Se utilizan los métodos setCaretPosition() que pone Dot y Mark en la posición inicial y el método
         moveCaretPosition() que hace que Dot se mueva a la siguiente posición. De esa forma el texto queda seleccionado
        */
        AreaTexto.setCaretPosition(posicion);
        AreaTexto.moveCaretPosition(TextoBuscar.length());
    }                                      

    private void ColorFuenteActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // BOTON COLOR DE FUENTE EN BARRA DE HERRAMIENTAS
        
        JColorChooser SeleccionarColor = new JColorChooser();           //Se crea un objeto tipo JColorChooser para seleccionar colores.
        
        
        /*
        Se crea un objeto tipo Color que despliega la paleta de colores en un cuadro de diálogo
        y almacena el color que seleccionamos en NuevoColor
        */
        Color NuevoColor = JColorChooser.showDialog(SeleccionarColor, "Color de Fuente", AreaTexto.getSelectionColor());
        
        //Esta sentencia asigna el color seleccionado al texto del AreaTexto, siempre que no se Nulo
        if(NuevoColor != null){
            AreaTexto.setForeground(NuevoColor);
        }
        
        
        /*
        SeleccionaColor.getSelectionModel().addChangeListener((ChangeListener) this);
        SeleccionaColor.setMaximumSize(new Dimension(30,30));
        
        setLayout(new BorderLayout());
        add(SeleccionaColor, BorderLayout.CENTER);
        
        Color colores = SeleccionaColor.getColor();
        AreaTexto.setForeground(colores);
        
        
        final JColorChooser SeleccionarColor = new JColorChooser();
        
        getContentPane().add(SeleccionarColor, BorderLayout.CENTER);
        
        SeleccionarColor.getSelectionModel().addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
               
                Color NuevoColor = Seleccio
                AreaTexto.setForeground(NuevoColor);
            }
          }
                
         */
        
        
        
    }                                           

    private void BotonNuevoActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // BOTON NUEVO DE LA BARRA DE HERRAMIENTAS
        
        AreaTexto.setText("");
        
        
    }                                          

    private void BotonAbrirActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // BOTON ABRIR DE LA BARRA DE HERRAMIENTAS
        
        AbrirFichero(); //Se llama al método AbrirFichro
    }                                          

    private void BotonGuardarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        //BOTON GUARDAR DE LA BARRA DE HERRAMIENTAS
        
        GuardarFichero(); //Se llama al método GuardarFichero
    }                                            

    private void BotonCopiarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // BOTON COPIAR BARRA DE HERRAMIENTAS
        
        ActionMap Boton = AreaTexto.getActionMap();
        Action botonCopiar = Boton.get(DefaultEditorKit.copyAction);
        botonCopiar.actionPerformed(evt); //La acción copiar se le asiga al evento (Pulsar botón)
    }                                           

    private void BotonCortarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        //BOTON CORTAR DE LA BARRA DE HERRAMIENTAS
        
        ActionMap Boton = AreaTexto.getActionMap();
        Action botonCortar = Boton.get(DefaultEditorKit.cutAction);
        botonCortar.actionPerformed(evt); //La acción copiar se le asiga al evento (Pulsar botón)
    }                                           

    private void BotonPegarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        //BOTON PEGAR DE LA BARRA DE HERRAMIENTAS
        
        ActionMap Boton = AreaTexto.getActionMap();
        Action botonPegar = Boton.get(DefaultEditorKit.pasteAction);
        botonPegar.actionPerformed(evt); //La acción copiar se le asiga al evento (Pulsar botón)
    }                                          

    private void BotonNegritaActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        
         AreaTexto.setFont(new Font(""+ TipoLetra.getItemAt(TipoLetra.getSelectedIndex()), Font.BOLD, Integer.parseInt(tamanoFuente[TamanoLetra.getSelectedIndex()])));
        
        
    }                                            

    private void BotonCursivaActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        
         AreaTexto.setFont(new Font(""+ TipoLetra.getItemAt(TipoLetra.getSelectedIndex()), Font.ITALIC, Integer.parseInt(tamanoFuente[TamanoLetra.getSelectedIndex()])));
    }                                            

    private void BotonSubrayadoActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        
         AreaTexto.setFont(new Font(""+ TipoLetra.getItemAt(TipoLetra.getSelectedIndex()), Font.PLAIN, Integer.parseInt(tamanoFuente[TamanoLetra.getSelectedIndex()])));
    }                                              

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JMenuItem Abrir;
    private javax.swing.JTextArea AreaTexto;
    private javax.swing.JToolBar BarraHerramientas;
    private javax.swing.JToolBar BarraHerramientas2;
    private javax.swing.JButton BotonAbrir;
    private javax.swing.JButton BotonCopiar;
    private javax.swing.JButton BotonCortar;
    private javax.swing.JButton BotonCursiva;
    private javax.swing.JButton BotonGuardar;
    private javax.swing.JButton BotonNegrita;
    private javax.swing.JButton BotonNuevo;
    private javax.swing.JButton BotonPegar;
    private javax.swing.JButton BotonSubrayado;
    private javax.swing.JMenuItem Buscar;
    private javax.swing.JButton ColorFuente;
    private javax.swing.JMenuItem Guardar;
    private javax.swing.JMenu MenuArchivo;
    private javax.swing.JMenu MenuEditar;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    // End of variables declaration                   
}

