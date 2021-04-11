package com.example.jms.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

/**
 * <p>Description: QueueConsumer</p>
 *
 * @author dbx
 * @since 2021/4/10 15:49
 */
public class QueueConsumer {

    public static void main(String[] args) throws Exception {

        //1.Create a JMS ConnectionFactory
        //use default pwd
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        //2.Create a JMS Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //3.Create a JMS Session

        //transacted:
        //true  session.commit()

        //acknowledgeMode:
        //AUTO_ACKNOWLEDGE 自动签收
        //CLIENT_ACKNOWLEDGE 手动签收   message.acknowledge();
        //DUPS_OK_ACKNOWLEDGE 允许副本的确认模式,而且允许重复消息
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        //4.Create the destination (Topic or Queue)
        Queue queue = session.createQueue("TEST.FOO");

        //5.Create a MessageConsumer from the Session to the Topic or Queue
        MessageConsumer consumer = session.createConsumer(queue);

        while (true) {
            // Wait for a message
//            Message message = consumer.receive();
            Message message = consumer.receive(1000);
            if (message == null) {
                break;
            }

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received: " + text);
            }else  if (message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;
                Enumeration mapNames = mapMessage.getMapNames();
                while (mapNames.hasMoreElements()) {
                    String str = (String)mapNames.nextElement();
                    String string = mapMessage.getString(str);
                    System.out.println("Received: " + str + "========" + string);
                }
            } else {
                System.out.println("Received: " + message);
            }


        }

//        consumer.setMessageListener(message -> {
//            try {
//                if (message instanceof TextMessage) {
//                    TextMessage textMessage = (TextMessage) message;
//                    String text = textMessage.getText();
//                    System.out.println("Received: " + text);
//                } else if (message instanceof MapMessage) {
//                    MapMessage mapMessage = (MapMessage) message;
//                    Enumeration mapNames = mapMessage.getMapNames();
//                    while (mapNames.hasMoreElements()) {
//                        String str = (String) mapNames.nextElement();
//                        String string = mapMessage.getString(str);
//                        System.out.println("Received: " + str + "========" + string);
//                    }
//                } else {
//                    System.out.println("Received: " + message);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

        // Clean up
        consumer.close();
        session.close();
        connection.close();


    }
}
