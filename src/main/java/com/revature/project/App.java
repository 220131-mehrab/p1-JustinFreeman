package com.revature.project;

import com.revature.project.*;
import java.sql.*;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;



public class App {
    public static void main(String[] args) throws SQLException {

        String webApp = "";

        Tomcat server = new Tomcat();
        server.setBaseDir(System.getProperty("java.io.tmpdir"));
        server.setPort(8080);
        server.getConnector();
        server.addContext(webApp, null);
        server.addServlet(webApp, "DefaultServlet", new DefaultServlet()).addMapping("/*");
        server.addServlet(webApp, "champServlet", new ChampServlet()).addMapping("/champions");

        try {
            server.start();
            System.out.println("\n Server running on http://localhost:"+ server.getConnector().getLocalPort());
            server.getServer().await();
        } catch (LifecycleException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }

}


