/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.AgendaSurService_Service;
import client.Evento;
import client.Tag;
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
    
    /**
     * Creates a new instance of SessionBean
     */
    public SessionBean() {
    }
    
    public List<Evento> getListaEventoAMostrar() {
        return listaEventoAMostrar;
    }

    public void setListaEventoAMostrar(List<Evento> listaEventoAMostrar) {
        this.listaEventoAMostrar = listaEventoAMostrar;
    }
    
    public void findEventosNoCaducadosYValidados(){
        this.listaEventoAMostrar = findEventosNoCaducadosYValidadosService();
    }
    
    public void findEvetnosNoValidados(){
        this.listaEventoAMostrar = findEventosNoValidadosService();
    }
    
    public void findEventosOrdenadosPorDistancia(double longitud, double latitud){
        this.listaEventoAMostrar = findEventosOrdenadosPorDistanciaService(longitud, latitud);
    }
    
    public void findEventosByTag(Tag tag){
        this.listaEventoAMostrar = findEventosByTagService(tag);
    }
    

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
    
    
    
}
