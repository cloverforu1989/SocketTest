package com.itvgame.remotecontrol.mina.proto;

import com.itvgame.remotecontrol.server.IoBuffer;



/**
 * Created by Clover4U on 2017/2/25.
 */

public class StbNotify extends ProtoBase {
    public int type;
    public String mess;

    public StbNotify(int type, String mess) {
    	this.type = type;
    	this.mess = mess;
    	id = ProtoBase.CLASS_StbNotify;
    }
    public byte[] toByte() {
        initBuf();
        ioBuf.putInt(type);
        putStr(mess);
        return getBytes();
    }

   
    IoBuffer toObject(byte[] bytes) {
        ioBuf = IoBuffer.wrap(bytes);
        ioBuf.skip(2);
        type = ioBuf.getInt();
        mess = getStr();
        return ioBuf;

    }


	public String toString() {
		return "StbNotify [type=" + type + ", mess=" + mess + "]";
	}
    

}
