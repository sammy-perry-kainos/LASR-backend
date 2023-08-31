package org.kainos.ea.core;

import org.joda.time.DateTime;
import org.kainos.ea.cli.ProjectRequestAddClient;
import org.kainos.ea.db.ClientDao;
import org.kainos.ea.db.ProjectDao;

import java.sql.SQLException;

public class ProjectValidator {

    private ClientDao clientDao;
    private ProjectDao projectDao;

    public ProjectValidator(ClientDao clientDao, ProjectDao projectDao) {
        this.clientDao = clientDao;
        this.projectDao = projectDao;
    }

    public String isValidProject(int id, ProjectRequestAddClient project) {
        try {
            if (!clientDao.validateClientId(project.getClientId())) {
                return "ClientID must exist in Client table";
            }

            if (!projectDao.validateProjectId(id)) {
                return "ProjectID must exist in Project table";
            }

            return null;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            return e.getMessage();
        }
    }
}
