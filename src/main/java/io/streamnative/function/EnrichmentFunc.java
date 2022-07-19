package io.streamnative.function;

import io.streamnative.datagen.CrateDBDataSource;
import io.streamnative.datagen.H2LookupStationData;
import io.streamnative.model.Sensor;
import io.streamnative.model.SensorEnriched;
import io.streamnative.model.StationData;
import org.apache.pulsar.common.functions.FunctionConfig;
import org.apache.pulsar.functions.LocalRunner;
import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;
import org.apache.pulsar.shade.org.apache.commons.lang3.SerializationUtils;

import java.util.Collections;

public class EnrichmentFunc implements Function<Sensor, byte[]> {

 @Override
 public byte[] process(Sensor input, Context context) {
  System.out.println("STATION ID: "+input.getStationId());
  H2LookupStationData h2LookupStationData = new H2LookupStationData();
  SensorEnriched sensorEnriched1 = null;
  byte[] b = null;
     try {
      // H2
      StationData stationData = h2LookupStationData.lookupStationData(input.getStationId());
      // Crate
      //CrateDBDataSource crateDBDataSource = new CrateDBDataSource();
      //StationData stationData = crateDBDataSource.getStationData(input.getStationId());
      sensorEnriched1 = extracted(input, stationData);
      System.out.println(sensorEnriched1);
      // Publish to output topic
      //context.publish("sensor_readings_enriched", sensorEnriched1);
      b = SerializationUtils.serialize(sensorEnriched1);
      //System.out.println("b => "+sensorEnriched1);
  } catch (Exception e) {
      e.printStackTrace();
  }
     return b;
 }

    private SensorEnriched extracted(Sensor input, StationData stationData) {
        SensorEnriched sensorEnriched = new SensorEnriched();
        sensorEnriched.setTimestamp(input.getTimestamp());
        sensorEnriched.setSensorReading(input.getSensorReading());
        sensorEnriched.setSensorType(input.getSensorType());
        sensorEnriched.setLocation(stationData.getLocation());
        String formattedVal = String.format("%s-US", input.getStationId());
        sensorEnriched.setStationId(formattedVal);
        return sensorEnriched;
    }

    @Override
 public void initialize(Context context) throws Exception {
  Function.super.initialize(context);
 }

 @Override
 public void close() throws Exception {
  Function.super.close();
 }

 /* Try to exclude the unsigned jars for LocalRunner in pom xml */
 /*public static void main(String[] args) throws Exception {
    FunctionConfig functionConfig = new FunctionConfig();
    functionConfig.setName("enrichment");
    functionConfig.setInputs(Collections.singleton("input"));
    functionConfig.setClassName(EnrichmentFunc.class.getName());
    functionConfig.setRuntime(FunctionConfig.Runtime.JAVA);
    functionConfig.setOutput("output");

    LocalRunner localRunner = LocalRunner.builder().functionConfig(functionConfig).build();
    localRunner.start(false);
 }*/

}
