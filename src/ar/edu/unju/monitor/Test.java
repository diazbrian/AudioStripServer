package ar.edu.unju.monitor;

public class Test {
    public static void main(String[] args) {
        AudioStripServer audioStripServer = new AudioStripServer();

        // se crean y se lanzan los usuarios (hilos)
        for (int i = 1; i <= 10; i++) { // bucle for con 10 usuarios
            new Thread(new Usuario(i, audioStripServer)).start();
        }
    }
}
