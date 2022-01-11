package com.demoblaze.test;

import java.util.UUID;

public class Utils {

    public static String getItemId() {
        return UUID.randomUUID().toString();
    }

    public static String getUserId() {
        return UUID.randomUUID().toString();
    }

}
