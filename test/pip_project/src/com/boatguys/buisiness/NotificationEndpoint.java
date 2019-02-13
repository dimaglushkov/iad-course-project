package com.boatguys.buisiness;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.jms.*;
import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/notifications")
public class NotificationEndpoint implements Serializable
{
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session)
    {
       peers.add(session);

    }

    @OnMessage
    public void onMessage(String message, Session session)
    {
        System.out.printf("Message received. Session id: %s Message: %s%n",
                session.getId(), message);
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @OnClose
    public void onClose(Session session)
    {
       peers.remove(session);
    }

    public static Set<Session> getPeers()
    {
        return peers;
    }

}
