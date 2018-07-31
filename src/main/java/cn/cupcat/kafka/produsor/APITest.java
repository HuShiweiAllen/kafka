package cn.cupcat.kafka.produsor;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Description: java类作用描述
 * @Author: zxy
 * @CreateDate: 2018/5/29 15:57
 * @Version: 1.0
 **/
public class APITest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "hadoop101:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("first", Integer.toString(i), Integer.toString(i)), (metadata, exception) -> {
                if (metadata != null) {
                    System.out.println("partition => " + metadata.partition() + "  topic => " + metadata.topic() + "   offset => " +
                            " " + metadata.offset() + " toString => " + metadata.toString());
                }
            });
        }
        producer.close();
    }

}
