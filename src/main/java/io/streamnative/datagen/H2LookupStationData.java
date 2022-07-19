package io.streamnative.datagen;

import io.streamnative.model.StationData;

import java.sql.*;

public class H2LookupStationData {

    public StationData lookupStationData(String stationId) throws SQLException {
        final String url = "jdbc:h2:mem:sensor";
        //final String JDBC_DRIVER = "org.h2.Driver";
        Connection connection = null;
        StationData stationData = null;
         try {
            //Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url);
            System.out.println("connected");
            String sqlWhereClause = "select * FROM station_data where station_id = '"+stationId+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlWhereClause);
            int count = 0;
            while (resultSet.next()) {
                stationData = new StationData();
                count++;
                int ID = resultSet.getInt("ID");
                stationData.setStationId(resultSet.getString("station_id"));
                stationData.setLocation(resultSet.getString("location"));
                System.out.println("Station Data : " + stationData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
             //throw new RuntimeException(e);
             System.out.println("NOT FOUND");
             e.printStackTrace();
         } finally {
            if (connection != null)
                connection.close();
        }
        return stationData;
    }

}