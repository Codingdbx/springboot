package com.example.jms.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * <p>Description: QueueProducer</p>
 *
 * @author dbx
 * @since 2021/4/10 15:25
 */
public class QueueProducer {

    public static void main(String[] args) throws Exception {

        /**
         * JMS开发的基本步骤
         *
         * 1：创建一个JMS ConnectionFactory
         * 2：通过JMS ConnectionFactory来创建JMS connection
         * 3：启动JMS connection
         * 4：通过connection创建JMS session
         * 5：创建JMS destination
         * 6：创建JMS producer,或者创建JMS message，并设置destination
         * 7：创建JMS consumer，或者是注册一个JMS message listener
         * 8：发送或者接受JMS message(s)
         * 9：关闭所有的JMS资源(connection, session, producer, consumer等)
         */

        //1.Create a JMS ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        //2.Create a JMS Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        //3.Create a JMS Session
        //transacted  if true,must commit session
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        //4.Create the destination (Topic or Queue)
        Queue queue = session.createQueue("TEST.QUEUE");

        //5.Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(queue);

        //设置传送模式 非持久化  DeliveryMode=NON_PERSISTENT
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        for (int i = 0; i <10; i++) {
            //Create a message
            TextMessage  message = session.createTextMessage();
            String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + i;
            message.setText(text);

            //Tell the producer to send the message
            producer.send(message);

            System.out.println("Published:" + text);
        }

//        for (int i = 0; i <10; i++) {
//            //Create a message
//            MapMessage message = session.createMapMessage();
//            String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + i;
//
//            message.setStringProperty("extra", "yeah" + i);
//            message.setString("message is:", text);
//
//            //Tell the producer to send the message
//            producer.send(message);
//
//            System.out.println("Published:" + text);
//        }

        // Clean up
        producer.close();
        session.close();
        connection.close();


    }







}
