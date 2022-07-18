package io.streamnative.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.streamnative.model.Sensor;
import io.streamnative.model.SensorEnriched;
import org.apache.pulsar.client.api.*;

import java.util.HashMap;
import java.util.Map;

public class SensorEnrichedConsumer {

    PulsarClient client = PulsarClient.builder()
            .serviceUrl("pulsar://localhost:6650")
            .build();

    MessageListener messageListener = (consumer, msg) -> {
        try {
            System.out.println(new String(msg.getData()));
            Map<String, Object> result = new ObjectMapper().readValue(new String(msg.getData()), HashMap.class);
            System.out.println("StationId : "+result.get("stationId"));
            System.out.println("Sensor Reading: "+result.get("sensorReading"));
            consumer.acknowledge(msg);
        } catch (Exception e) {
            e.printStackTrace();
            consumer.negativeAcknowledge(msg);
        }
    };

    Consumer consumer = client.newConsumer(Schema.JSON(SensorEnriched.class))
            .topic("sensor_readings_enriched")
            .subscriptionName("sensor-subscription-2")
            .messageListener(messageListener)
            .subscribe();
    public SensorEnrichedConsumer() throws PulsarClientException {
    }

    public static void main(String[] args) {
        try {
            SensorEnrichedConsumer srp = new SensorEnrichedConsumer();
        } catch (PulsarClientException pe) {
            pe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
