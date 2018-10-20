package com.serial;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * Created by trying on 2018/10/20.
 * 自定义序列化接口
 */
public abstract class Serializer {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    protected ByteBuf writeBuffer;

    protected ByteBuf readBuffer;

    /**
     * 反序列化具体实现
     */
    protected abstract void read();

    /**
     * 序列化具体实现
     */
    protected abstract void write();

    /**
     * 从byte数组获取数据
     * @param bytes
     * @return
     */
    public Serializer readFromBytes(byte[] bytes){
        readBuffer = BufferFactory.getBuffer(bytes);
        read();
        readBuffer.clear();
        return this;
    }

    /**
     * 从buffer获取数据
     * @param readBuffer
     */
    public void readFromBuffer(ByteBuf readBuffer){
        this.readBuffer = readBuffer;
        read();
    }

    /**
     * 写入本地buffer
     * @return
     */
    public ByteBuf write2LocalBuff(){
        writeBuffer = BufferFactory.getBuffer();
        write();
        return writeBuffer;
    }

    /**
     * 写入目标buffer
     * @param buffer
     * @return
     */
    public ByteBuf write2TargetBuff(ByteBuf buffer){
        writeBuffer = buffer;
        write();
        return writeBuffer;
    }

    public byte[] getBytes(){
        write2LocalBuff();
        byte[] bytes = null;
        if(writeBuffer.writerIndex() == 0){
            bytes = new byte[0];
        }else{
            bytes = new byte[writeBuffer.writerIndex()];
            writeBuffer.readBytes(bytes);
        }
        writeBuffer.clear();
        return bytes;
    }

    public byte readByte() {
        return readBuffer.readByte();
    }

    public short readShort() {
        return readBuffer.readShort();
    }

    public int readInt() {
        return readBuffer.readInt();
    }

    public long readLong() {
        return readBuffer.readLong();
    }

    public float readFloat() {
        return readBuffer.readFloat();
    }

    public double readDouble() {
        return readBuffer.readDouble();
    }


    public String readString() {
        int size = readBuffer.readShort();
        if (size <= 0) {
            return "";
        }

        byte[] bytes = new byte[size];
        readBuffer.readBytes(bytes);

        return new String(bytes,CHARSET);
    }

    public Serializer writeLong(Long value) {
        writeBuffer.writeLong(value);
        return this;
    }

    public Serializer writeString(String value) {
        if (value == null || value.isEmpty()) {
            writeShort((short) 0);
            return this;
        }

        byte data[] = value.getBytes(CHARSET);
        short len = (short) data.length;
        writeBuffer.writeShort(len);
        writeBuffer.writeBytes(data);
        return this;
    }

    public Serializer writeShort(Short value) {
        writeBuffer.writeShort(value);
        return this;
    }

    public Serializer writeInt(Integer value) {
        writeBuffer.writeInt(value);
        return this;
    }

}
