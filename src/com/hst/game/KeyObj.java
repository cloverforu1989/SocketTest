package com.hst.game;

import com.clover4u.scene.SceneManager;
import com.clover4u.utils.Mtd;
import com.itvgame.remotecontrol.client.ClientIoSession;
import com.itvgame.remotecontrol.server.IoSession;

/**
 * 键值队列用来解决粘包问题
 * 
 * @author Clover4Green
 * 
 */
public class KeyObj {
	int keyCode;
	boolean isPressed;
	boolean isRelessed;

	public KeyObj(int keyCode, boolean isPressed, boolean isRelessed) {
		super();
		this.keyCode = keyCode;
		this.isPressed = isPressed;
		this.isRelessed = isRelessed;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	public boolean isRelessed() {
		return isRelessed;
	}

	public void setRelessed(boolean isRelessed) {
		this.isRelessed = isRelessed;
	}

	public static  void decodeStr(String s, IoSession session) {
		int zz = 0;
		for (;;) {
			if (s == null || s.indexOf("<key>") == -1 || s.indexOf("</key>") == -1)
				return;
			s = s.trim();

			int startIndex = s.indexOf("<key>") + 5;
			int endIndex = s.indexOf("</key>");
			String temp = s.substring(startIndex, endIndex).trim();
			s = s.substring(endIndex + 3);
			if (Mtd.crParam(temp)) {
				if (temp.indexOf("kd#") != -1) {
					String[] m = Mtd.spliteStr(temp, "#");
					session.sendMsg(m[2]);
					if (Mtd.isNumber(m[1])) {
						GameScene.keyObjVector.addElement(new KeyObj(Integer.parseInt(m[1]), true, false));
					}
				} else if (temp.indexOf("ku#") != -1) {
					String[] m = Mtd.spliteStr(temp, "#");
					session.sendMsg(m[2]);
					if (Mtd.isNumber(m[1])) {
						GameScene.keyObjVector.addElement(new KeyObj(Integer.parseInt(m[1]), false, true));
					}
                }

			}
		}

	}
	
	public static  void decodeStr(String s, ClientIoSession session) {
		int zz = 0;
		for (;;) {
			if (s == null || s.indexOf("<key>") == -1 || s.indexOf("</key>") == -1)
				return;
			s = s.trim();

			int startIndex = s.indexOf("<key>") + 5;
			int endIndex = s.indexOf("</key>");
			String temp = s.substring(startIndex, endIndex).trim();
			s = s.substring(endIndex + 3);
//			if (Mtd.crParam(temp)) {
//				if (temp.indexOf("kd#") != -1) {
//					String[] m = Mtd.spliteStr(temp, "#");
//					session.sendMsg(m[2]);
//					if (Mtd.isNumber(m[1])) {
//						GameScene.keyObjVector.addElement(new KeyObj(Integer.parseInt(m[1]), true, false));
//					}
//				} else if (temp.indexOf("ku#") != -1) {
//					String[] m = Mtd.spliteStr(temp, "#");
//					session.sendMsg(m[2]);
//					if (Mtd.isNumber(m[1])) {
//						GameScene.keyObjVector.addElement(new KeyObj(Integer.parseInt(m[1]), false, true));
//					}
//                }
//
//			}
		}

	}


}
