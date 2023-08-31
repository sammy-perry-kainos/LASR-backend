package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryEmployeeProjectService;
import org.kainos.ea.cli.DeliveryEmployeeProjectRequest;
import org.kainos.ea.client.FailedToAssignDeliveryEmployeeToProjectException;
import org.kainos.ea.client.FailedToRemoveDeliveryEmployeeFromProjectException;
import org.kainos.ea.db.DeliveryEmployeeProjectsDAO;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Team LASR DeliveryEmployeeProject API")
@Path("/api")
public class DeliveryEmployeeProjectController {
    DeliveryEmployeeProjectsDAO deliveryEmployeeProjectsDAO = new DeliveryEmployeeProjectsDAO();
    DeliveryEmployeeProjectService deliveryEmployeeProjectService
            = new DeliveryEmployeeProjectService(deliveryEmployeeProjectsDAO);

    @POST
    @Path("/deliveryemployeeproject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignDeliveryEmployeeToProject(DeliveryEmployeeProjectRequest request){
        try{
            int id = deliveryEmployeeProjectService.assignDeliveryEmployeeToProject(request.getDeliveryEmployeeId(),
                    request.getProjectId());

            return Response.ok(id).build();
        } catch (FailedToAssignDeliveryEmployeeToProjectException e){
            System.err.println(e.getMessage());
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deliveryemployeeproject")
    @Produces
    public Response removeDeliveryEmployeeFromProject(DeliveryEmployeeProjectRequest request){
        try{
            deliveryEmployeeProjectService.removeDeliveryEmployeeFromProject(request.getDeliveryEmployeeId(),
                    request.getProjectId());

            return Response.ok().build();
        } catch (FailedToRemoveDeliveryEmployeeFromProjectException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }
}
