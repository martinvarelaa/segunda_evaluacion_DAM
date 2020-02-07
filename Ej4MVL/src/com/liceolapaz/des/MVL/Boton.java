package com.liceolapaz.des.MVL;

import javax.swing.*;
import java.awt.*;

public class Boton extends JButton {
    private int fila;
    private int columna;
    private int numeroBoton;



    private boolean pulsado;
    private Tablero tablero;





    public Boton ( Tablero tablero, int fila, int columna, int numeroBoton, boolean pulsado ) {
        this.fila = fila;
        this.columna = columna;
        this.numeroBoton = numeroBoton;
        this.pulsado = pulsado;
        this.tablero = tablero;
        setNumeroBoton ( numeroBoton );
        setBackground ( Color.PINK );

        pulsar ( );
    }



    private void pulsar ( ) {
        this.addActionListener ( actionEvent -> {
            if (!this.pulsado) {
                pulsado = true;
                setBackground ( Color.blue );
                tablero.hayPareja ( this );
                if (pulsado){
                    this.setEnabled ( false );
                }




            }
        } );


    }

    public int getFila ( ) {
        return fila;
    }

    public void setFila ( int fila ) {
        this.fila = fila;
    }

    public int getColumna ( ) {
        return columna;
    }

    public void setColumna ( int columna ) {
        this.columna = columna;
    }

    public int getNumeroBoton ( ) {
        return numeroBoton;
    }

    public void setNumeroBoton ( int numeroBoton ) {
        this.numeroBoton = numeroBoton;
    }

    public boolean isPulsado ( ) {
        return pulsado;
    }

    public void setPulsado ( boolean pulsado ) {
        this.pulsado = pulsado;
    }




}