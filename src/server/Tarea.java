package server;

import java.util.ArrayList;

public class Tarea {

    private int numTareas;
    private String descripcion;
    private String estado;
    private ArrayList<String> listaDescripcion = new ArrayList<>();
    private ArrayList<String> listaEstado  = new ArrayList<>();

    public Tarea(){

    }

    public int getNumTareas() {
        return numTareas;
    }

    public void setNumTareas(int numTareas){
        this.numTareas = numTareas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<String> getListaDescripcion() {
        return listaDescripcion;
    }

    public void setListaDescripcion(String descripcion) {
        this.listaDescripcion.add(descripcion);
    }

    public ArrayList<String> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(String estado) {
        this.listaEstado.add(estado);
    }

    @Override
    public String toString() {
        return "Tarea: " + descripcion +
                ", con estado " + estado + ".";
    }

    public String toString(int i) {
        return "Tarea: " + listaDescripcion.get(i) +
                ", con estado " + listaEstado.get(i) + ".";
    }
}
