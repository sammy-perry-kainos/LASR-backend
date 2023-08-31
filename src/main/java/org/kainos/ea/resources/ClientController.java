package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.api.ClientService;
import org.kainos.ea.api.ProjectService;
import org.kainos.ea.cli.Client;
import org.kainos.ea.cli.ProjectRequestAddClient;
import org.kainos.ea.client.*;
import org.kainos.ea.core.AuthValidator;
import org.kainos.ea.core.ProjectValidator;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.db.ClientDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.ProjectDao;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Api("Team SCM Client API")
@Path("/api")
public class ClientController {

    private ClientService clientService = new ClientService(new ClientDao(new DatabaseConnector()));

    private AuthService authService = new AuthService(new AuthDao(new DatabaseConnector()),
            new AuthValidator(new AuthDao(new DatabaseConnector())));

    @GET
    @Path("/client")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClientSalesEmployees(@QueryParam("token") String token) {
        try {
            if (!authService.isAdmin(token) && !authService.isSales(token)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        } catch (FailedToVerifyTokenException e) {
            return Response.serverError().build();
        }

        try {
            return Response.ok(clientService.getAllClientSalesEmployees()).build();
        } catch (FailedToGetClientException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/get_all_clients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClients(){
        try{
            return Response.ok(clientService.getAllClients()).build();
        } catch (FailedToGetAllClientsException e){
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }

    @GET
    @Path("/client_highest_value")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientWithHighestValueProjects(@QueryParam("token") String token) {
        try {
            if (!authService.isAdmin(token) && !authService.isSales(token)) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } catch (TokenExpiredException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        } catch (FailedToVerifyTokenException e) {
            return Response.serverError().build();
        }

        try {
            return Response.ok(clientService.getClientWithHighestValueProjects()).build();
        } catch (FailedToGetClientException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}