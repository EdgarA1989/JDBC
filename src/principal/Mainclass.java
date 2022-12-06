/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.logging.Logger;
import java.util.logging.Level;
import principal.dominio.usuario.UsuarioServicio;


/**
 *
 * @author EANDRADA
 */
public class Mainclass {

    /**
     * @param args the command line arguments
     */
    
 
    public static void main(String[] args) {
        UsuarioServicio usuarioservicio = new UsuarioServicio();
           
        try {
            usuarioservicio.crearUsuario("edgar.andrada@gmail.com", "12345678");
        } catch (Exception ex) {
            Logger.getLogger(Mainclass.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    
    
    
}
