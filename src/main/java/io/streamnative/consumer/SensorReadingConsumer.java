package io.streamnative.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.streamnative.model.Sensor;
import org.apache.pulsar.client.api.*;
import java.util.HashMap;
import java.util.Map;

public class SensorReadingConsumer {

    PulsarClient client = PulsarClient.builder()
            .serviceUrl("pulsar://localhost:6650")
            .build();

    MessageListener messageListener = (consumer, msg) -> {
        try {
            //System.out.println(new String(msg.getData()));
            Map<String, Object> result = new ObjectMapper().readValue(new String(msg.getData()), HashMap.class);
            System.out.println("StationId : "+result.get("stationId"));
            System.out.println("Sensor Reading: "+result.get("sensorReading"));
           consumer.acknowledge(msg);
        } catch (Exception e) {
            e.printStackTrace();
            consumer.negativeAcknowledge(msg);
        }
    };

    Consumer consumer = client.newConsumer(Schema.JSON(Sensor.class))
            .topic("sensor_readings")
            .subscriptionName("sensor-subscription")
            .messageListener(messageListener)
            .subscribe();
    public SensorReadingConsumer() throws PulsarClientException {
    }

    public static void main(String[] args) {
        try {
            SensorReadingConsumer srp = new SensorReadingConsumer();
        } catch (PulsarClientException pe) {
            pe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
