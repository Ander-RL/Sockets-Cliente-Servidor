package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int PUERTO = 9876;
    private ServerSocket serverSocket; //Socket correspondiente al servidor
    private Socket socket; //Socket correspondiente al cliente
    private int numTareas;
    private Tarea tarea;

    public Server() throws IOException {
        serverSocket = new ServerSocket(PUERTO);
        socket = new Socket();
    }

    // Funcion para iniciar la conexion
    public void iniciarServer() throws IOException {

        // Se mantiene a la espara de los datos
        while (true) {
            System.out.println("Esperando conexion del cliente");
            // Se guarda la peticion que llegue al servidor en el socket
            socket = serverSocket.accept();
            // El servidor se queda a la espera de recibir peticiones

            // Al recibir la peticion, se inicia la conexion
            DataOutputStream salidaCliente = new DataOutputStream(socket.getOutputStream());
            // Envia mensaje al cliente
            salidaCliente.writeUTF("> Bienvenido. ¿Como te llamas?");

            // Recoge mensaje del cliente y se muestra por pantalla
            DataInputStream entradaCliente = new DataInputStream(socket.getInputStream());
            String nombre = entradaCliente.readUTF();
            System.out.println("Usuario: " + nombre);
            salidaCliente.writeUTF("> Hola, " + nombre + ".");

            // Pregunta cuantas tareas deben realizarse
            salidaCliente.writeUTF("> Cuantas tareas deseas realizar?");
            // Recoge la respuesta
            tarea = new Tarea();
            numTareas = Integer.parseInt(entradaCliente.readUTF());
            tarea.setNumTareas(numTareas);

            for (int i = 0; i < numTareas; i++) {
                System.out.println("Tarea " + (i + 1));
                // Manda al cliente mensaje pidiendo describcion tarea
                salidaCliente.writeUTF("> Tarea " + (i + 1) + "\n> Describe la tarea:");

                // Lo recoge y lo mete en el objeto tarea
                boolean respuesta = true;
                String describTarea;
                // Con este bulce nos aseguramos de esperar a la respuesta antes de hacer la siguiente pregunta
                while (respuesta) {
                    describTarea = entradaCliente.readUTF();
                    if (!describTarea.equals("")) {  // Nos aseguramos de que no sea un String vacio
                        tarea.setDescripcion(describTarea);
                        System.out.println("Descripcion recibida: " + describTarea);
                        respuesta = false;
                    } else {
                        respuesta = true;
                    }
                }

                // Manda al cliente mensaje pidiendo estado tarea
                salidaCliente.writeUTF("> Define el estado de la tarea:");

                // Lo recoge y lo mete en el objeto tarea
                respuesta = true;
                String estadoTarea;
                // Este bucle realiza una tarea analoga al anterior
                while (respuesta) {
                    estadoTarea = entradaCliente.readUTF();
                    if (!estadoTarea.equals("")) {
                        tarea.setEstado(estadoTarea);
                        System.out.println("Estado recibido: " + estadoTarea);
                        respuesta = false;
                    }
                }

                // Introducimos la descripcion y el estado en sus correspondientes listas
                tarea.setListaDescripcion(tarea.getDescripcion());
                tarea.setListaEstado(tarea.getEstado());
            }

            System.out.println("Listando tareas...");
            salidaCliente.writeUTF("> Listando tareas...");
            for (int i = 0; i < numTareas; i++) {
                salidaCliente.writeUTF(tarea.toString(i)); // Recuperamos los datos de las listas se envian
            }
        }
    }
}
