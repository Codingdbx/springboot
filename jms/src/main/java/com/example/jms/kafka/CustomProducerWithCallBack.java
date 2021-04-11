package com.example.jms.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * <p>Description: 带回调函数的 API</p>
 *
 * 回调函数会在 producer 收到 ack 时调用，为异步调用，该方法有两个参数，分别是
 * RecordMetadata 和 Exception，如果 Exception 为 null，说明消息发送成功，如果
 * Exception 不为 null，说明消息发送失败。
 * @author dbx
 * @date 2021/3/21 21:28
 * @since JDK1.8
 */
public class CustomProducerWithCallBack {

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
//            producer.send(new ProducerRecord<>("first", Integer.toString(i), "danny:hello:" + i));
            //回调函数，该方法会在 Producer 收到 ack 时调用，为异步调用
            producer.send(new ProducerRecord<>("first", Integer.toString(i), "danny:hello:" + i), (metadata, exception) -> {
                if (exception == null) {
                    System.out.println("success->" + metadata.offset());
                } else {
                    exception.printStackTrace();
                }
            });
        }
        producer.close();

    }
}
