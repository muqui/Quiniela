/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author mq12
 */
public class Jugador {

    private int id;
    private String nombre;
    private String codigo;
    private int aciertos;

    public Jugador(int id, String nombre, String codigo, int aciertos) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.aciertos = aciertos;
    }

    public Jugador() {
       
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the aciertos
     */
    public int getAciertos() {
        return aciertos;
    }

    /**
     * @param aciertos the aciertos to set
     */
    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }
}
