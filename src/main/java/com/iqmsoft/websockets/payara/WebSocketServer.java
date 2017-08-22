
package com.iqmsoft.websockets.payara;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ApplicationScoped
@ServerEndpoint("/echo")
public class WebSocketServer {
    
    private static final Logger logger = Logger.getLogger(WebSocketServer.class.getName());

    @OnOpen
    public void onOpen(Session session) {
        logger.log(Level.INFO, "Opening Session {0}", session.getId());        
    }

    @OnClose
    public void onClose(Session session) {
        logger.log(Level.INFO, "Closing Session {0}", session.getId());
    }

    @OnError
    public void onError(Throwable t) {
        logger.log(Level.INFO,"onError",t);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            logger.log(Level.INFO, "Received Message on Session {0}", session.getId());  
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
}
