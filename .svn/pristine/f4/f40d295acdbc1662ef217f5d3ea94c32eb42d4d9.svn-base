package com.itvgame.remotecontrol.mina.proto;

import com.itvgame.remotecontrol.server.IoBuffer;


/**
 * Created by Administrator on 2016/10/11.
 */

public class MobileConn extends ProtoBase {
    public String userId;
    public String model;
    public String version;

    public MobileConn(){
    	id = CLASS_MobileConn;
    }

    public MobileConn(String userId, String model, String version) {
        this.userId = userId;
        this.model = model;
        this.version = version;
    }

   
    public String toString() {
        return "MobileConn{" +
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

   
    IoBuffer toObject(byte[] bytes) {
        ioBuf = IoBuffer.wrap(bytes);
        ioBuf.skip(2);
        userId = getStr();
        model = getStr();
        version = getStr();
        return ioBuf;
    }
}
