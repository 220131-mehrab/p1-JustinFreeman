package com.revature.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ChampServlet extends HttpServlet {
    Connection connect;

    {
        try {
            connect = DriverManager.getConnection("jdbc:h2:mem:test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;INIT=runscript from 'classpath:schema.sql'", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Champion> champion = new ArrayList<>();
        try {
            ResultSet resultSet = connect.prepareStatement("select * from champion").executeQuery();
            while (resultSet.next()) {
                Champion addChampion = new Champion(resultSet.getInt("champId"),
                        resultSet.getString("name"),
                        resultSet.getString("classType"),
                        resultSet.getInt("health"));
                champion.add(addChampion);
            }
        }catch (SQLException e) {
            System.err.println("Failed to retrieve from Database: " + e.getMessage());
        }

        ObjectMapper mapper = new ObjectMapper();
        String results = mapper.writeValueAsString(champion);
        resp.setContentType("application/json");
        resp.getWriter().println(results);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Champion newChampion = mapper.readValue(req.getInputStream(), Champion.class);
        try {
            PreparedStatement preparedStatement = connect.prepareStatement("Insert into champions values (?, ?, ?, ?)");
            preparedStatement.setInt(1, newChampion.getChampId());
            preparedStatement.setString(2, newChampion.getName());
            preparedStatement.setString(3, newChampion.getClassType());
            preparedStatement.setInt(4, newChampion.getHealth());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Failed to insert new Champion: " + e.getMessage());
        }
    }
}
