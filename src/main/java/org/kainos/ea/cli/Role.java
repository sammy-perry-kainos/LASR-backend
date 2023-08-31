package org.kainos.ea.cli;

public enum Role {
    ADMIN,
    USER,
    HR,
    SALES,
    MANAGEMENT;

    public static Role fromInteger(int x) {
        switch(x) {
            case 1:
                return ADMIN;
            case 2:
                return USER;
            case 3:
                return HR;
            case 4:
                return SALES;
            case 5:
                return MANAGEMENT;
        }
        return null;
    }
}
