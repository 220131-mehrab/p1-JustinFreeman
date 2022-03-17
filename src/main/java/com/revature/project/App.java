package com.revature.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.LifecycleException;

import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.io.InputStream;


public class App {
    public static void main(String[] args) {

        createServer();

    }


    public static void createServer() {
        String webApp = "";

        Tomcat server = new Tomcat();
        server.setBaseDir(System.getProperty("java.io.tmpdir"));
        server.setPort(8080);
        server.getConnector();
        server.addContext(webApp, null);
        server.addServlet(webApp, "DefaultServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String fileName = req.getPathInfo();
                String resourceDir = "Static";

                if (fileName == null || fileName.equals("/"))
                    fileName = "/index.html";

                InputStream file = getClass().getClassLoader().getResourceAsStream(resourceDir + fileName);

                if (file == null) {
                    file = getClass().getClassLoader().getResourceAsStream(resourceDir + "/index.html");
                }

                IOUtils.copy(file, resp.getOutputStream());
            }
        }).addMapping("/*");
        server.addServlet(webApp, "champServlet", new ChampServlet()).addMapping("/champions");

        try {
            server.start();
        } catch (LifecycleException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}
