package com.itvgame.remotecontrol.mina.proto;

import com.itvgame.remotecontrol.server.IoBuffer;


/**
 * Created by Administrator on 2016/10/11.
 */

public class RemoteControl extends ProtoBase {
    public byte key;

    public RemoteControl(){
    	id = CLASS_RemoteControl;
    }

    public RemoteControl(byte keyVal, boolean on){
    	key = (byte) (on? (keyVal & 0x7f) : (keyVal | 0x80));
        id = CLASS_RemoteControl;
    }

     //111 1111    1000 0000
  

     public void setValue(byte keyVal, boolean on){
         key = (byte) (on? (keyVal & 0x7f) : (keyVal | 0x80));
     }

     public byte keyVal(){
         return (byte) (key & 0x7f);
     }

     public boolean isPress(){
         return key > 0;
     }

    public byte[] toByte() {
        initBuf();
        ioBuf.put(key);
        return getBytes();
    }

    
    IoBuffer toObject(byte[] bytes) {
    	ioBuf = IoBuffer.wrap(bytes);
    	ioBuf.skip(1);
    	key = ioBuf.get();
        key = bytes[2];
    	return ioBuf;
    }
    
    public StringBuffer toBinaryStringBuffer(){
      int a = key;
  	  StringBuffer sb = new StringBuffer();
  	  while(a != 0) {
  		  int b = a % 2;
  		  sb.append(b==0?0:1);
  		  a /= 2;
  		 
  	 }
  	  return sb.reverse();
    }

	public String toString() {
		return "RemoteControl [key=" + keyVal() + "]";
	}
    
    
    
}
