/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.AgendaSurService_Service;
import client.Comentario;
import client.Evento;
import client.Tag;
import client.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Adrián
 */
@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/AgendaSur-war/agendaSurService.wsdl")
    private AgendaSurService_Service service;

    
    private List<Evento> listaEventoAMostrar;
    private List<Tag> listaTagsAMostrar;
    private Usuario usuario = findUsuarioService("cardenitas96@gmail.com");
    private Tag tag;
    private Evento evento;
    private boolean meGusta = false;
    private List<Comentario> listaComentario;

    
    
    /**
     * Creates a new instance of SessionBean
     */
    public SessionBean() {
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Tag> getListaTagsAMostrar() {
        return listaTagsAMostrar;
    }

    public void setListaTagsAMostrar(List<Tag> listaTagsAMostrar) {
        this.listaTagsAMostrar = listaTagsAMostrar;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public boolean isMeGusta() {
        return meGusta;
    }

    public void setMeGusta(boolean meGusta) {
        this.meGusta = meGusta;
    }

    public List<Comentario> getListaComentario() {
        return listaComentario;
    }

    public void setListaComentario(List<Comentario> listaComentario) {
        this.listaComentario = listaComentario;
    }
    
    public List<Evento> getListaEventoAMostrar() {
        return listaEventoAMostrar;
    }

    public void setListaEventoAMostrar(List<Evento> listaEventoAMostrar) {
        this.listaEventoAMostrar = listaEventoAMostrar;
    }
    /*
    EVENTOS
    */
    public String findEventosNoCaducadosYValidados(){
        this.listaEventoAMostrar = findEventosNoCaducadosYValidadosService();
        return "listadoEventos";
    }
    
    public String findEvetnosNoValidados(){
        this.listaEventoAMostrar = findEventosNoValidadosService();
        return "listadoEventos";
    }
    
    public String findEventosOrdenadosPorDistancia(double longitud, double latitud){
        this.listaEventoAMostrar = findEventosOrdenadosPorDistanciaService(longitud, latitud);
        return "listadoEventos";
    }
    
    public String findEventosByTag(){
        this.listaEventoAMostrar = findEventosByTagService(this.findAllTagService().get(0));
        return "listadoEventos";
    }
    /*
    TAGS
    */
    
    public String findAllTags(){
        this.listaTagsAMostrar = findAllTagService();
        return "listadoTags";
    }
    
    public String findTagsEvento(){
        this.listaTagsAMostrar = this.findTagsEventoService(
                this.listaEventoAMostrar.get(this.listaEventoAMostrar.size()-1).getId());
        return "listadoTags";
    }
    
    public String findTagsUsuario(){
        this.listaTagsAMostrar = this.findTagsUsuarioService(usuario.getEmail());
        return "listadoTags";
    }
    
    public String findTag(){
        this.tag = this.findTagService("Sport");
        return null;
    }
    
    public String findUsuario(){
        this.usuario = this.findUsuarioService("david_92.8@hotmail.com");
        return null;
    }
    
    public String findEvento(){
        this.evento = this.findEventoService(1);
        return null;
    }
    
    public String findComentariosEvento(){
        this.listaComentario = this.findComentariosEventoService(1);
        return "listadoComentarios";
    }
    
    public String existeMeGusta(){
        meGusta = this.existeMeGustaService(listaEventoAMostrar.get(0), usuario);
        return null;
    }
    
    
    
    /*
    MÉTODOS DEL SERVICIO
    */

    private java.util.List<client.Evento> findEventosNoCaducadosYValidadosService() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.findEventosNoCaducadosYValidados();
    }

    private java.util.List<client.Evento> findEventosNoValidadosService() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.findEventosNoValidados();
    }

    private java.util.List<client.Evento> findEventosOrdenadosPorDistanciaService(double arg0, double arg1) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.findEventosOrdenadosPorDistancia(arg0, arg1);
    }

    private java.util.List<client.Evento> findEventosByTagService(client.Tag arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.findEventosByTag(arg0);
    }

    
    
    private java.util.List<client.Tag> findAllTagService() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.findAllTag();
    }

    private Tag findTagService(java.lang.Object id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.findTag(id);
    }

    private Evento findEventoService(java.lang.Object id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.findEvento(id);
    }

    private Usuario findUsuarioService(java.lang.Object id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.findUsuario(id);
    }

    private java.util.List<client.Tag> findTagsEventoService(int arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.findTagsEvento(arg0);
    }

    private java.util.List<client.Tag> findTagsUsuarioService(java.lang.String arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.findTagsUsuario(arg0);
    }

    private java.util.List<client.Comentario> findComentariosEventoService(int arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.findComentariosEvento(arg0);
    }

    private boolean existeMeGustaService(client.Evento arg0, client.Usuario arg1) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        return port.existeMeGusta(arg0, arg1);
    }

    
    
    
    
    
}
