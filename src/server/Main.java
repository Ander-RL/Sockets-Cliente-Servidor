package server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        System.out.println("> Iniciando servidor........");
        // Se inicia el servidor
        server.iniciarServer();
    }
}
