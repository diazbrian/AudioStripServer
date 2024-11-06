package ar.edu.unju.monitor;

public class AudioStripServer {
    private static final int MAX_USUARIOS = 3;  // la cantidad de  usuarios que soporta el server
    private int usuariosActivos = 0;// contador de usuarios activos

    public void procesarCancion(int usuarioId) {
        // sincronizacion para los usuarios en espera o usuarios activos
        synchronized (this) { // usamos la clase misma como objeto sincronizado
            try {
                // espera a que haya espacio disponible para el usuario
                while (usuariosActivos >= MAX_USUARIOS) {
                    System.out.println("Usuario #" + usuarioId + " esperando para subir su cancion...");
                    wait(); // usuario en espera
                }

                // aumenta la cantidad de usuarios activos en el servidor
                usuariosActivos++;
                System.out.println("Usuario #" + usuarioId + " esta subiendo su cancion. Usuarios activos: " + usuariosActivos);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // procesamiento de la cancion
        try {
            Thread.sleep((int)(Math.random() * 4000 + 2000)); // 2 a 4 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Usuario #" + usuarioId + " termino la transformacion a instrumental.");

        // usamos otro bloque sincronizado para llamar a notify()
        synchronized (this) {
            // disminuye la cantidad de usuarios en el server
            usuariosActivos--;
            System.out.println("Usuario #" + usuarioId + " ha salido. Usuarios activos: " + usuariosActivos);
            notify(); // notificar a un usuario en espera
        }
    }
}
