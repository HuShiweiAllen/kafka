package cn.cupcat.kafka.etl;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.TopologyBuilder;

import java.util.Properties;

/**
 * @Description: java类作用描述
 * @Author: zxy
 * @CreateDate: 2018/5/30 16:12
 * @Version: 1.0
 */
public class Application {


    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "logFilter");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop101:9092");

        StreamsConfig streamsConfig = new StreamsConfig(properties);

        TopologyBuilder builder = new TopologyBuilder();

        builder.addSource("SOURCE","first")
                .addProcessor("PROCESSOR", LogProcessor::new, "SOURCE")
                .addSink("SINK","second","PROCESSOR");

        KafkaStreams kafkaStreams = new KafkaStreams(builder, streamsConfig);

        kafkaStreams.start();
    }
}
