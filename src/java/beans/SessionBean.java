/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.AgendaSurService_Service;
import client.Comentario;
import client.ComentarioPK;
import client.Evento;
import client.Tag;
import client.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;
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
    private Usuario usuario;
    private Tag tag;
    private Evento evento;
    private boolean meGusta = false;
    private List<Comentario> listaComentario;
    private double longitud;
    private double latitud;

    
    
    /**
     * Creates a new instance of SessionBean
     */
    public SessionBean() {
    }
    
    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
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
    
    public String findEventosOrdenadosPorDistancia(){
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
        this.listaTagsAMostrar = this.findTagsEventoService(1);
        return "listadoTags";
    }
    
    public String findTagsUsuario(){
        this.listaTagsAMostrar = this.findTagsUsuarioService("cardentias96@gmail.com");
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

    /*
    siguiente tandaaaa
    */
    public String editEvento(){
        List<Evento> eventos = this.findEventosNoValidadosService();
        Evento e = eventos.get(0);
        e.setNombre("Nombre cambiado");
        this.editEventoService(e);
        return null;
    }
    
    public String createEvento(){
        Evento e = new Evento();
        e.setNombre("Evento creado");
        e.setDescripcion("evento creado");
        e.setDireccion("evento creado");
        e.setFechainicio("2017-12-05 20:34:01");
        e.setFechafin("2017-12-10 20:35:00");
        e.setLongitud((float) 0.5);
        e.setLatitud((float) 0.6);
        e.setValidado(false);
        e.setCreador(this.findUsuarioService("cardenita96@gmail.com"));
        this.createEventoService(e);
        return null;
    }
    
    public String asignarTagsAEvento(){
        Evento e = findEventosNoValidadosService().get(0);
        String[] tags = {"Sport","Adult"};
        this.asignarTagsAEventoService(e, Arrays.asList(tags));
        return null;
    }
    
    public String asignarTagsAUsuario(){
        String[] tags = {"Sport","Adult"};
        this.asignarTagsAUsuarioService("cardenitas96@gmail.com", Arrays.asList(tags));
        return null;
    }
    
    public String removeEvento(){
        this.removeEventoService(this.findEventosNoValidadosService().get(0));
        return null;
    }
    
    public String darMeGusta(){
        this.darMeGustaService(findEventoService(1), findUsuarioService("david_92.8@hotmail.com"));
        return null;
    }
    
    public String validarEvento(){
        this.validarEventoService(this.findEventosNoValidadosService().get(0).getId());
        return null;
    }
    
    public String createComentario(){
        Comentario comentario = new Comentario();
        comentario.setComentario("Comentario de prueba");
        comentario.setFecha("2017-11-25 10:44:13");
        ComentarioPK pk = new ComentarioPK();
        pk.setEventoId(1);
        pk.setUsuarioEmail("david_92.8@hotmail.com");
        comentario.setComentarioPK(pk);
        comentario.setEvento(findEventoService(1));
        comentario.setUsuario(findUsuarioService("david_92.8@hotmail.com"));
        this.createComentarioService(comentario);
        return null;
    }
    
    public String sendMail(){
        this.sendMailService(1);
        return null;
    } 

    private void editEventoService(client.Evento entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        port.editEvento(entity);
    }

    private void createEventoService(client.Evento entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        port.createEvento(entity);
    }

    private void asignarTagsAEventoService(client.Evento arg0, java.util.List<java.lang.String> arg1) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        port.asignarTagsAEvento(arg0, arg1);
    }

    private void asignarTagsAUsuarioService(java.lang.String arg0, java.util.List<java.lang.String> arg1) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        port.asignarTagsAUsuario(arg0, arg1);
    }

    private void removeEventoService(client.Evento entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        port.removeEvento(entity);
    }
//Mañana seguimos desde aqui************************************
    private void darMeGustaService(client.Evento arg0, client.Usuario arg1) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        port.darMeGusta(arg0, arg1);
    }

    private void validarEventoService(int arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        port.validarEvento(arg0);
    }

    private void createComentarioService(client.Comentario entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        port.createComentario(entity);
    }

    private void sendMailService(int arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        client.AgendaSurService port = service.getAgendaSurServicePort();
        port.sendMail(arg0);
    }
    
    
    
    
}
