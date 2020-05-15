package DataBase;

import java.sql.*;

public class BasesTableCreater {
    private static String user = "programiifromskolkovo";
    private static String password = "programiifromskolkovo";
    private static String url = "jdbc:postgresql://localhost:5432/Tickets";
    private static String commands = "CREATE TABLE IF NOT EXISTS locations\n" +
            "(\n" +
            "    id SERIAL PRIMARY KEY,\n" +
            "    x double precision NOT NULL,\n" +
            "    y real NOT NULL,\n" +
            "    z bigint NOT NULL,\n" +
            "    name character varying COLLATE pg_catalog.\"default\"\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS address\n" +
            "(\n" +
            "    id SERIAL PRIMARY KEY,\n" +
            "    zipcode character varying COLLATE pg_catalog.\"default\",\n" +
            "    town integer,\n" +
            "    FOREIGN KEY (town) REFERENCES locations (id)\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS venues\n" +
            "(\n" +
            "    id BIGSERIAL PRIMARY KEY,\n" +
            "    name character varying COLLATE pg_catalog.\"default\" NOT NULL,\n" +
            "    capacity integer,\n" +
            "    type character varying COLLATE pg_catalog.\"default\" NOT NULL,\n" +
            "    address integer,\n" +
            "    FOREIGN KEY (address) REFERENCES address (id)\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS coordinates\n" +
            "(\n" +
            "    id SERIAL PRIMARY KEY,\n" +
            "    x bigint NOT NULL,\n" +
            "    y double precision NOT NULL\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS users\n" +
            "(\n" +
            "    id SERIAL PRIMARY KEY,\n" +
            "    name character varying COLLATE pg_catalog.\"default\" NOT NULL,\n" +
            "    mail character varying COLLATE pg_catalog.\"default\",\n" +
            "    password bytea\n" +
            ");\n" +
            "CREATE TABLE IF NOT EXISTS tickets\n" +
            "(\n" +
            "    id SERIAL PRIMARY KEY,\n" +
            "    name character varying COLLATE pg_catalog.\"default\" NOT NULL,\n" +
            "    coordinates integer NOT NULL,\n" +
            "    creationdate character varying(30) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
            "    price integer NOT NULL,\n" +
            "    comment character varying COLLATE pg_catalog.\"default\",\n" +
            "    refundable boolean NOT NULL,\n" +
            "    type character varying COLLATE pg_catalog.\"default\",\n" +
            "    venue integer NOT NULL,\n" +
            "    key character varying COLLATE pg_catalog.\"default\" NOT NULL,\n" +
            "    userofticket integer NOT NULL,\n" +
            "    FOREIGN KEY (coordinates) REFERENCES coordinates (id),\n" +
            "    FOREIGN KEY (userofticket) REFERENCES users (id),\n" +
            "    FOREIGN KEY (venue) REFERENCES venues (id)\n" +
            ");";

    public static DataBaseManagerTickets getDataBase(String u, String p, String ur){
        if(!(u==null)){
            user = u;
            password = p;
            url = ur;
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
            DataBaseManagerTickets dbmt = new DataBaseManagerTickets(connection);
            PreparedStatement st = connection.prepareStatement(commands);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                System.out.println(rs.toString());
            }
            dbmt.fillTable(new TicketsList(), new BaseOwners());
            return dbmt;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
