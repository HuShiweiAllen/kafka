package cn.cupcat.kafka.inteception;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: zxy
 * @CreateDate: 2018/5/30 15:27
 * @Version: 1.0
 */
public class CountIntecepter implements ProducerInterceptor<String, String> {


    private int successCount = 0;
    private int errorCount = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            errorCount++;
            return;
        }
        successCount++;
    }

    @Override
    public void close() {
        System.out.printf("successCount = %d, errorCount = %d", successCount, errorCount);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
