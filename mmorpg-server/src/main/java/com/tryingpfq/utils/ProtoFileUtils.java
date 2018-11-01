package com.tryingpfq.utils;

import com.annotation.Packet;
import com.baidu.bjf.remoting.protobuf.ProtobufIDLGenerator;
import com.tryingpfq.packet.AbstractPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.lang.annotation.Annotation;
/**
 * @author tryingpfq
 * @date 2018/11/1
 */
public class ProtoFileUtils {
    private static Logger logger = LoggerFactory.getLogger(ProtoFileUtils.class);

    /**
     * 生成.proto文件
     * @param packet
     */
    public static void createProtoFile(AbstractPacket packet){
        /**
         * 增加对.proto文件的处理
         */
        StringBuilder sb = new StringBuilder();
        String code = ProtobufIDLGenerator.getIDL(packet.getClass(),null,null,true);
        /**通过反射获取该包上注解 **/
        Annotation descriptePacket = packet.getClass().getAnnotation(Packet.class);

        if(descriptePacket != null){
            String des = ((Packet) descriptePacket).description();
            if(des != null){
                sb.append("//"+des);
                sb.append("\n"+code);

                String path = "D:\\mmorpg\\protofile" + packet.getPacketId() + "_" + packet.getClass().getSimpleName()+"_"+des+".proto";
                //生产文件
                File file = new File(path);
                try{
                    if(FileUtils.createFile(file)){
                        FileWriter fw = new FileWriter(file);
                        fw.write(sb.toString());
                        fw.flush();
                        fw.close();
                        logger.info("生成文件:{}",file.getAbsolutePath());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    logger.error("文件创建失败");
                }
            }
        }
    }
}
