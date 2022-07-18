package io.streamnative.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SensorEnriched implements Serializable {

    private Timestamp timestamp;

    private float sensorReading;

    private String location;

    private String sensorType;

    private String stationId;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public float getSensorReading() {
        return sensorReading;
    }

    public void setSensorReading(float sensorReading) {
        this.sensorReading = sensorReading;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public SensorEnriched() {
    }

    public SensorEnriched(Timestamp timestamp, float sensorReading, String location, String sensorType, String stationId) {
        this.timestamp = timestamp;
        this.sensorReading = sensorReading;
        this.location = location;
        this.sensorType = sensorType;
        this.stationId = stationId;
    }

}
