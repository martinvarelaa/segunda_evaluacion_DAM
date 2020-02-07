package com.liceolapaz.des.MVL;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;


public class Tablero extends JPanel {


    private int filas;
    private int columnas;
    private Boton[][] botonArray;
    private Ventana ventana;
    private int parejas;
    private int contador1 = 0;
    Boton botonAnterior;


    public Tablero ( Ventana ventana, int filas, int columnas ) {
        this.filas = filas;
        this.columnas = columnas;
        this.ventana = ventana;
        this.parejas = (filas*columnas)/2;

        setLayout ( new GridLayout ( filas, columnas ) );
        anhadirBotones ( );
        setBorder ( new LineBorder ( Color.blue, 6 ) );
        setVisible ( true );

    }

    private void anhadirBotones() {
        botonArray = new Boton[this.filas][this.columnas];
        for (int filas = 0; filas < this.filas; filas++) {
            for (int columnas = 0; columnas < this.columnas; columnas++) {
                this.botonArray[filas][columnas] = new Boton ( this, filas, columnas, 0, false );
                this.add ( botonArray[filas][columnas] );
            }
        }
        Random rand = new Random ( );
        int    valor;
        int    i    = 1;
        while (i <= 2) {
            valor = 1;
            while (valor <= (filas * columnas) / 2) {
                int fila    = rand.nextInt ( this.filas );
                int columna = rand.nextInt ( this.columnas );
                if (botonArray[fila][columna].getNumeroBoton ( ) == 0) {
                    botonArray[fila][columna].setNumeroBoton ( valor );
                    botonArray[fila][columna].setText ( Integer.toString ( valor ) );
                    valor++;
                }
            }
            i++;
        }
    }

    protected void hayPareja ( Boton boton ) {


        if (botonAnterior == null) {
             botonAnterior = boton;


        } else {

            if (botonAnterior.getNumeroBoton () == boton.getNumeroBoton ()) {
                botonAnterior = null;
                parejas--;
                contador1++;

            } else {
                            botonAnterior.setPulsado ( false );
                            botonAnterior.setEnabled ( true );
                            boton.setPulsado ( false );
                            boton.setBackground ( Color.pink );
                            botonAnterior.setBackground ( Color.pink );
                    contador1++;
                botonAnterior = null;


            }
            actualizarPanel ( this.ventana );
            hayfin ();

        }


    }

    private void actualizarPanel ( Ventana ventana ) {
        ventana.lbl_contador_1.setText ( " " + Integer.toString ( contador1 ) + " " );
        ventana.lbl_contador_2.setText ( " " + Integer.toString ( parejas ) + " " );



    }

    protected void hayfin(){
     if (parejas == 0){
         System.out.println ("Ganaste" );
     }


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


}
