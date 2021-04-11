package com.example.jms.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * <p>Description: TopicProducer</p>
 *
 * @author dbx
 * @since 2021/4/10 17:41
 */
public class TopicProducer {

    public static void main(String[] args) throws Exception{
        //1.Create a JMS ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        //2.Create a JMS Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //3.Create a JMS Session
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        //4.Create the destination (Topic or Queue)
        Topic topic = session.createTopic("TEST.TOPIC");

        //5.Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(topic);

        //设置传送模式 持久化 default:  DeliveryMode=PERSISTENT
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        for (int i = 0; i <10; i++) {
            //Create a message
            TextMessage  message = session.createTextMessage();
            String text = "Hello world! "+ i;
            message.setText(text);

            //Tell the producer to send the message
            producer.send(message);
            System.out.println("Published:" + text);
        }

        // Clean up
        producer.close();
        session.close();
        connection.close();

    }

}
