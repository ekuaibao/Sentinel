package com.alibaba.csp.sentinel.dashboard.util;

public class PathUtils {
    public static String concat(String a, String b) {
        String p = a;
        if (p.endsWith("/")) {
            p = p.substring(0, p.length() - 1);
        }
        if (b.startsWith("/")) {
            p += b;
        } else {
            p += "/" + b;
        }
        return p;
    }
}
