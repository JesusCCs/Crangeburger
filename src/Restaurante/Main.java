package Restaurante;

import Hilo.Cliente;
import Hilo.Cocinero;
import SeccionCritica.Dispensador;

public class Main {

    private static final int NUM_CLIENTES = 4;
    private static final int NUM_COCINEROS = 1;

    public static void main(String[] args) {

        Dispensador dispensador = new Dispensador();

        Cocinero[] cocineros = new Cocinero[NUM_COCINEROS];
        Cliente[] clientes = new Cliente[NUM_CLIENTES];

        for (int i = 0; i < NUM_COCINEROS; i++) {
            cocineros[i] = new Cocinero(dispensador,i);
        }

        for (int i = 0; i < NUM_CLIENTES; i++) {
            clientes[i] = new Cliente(dispensador,i);
        }

        dispensador.setClientes(clientes);

        for (Cocinero cocinero : cocineros) {
            cocinero.start();
        }

        for (Cliente cliente : clientes) {
            cliente.start();
        }
    }
}
