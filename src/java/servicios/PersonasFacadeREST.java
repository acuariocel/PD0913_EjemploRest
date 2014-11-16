/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import modelo.Personas;

/**
 *
 * @author INTERNET3
 */
@Stateless
@Path("modelo.personas")
public class PersonasFacadeREST extends AbstractFacade<Personas> {

    @PersistenceContext(unitName = "PD0913_EjemploRestPU")
    private EntityManager em;

    public PersonasFacadeREST() {
        super(Personas.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Personas entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Personas entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public Personas find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public List<Personas> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Personas> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Path("findForCedula/cedula={cedula}")
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public Personas findForCedula(@PathParam("cedula") String cedula) {
        TypedQuery<Personas> q;
        q = getEntityManager().createNamedQuery("Personas.findByCedula", Personas.class);
        q.setParameter("cedula", cedula);
        Personas persona;
        try {
            persona = q.getSingleResult();
        } catch (NoResultException e) {
            persona = null;
        }
        return persona;
    }

    @GET
    @Path("existePorCedula/cedula={cedula}")
    @Produces("text/plain")
    public Boolean existePorCedula(@PathParam("cedula") String cedula) {
        Boolean res=true;
        if (buscaPersona(cedula)==null) {
            res=false;
        }
        return res; 
    }

    @POST
    @Path("registro")
    @Produces({"text/plain", "application/json"})
    public Boolean createPorParametros(@FormParam("cedula") String cedula,
            @FormParam("nombres") String nombres,
            @FormParam("apellidos") String apellidos,
            @FormParam("edad") Integer edad) {
        if (buscaPersona(cedula)==null) {
            try {
                Personas persona = new Personas(cedula, nombres, apellidos, edad, new Date(), new Date());
                super.create(persona);
                return true;
            } catch (NullPointerException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public Personas buscaPersona(String cedula) {
        TypedQuery<Personas> q;
        q = getEntityManager().createNamedQuery("Personas.findByCedula", Personas.class);
        q.setParameter("cedula", cedula);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
