package com.itvgame.remotecontrol.main;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

import com.clover4u.startup.MainCanvas;




public  class RemoteCanvas extends GameCanvas implements RemoteControlListener {
    
	
	protected RemoteCanvas(boolean suppressKeyEvents) {
		super(suppressKeyEvents);
		// TODO Auto-generated constructor stub
	}


	public void keyPressedRemote(int keyCode) {
		keyPressed(keyCode);
	}

	
	public void keyRleasedRemote(int keyCode) {
		// TODO Auto-generated method stub
		keyReleased(keyCode);
	}




	




}
