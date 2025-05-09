package com.util;

import com.model.ModelKaryawan;

public class Session {
    private static String role;
    private static ModelKaryawan loggedInKaryawan;

    public static void setLoggedInKaryawan(ModelKaryawan karyawan) {
        loggedInKaryawan = karyawan;
    }

    public static ModelKaryawan getLoggedInKaryawan() {
        return loggedInKaryawan;
    }

    public static void setRole(String userRole) {
        role = userRole;
    }

    public static String getRole() {
        return role;
    }
}