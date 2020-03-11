package com.liceolapaz.des.MVL;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;


public class Ventana extends JFrame {

    private Tablero tablero;
    private JLabel lbl_contador_1;
    private JLabel lbl_contador_2;
    private JLabel lbl_contador_3;
    private Cronometro cr;
    private int filas;
    private int columnas;
    private Dialogo dialogo;
    private boolean almacenar = false;


    public Ventana ( ) {
        anhadir_propiedades_Ventana ( false, null );
        anhadir_Panel_y_Elementos ( );


    }

    private void anhadir_propiedades_Ventana ( boolean resizable, LayoutManager layout ) {
        setTitle ( "Buscar Parejas - Martín Varela Lorenzo" );
        setLayout ( layout );
        setResizable ( resizable );
        setSize ( 720, 490 );
        setLocationRelativeTo ( null );
        setDefaultCloseOperation ( EXIT_ON_CLOSE );
        ImageIcon iconoventana = new ImageIcon ( getClass ( ).getResource ( "/iconoventana.png" ) );
        setIconImage ( iconoventana.getImage ( ) );

    }

    private void anhadir_Panel_y_Elementos ( ) {
        JPanel    panelPrincipal = new JPanel ( );
        JLabel    titulo         = new JLabel ( "Buscar Parejas" );
        JLabel    instruccion    = new JLabel ( "Pulse en la imagen para empezar a jugar" );
        JLabel    autor          = new JLabel ( " Autor: Martín Varela Lorenzo" );
        JButton   boton          = new JButton ( );
        ImageIcon imagenBoton    = new ImageIcon ( getClass ( ).getResource ( "/icono.png" ) );


        this.add ( panelPrincipal );

        panelPrincipal.setSize ( 720, 490 );
        panelPrincipal.setLayout ( null );


        titulo.setBounds ( 290, 0, 150, 60 );
        titulo.setFont ( new Font ( "Serif", Font.BOLD, 20 ) );


        boton.setIcon ( imagenBoton );
        boton.setBounds ( 265, 60, 200, 200 );

        instruccion.setBounds ( 225, 260, 300, 60 );
        instruccion.setFont ( new Font ( "Serif", Font.BOLD, 14 ) );

        autor.setBounds ( 265, 330, 300, 60 );
        autor.setFont ( new Font ( "Serif", Font.BOLD, 13 ) );

        panelPrincipal.add ( titulo );
        panelPrincipal.add ( boton );
        panelPrincipal.add ( instruccion );
        panelPrincipal.add ( autor );

        accionPanelPrincipal ( boton );
    }

    private void accionPanelPrincipal ( JButton boton ) {

        boton.addActionListener ( actionEvent -> {
            this.getContentPane ( ).removeAll ( );
            this.repaint ( );
            this.anhadir_elementos_ventana_de_Juego ( 3, 4, "00", "  " + 3 * 4 / 2 + "  ", "00:00:00" );


        } );

    }

    protected void anhadir_elementos_ventana_de_Juego ( int filas, int columnas, String textoContador1, String textoContador2, String textoContador3 ) {
        this.anhadir_propiedades_Ventana ( true, new BorderLayout ( ) );
        crearMenuSup ( );
        this.tablero = new Tablero ( this, filas, columnas );
        this.add ( this.tablero, BorderLayout.CENTER );
        crearMenuInf ( textoContador1, textoContador2, textoContador3 );


    }

    private void crearMenuInf ( String textoContador1, String textoContador2, String textoContador3 ) {
        JPanel panelInferior = new JPanel ( );
        panelInferior.setLayout ( new FlowLayout ( FlowLayout.CENTER, 50, 30 ) );
        panelInferior.setBackground ( Color.YELLOW );


        Font       fuente = new Font ( "Agency FB", Font.BOLD, 20 );
        LineBorder borde  = new LineBorder ( Color.BLACK, 4 );

        JLabel lbl_intentos = new JLabel ( "  Intentos  " );
        lbl_intentos.setFont ( fuente );
        lbl_intentos.setBorder ( borde );


        lbl_contador_1 = new JLabel ( textoContador1 );
        lbl_contador_1.setFont ( fuente );
        lbl_contador_1.setBorder ( borde );


        JLabel lbl_parejas = new JLabel ( "  Parejas  " );
        lbl_parejas.setFont ( fuente );
        lbl_parejas.setBorder ( borde );


        lbl_contador_2 = new JLabel ( textoContador2 );
        lbl_contador_2.setFont ( fuente );
        lbl_contador_2.setBorder ( borde );

        ImageIcon relojIcon = new ImageIcon ( getClass ( ).getResource ( "/reloj.png" ) );
        JLabel    lbl_reloj = new JLabel ( );
        lbl_reloj.setIcon ( relojIcon );

        lbl_contador_3 = new JLabel ( textoContador3 );
        if (cr == null) {
            cr = new Cronometro ( lbl_contador_3, 0 );
            cr.start ( );
        }
        lbl_contador_3.setFont ( fuente );
        lbl_contador_3.setBorder ( borde );

        panelInferior.add ( lbl_intentos );
        panelInferior.add ( lbl_contador_1 );
        panelInferior.add ( lbl_parejas );
        panelInferior.add ( lbl_contador_2 );
        panelInferior.add ( lbl_reloj );
        panelInferior.add ( lbl_contador_3 );

        this.add ( panelInferior, BorderLayout.SOUTH );


        this.revalidate ( );
    }

    private void crearMenuSup ( ) {
        JMenuBar barra    = new JMenuBar ( );
        JMenu    opciones = new JMenu ( "Opciones" );
        JMenu    partida  = new JMenu ( "Partida" );

        barra.add ( partida );
        barra.add ( opciones );
        this.setJMenuBar ( barra );

        JMenuItem nuevaPartidaItem        = new JMenuItem ( "Nueva partida" );
        JMenuItem guardarPartidaItem      = new JMenuItem ( "Guardar partida" );
        JMenuItem cargarPartidaItem       = new JMenuItem ( "Cargar partida" );
        JMenuItem salirItem               = new JMenuItem ( "Salir" );
        JCheckBox almacenarResultadosItem = new JCheckBox ( "Almacenar Resultados" );
        JMenuItem nivelDificultadItem     = new JMenuItem ( "Nivel de dificultad" );

        nuevaPartidaItem.setIcon ( new ImageIcon ( getClass ( ).getResource ( "/nuevo.png" ) ) );
        guardarPartidaItem.setIcon ( new ImageIcon ( getClass ( ).getResource ( "/guardar.png" ) ) );
        cargarPartidaItem.setIcon ( new ImageIcon ( getClass ( ).getResource ( "/abrir.png" ) ) );
        salirItem.setIcon ( new ImageIcon ( getClass ( ).getResource ( "/salir.png" ) ) );
        nivelDificultadItem.setIcon ( new ImageIcon ( getClass ( ).getResource ( "/cambiarDificultad.png" ) ) );

        funcionMenu ( nuevaPartidaItem, guardarPartidaItem, cargarPartidaItem, salirItem, nivelDificultadItem, almacenarResultadosItem );


        partida.add ( nuevaPartidaItem );
        partida.add ( guardarPartidaItem );
        partida.add ( cargarPartidaItem );
        partida.add ( salirItem );
        opciones.add ( almacenarResultadosItem );
        opciones.add ( nivelDificultadItem );

        nuevaPartidaItem.setAccelerator ( KeyStroke.getKeyStroke ( "ctrl N" ) );
        guardarPartidaItem.setAccelerator ( KeyStroke.getKeyStroke ( "ctrl G" ) );
        cargarPartidaItem.setAccelerator ( KeyStroke.getKeyStroke ( "ctrl C" ) );
        salirItem.setAccelerator ( KeyStroke.getKeyStroke ( "ctrl S" ) );
        nivelDificultadItem.setAccelerator ( KeyStroke.getKeyStroke ( "ctrl O" ) );

        nuevaPartidaItem.setMnemonic ( 'N' );
        guardarPartidaItem.setMnemonic ( 'G' );
        cargarPartidaItem.setMnemonic ( 'C' );
        salirItem.setMnemonic ( 'S' );
        nivelDificultadItem.setMnemonic ( 'D' );

    }

    private void funcionMenu ( JMenuItem nuevaPartidaItem, JMenuItem guardarPartida, JMenuItem cargarPartida, JMenuItem salirPartida, JMenuItem cambiarDificultad, JCheckBox almacenarResultados) {
        nuevaPartidaItem.addActionListener ( actionEvent -> {

            cr.parar ( );
            cr = null;
            this.getContentPane ( ).removeAll ( );
            this.repaint ( );
            anhadir_elementos_ventana_de_Juego ( 3, 4, "00", "  " + tablero.getFilas ( ) * tablero.getColumnas ( ) / 2 + "  ", "00:00:00" );

        } );

        guardarPartida.addActionListener ( actionEvent -> {

            JFileChooser selector = new JFileChooser ( );
            FileWriter   fw;
            String       partida;
            int          opcion   = selector.showOpenDialog ( this );
            if (opcion == JFileChooser.APPROVE_OPTION) {

                try {
                    partida = +tablero.getFilas ( ) + "," + tablero.getColumnas ( ) + ",";
                    partida += tablero.getParejas ( ) + "," + tablero.getContador1 ( ) + "," + this.lbl_contador_3.getText ( ) + "\r\n";
                    fw = new FileWriter ( selector.getSelectedFile ( ) );
                    BufferedWriter writer = new BufferedWriter ( fw );

                    for (int i = 0; i < tablero.getFilas ( ); i++) {
                        for (int j = 0; j < tablero.getColumnas ( ); j++) {


                            int valor = tablero.getBotonArray ( )[i][j].getNumeroBoton ( );
                            partida += valor + "," + tablero.getBotonArray ( )[i][j].getFila ( ) + "," + tablero.getBotonArray ( )[i][j].getColumna ( ) + "," + tablero.getBotonArray ( )[i][j].isPulsado ( ) + "\r\n";

                        }
                    }

                    writer.write ( partida );
                    writer.flush ( );

                } catch (NullPointerException | IOException e) {
                    e.printStackTrace ( );
                }

            }
        } );

        cargarPartida.addActionListener ( actionEvent -> {
            String[]     arrayString;
            String[]     propiedadesPartida;
            String       linea;
            JFileChooser selector        = new JFileChooser ( );
            int          opcion          = selector.showOpenDialog ( this );
            boolean      archivoCorrecto = true;
            String[]     minSeg          = null;
            if (opcion == JFileChooser.APPROVE_OPTION) {

                try {
                    FileReader     fr            = new FileReader ( selector.getSelectedFile ( ).getAbsoluteFile ( ) );
                    BufferedReader reader        = new BufferedReader ( fr );
                    int            cont          = 0;
                    Boton[][]      nuevosBotones = null;

                    while ((linea = reader.readLine ( )) != null) {

                        arrayString = linea.split ( "," );

                        if (cont == 0 && arrayString.length == 5) {
                            propiedadesPartida = arrayString;
                            if (archivoCorrecto) {
                                this.getContentPane ( ).removeAll ( );
                                this.revalidate ( );
                                crearMenuSup ( );
                                nuevosBotones = new Boton[Integer.parseInt ( propiedadesPartida[0] )][Integer.parseInt ( propiedadesPartida[1] )];
                                this.tablero = new Tablero ( this, Integer.parseInt ( propiedadesPartida[0] ), Integer.parseInt ( propiedadesPartida[1] ), nuevosBotones );
                                tablero.setContador1 ( Integer.parseInt ( propiedadesPartida[3] ) );
                                tablero.setParejas ( Integer.parseInt ( propiedadesPartida[2] ) );
                                crearMenuInf ( propiedadesPartida[3], propiedadesPartida[2], propiedadesPartida[4] );
                                minSeg = propiedadesPartida[4].split ( ":" );

                            }
                            cont++;

                        } else if (cont != 0 && arrayString.length == 4) {

                            Boton boton = new Boton ( this.tablero, Integer.parseInt ( arrayString[1] ), Integer.parseInt ( arrayString[2] ), Integer.parseInt ( arrayString[0] ), Boolean.parseBoolean ( arrayString[3] ) );
                            nuevosBotones[Integer.parseInt ( arrayString[1] )][Integer.parseInt ( arrayString[2] )] = boton;

                            tablero.add ( nuevosBotones[Integer.parseInt ( arrayString[1] )][Integer.parseInt ( arrayString[2] )] );
                            repaint ( );


                        } else {
                            archivoCorrecto = false;
                           
                        }

                        this.add ( this.tablero, BorderLayout.CENTER );


                        cr.parar ( );
                        cr = null;
                        cr = new Cronometro ( lbl_contador_3, Integer.parseInt ( minSeg[2] ) );
                        cr.start ( );
                        revalidate ( );
                    }
                } catch (IOException e) {

                }


            }


        } );

        salirPartida.addActionListener ( actionEvent -> System.exit ( 0 ) );

        cambiarDificultad.addActionListener ( actionEvent -> {

             dialogo = new Dialogo ( this );
            dialogo.setVisible ( true );


        } );

        almacenarResultados.addActionListener ( actionEvent -> {
            almacenar = true;
        } );


    }

    public int getFilas ( ) {
        return filas;
    }

    public void setFilas ( int filas ) {
        this.filas = filas;
    }

    public int getColumnas ( ) {
        return columnas;
    }

    public void setColumnas ( int columnas ) {
        this.columnas = columnas;
    }

    public Tablero getTablero ( ) {
        return tablero;
    }

    public JLabel getLbl_contador_1 ( ) {
        return lbl_contador_1;
    }

    public JLabel getLbl_contador_2 ( ) {
        return lbl_contador_2;
    }

    public Cronometro getCr ( ) {
        return cr;
    }

    public void setCr ( Cronometro cr ) {
        this.cr = cr;
    }

    public JLabel getLbl_contador_3 ( ) {
        return lbl_contador_3;
    }

    public boolean isAlmacenar ( ) {
        return almacenar;
    }

}
