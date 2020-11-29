package SeccionCritica;

import Hilo.Cliente;

public class Dispensador {

    private int tray;

    public Dispensador() {
        tray = 0;
    }

    public synchronized void hacerHamburguesa(int cocineroID) {
        tray++;
        System.out.println("Cocinero " + cocineroID + " hace hamburguesa. Hamburguesas en el tray: " + tray);
        notify();
    }

    public synchronized void consumirHamburguesa(Cliente cliente) throws InterruptedException {
        System.out.println("Cliente " + cliente.getID() + " tiene hambre. Hamburguesas en el tray: " + tray);

        while (tray == 0) {
            System.out.println("Cliente " + cliente.getID() + " no puede comer.");
            wait();
        }

        tray--;

        System.out.println("Cliente " + cliente.getID() + " come una cangreburguer. Hamburguesas en el tray: " + tray);
        notify();
    }
}
