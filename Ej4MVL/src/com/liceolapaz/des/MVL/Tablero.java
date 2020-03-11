package com.liceolapaz.des.MVL;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class Tablero extends JPanel {
    private Ventana ventana;
    Dialogo dialogo = new Dialogo ( null);
    private int filas;
    private int columnas;
    private Boton[][] botonArray;
    private int contador1;
    Boton botonAnterior;
    private int parejas;


    public Tablero ( Ventana ventana, int filas, int columnas ) {
        this.filas = filas;
        this.columnas = columnas;
        this.ventana = ventana;
        this.parejas = (filas * columnas) / 2;

        setLayout ( new GridLayout ( filas, columnas ) );
        anhadirBotones ( this );
        setBorder ( new LineBorder ( Color.blue, 6 ) );
        setVisible ( true );


    }

    public Tablero ( Ventana ventana, int filas, int columnas, Boton[][] nuevosBotones ) {
        this.filas = filas;
        this.columnas = columnas;
        this.ventana = ventana;
        this.parejas = (filas * columnas) / 2;
        this.botonArray = nuevosBotones;
        setLayout ( new GridLayout ( filas, columnas ) );
        setBorder ( new LineBorder ( Color.blue, 6 ) );
        setVisible ( true );

        nuevosBotones = new Boton[this.filas][this.columnas];
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {

                this.botonArray[i][j] = nuevosBotones[i][j];

            }
        }

    }

    private void anhadirBotones ( Tablero tablero ) {
        botonArray = new Boton[this.filas][this.columnas];
        for (int filas = 0; filas < this.filas; filas++) {
            for (int columnas = 0; columnas < this.columnas; columnas++) {
                this.botonArray[filas][columnas] = new Boton ( tablero, filas, columnas, 0, false );
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
                dialogo.crearDialogoTablero ( "Los números destapados son iguales", "Bien hecho", JOptionPane.INFORMATION_MESSAGE );

            } else {
                            botonAnterior.setPulsado ( false );
                            botonAnterior.setEnabled ( true );
                            boton.setPulsado ( false );
                            boton.setBackground ( Color.pink );
                            botonAnterior.setBackground ( Color.pink );
                    contador1++;
                botonAnterior = null;
                dialogo.crearDialogoTablero ( "Los números destapados no son iguales", "Sigue buscando", JOptionPane.INFORMATION_MESSAGE );


            }
            actualizarPanel ( this.ventana );
            hayfin ( );

        }


    }

    protected void actualizarPanel ( Ventana ventana ) {
        ventana.getLbl_contador_1 ( ).setText ( " " + contador1 + " " );
        ventana.getLbl_contador_2 ( ).setText ( " " + parejas + " " );


    }

    protected void hayfin ( ) {
        if (parejas == 0) {

            if (ventana.isAlmacenar ( )){

                FileWriter fw;
                BufferedWriter writer;

                try {
                    fw = new FileWriter ( new File ( "resultados.txt"));
                    writer = new BufferedWriter ( fw );
                    Date       fecha      = new Date ();
                    DateFormat fechayHora = new SimpleDateFormat ( "HH:mm:ss dd/MM/yyyy");
                    writer.write ( "Martín Varela, tiempo: " + ventana.getLbl_contador_3 ().getText () + " ,dificultad: " + dialogo.getDificultad () + " con fecha y hora: "+fechayHora.format ( fecha ) );
                    writer.close ();
                    fw.close ();
                } catch (IOException e) {}

            }

            ventana.getCr ( ).parar ( );
            ventana.setCr ( null );
            dialogo.crearDialogoTablero ( "Has ganado en " + contador1 + " intentos!!!", "Enhorabuena", JOptionPane.INFORMATION_MESSAGE );
            if (dialogo.crearDialogoPreguntaTablero ( "¿Quieres jugar otra partida?", "Fin de partida" ) == JOptionPane.YES_OPTION) {

                ventana.getContentPane ( ).removeAll ( );
                ventana.repaint ( );
                ventana.anhadir_elementos_ventana_de_Juego ( 3, 4, "00", "  " + this.getFilas ( ) * this.getColumnas ( ) / 2 + "  ", "00:00:00" );

            }else{
                System.exit ( 0 );
            }
        }


    }

    public int getFilas ( ) {
        return filas;
    }

    public int getColumnas ( ) {
        return columnas;
    }

    public Boton[][] getBotonArray ( ) {
        return botonArray;
    }

    public int getParejas ( ) {
        return parejas;
    }

    public int getContador1 ( ) {
        return contador1;
    }

    public void setContador1 ( int contador1 ) {
        this.contador1 = contador1;
    }

    public void setParejas ( int parejas ) {
        this.parejas = parejas;
    }


}
