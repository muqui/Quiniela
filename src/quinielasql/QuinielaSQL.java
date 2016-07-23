/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quinielasql;

import controlador.ControladorCrearQuiniela;
import controlador.ControladorPrincipal;

import vista.VistaPrincipal;

/**
 *
 * @author mq12
 */
public class QuinielaSQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //Modelo
        
        //Vista
        VistaPrincipal Vistaprincipal = new VistaPrincipal();
      
                
        
        //controlador
        ControladorPrincipal controladorPrincopal = new ControladorPrincipal(Vistaprincipal); 
        ControladorCrearQuiniela controladorCrearQuiniela = new ControladorCrearQuiniela(Vistaprincipal);
//        ControladorCapturarResult ControladorJugar  = new ControladorCapturarResult(principal);
//        ControladorGanadores controladorGanadores = new ControladorGanadores(principal);
        //Metodos
        controladorPrincopal.iniciar();
    }
    
}
