package com.itvgame.remotecontrol.main;

public class RemoteKey {
	// �ֻ�ң�ش����ֵ
	public static final byte KEY_REMOTE_UP = 10;
	public static final byte KEY_REMOTE_DOWN = 11;
	public static final byte KEY_REMOTE_LEFT = 12;
	public static final byte KEY_REMOTE_RIGHT = 13;
	public static final byte KEY_REMOTE_OK = 14;
	public static final byte KEY_REMOTE_LEFT_UP = 15;
    public static final byte KEY_REMOTE_RIGHT_UP = 16;
    public static final byte KEY_REMOTE_LEFT_DOWN = 17;
    public static final byte KEY_REMOTE_RIGHT_DOWN = 18;
	public static final byte KEY_REMOTE_BACK = 0;
	public static final byte KEY_REMOTE_HOME = 20;
	public static final byte KEY_REMOTE_0 = 48;
	public static final byte KEY_REMOTE_1 = 49;
	public static final byte KEY_REMOTE_2 = 50;
	public static final byte KEY_REMOTE_3 = 51;
	public static final byte KEY_REMOTE_4 = 52;
	public static final byte KEY_REMOTE_5 = 53;
	public static final byte KEY_REMOTE_6 = 54;
	public static final byte KEY_REMOTE_7 = 55;
	public static final byte KEY_REMOTE_8 = 56;
	public static final byte KEY_REMOTE_9 = 57;
    
	// �����м�ֵ
	public static final byte KEY_UP = -1;
	public static final byte KEY_LEFT = -3;
	public static final byte KEY_RIGHT = -4;
	public static final byte KEY_DOWN = -2;
	public static final byte KEY_OK = -5;// ȷ����
	public static final byte KEY_0 = 48;
	public static final byte KEY_1 = 49;
	public static final byte KEY_2 = 50;
	public static final byte KEY_3 = 51;
	public static final byte KEY_4 = 52;
	public static final byte KEY_5 = 53;
	public static final byte KEY_6 = 54;
	public static final byte KEY_7 = 55;
	public static final byte KEY_8 = 56;
	public static final byte KEY_9 = 57;
	public static final byte KEY_BACK = 0;
	public static final byte KEY_LEFT_UP = 15;
	public static final byte KEY_RIGHT_UP = 16;
	public static final byte KEY_LEFT_DOWN = 17;
	public static final byte KEY_RIGHT_DOWN = 18;
	public static final byte KEY_HOME = 20;
    public static boolean isDirectionKey(byte remoteKey) {
    	boolean result = false;
    	switch (remoteKey) {
		case KEY_REMOTE_UP:
			
		case KEY_REMOTE_DOWN:
			
		case KEY_REMOTE_LEFT:
			
		case KEY_REMOTE_RIGHT:
			
		case KEY_REMOTE_OK:
			
		case KEY_REMOTE_LEFT_DOWN:
			
		case KEY_REMOTE_LEFT_UP:
			
		case KEY_REMOTE_RIGHT_DOWN:
			
		case KEY_REMOTE_RIGHT_UP:
			result = true;
			break;
			
	   }
    	return result;
    }
	public static byte swapRemoteKeyToSetBoxKey(byte remoteKey) {
		switch (remoteKey) {
		case KEY_REMOTE_UP:
			remoteKey = KEY_UP;
			break;
		case KEY_REMOTE_DOWN:
			remoteKey = KEY_DOWN;
			break;
		case KEY_REMOTE_LEFT:
			remoteKey = KEY_LEFT;
			break;
		case KEY_REMOTE_RIGHT:
			remoteKey = KEY_RIGHT;
			break;
		case KEY_REMOTE_OK:
			remoteKey = KEY_OK;
			break;
	   }
		return remoteKey;
	}
    public static String getStr(byte remoteKey) {
    	String name = "";
    	switch (remoteKey) {
		case KEY_REMOTE_UP:
			name = "��";
			break;
		case KEY_REMOTE_DOWN:
			name = "��";
			break;
		case KEY_REMOTE_LEFT:
			name = "��";
			break;
		case KEY_REMOTE_RIGHT:
			name = "��";
			break;
		case KEY_REMOTE_OK:
			name = "OK";
			break;
		case KEY_REMOTE_LEFT_DOWN:
			name = "����";
			break;
		case KEY_REMOTE_LEFT_UP:
			name = "����";
			break;
		case KEY_REMOTE_RIGHT_DOWN:
			name = "����";
			break;
		case KEY_REMOTE_RIGHT_UP:
			name = "����";
			break;
			
	   }
    	
    	return name;
    	
    	
    }
	public static void main(String[] args) {
		byte a = 127;
		System.out.println(((byte) (127 | 128)));
	}
}