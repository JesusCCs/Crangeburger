package SeccionCritica;

import Hilo.Cliente;

public class Dispensador {

    int tray;
    Cliente[] clientes;

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
            cliente.masHambre();
            System.out.println("Cliente " + cliente.getID() + " no puede comer. Hambre: " + cliente.getHambre());
            wait();
        }

        if (!esMasHambriento(cliente)) {
            System.out.println("Cliente " + cliente.getID() + " no puede comer. Hay otro más famélico.");
            notify(); // Notificamos al siguiente ¿?
            return;
        }

        cliente.comer();
        tray--;

        System.out.println("Cliente " + cliente.getID() + " come una cangreburguer. Hamburguesas en el tray: " + tray);
        notify();
    }

    private boolean esMasHambriento(Cliente clienteActual) {
        Cliente clienteConMasHambre = clientes[0];
        for (var cliente : clientes) {
            if (clienteConMasHambre.getHambre() <= cliente.getHambre()) {
                clienteConMasHambre = cliente;
            }
        }

        return clienteActual.getID() == clienteConMasHambre.getID();
    }

    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }
}
