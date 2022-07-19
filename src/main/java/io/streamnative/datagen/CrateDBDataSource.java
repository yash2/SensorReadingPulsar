package io.streamnative.datagen;

import io.streamnative.model.StationData;

import java.sql.*;

public class CrateDBDataSource {
    //private static Properties properties;
    private Connection connection;
    //public void getConnection() throws SQLException {
    //}
    public StationData getStationData(String stationId) throws SQLException {
        try {
            //Class.forName("");
            connection = DriverManager.getConnection("jdbc:crate://localhost:5432/");
        } catch (SQLException e) {
            throw new SQLException("Cannot connect to the database", e);
        }
        PreparedStatement statement = connection.prepareStatement(String.format(
               " SELECT * FROM doc.station_data where station_id = %S ", stationId));
        statement.setString(1, stationId);
        ResultSet results = statement.executeQuery();
        StationData stationData = new StationData();
        if (results.next()) {
            stationData.setLocation((String) results.getObject("location"));
            return stationData;
        } else {
            return null;
        }

    }

}
