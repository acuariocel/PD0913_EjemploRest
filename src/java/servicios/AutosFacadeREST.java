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
import modelo.Autos;
import modelo.Personas;

/**
 *
 * @author INTERNET2
 */
@Stateless
@Path("modelo.autos")
public class AutosFacadeREST extends AbstractFacade<Autos> {
    @PersistenceContext(unitName = "PD0913_EjemploRestPU")
    private EntityManager em;

    public AutosFacadeREST() {
        super(Autos.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Autos entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Autos entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Autos find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public List<Autos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public List<Autos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findForPlaca/placa={pla}")
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public String findForCedulaDatos(@PathParam("pla") String placa) {
        TypedQuery<Autos> q;
        q = getEntityManager().createNamedQuery("Autos.findByPlaca", Autos.class);
        q.setParameter("placa", placa);
        Autos auto;
        try {
            auto = q.getSingleResult();
        } catch (NoResultException e) {
            return false+"";
        }
        return "{\"placa\":\"" + auto.getPlaca()
                + "\",\"color\":\"" + auto.getColor() + "\"}";
    }
    public Autos buscaAuto(String placa) {
        TypedQuery<Autos> q;
        q = getEntityManager().createNamedQuery("Autos.findByPlaca", Autos.class);
        q.setParameter("placa", placa);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    @POST
    @Path("registro")
    @Produces({"text/plain; charset=UTF-8", "application/json"})
    public String createPorParametros(@FormParam("placa") String placa,
            @FormParam("color") String color) {
        Autos auto=buscaAuto(placa);
        if (auto == null) {
            try {
                auto = new Autos(placa, color);
                super.create(auto);
                return true+"";
            } catch (NullPointerException e) {
                return false+"";
            }
        } else {
            return auto.getColor();
        }
    }

}
