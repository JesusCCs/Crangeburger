package Hilo;

import SeccionCritica.Dispensador;

import java.util.Random;

public class Cliente extends Thread {

    private final Dispensador dispensador;
    private final int ID;

    private static final int MIN = 1000;
    private static final int MAX = 3000;

    public Cliente(Dispensador dispensador, int ID) {
        this.dispensador = dispensador;
        this.ID = ID;
    }

    @Override
    public void run() {
        while (true) {
            ejecutarAccion();
        }
    }

    private void ejecutarAccion() {
        dispensador.consumirHamburguesa(this);

        try {
            Random r = new Random();
            Thread.sleep(r.nextInt(MAX - MIN) + MIN);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getID() {
        return ID;
    }
}
