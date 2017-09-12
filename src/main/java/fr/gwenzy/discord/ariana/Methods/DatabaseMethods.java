package fr.gwenzy.discord.ariana.Methods;

import java.sql.*;

public class DatabaseMethods {

    public static Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.10:3306/Ariana", "Ariana", "dbR62cttuoDruXF4");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }






}