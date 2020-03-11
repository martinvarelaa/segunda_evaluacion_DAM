package com.liceolapaz.des.MVL;

import javax.swing.*;
import java.awt.*;

public class Dialogo extends JDialog {
    private JOptionPane aviso = new JOptionPane ( );

    public String getDificultad ( ) {
        return dificultad;
    }



    private String dificultad = "Media";


    public Dialogo ( Ventana ventana ) {
        JRadioButton btn1     = new JRadioButton ( "Fácil" );
        JRadioButton btn2     = new JRadioButton ( "Medio" );
        JRadioButton btn3     = new JRadioButton ( "Dificil" );
        JRadioButton btn4     = new JRadioButton ( "Personalizado" );
        ButtonGroup  grupo    = new ButtonGroup ( );
        JPanel       panel1   = new JPanel ( );
        JPanel       panel2   = new JPanel ( );
        JPanel       panel3   = new JPanel ( );
        JPanel       panel4   = new JPanel ( );
        JTextField   txt1     = new JTextField ( );
        JTextField   txt2     = new JTextField ( );
        JLabel       lbl1     = new JLabel ( "Filas" );
        JLabel       lbl2     = new JLabel ( "Columnas" );
        JButton      aceptar  = new JButton ( "Aceptar" );
        JButton      cancelar = new JButton ( "Cancelar" );
        ImageIcon    imagen   = new ImageIcon ( getClass ( ).getResource ( "/iconoventana.png" ) );


        this.setIconImage ( imagen.getImage ( ) );
        this.setSize ( 350, 350 );
        this.setLayout ( new GridLayout ( 4, 0 ) );
        this.setDefaultCloseOperation ( DISPOSE_ON_CLOSE );
        this.setLocationRelativeTo ( ventana );

        grupo.add ( btn1 );
        grupo.add ( btn2 );
        grupo.add ( btn3 );
        grupo.add ( btn4 );

        panel1.setLayout ( new FlowLayout ( ) );
        panel1.add ( btn1 );
        panel1.add ( btn2 );
        panel1.add ( btn3 );
        panel1.add ( btn4 );

        panel2.setLayout ( new FlowLayout ( ) );
        panel2.add ( lbl1 );
        txt1.setPreferredSize ( new Dimension ( 90, 20 ) );
        panel2.add ( txt1 );

        panel3.setLayout ( new FlowLayout ( ) );
        panel3.add ( lbl2 );
        txt2.setPreferredSize ( new Dimension ( 90, 20 ) );
        panel3.add ( txt2 );

        panel4.setLayout ( new FlowLayout ( ) );
        panel4.add ( aceptar );
        panel4.add ( cancelar );

        btn1.setSelected ( true );
        if (btn1.isSelected ( )) {
            txt1.setText ( "3" );
            txt2.setText ( "4" );
            txt1.setEnabled ( false );
            txt2.setEnabled ( false );
            dificultad = "Fácil";
        }

        this.add ( panel1, BorderLayout.NORTH );
        panel1.setSize ( 350 / 4, 350 / 4 );
        this.add ( panel2, BorderLayout.CENTER );
        panel2.setSize ( 350 / 4, 350 / 4 );
        this.add ( panel3, BorderLayout.CENTER );
        panel3.setSize ( 350 / 4, 350 / 4 );
        this.add ( panel4, BorderLayout.SOUTH );
        panel4.setSize ( 350 / 4, 350 / 4 );

        txt1.setDisabledTextColor ( Color.DARK_GRAY );
        txt2.setDisabledTextColor ( Color.DARK_GRAY );
        btn1.addActionListener ( actionEvent -> {
            txt1.setText ( "3" );
            txt2.setText ( "4" );
            txt1.setEnabled ( false );
            txt2.setEnabled ( false );
            dificultad = "Fácil";
        } );

        btn2.addActionListener ( actionEvent -> {
            txt1.setText ( "4" );
            txt2.setText ( "5" );
            txt1.setEnabled ( false );
            txt2.setEnabled ( false );
            dificultad = "Media";
        } );

        btn3.addActionListener ( actionEvent -> {
            txt1.setText ( "6" );
            txt2.setText ( "6" );
            txt1.setEnabled ( false );
            txt2.setEnabled ( false );
            dificultad = "Dificil";
        } );


        btn4.addActionListener ( actionEvent -> {
            txt1.setEnabled ( true );
            txt2.setEnabled ( true );
            dificultad = "Personalizada";
        } );


        aceptar.addActionListener ( actionEvent -> {
            if(txt1.getText ( ).equals ( "" ) || txt2.getText ( ).equals ( "" ) || Integer.parseInt ( txt1.getText ( ) ) <= 0 ||  Integer.parseInt ( txt2.getText ( ) ) <= 0  ){
                crearDialogoTablero ( "Introduce datos correctos!", "Error", JOptionPane.ERROR_MESSAGE  );
            }
            else if ((Integer.parseInt ( txt1.getText ( ) ) * Integer.parseInt ( txt2.getText ( ) )) % 2 == 0  ) {
                //Comprobación de que no sea 0 o nulo el valor del texto
                ventana.setFilas ( Integer.parseInt ( txt1.getText ( ) ) );
                ventana.setColumnas ( Integer.parseInt ( txt2.getText ( ) ) );
                ventana.getCr ( ).parar ( );
                ventana.setCr ( null );
                ventana.getContentPane ( ).removeAll ( );
                ventana.repaint ( );
                ventana.getTablero ( ).setParejas ( ventana.getFilas ( ) / ventana.getColumnas ( ) / 2 );
                ventana.anhadir_elementos_ventana_de_Juego ( Integer.parseInt ( txt1.getText ( ) ), Integer.parseInt ( txt2.getText ( ) ), "00", "  " + Integer.toString ( (ventana.getTablero ( ).getFilas ( ) * ventana.getTablero ( ).getColumnas ( ) / 2) ) + "  ", "00:00:00" );
                ventana.getTablero ( ).actualizarPanel ( ventana );
                this.dispose ( );


            } else {
                crearDialogoTablero ( "Las casillas deben dar un número par!", "Error", JOptionPane.ERROR_MESSAGE );
            }


        } );
        cancelar.addActionListener ( actionEvent -> {
            this.dispose ( );

        } );

    }

    protected void crearDialogoTablero ( String mensaje, String titulo, int tipo ) {
        aviso.showMessageDialog ( null, mensaje, titulo, tipo );

    }

    protected int crearDialogoPreguntaTablero ( String mensaje, String titulo ) {
        return aviso.showConfirmDialog ( null, mensaje, titulo, JOptionPane.YES_NO_OPTION );
    }


}
