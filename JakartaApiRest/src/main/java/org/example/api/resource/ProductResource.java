package org.example.api.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.Product;
import org.example.services.ProductService;

import java.util.List;

@Path("/products")
public class ProductResource {

//    @Inject
//    private ProductService service;
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response get(){
//
//        List<Product> products = service.listAll();
//        return Response.ok().status(Response.Status.OK).entity(products).build();
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        return Response.ok("[{\"id\":1,\"name\":\"Producto A\"}]").build();
    }
}
