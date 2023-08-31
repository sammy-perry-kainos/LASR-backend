package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.api.DeliveryService;
import org.kainos.ea.cli.DeliveryRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.AuthValidator;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.DeliveryDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Team SCM DeliveryEmployee API")
@Path("/api")
public class DeliveryController {
    private DeliveryService deliveryService = new DeliveryService(new DeliveryDao(new DatabaseConnector()));

    private AuthService authService = new AuthService(new AuthDao(new DatabaseConnector()),
            new AuthValidator(new AuthDao(new DatabaseConnector())));

    @POST
    @Path("/deliveryemployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployee(DeliveryRequest deliveryRequest, @QueryParam("token") String token) {
        try {
            if (!authService.isAdmin(token) && !authService.isHR(token)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        } catch (FailedToVerifyTokenException e) {
            return Response.serverError().build();
        }

        try {
            return Response.ok(deliveryService.createDeliveryEmployee(deliveryRequest)).build();
        } catch (FailedToCreateDeliveryEmployeeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/deliveryemployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response updateDeliveryEmployee(@PathParam("id") int id, DeliveryRequest deliveryRequest, @QueryParam("token") String token) {
        try {
            if (!authService.isAdmin(token) && !authService.isHR(token)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        } catch (FailedToVerifyTokenException e) {
            return Response.serverError().build();
        }

        try {
            deliveryService.updateDeliveryEmployee(id, deliveryRequest);
            return Response.ok().build();
        } catch (DeliveryEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/deliveryemployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getDeliveryEmployeeById(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            if (!authService.isAdmin(token) && !authService.isHR(token)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        } catch (FailedToVerifyTokenException e) {
            return Response.serverError().build();
        }

        try {
            return Response.ok(deliveryService.getDeliveryEmployeeById(id)).build();
        } catch (FailedToGetDeliveryEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (DeliveryEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();

        }
    }

    @GET
    @Path("/deliveryemployees")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getDeliveryEmployees(@QueryParam("token") String token) {
        try {
            if (!authService.isAdmin(token) && !authService.isHR(token)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        } catch (FailedToVerifyTokenException e) {
            return Response.serverError().build();
        }

        try {
            return Response.ok(deliveryService.getAllDeliveryEmployees()).build();
        } catch (FailedToGetDeliveryEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/deliveryemployees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeliveryEmployee(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            if (!authService.isAdmin(token) && !authService.isHR(token)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        } catch (FailedToVerifyTokenException e) {
            return Response.serverError().build();
        }

        try {
            deliveryService.deleteDeliveryEmployee(id);

            return Response.ok().build();
        } catch (DeliveryEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteDeliveryEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}

