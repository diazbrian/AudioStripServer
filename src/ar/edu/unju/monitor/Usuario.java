package ar.edu.unju.monitor;

public class Usuario implements Runnable {
    private final int id;
    private final AudioStripServer audioStripServer;

    public Usuario(int id, AudioStripServer audioStripServer) {
        this.id = id;
        this.audioStripServer = audioStripServer;
    }

    // llegada del usuario al servidor
    @Override
    public void run() {
        System.out.println("Usuario #" + id + " quiere subir una cancion.");
        // peticion de proceso de transformacion
        audioStripServer.procesarCancion(id);
    }
}
