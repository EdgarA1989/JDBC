/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import principal.dominio.usuario.Usuario;

/**
 *
 * @author EANDRADA
 */
public final class usuarioDAO extends DAO{
    
    public void guardarUsuario(Usuario usuario) throws Exception { //Se envia usuario comoa rgumento donde toma los datos
        try {
            if (usuario == null) { //VALIDADAR USUARIO 
                throw new Exception("Debe indicar el usuario");
            }

            String sql = "INSERT INTO Usuario (correoElectronico, clave)" //GENERO MI VARIABLE STRING SENTENCIA DE SQL
                    + "VALUES ( '" + usuario.getCorreoElectronico() + "' , '" + usuario.getClave() + "' );";
            //PLASMO MI QUERY EN LA BD
            
            insertarModificarEliminar(sql); //METODO HEREDADO DEL PADRE DAO
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
     public void modificarUsuario(Usuario usuario) throws Exception {
        try {
            if (usuario == null) {
                throw new Exception("Debe indicar el usuario que desea modificar");
            }

            String sql = "UPDATE Usuario SET "
                    + "clave = '" + usuario.getClave() + "' WHERE correoElectronico = '" + usuario.getCorreoElectronico() + "'";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
     public void eliminarUsuario(String correEletronico) throws Exception {
        try {

            String sql = "DELETE FROM Usuario WHERE correoElectronico = '" + correEletronico + "'";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
     public Usuario buscarUsuarioPorCorreoElectronico(String correoElectronico) throws Exception {
        try {

            String sql = "SELECT * FROM Usuario "
                    + " WHERE correoElectronico = '" + correoElectronico + "'";

            consultarBase(sql); //LE PASO EL SCRIPT QUE CREE

            Usuario usuario = null; //NECESITO MOLDE DE USUARIO PARA RELLENARLO A CONTINUACION
            while (resultado.next()) { //SI RESULTADO LOGRO CAPTAR ALGO
                usuario = new Usuario();
                usuario.setId(resultado.getInt(1)); //SE PUEDE COLOCAR EL NUMERO DE COLUMNA O "id" NOMBRE DE COLUMNA
                usuario.setCorreoElectronico(resultado.getString(2));
                usuario.setClave(resultado.getString(3));
            }
            desconectarBase();
            return usuario;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
     }
        
    public Usuario buscarUsuarioPorId(Integer id) throws Exception {
        try {

            String sql = "SELECT * FROM Usuario "
                    + " WHERE id = '" + id + "'";

            consultarBase(sql);

            Usuario usuario = null;
            while (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getInt(1));
                usuario.setCorreoElectronico(resultado.getString(2));
                usuario.setClave(resultado.getString(3));
            }
            desconectarBase();
            return usuario;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Collection<Usuario> listarUsuarios() throws Exception {//PARA CONSULTAR SOBRE VARIOS USUARIOS AL MISMO TIEMPO GENERO UN ARRAYLIST
        try {
            String sql = "SELECT correoElectronico, clave FROM Usuario; "; // CONSULTO SOBRE AMBOS DATOS QUERY NATIVA

            consultarBase(sql);

            Usuario usuario = null;
            Collection<Usuario> usuarios = new ArrayList();
            while (resultado.next()) {
                usuario = new Usuario();
                usuario.setCorreoElectronico(resultado.getString(1));//CAMBIA XQ AHORA LA COLUMNA 1 ES CORREOELECTRONICO EN LA QUERY
                usuario.setClave(resultado.getString(2));
                usuarios.add(usuario);
            }
            desconectarBase();
            return usuarios;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }
}



