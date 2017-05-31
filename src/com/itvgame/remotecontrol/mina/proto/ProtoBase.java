package com.itvgame.remotecontrol.mina.proto;




import java.io.UnsupportedEncodingException;

import com.hst.game.GameScene;
import com.itvgame.remotecontrol.main.RemoteControlMain;
import com.itvgame.remotecontrol.server.IoBuffer;

/**
 * Created by Administrator on 2016/10/11.
 */

public abstract class ProtoBase {

	public static final byte CLASS_STB_Conn = 0;
	public static final byte CLASS_MobileConn = 1;
	public static final byte CLASS_ConnectResule = 2;
	public static final byte CLASS_RemoteControl = 3;
	public static final byte CLASS_StbNotify = 5;
	public static final byte CLASS_StbNotify2 = 4;
	static byte cur_ver = 0;
	IoBuffer ioBuf;
	public byte id;

	static byte ClassList[] = new byte[] { CLASS_STB_Conn, CLASS_MobileConn, CLASS_ConnectResule, CLASS_RemoteControl,CLASS_StbNotify2,CLASS_StbNotify};

	byte getClassIdx(byte id) {

		for (byte idx = 0; idx < ClassList.length; idx++) {
			if (id == ClassList[idx]) {
				return idx;
			}
		}
		return -1;
	}

	 void initBuf() {
	 ioBuf = IoBuffer.allocate(128).setAutoExpand(true);
	 ioBuf.put(cur_ver);
	 ioBuf.put(id);
	
	}

	void putStr(String str) {
		byte[] bytes = null;
		try {
			bytes = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ioBuf.putShort((short) bytes.length);
		ioBuf.put(bytes);
		// try {
		// ioBuf.putString(str, Charset.forName("UTF-8").newEncoder());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	String getStr() {
		short len = ioBuf.getShort();
		byte[] bytes = new byte[len];
		ioBuf.get(bytes);
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		// try {
		// return ioBuf.getString(Charset.forName("UTF-8").newDecoder());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// return null;
	}

	byte[] getBytes() {
		int pos = ioBuf.position();
		ioBuf.flip();
		byte[] bytes = new byte[pos];
		ioBuf.get(bytes);
		ioBuf.free();
//		
//		for(int i = 0; i < bytes.length; i++) {
//			System.out.print(bytes[i]+"_");
//		}
		return bytes;
	}

	public static Object[] decode(byte[] bytes) {
		
		//StringBuffer sb = new StringBuffer();
//		
//		sb.append("原始数据[");
//		for(int j = 0; j < bytes.length; j ++) {
//			sb.append(bytes[j]+" ");
//		}
//		sb.append("]");
		 //GameScene.addMsg(sb.toString());
		
		
		
		Object[] object = new Object[3];
		int count = 0;
		boolean decodEnd = false;
		IoBuffer ioBuffer = null;
		
		outer:while(!decodEnd && bytes.length > 2) {
			if (bytes[0] == cur_ver) {
				byte idx = bytes[1];
				if (idx < ClassList.length) {
					try {
						ProtoBase obj = null;
						switch(idx) {
						case CLASS_STB_Conn:
							obj = new STB_Conn();
							break;
						case CLASS_MobileConn:
							obj = new MobileConn();
							break;
						case CLASS_ConnectResule:
							obj = new ConnectResule();
							break;
						case CLASS_RemoteControl:
							obj = new RemoteControl();
							break;
						default:
							break outer;
							
						}
						if(count >= object.length) {
							Object[] objArr = new Object[object.length + 2];
							System.arraycopy(object, 0, objArr, 0, object.length);
							object = objArr;
						}
						
						
						if(!decodEnd) {
							try {
								ioBuffer = obj.toObject(bytes);
								bytes = ioBuffer.getRemainingBytes();
								object[count++] =  obj;
								if(bytes == null) {
									break outer;
								}
							}catch(Exception e) {
								break outer;
							} 
							
						}else {
							break;
						}
							
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					break;
				}
			}else if(bytes[0] == 1) {//心跳包
				RemoteControlMain.log("msg", "心跳包~服务器回应");
				if(bytes.length >=4 ) {
				   byte[] b = new byte[bytes.length - 4];
				   System.arraycopy(bytes, 0, b, 0, b.length);
				   bytes = b;
				}else {
					bytes = null;
					break;
				}
			}else {
				object[count++] =  bytes;
				break outer;
				
			}
		}
		
		return object;
	}

	public abstract byte[] toByte();

	abstract IoBuffer toObject(byte[] bytes);
	

}
