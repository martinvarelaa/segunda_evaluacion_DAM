package com.liceolapaz.des.MVL;

import javax.swing.*;

public class Cronometro extends Thread {

    private JLabel lblContador;
    private int seg;
    private boolean puedeEjecutarse = true;

    public Cronometro ( JLabel lblContador, int seg ) {
        super ( );
        this.lblContador = lblContador;
        this.seg = seg;
    }

    public void run ( ) {

        while (puedeEjecutarse) {
            try {
                this.sleep ( 1000 );
                seg++;
                formatoTiempo ( seg );
                String cronometro = (formatoTiempo ( seg ));
                lblContador.setText ( cronometro );

            } catch (InterruptedException e) {
            }

        }

    }

    protected void parar ( ) {
        puedeEjecutarse = false;
    }

    protected String formatoTiempo ( int tiempoSegs ) {
        int    hrs              = tiempoSegs / 3600;
        int    segRestantes     = seg - hrs * 3600;
        int    min              = segRestantes / 60;
        int    seg              = segRestantes - min * 60;
        String tiempoFormateado = "";
        if (hrs < 10) {
            tiempoFormateado += "0";
        }
        tiempoFormateado += hrs + ":";
        if (min < 10) {
            tiempoFormateado += "0";
        }
        tiempoFormateado += min + ":";
        if (seg < 10) {
            tiempoFormateado += "0";
        }
        tiempoFormateado += seg;

        return tiempoFormateado;

    }

}
