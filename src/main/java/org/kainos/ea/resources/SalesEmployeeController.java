package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.api.SalesEmployeeService;
import org.kainos.ea.cli.SalesEmployee;
import org.kainos.ea.cli.SalesRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.AuthValidator;
import org.kainos.ea.core.SalesEmployeeValidator;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.SalesDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Api("Team SCM SalesEmployee API")
@Path("/api")
public class SalesEmployeeController {

    private final SalesEmployeeService salesEmployeeService = new SalesEmployeeService(new SalesDao(new DatabaseConnector()), new SalesEmployeeValidator());

    private AuthService authService = new AuthService(new AuthDao(new DatabaseConnector()),
            new AuthValidator(new AuthDao(new DatabaseConnector())));

    @POST
    @Path("/SalesEmployee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(SalesRequest employee, @QueryParam("token") String token){
        try {
            if (!authService.isAdmin(token) && !authService.isHR(token)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        } catch (FailedToVerifyTokenException e) {
            return Response.serverError().build();
        }

        try{
            int id = salesEmployeeService.createSales(employee);
            return Response.status(Response.Status.CREATED).entity(id).build();
        } catch (FailedToCreateSalesException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (InvalidSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/SalesEmployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, SalesRequest employee, @QueryParam("token") String token){
        try {
            if (!authService.isAdmin(token) && !authService.isHR(token)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        } catch (FailedToVerifyTokenException e) {
            return Response.serverError().build();
        }

        try{
            salesEmployeeService.updateSalesEmployee(id, employee);

            return Response.ok().build();
        } catch (SalesEmployeeDoesNotExistException | InvalidSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToUpdateSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/SalesEmployee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewSalesEmployee(@PathParam("id") int id, @QueryParam("token") String token){
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
            return Response.ok(salesEmployeeService.viewSalesEmployee(id)).build();
        } catch (SalesEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (FailedToGetSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/SalesEmployees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSalesEmployees(@QueryParam("token") String token){
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
            return Response.ok(salesEmployeeService.getAllSalesEmployees()).build();
        } catch (FailedToGetSalesEmployeesException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/SalesEmployee/{id}")
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
            salesEmployeeService.deleteSalesEmployee(id);

            return Response.ok().build();
        } catch (SalesEmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToDeleteSalesEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
