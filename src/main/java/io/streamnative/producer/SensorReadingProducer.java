package io.streamnative.producer;

import io.streamnative.model.Sensor;
import org.apache.pulsar.client.api.*;

import java.sql.Timestamp;
import java.util.Random;

public class SensorReadingProducer {

    final String[] station_ids = {"SWA531", "SNY042", "SGA014", "SLA404", "SKS019", "SIO112", "SOK000"};

    public SensorReadingProducer() throws PulsarClientException, InterruptedException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();
        Producer<Sensor> producer = client.newProducer(Schema.JSON(Sensor.class))
                .topic("sensor_readings")
                .create();
        while(true) {
            producer.send(getRandomSensorReading());
            Thread.sleep(5000); // Wait for 5 seconds
        }
    }

    private Sensor getRandomSensorReading() {
        long now = System.currentTimeMillis();
        Timestamp ts = new Timestamp(now);
        float tempVal = new Random().nextFloat(1F, 100F);
        int si = new Random().nextInt(station_ids.length);
        System.out.println("TIMESTAMP: "+ts);
        System.out.println("TEMP READING: "+ tempVal);
        System.out.println("STATION ID: "+station_ids[si]);
        System.out.println("-----");
        return new Sensor(ts, "BMP180", tempVal, station_ids[si]);
    }

    public static void main(String[] args) {
        try {
            SensorReadingProducer srp = new SensorReadingProducer();
        } catch (PulsarClientException pce) {
            System.out.println("ERROR CALLING PRODUCER!!");
            pce.getStackTrace();
        } catch (InterruptedException ie) {
            System.out.println("ERROR!!");
            ie.getStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
