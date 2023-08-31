package org.kainos.ea.resources;

import io.swagger.annotations.Api;
<<<<<<< HEAD
import org.kainos.ea.api.AuthService;
=======
import org.kainos.ea.api.FailedToGetProjectException;
>>>>>>> c1376be (Get all clients)
import org.kainos.ea.api.ProjectService;
import org.kainos.ea.cli.ProjectRequestAddClient;
import org.kainos.ea.client.FailedToUpdateProjectException;
import org.kainos.ea.client.FailedToVerifyTokenException;
import org.kainos.ea.client.InvalidProjectException;
import org.kainos.ea.client.TokenExpiredException;
import org.kainos.ea.core.AuthValidator;
import org.kainos.ea.core.ProjectValidator;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.db.ClientDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.ProjectDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team SCM Project API")
@Path("/api")
public class ProjectController {
    private ProjectService projectService = new ProjectService(new ProjectDao(new DatabaseConnector()),
            new ProjectValidator(new ClientDao(new DatabaseConnector()), new ProjectDao(new DatabaseConnector())));

    private AuthService authService = new AuthService(new AuthDao(new DatabaseConnector()),
            new AuthValidator(new AuthDao(new DatabaseConnector())));

    @PUT
    @Path("/project/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addClientToProject(@PathParam("id") int id,
                                       ProjectRequestAddClient project,
                                       @QueryParam("token") String token) {
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
            projectService.addClientToProject(id, project);

            return Response.ok().build();
        } catch (FailedToUpdateProjectException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        } catch (InvalidProjectException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/projects")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClients(){
        try{
            return Response.ok(projectService.getAllProjects()).build();
        } catch (FailedToGetProjectException e){
            System.out.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}
