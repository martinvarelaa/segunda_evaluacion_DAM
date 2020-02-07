package com.liceolapaz.des.MVL;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Ventana extends JFrame {
    Tablero tablero;
    JLabel lbl_contador_1;
    JLabel lbl_contador_2;


    public Ventana ( ) {
        anhadir_propiedades_Ventana ( false, null );
        anhadir_Panel_y_Elementos ( );

    }

    private void anhadir_propiedades_Ventana ( boolean resizable, LayoutManager layout ) {  //Modificar para cambiar la ventana
        setTitle ( "Buscar Parejas - Martín Varela Lorenzo" );
        setLayout ( layout );
        setResizable ( resizable );
        setSize ( 720, 490 );
        setLocationRelativeTo ( null );
        setDefaultCloseOperation ( EXIT_ON_CLOSE );
        ImageIcon iconoventana = new ImageIcon ( getClass ().getResource ( "/iconoventana.png" ) );
        setIconImage ( iconoventana.getImage () );

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
            this.anhadir_elementos_ventana_de_Juego ( );

        } );

    }

    private void anhadir_elementos_ventana_de_Juego ( ) {
        this.anhadir_propiedades_Ventana ( true, new BorderLayout ( ) );

        crearMenu ( );

        this.add ( tablero = new Tablero ( this, 3, 4 ), BorderLayout.CENTER );

        JPanel panelInferior = new JPanel ( );
        panelInferior.setLayout ( new FlowLayout ( FlowLayout.CENTER, 50, 30 ) );
        panelInferior.setBackground ( Color.YELLOW );


        Font       fuente = new Font ( "Agency FB", Font.BOLD, 20 );
        LineBorder borde  = new LineBorder ( Color.BLACK, 4 );

        JLabel lbl_intentos = new JLabel ( "  Intentos  " );
        lbl_intentos.setFont ( fuente );
        lbl_intentos.setBorder ( borde );


        lbl_contador_1 = new JLabel ("  00  " );
        lbl_contador_1.setFont ( fuente );
        lbl_contador_1.setBorder ( borde );


        JLabel lbl_parejas = new JLabel ( "  Parejas  " );
        lbl_parejas.setFont ( fuente );
        lbl_parejas.setBorder ( borde );


        lbl_contador_2 = new JLabel ( "  " + Integer.toString ( (tablero.getFilas ( ) * tablero.getColumnas ( ) / 2) ) + "  " );
        lbl_contador_2.setFont ( fuente );
        lbl_contador_2.setBorder ( borde );

        ImageIcon relojIcon = new ImageIcon ( getClass ( ).getResource ( "/reloj.png" ) );
        JLabel    lbl_reloj = new JLabel ( );
        lbl_reloj.setIcon ( relojIcon );

        JLabel lbl_contador_3 = new JLabel ( " 000 " );
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

    private void crearMenu ( ) {
        JMenuBar barra    = new JMenuBar ( );
        JMenu    opciones = new JMenu ( "Opciones" );
        JMenu    partida  = new JMenu ( "Partida" );

        barra.add ( partida );
        barra.add ( opciones );
        this.setJMenuBar ( barra );

        JMenuItem nuevaPartidaItem        = new JMenuItem ( "Nueva partida");
        JMenuItem guardarPartidaItem      = new JMenuItem ("Guardar partida" );
        JMenuItem cargarPartidaItem       = new JMenuItem ( "Cargar partida");
        JMenuItem salirItem               = new JMenuItem ( "Salir");
        JMenuItem almacenarResultadosItem = new JMenuItem ( "Almacenar Resultados");
        JMenuItem nivelDificultadItem        = new JMenuItem ( "Nivel de dificultad");

        nuevaPartidaItem.setIcon ( new ImageIcon ( getClass ().getResource ( "/nuevo.png" ) ) );
        guardarPartidaItem.setIcon ( new ImageIcon ( getClass ().getResource ( "/guardar.png" ) ) );
        cargarPartidaItem.setIcon ( new ImageIcon ( getClass ().getResource ( "/abrir.png" ) ) );
        salirItem.setIcon ( new ImageIcon ( getClass ().getResource ( "/salir.png" ) ) );
        nivelDificultadItem.setIcon ( new ImageIcon ( getClass ().getResource ( "/cambiarDificultad.png" ) ) );

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
        //FUNCIONES BOTONES
    }


}
