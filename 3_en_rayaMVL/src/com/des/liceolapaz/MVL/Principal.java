package com.des.liceolapaz.MVL;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {


        jugar();

    }

    private static void jugar() {

        char jugador_1 = 'X';
        char jugador_2 = 'O';
        char defecto = '-';
        char[][] tablero = new char[3][3];
        boolean turno = true;


        int fila = 0;
        int columna = 0;

        mostrarTableroVacio(tablero, defecto);
        while (true) {
            mostrarTurno(turno);

            while (true) {
                columna = pedirJugada("Seleccione fila: ");

                fila = pedirJugada("Seleccione columna: ");

                if (validarPosicion(tablero, defecto, fila, columna)) {
                    break;

                }
                continue;
            }

            insertarFicha(tablero, fila, columna, jugador_1, jugador_2, turno);

            mostrarTableroEnPartida(tablero);

            if (hayUnGanadorPorColumna(tablero, fila, columna, defecto) || hayUnGanadorPorDiagonalPrincipal(tablero, defecto, fila, columna) || hayUnGanadorPorFila(tablero,columna,fila,defecto)
            || hayUnGanadorPorDiagonalInversa(tablero,defecto,fila,columna) || matrizLlena(tablero,defecto)) {
                if (turno){
                    System.out.println("Ha ganado el Jugador 1!");
                    break;
                }else{
                    System.out.println("Ha ganado el Jugador 2!");
                    break;
                }


            }


            turno = !turno;

        }
    }

    private static void mostrarTurno(boolean turno) {
        if (turno) {
            System.out.println("Le toca al jugador 1");

        } else {
            System.out.println("Le toca al jugador 2");

        }

    }

    private static int pedirJugada(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mensaje);
        return sc.nextInt();

    }

    private static void mostrarTableroVacio(char[][] tablero, char defecto) {

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = defecto;
                System.out.print(tablero[i][j] + " ");

            }
            System.out.println(" ");

        }
    }

    private static boolean validarPosicion(char[][] tablero, char defecto, int fila, int columna) {


        if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == defecto) {
            return true;
        } else {
            System.out.println("Seleccione una posición válida! ");
        }


        return false;


    }

    private static void insertarFicha(char[][] matriz, int fila, int columna, char jugador1, char jugador2, boolean turno) {
        if (turno) {
            matriz[fila][columna] = jugador1;

        } else {
            matriz[fila][columna] = jugador2;
        }
    }

    private static void mostrarTableroEnPartida(char[][] tablero) {

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {

                System.out.print(tablero[i][j] + " ");

            }
            System.out.println("");

        }
    }

    private static boolean hayUnGanadorPorFila(char[][] tablero, int columna, int fila, char defecto) {

        if (tablero[0][columna] == tablero[1][columna] && tablero[1][columna] == tablero[2][columna] && tablero[fila][columna] != defecto) {
            return true;

        } else
            return false;
    }

    private static boolean hayUnGanadorPorColumna(char[][] tablero, int fila, int columna, char defecto) {

        if (tablero[fila][0] == tablero[fila][1] && tablero[fila][1] == tablero[fila][2] && tablero[fila][columna] != defecto) {
            return true;

        } else
            return false;
    }

    private static boolean hayUnGanadorPorDiagonalPrincipal(char[][] tablero, char defecto, int fila, int columna) {

        if (tablero[0][0] != defecto && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true;

        } else {
            return false;
        }

    }

    private static boolean hayUnGanadorPorDiagonalInversa(char[][] tablero, char defecto, int fila, int columna) {

        if ((tablero[0][2] == tablero[1][1]) && (tablero[1][1] == tablero[2][0]) && (tablero[2][0] != defecto)) {
            return true;

        } else {
            return false;
        }

    }

    private static boolean matrizLlena(char[][] tablero, char defecto){
        for (int i = 0; i <tablero.length ; i++) {
            for (int j = 0; j <tablero.length ; j++) {
               if (tablero[i][j] == defecto){
                   return false;
                }
            }
        }
return true;
    }


    }





