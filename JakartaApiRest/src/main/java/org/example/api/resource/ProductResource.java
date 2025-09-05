package org.example.api.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.Product;
import org.example.services.ProductService;

import java.util.List;

@Path("/products")
public class ProductResource {

    @Inject
    private ProductService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){

        List<Product> products = service.listAll();
        return Response.ok().status(Response.Status.OK).entity(products).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBy(@PathParam("id") Integer id){

       Product product = service.findById(id);

        return Response.ok() .status(Response.Status.OK).entity(product).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response post(Product product){

        service.save(product);

        return Response.ok() .status(Response.Status.CREATED) .entity(product).build();
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response put(@PathParam("id") Integer id, Product product){

        if(service.findById(id) == null)
            return Response.ok() .status(Response.Status.NOT_FOUND).entity("Producto no encontrado").build();

        product.setId(id);

        service.update(product);

        return Response.ok() .status(Response.Status.OK) .entity(product).build();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response delete(@PathParam("id") Integer id ){

        if(service.findById(id) == null)
            return Response.ok() .status(Response.Status.NOT_FOUND).entity("Producto no encontrado").build();

        service.delete(id);

        return Response.ok() .status(Response.Status.NO_CONTENT).build();
    }
}
