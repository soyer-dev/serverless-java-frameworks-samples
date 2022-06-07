# Serverless Spring Boot Application Demo

## Deployment

Deploy the demo to your AWS account using [AWS SAM](https://aws.amazon.com/serverless/sam/).

### Option 1: Managed Runtime

```bash
mvn clean package
sam deploy -g
```

This will create a kinesis stream and a Lambda function reading from it.

You can add records to the Kinesis stream using below cli. It will add a record with the current date.


```bash
aws kinesis put-record --stream-name sam-app-stream-zzzzzzzzz   --data `date|base64` --partition-key `uuidgen`
```

You will need to update the application.properties file to connect your own database.

## CloudWatch Logs Insights

Using this CloudWatch Logs Insights query you can analyse the latency of the requests made to the Lambda functions.

The query separates cold starts from other requests and then gives you p50, p90 and p99 percentiles.

```
filter @type="REPORT"
| fields greatest(@initDuration, 0) + @duration as duration, ispresent(@initDuration) as coldStart
| stats count(*) as count, pct(duration, 50) as p50, pct(duration, 90) as p90, pct(duration, 99) as p99, max(duration) as max by coldStart
```

Latency for JVM version:
<p align="center">
  <img src="../imgs/springboot/springboot-sample-log-insights.JPG" alt="JVM Version Log Insights"/>
</p>

Latency for GraalVM version:

<p align="center">
  <img src="../imgs/springboot/springboot-native-log-insights.JPG" alt="GraalVM Version Log Insights"/>
</p>

## AWS X-Ray Tracing
You can add additional detail to your X-Ray tracing by adding a TracingInterceptor to your AWS SDK clients.

Example cold start trace for JVM version:

<p align="center">
  <img src="../imgs/springboot/springboot-sample-cold-trace.JPG" alt="JVM Version Cold Trace Example"/>
</p>

