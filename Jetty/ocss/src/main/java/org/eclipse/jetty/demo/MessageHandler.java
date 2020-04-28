package org.eclipse.jetty.demo;


import org.eclipse.jetty.websocket.api.WebSocketListener; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHandler implements WebSocketListener { 
    private static Logger logger=LoggerFactory.getLogger(MessageHandler.class); 

    public MessageHandler() {
        logger.info("MessageHandler Init............");    

    }

	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebSocketConnect(org.eclipse.jetty.websocket.api.Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebSocketError(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebSocketBinary(byte[] payload, int offset, int len) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWebSocketText(String message) {
		// TODO Auto-generated method stub
		
	}

 


	}
