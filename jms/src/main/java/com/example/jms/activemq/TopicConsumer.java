package com.example.jms.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * <p>Description: TopicConsumer</p>
 *
 *  先启动消费者订阅主题，然后才能收到发送的消息
 *
 * @author dbx
 * @since 2021/4/10 17:45
 */
public class TopicConsumer {

    public static void main(String[] args) throws Exception {

        //1.Create a JMS ConnectionFactory
        //use default pwd
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        //2.Create a JMS Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //3.Create a JMS Session
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        //4.Create the destination (Topic or Queue)
        Topic topic = session.createTopic("TEST.TOPIC");

        //5.Create a MessageConsumer from the Session to the Topic or Queue
        MessageConsumer consumer = session.createConsumer(topic);

        consumer.setMessageListener(message -> {
            try {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println("Received: " + text);
                }else {
                    System.out.println("Received: " + message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.in.read();

        // Clean up
        consumer.close();
        session.close();
        connection.close();


    }
}
