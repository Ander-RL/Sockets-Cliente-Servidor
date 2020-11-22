package client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final String DIRECCION = "localhost";
    private final int PUERTO = 9876;
    private Socket socket;

    public Client() throws IOException {
        socket = new Socket(DIRECCION, PUERTO);
    }

    public void iniciarClient() throws IOException {

        // Se inicia la entrada de datos y se muestra la entrada por pantalla
        DataInputStream entradaServidor = new DataInputStream(socket.getInputStream());
        System.out.println("Recibiendo mensaje del servidor....");
        System.out.println(entradaServidor.readUTF());

        // Respuesta al servidor
        DataOutputStream salidaServidor = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        // Pregunta el nombre
        String nombre = scanner.nextLine(); // Se recoge la respuesta del usuario
        salidaServidor.writeUTF(nombre);

        // Recibe saludo
        System.out.println(entradaServidor.readUTF());

        // Pregunta sobre tareas
        System.out.println(entradaServidor.readUTF());
        // Responde a la pregunta
        String numero = scanner.nextLine();
        salidaServidor.writeUTF(numero);
        int tareas = Integer.parseInt(numero);

        for (int i = 0; i < tareas; i++) {
            // Se recibe peticion descripcion tarea i
            System.out.println(entradaServidor.readUTF());
            // Se responde
            boolean respuesta = true;
            do {
                String descripcion = scanner.nextLine();
                if (!descripcion.equals("")) {
                    salidaServidor.writeUTF(descripcion);
                    respuesta = false;
                } else {
                    respuesta = true;
                }
            } while (respuesta);

            // Se recibe peticion estado tarea i
            System.out.println(entradaServidor.readUTF());
            // Se responde
            do {
                String estado = scanner.nextLine();
                if (!estado.equals("")) {
                    salidaServidor.writeUTF(estado);
                    respuesta = false;
                } else {
                    respuesta = true;
                }
            } while (respuesta);
        }
        // Servidor avisa de la impresion de tareas
        System.out.println(entradaServidor.readUTF());

        // Se imprime la lista de tareas en que manda el servidor
        for(int i = 0; i < tareas; i++){
        System.out.println(entradaServidor.readUTF());
        }

        scanner.close();          // Cerramos la entrada por teclado
        salidaServidor.close();  // Cerramos la salida de datos
        entradaServidor.close(); // Cerramos la entrada de datos
        socket.close();          // Cerramos el socket
    }
}
