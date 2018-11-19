package com.tryingpfq.common.utils;

import com.annotation.Packet;
import com.baidu.bjf.remoting.protobuf.ProtobufIDLGenerator;
import com.tryingpfq.common.packet.AbstractPacket;
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

    private static final String PATH = "protos";

    /**
     * 生成.proto文件
     * @param packet
     */
    public static void createProtoFile(AbstractPacket packet){

        StringBuilder sb = new StringBuilder();
        String code = ProtobufIDLGenerator.getIDL(packet.getClass(),null,null,true);
        /**通过反射获取该包上注解 **/
        Annotation annoPacket = packet.getClass().getAnnotation(Packet.class);

        if(annoPacket != null){
            String des = ((Packet) annoPacket).description();
            if(des != null){
                sb.append("// "+des);
                sb.append("\n"+code);
                //生产文件
                File file = new File(PATH+File.separator+packet.getClass().getSimpleName()+"_"+des+".proto");
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
