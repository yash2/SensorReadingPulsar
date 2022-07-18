package io.streamnative.model;

import java.sql.Timestamp;

public class Sensor {

    private Timestamp timestamp;

    private String sensorType;

    private float sensorReading;

    private String stationId;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public float getSensorReading() {
        return sensorReading;
    }

    public void setSensorReading(float sensorReading) {
        this.sensorReading = sensorReading;
    }

    public String getStationId() { return stationId; }

    public void setStationId(String stationId) { this.stationId = stationId; }

    public Sensor() {
    }

    public Sensor(Timestamp timestamp, String sensorType, float sensorReading, String stationId) {
        this.timestamp = timestamp;
        this.sensorType = sensorType;
        this.sensorReading = sensorReading;
        this.stationId = stationId;
    }

}
