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
public class Partido {

    private int idpartidos;
    private String resultado;
    private String local;
    private String visitante;
    //private int quiniela_idquiniela;
    private int golesLocal;
    private int golesVisita;
    public Partido(){
    
    }
      public Partido( String resultado, String local, String visitante, int golesLocal, int golesVisita ) {
      
       this.resultado = resultado;
       this.local = local;
       this.visitante = visitante;
       this.golesLocal = golesLocal;
       this.golesVisita = golesVisita;
    }
    /**
     * @return the idpartidos
     */
    public int getIdpartidos() {
        return idpartidos;
    }

    /**
     * @param idpartidos the idpartidos to set
     */
    public void setIdpartidos(int idpartidos) {
        this.idpartidos = idpartidos;
    }

    /**
     * @return the resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * @return the visitante
     */
    public String getVisitante() {
        return visitante;
    }

    /**
     * @param visitante the visitante to set
     */
    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

   

    /**
     * @return the golesLocal
     */
    public int getGolesLocal() {
        return golesLocal;
    }

    /**
     * @param golesLocal the golesLocal to set
     */
    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    /**
     * @return the golesVisita
     */
    public int getGolesVisita() {
        return golesVisita;
    }

    /**
     * @param golesVisita the golesVisita to set
     */
    public void setGolesVisita(int golesVisita) {
        this.golesVisita = golesVisita;
    }
}
