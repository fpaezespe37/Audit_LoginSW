/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import ec.edu.monster.controlador.EnviarCorreo;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class Correo {
    private String usuarioCorreo;
    private String contrasenia;
    private String rutaArchivo;
    private String nombreArchivo;
    private String destino;
    private String asunto;
    private String mensaje;
    
     /*@return the usuarioCorreo
     */
    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    /**
     * @param usuarioCorreo the usuarioCorreo to set
     */
    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return the rutaArchivo
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * @param rutaArchivo the rutaArchivo to set
     */
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
 
    
    public int enviarCorreo(String asunto, String mensaje, String correo)
    {
        Correo c = new Correo();
        c.setContrasenia("dyshchpbjxywuauz");
        c.setUsuarioCorreo("monster.proyecto@gmail.com");
        c.setAsunto(asunto);
        c.setMensaje(mensaje);
        c.setDestino(correo.trim());
        EnviarCorreo co = new EnviarCorreo ();
        if(co.enviarCorreo(c))
        {
            return 1;
        }
        else
        {
           return 0;
        }
    }
}
