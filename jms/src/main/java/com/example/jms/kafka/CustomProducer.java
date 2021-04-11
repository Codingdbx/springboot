package com.example.jms.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * <p>Description: 不带回调函数的 API </p>
 *
 * @author dbx
 * @date 2021/3/21 21:28
 * @since JDK1.8
 */
public class CustomProducer {

    /**
     * 需要用到的类：
     * KafkaProducer：需要创建一个生产者对象，用来发送数据
     * ProducerConfig：获取所需的一系列配置参数
     * ProducerRecord：每条数据都要封装成一个 ProducerRecord 对象
     */

    /**
     * new: producer/bin/producer-console-consumer.sh  --bootstrap-server zookeeperCluster01:9092 --topic first
     * old: producer/bin/producer-console-consumer.sh  --bootstrap-server zookeeperCluster01:2181 --topic first
     *
     */
    public static void main(String[] args) {
        Properties props = new Properties();
        //producer 集群，broker-list
        props.put("bootstrap.servers", "192.168.1.8:9092");
        props.put("acks", "all");
        //重试次数
        props.put("retries", 1);
        //批次大小
        props.put("batch.size", 16384);
        //等待时间
        props.put("linger.ms", 1);
        //RecordAccumulator 缓冲区大小
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.producer.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.producer.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("first", Integer.toString(i), "danny:hello:" + i));
        }

        producer.close();

    }
}
