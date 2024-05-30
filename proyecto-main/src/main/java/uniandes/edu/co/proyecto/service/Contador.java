package uniandes.edu.co.proyecto.service;

import java.util.Random;

public class Contador {
    private static int contadorId1 = 0;
    private static int contadorId2 = 0;
    private static int contadorId3 = 0;
    private static int contadorId4 = 0;
    private static int contadorId5 = 0;
    private static int contadorId6 = 0;
    private static int contadorId7 = 0;


    static {
        Random random = new Random();
        contadorId1 = random.nextInt(100000);
        contadorId2 = random.nextInt(100000);
        contadorId3 = random.nextInt(100000);
        contadorId4 = random.nextInt(100000);
        contadorId5 = random.nextInt(100000);
        contadorId6 = random.nextInt(100000);
        contadorId7 = random.nextInt(100000);
    }

    public static int generarIdOficina() {
        return ++contadorId1;
    }

    public static int generarNumPrestamo() {
        return ++contadorId4;
    }

    public static int generarNumCuenta() {
        return ++contadorId3;
    }

    public static int generarIdPuntoAtencion() {
        return ++contadorId2;
    }

    public static int generarIdOperacionCuenta() {
        return ++contadorId5;
    }

    public static int generarIdOperacionPrestamo() {
        return ++contadorId6;
    }

    public static int generarIdOperacionTransferencia() {
        return ++contadorId7;
    }
}
