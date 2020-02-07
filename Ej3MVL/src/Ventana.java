import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ventana extends JFrame {
    JTextArea editorTexto;
    String    ruta;

    public Ventana () {
        definirPropiedades ( ); //Me falta el icono de la ventana
        crearBarra ( );
        anhadirEditor ( );

    }

    //Definiendo la ventana
    private void definirPropiedades () {
        setTitle ( "Documento Nuevo" );
        setSize ( 1024 , 768 );
        setResizable ( true );
        setDefaultCloseOperation ( EXIT_ON_CLOSE );
        setLocationRelativeTo ( null );
        setLayout ( new BorderLayout ( ) );


    }

    //Creando la barra de navegación
    private void crearBarra () {
        JMenuBar barra   = new JMenuBar ( );
        JMenu    archivo = new JMenu ( "Archivo" );

        setJMenuBar ( barra );
        barra.add ( archivo );

        anhadir_items_y_funciones ( archivo );

    }

    //Añadir items
    private void anhadir_items_y_funciones (JMenu menu) {
        JMenuItem nuevo_item       = new JMenuItem ( "Nuevo" );
        JMenuItem abrir_item       = new JMenuItem ( "Abrir" );
        JMenuItem guardar_item     = new JMenuItem ( "Guardar" );
        JMenuItem guardarComo_item = new JMenuItem ( "Guardar como..." );
        JMenuItem salir_item       = new JMenuItem ( "Salir" );

        menu.add ( nuevo_item );
        menu.add ( abrir_item );
        menu.add ( guardar_item );
        menu.add ( guardarComo_item );
        menu.add ( salir_item );


        anhadir_shortcuts_barra ( nuevo_item , abrir_item , guardar_item , guardarComo_item , salir_item );
        anhadir_iconos_barra ( nuevo_item , abrir_item , guardar_item , guardarComo_item , salir_item );
        anhadirFunciones ( nuevo_item , abrir_item , guardar_item , guardarComo_item , salir_item );

    }

    private void anhadirFunciones (JMenuItem nuevo_item , JMenuItem abrir_item , JMenuItem guardar_item , JMenuItem guardarComo_item , JMenuItem salir_item) {
        nuevoArchivo ( nuevo_item );
        abrirArchivo ( abrir_item );
        guardarArchivo ( guardar_item );
        guardarComo(guardarComo_item);
        salir ( salir_item );
    }

    //Añadir shortcuts
    private void anhadir_shortcuts_barra (JMenuItem nuevo , JMenuItem abrir , JMenuItem guardar , JMenuItem guardarComo , JMenuItem salir) {
        nuevo.setAccelerator ( KeyStroke.getKeyStroke ( "ctrl N" ) );
        abrir.setAccelerator ( KeyStroke.getKeyStroke ( "ctrl O" ) );
        guardar.setAccelerator ( KeyStroke.getKeyStroke ( "ctrl S" ) );
        guardarComo.setAccelerator ( KeyStroke.getKeyStroke ( "ctrl G" ) );
        salir.setAccelerator ( KeyStroke.getKeyStroke ( "ctrl E" ) );
    }

    //Añadir iconos
    private void anhadir_iconos_barra (JMenuItem nuevo , JMenuItem abrir , JMenuItem guardar , JMenuItem guardarComo , JMenuItem salir) {
        ImageIcon nuevo_Icon       = new ImageIcon ( getClass ( ).getResource ( "img/nuevo.png" ) );
        ImageIcon abrir_Icon       = new ImageIcon ( getClass ( ).getResource ( "img/abrir.png" ) );
        ImageIcon guardar_Icon     = new ImageIcon ( getClass ( ).getResource ( "img/guardar.png" ) );
        ImageIcon guardarComo_Icon = new ImageIcon ( getClass ( ).getResource ( "img/guardarc.png" ) );
        ImageIcon salir_Icon       = new ImageIcon ( getClass ( ).getResource ( "img/salir.png" ) );

        nuevo.setIcon ( nuevo_Icon );
        abrir.setIcon ( abrir_Icon );
        guardar.setIcon ( guardar_Icon );
        guardarComo.setIcon ( guardarComo_Icon );
        salir.setIcon ( salir_Icon );
    }

    //Añadiendo panel
    private void anhadirEditor () {
        editorTexto = new JTextArea ( );
        editorTexto.setBackground ( Color.BLACK );
        editorTexto.setBorder ( new LineBorder ( Color.BLUE , 5 ) );
        editorTexto.setFont ( new Font ( "Times Roman-Bold" , Font.BOLD , 15 ) );
        editorTexto.setForeground ( Color.RED );
        // editorTexto.setSize (  1000 , 700);
        // editorTexto.setMargin ( new Insets ( 10,10,10,10 ) );


        this.add ( editorTexto );

    }

    //Primera función (Nuevo Archivo)
    private void nuevoArchivo (JMenuItem nuevo) {
        nuevo.addActionListener ( actionEvent -> {
            int opcion = JOptionPane.showConfirmDialog ( this , "El texto no guardado se perderá, desea continuar?" , "Nuevo documento" , JOptionPane.YES_NO_OPTION );
            if (opcion == JOptionPane.OK_OPTION) {
                editorTexto.selectAll ( );
                editorTexto.replaceSelection ( "" );
                ruta = null;
                // this.setTitle ( "Documento nuevo" );

            }
        } );


    }

    //Segunda función (Abrir Archivo)
    private void abrirArchivo (JMenuItem abrir) {

        abrir.addActionListener ( actionEvent -> {

            ruta = null;

            int opcion = JOptionPane.showConfirmDialog ( this , "El texto no guardado se perderá, desea continuar?" , "Abrir fichero" , JOptionPane.OK_CANCEL_OPTION );

            if (opcion == 0) {

                ruta = JOptionPane.showInputDialog ( "Introduzca la ruta del archivo: " );

            }


            if (ruta != null) {

                File archivo = new File ( ruta );
                if (archivo.canRead ( ) && archivo.canWrite ( ) && archivo.isFile ( )) {


                    String texto = "";


                    try {
                        Scanner scanner = new Scanner ( archivo );
                        String  linea;


                        while (scanner.hasNextLine ( )) {
                            linea = scanner.nextLine ( );
                            texto += linea + "\r\n";
                        }
                        scanner.close ( );
                        editorTexto.setText ( texto );
                    } catch (IOException | NullPointerException e) {

                    }
                }
                else {
                    JOptionPane.showMessageDialog ( this , "Introduzca una ruta correcta o asegúrase de poder leer y escribir el archivo!" , "Fallo de lectura" , JOptionPane.WARNING_MESSAGE );
                    ruta = null;
                }


            }


        } );


    }

    //Tercera función (Guardado normal)
    private void guardarArchivo (JMenuItem guardar) {
        guardar.addActionListener ( actionEvent -> {
            FileWriter     fw;
            BufferedWriter writer;
            String         texto = editorTexto.getText ( );
            if (ruta == null) {
                JFileChooser selector = new JFileChooser ( );
                anhadirFiltro ( selector );
                int opcion = selector.showOpenDialog ( this );
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    try {
                        File archivo = selector.getSelectedFile ( );
                        ruta   = archivo.getAbsolutePath ( );
                        fw     = new FileWriter ( ruta );
                        writer = new BufferedWriter ( fw );

                        writer.write ( texto ); //Esto borra el texto que haya en el anterior archivo, es asi como tiene que hacerse?
                        writer.close ( );

                    } catch (IOException e) {

                    }


                }


            }
            else {
                try {
                    fw     = new FileWriter ( ruta );
                    writer = new BufferedWriter ( fw );
                    writer.write ( texto );
                    writer.close ( );
                } catch (IOException e) {

                }


            }


        } );


    }

    //Cuarta función  (FALTAN COMPROBACIONES DE RUTA)
    private void guardarComo (JMenuItem guardarComo) {
        guardarComo.addActionListener ( actionEvent -> {
            FileWriter     fw;
            BufferedWriter writer;
            String         texto    = editorTexto.getText ( );
            JFileChooser   selector = new JFileChooser ( );
            anhadirFiltro ( selector );

            try {
                File archivo = selector.getSelectedFile ( );
                ruta   = archivo.getAbsolutePath ( );
                fw     = new FileWriter ( ruta );
                writer = new BufferedWriter ( fw );

                writer.write ( texto ); //Esto borra el texto que haya en el anterior archivo, es asi como tiene que hacerse?
                writer.close ( );

            } catch (IOException e) {

            }

        } );

    }

    //Quinta función
    private void salir (JMenuItem salir) {
        salir.addActionListener ( actionEvent -> {
            int opcion = JOptionPane.showConfirmDialog ( this , "El texto no guardado se perderá, desea continuar?" , "Abrir fichero" , JOptionPane.OK_CANCEL_OPTION );
            if (opcion == JOptionPane.OK_OPTION) {

                System.exit ( 0 );
            }
        } );


    }

    //Filtros del selector
    private void anhadirFiltro (JFileChooser selector) {
        FileNameExtensionFilter filtro = new FileNameExtensionFilter ( "texto" , ".txt" );
        selector.setFileFilter ( filtro );
    }


} //FIN


