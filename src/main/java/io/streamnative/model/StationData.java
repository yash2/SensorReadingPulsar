package io.streamnative.model;

public class StationData {

    private String stationId;

    private String location;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

     public StationData() {
    }

    public StationData(String stationId, String location) {
        this.stationId = stationId;
        this.location = location;
    }

}
