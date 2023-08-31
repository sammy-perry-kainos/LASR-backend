package org.kainos.ea.core;

import org.kainos.ea.cli.User;
import org.kainos.ea.db.AuthDao;

import java.sql.SQLException;

public class AuthValidator {
    private AuthDao authDao;

    public AuthValidator(AuthDao authDao) {
        this.authDao = authDao;
    }

    public String isValidUser(User user) {
        try {
            if (authDao.checkUserName(user.getUsername())) {
                return "Username already exists in user table";
            }

            if (authDao.checkRoleID(user.getRoleID())) {
                return "RoleID is not valid. Please use valid role ID";
            }

            return null;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
}
