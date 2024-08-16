package com.the.soundspace.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {

    public static void addJwtCookieAndHeader(HttpServletResponse response, String jwtToken) {
        Cookie accessCookie = new Cookie("access-token", jwtToken);
        accessCookie.setHttpOnly(true);
        accessCookie.setPath("/");
        response.addCookie(accessCookie);
        response.addHeader("access-token", jwtToken);
    }
}
