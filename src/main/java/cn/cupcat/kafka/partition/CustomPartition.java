package cn.cupcat.kafka.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 目的：
 * 让所有的数据都存放到0号分区中
 *
 * @Description: 自定义分区
 * @Author: zxy
 * @CreateDate: 2018/5/30 11:54
 * @Version: 1.0
 */
public class CustomPartition implements Partitioner {


    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
