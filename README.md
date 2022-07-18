# SensorReadingPulsar

> mvn clean install

To run the enrichment function:
./pulsar-admin functions localrun --jar sensorreadingpulsardemo-1.0-SNAPSHOT.jar --classname io.streamnative.function.EnrichmentFunc --web-service-url http://pulsar-web-service:8080 --inputs persistent://public/default/sensor_readings --output persistent://public/default/sensor_readings_enriched --tenant public --namespace default --name EnrichmentFunc

