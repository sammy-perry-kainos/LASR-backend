package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.ProjectService;
import org.kainos.ea.cli.ProjectRequestAddClient;
import org.kainos.ea.client.FailedToUpdateProjectException;
import org.kainos.ea.client.InvalidProjectException;
import org.kainos.ea.core.ProjectValidator;
import org.kainos.ea.db.ClientDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.ProjectDao;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team SCM Project API")
@Path("/api")
public class ProjectController {
    private ProjectService projectService = new ProjectService(new ProjectDao(new DatabaseConnector()),
            new ProjectValidator(new ClientDao(new DatabaseConnector()), new ProjectDao(new DatabaseConnector())));

    @PUT
    @Path("/project/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addClientToProject(@PathParam("id") int id,
                                       ProjectRequestAddClient project) {
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
}
