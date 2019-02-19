package com.gamers.Beans;

import com.gamers.Entities.Report;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.*;
import java.sql.Timestamp;

@ApplicationScoped
public class ReportNotificationSender
{

    @Resource(name="jms/ReportNotifierPool")
    private ConnectionFactory connectionFactory;

    @Resource(name="jms/ReportNotifierTopic")
    private Destination destination;

    public void notify(String topic, String from)
    {
        try
        {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            //message.setStringProperty("reportId", String.valueOf(report.getId()));
            //message.setStringProperty("reportTimestamp", report.getTime().toString());
            message.setStringProperty("from", from);
            message.setText(topic);
            producer.send(message);
            session.close();
            connection.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
