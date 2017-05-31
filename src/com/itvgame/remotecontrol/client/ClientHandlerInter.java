package com.itvgame.remotecontrol.client;

public interface ClientHandlerInter{
	
	public abstract void exceptionCaught(ClientIoSession session, Throwable cause);
		

	public abstract void inputClosed(ClientIoSession session);

	
	public abstract void messageSent(ClientIoSession session, Object message) ;

	
	public abstract void sessionClosed(ClientIoSession session) ;

	
	public abstract void sessionCreated(ClientIoSession session) ;
	
	public abstract void sessionOpened(ClientIoSession session) ;

	
	public abstract  void messageReceived(ClientIoSession session, byte[] message);
	
	
	
}

