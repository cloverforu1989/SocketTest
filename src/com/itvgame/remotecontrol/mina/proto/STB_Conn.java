package com.itvgame.remotecontrol.mina.proto;

import com.itvgame.remotecontrol.server.IoBuffer;



/**
 * Created by Administrator on 2016/10/11.
 */

public class STB_Conn extends ProtoBase {
    public String userId;
    public String model;
    public String version;

    public STB_Conn(){
    	id = CLASS_STB_Conn;
    }

    public STB_Conn(String userId, String model, String version) {
        this.userId = userId;
        this.model = model;
        this.version = version;
        id = CLASS_STB_Conn;
    }

    
    public String toString() {
        return "STB_Conn{" +
                "userId='" + userId + '\'' +
                ", model='" + model + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

   
    public byte[] toByte() {
        initBuf();
        putStr(userId);
        putStr(model);
        putStr(version);
        return getBytes();
    }

    public IoBuffer toObject(byte[] bytes) {
        ioBuf = IoBuffer.wrap(bytes);
        ioBuf.skip(2);
        userId = getStr();
        model = getStr();
        version = getStr();
        return ioBuf;
    }

}
