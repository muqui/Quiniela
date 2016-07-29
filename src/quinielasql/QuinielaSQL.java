/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quinielasql;

import controlador.ControladorAbrirQuiniela;
import controlador.ControladorCrearQuiniela;
import controlador.ControladorJugar;
import controlador.ControladorPrincipal;
import vista.JPanelJugar;
import vista.JPanelCrearQuiniela;
import vista.VistaP;

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
       // VistaPrincipal Vistaprincipal = new VistaPrincipal();
           VistaP vistaPrincipal = new VistaP();
           JPanelCrearQuiniela jpanelCrearQuiniela = new JPanelCrearQuiniela();
           JPanelJugar  jpanelJugar = new JPanelJugar();
        //controlador
        ControladorPrincipal controladorPrincopal = new ControladorPrincipal(vistaPrincipal); 
        ControladorCrearQuiniela controladorCrearQuiniela = new ControladorCrearQuiniela(vistaPrincipal, jpanelCrearQuiniela);
        ControladorAbrirQuiniela ControladorAbrirQuiniela = new ControladorAbrirQuiniela(vistaPrincipal);
        ControladorJugar controladorJugar = new ControladorJugar(vistaPrincipal,jpanelJugar);
//        ControladorCapturarResult ControladorJugar  = new ControladorCapturarResult(principal);
//        ControladorGanadores controladorGanadores = new ControladorGanadores(principal);
        //Metodos
        controladorPrincopal.iniciar();
    }
    
}
