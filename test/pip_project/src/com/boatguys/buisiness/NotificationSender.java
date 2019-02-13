package com.boatguys.buisiness;

import com.rabbitmq.jms.admin.RMQConnectionFactory;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@ApplicationScoped
public class NotificationSender
{
    private ConnectionFactory factory;
    private Destination  topic;

    private Connection connection;
    private Session session;

    public NotificationSender()
    {
        factory = createConnectionFactory();

        try
        {
            connection = factory.createConnection();
            session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            topic = session.createTopic("customerTopic");

            MessageConsumer consumer1 = session.createConsumer(topic);

            consumer1.setMessageListener(new NotificationsListener());

            connection.start();
        } catch (JMSException e)
        {
            e.printStackTrace();
        }


    }

    public void sendMessage(String msg) throws NamingException, JMSException
    {

        // Create the message
        TextMessage message = session.createTextMessage();
        message.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
        message.setText(msg);

        MessageProducer producer = session.createProducer(topic);

        producer.send(message);
    }

    private ConnectionFactory createConnectionFactory()
    {
        RMQConnectionFactory cf = new RMQConnectionFactory();

        cf.setHost("localhost");
        cf.setPort(5672);
        cf.setVirtualHost("/");
        cf.setUsername("admin");
        cf.setPassword("admin");

        return cf;
    }
}
