package org.kainos.ea.api;

import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.Role;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.*;
import org.kainos.ea.core.AuthValidator;
import org.kainos.ea.db.AuthDao;

import java.sql.SQLException;

public class AuthService {
    private AuthDao authDao;
    private AuthValidator authValidator;

    public AuthService(AuthDao authDao, AuthValidator authValidator) {
        this.authDao = authDao;
        this.authValidator = authValidator;
    }

    public String login(Login login) throws FailedToLoginException, FailedToGenerateTokenException {
        if (authDao.validLogin(login)) {
            try {
                return authDao.generateToken(login.getUsername());
            } catch (SQLException e) {
                throw new FailedToGenerateTokenException();
            }
        }

        throw new FailedToLoginException();
    }

    public int createUser(User user) throws FailedToCreateUserException, InvalidUserException {
        try {
            String validation = authValidator.isValidUser(user);

            if (validation != null) {
                throw new InvalidUserException(validation);
            }

            int id = authDao.createLogin(user);

            if (id == -1) {
                throw new FailedToCreateUserException();
            }

            return id;

        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateUserException();
        }
    }

    public boolean isAdmin(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            int roleID = authDao.getRoleIDFromToken(token);

            if (Role.fromInteger(roleID) == Role.ADMIN) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }

        return false;
    }

    public boolean isUser(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            int roleID = authDao.getRoleIDFromToken(token);

            if (Role.fromInteger(roleID) == Role.USER) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }

        return false;
    }

    public boolean isHR(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            int roleID = authDao.getRoleIDFromToken(token);

            if (Role.fromInteger(roleID) == Role.HR) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }

        return false;
    }

    public boolean isSales(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            int roleID = authDao.getRoleIDFromToken(token);

            if (Role.fromInteger(roleID) == Role.SALES) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }

        return false;
    }

    public boolean isManagement(String token) throws TokenExpiredException, FailedToVerifyTokenException {
        try {
            int roleID = authDao.getRoleIDFromToken(token);

            if (Role.fromInteger(roleID) == Role.MANAGEMENT) {
                return true;
            }
        } catch (SQLException e) {
            throw new FailedToVerifyTokenException();
        }

        return false;
    }
}
