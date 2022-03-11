package com.revature.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        server.addServlet(webApp,"defaultServlet", new DefaultServlet()).addMapping("/*");
        //server.addServlet(webApp, "indexServlet", new IndexServlet(indexService)).addMapping("/Search");

        try {
            server.start();
            System.out.println("Server running on http://localhost:" + server.getConnector().getLocalPort());
            server.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

    HttpServlet championServlet = new HttpServlet() {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Champion> champions = new ArrayList<>();
            try {
                ResultSet rs = connection.prepareStatement("select * from Champion").executeQuery();
                while (rs.next()) {
                    champions.add(new Champion(rs.getString("Name"), rs.getString("classType"), rs.getInt("Health")));
                }
            } catch (SQLException e) {
                System.err.println(("Failed to retrieve from database: " + e.getSQLState()));
            }

            ObjectMapper mapper = new ObjectMapper();
            String results = mapper.writeValueAsString(champions);
            resp.setContentType("application/json");
            resp.getWriter().println(results);
        }

        @Override
        protected void doPost(HttpServletRequest req)
    }
}
