package io.streamnative.datagen;

import java.sql.*;

public class H2InsertStationData {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:mem:sensor";
        Connection connection = DriverManager.getConnection(url);
        System.out.println("connected");
        String sql1 = "create table station_data (ID int primary key, station_id varchar(7), location varchar(50))";
        Statement statement = connection.createStatement();
        statement.execute(sql1);
        System.out.println("created table station_data.");
        sql1 = "insert into station_data (ID, station_id, location) values  (1, 'SWA531', 'WASHINGTON'), (2, 'SNY042', 'NEW YORK'), (3, 'SGA014', 'GEORGIA'), (4, 'SLA404', 'LOUISIANA'), (5, 'SKS019', 'KANSAS'), (6, 'SIO112', 'IOWA'), (7, 'SOK000', 'NOT APPLICABLE')";
        int rows = statement.executeUpdate(sql1);
        String sql2 = "SELECT * FROM station_data";
        ResultSet resultSet = statement.executeQuery(sql2);
        int count = 0;
        while (resultSet.next()) {
            count++;
            int ID = resultSet.getInt("ID");
            String stationId = resultSet.getString("station_id");
            String location = resultSet.getString("location");
            System.out.println("Station ID: " + stationId + " , Location: " + location);
        }
        connection.close();
    }

}