/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.dominio.usuario;

import java.util.Collection;
import principal.persistencia.usuarioDAO;

/**
 *
 * @author EANDRADA
 */
public class UsuarioServicio {
    
    private usuarioDAO dao; // CREO LA RELACION ENTRE EL SERVICIO Y USUARIO DAO

    public UsuarioServicio(usuarioDAO dao) { //CONSTRUCTOR DAO
        this.dao = dao;
    }

    public UsuarioServicio() {
    }
    
    
        public void crearUsuario(String correoElectronico, String clave) throws Exception { //VALIDO SI LOS DATOS SON CORRETOS USANDO EXCEPCIONES SI TODO ESTABA BIEN SE CREA EL USUARIO

        try {
            //Validamos
            if (correoElectronico == null || correoElectronico.trim().isEmpty()) { //SI CORREO ES NULLO O ESTA VACIO
                throw new Exception("Debe indicar el correo electrónico");
            }
            if (correoElectronico.contains("@") == false) { // SI NO CONTIENE @ ERROR
                throw new Exception("El correo electrónico es incorrecto");
            }
            if (clave == null || clave.trim().isEmpty()) { 
                throw new Exception("Debe indicar la clave");
            }
            if (clave.length() < 8) { //DEBE TENER AL MENO 8 CARACTERES LA CLAVE
                throw new Exception("La clave no puede tener menos de 8 caracteres");
            }
            if (buscarUsuarioPorCorreoElectronico(correoElectronico) != null) { //VALIDO SI AL BUSCAR USUARIO POR EL CORREO SI EXISTE ME LO INDICA TMB SE PUEDE HACER DESDE LA BASE DE DATOS CON UNIQUE
                throw new Exception("Ya existe un usuario con el correo electrónico indicado " + correoElectronico);
            }

            //Creamos el usuario
            Usuario usuario = new Usuario();
            usuario.setCorreoElectronico(correoElectronico);
            usuario.setClave(clave);
            dao.guardarUsuario(usuario); //LLAMO A METODO PADRE PARA INSERTAR EL USARIO EN LA BASE DE DATOS CON EL METODO USANDO LA VARIABLE CREADA dao
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarClaveUsuario(String correoElectronico, String claveActual, String nuevaClave) throws Exception {

        try {

            //Validamos
            if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
                throw new Exception("Debe indicar el usuario");
            }

            if (claveActual == null || claveActual.trim().isEmpty()) {
                throw new Exception("Debe indicar la clave actual");
            }

            if (nuevaClave == null || nuevaClave.trim().isEmpty()) {
                throw new Exception("Debe indicar la clave nueva");
            }

            //Buscamos
            Usuario usuario = buscarUsuarioPorCorreoElectronico(correoElectronico);

            //Validamos
            if (!usuario.getClave().equals(claveActual)) {
                throw new Exception("La clave actual no es la regsitrada en el sistema para el correo electrónico indicado");
            }

            //Modificamos
            usuario.setClave(nuevaClave);

            dao.modificarUsuario(usuario);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarUsuario(String correoElectronico) throws Exception {

        try {

            //Validamos 
            if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
                throw new Exception("Debe indicar el correo electrónico");
            }

            dao.eliminarUsuario(correoElectronico);
        } catch (Exception e) {
            throw e;
        }
    }

    public Usuario buscarUsuarioPorCorreoElectronico(String correoElectronico) throws Exception {

        try {

            //Validamos
            if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
                throw new Exception("Debe indicar el correo electrónico");
            }

            Usuario usuario = dao.buscarUsuarioPorCorreoElectronico(correoElectronico);

            return usuario;
        } catch (Exception e) {
            throw e;
        }
    }

    public Usuario buscarUsuarioPorId(Integer id) throws Exception {

        try {

            //Validamos
            if (id == null) {
                throw new Exception("Debe indicar el id");
            }

            Usuario usuario = dao.buscarUsuarioPorId(id);

            return usuario;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Usuario> listarUsuario() throws Exception {

        try {

            Collection<Usuario> usuarios = dao.listarUsuarios();

            return usuarios;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirUsuarios() throws Exception {

        try {

            //Listamos los usuarios
            Collection<Usuario> usuarios = listarUsuario();

            //Imprimimos los usuarios
            if (usuarios.isEmpty()) {
                throw new Exception("No existen usuarios para imprimir");
            } else {
                for (Usuario u : usuarios) {
                    System.out.println(u);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
    

