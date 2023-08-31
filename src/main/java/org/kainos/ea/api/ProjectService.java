package org.kainos.ea.api;

import org.kainos.ea.cli.Client;
import org.kainos.ea.cli.Project;
import org.kainos.ea.cli.ProjectRequestAddClient;
import org.kainos.ea.client.FailedToUpdateProjectException;
import org.kainos.ea.client.InvalidProjectException;
import org.kainos.ea.core.ProjectValidator;
import org.kainos.ea.db.ProjectDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private ProjectDao projectDao;
    private ProjectValidator projectValidator;

    public ProjectService(ProjectDao projectDao, ProjectValidator projectValidator) {
        this.projectDao = projectDao;
        this.projectValidator = projectValidator;
    }

    public ArrayList<Project> getAllProjects() throws FailedToGetProjectException {
        try{
            return projectDao.getAllProjects();
        } catch (SQLException e){
            throw new FailedToGetProjectException();
        }
    }
    public void addClientToProject (int id, ProjectRequestAddClient project) throws FailedToUpdateProjectException, InvalidProjectException
    {
        try {
            String validation = projectValidator.isValidProject(id, project);

            if (validation != null) {
                throw new InvalidProjectException(validation);
            }

            projectDao.addClientToProject(id, project);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateProjectException();
        }
    }
}
