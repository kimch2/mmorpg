package file.csv;


import com.google.common.base.Charsets;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author tryingpfq
 * @date 2019/6/10 15:34
 * 当要读取excel文件内容是 可以转为csv格式解析，会比较方便一些 但要注意格式
 */
public class CsvReader {

    static{
        try {
            Path path = Paths.get("C:\\Users\\4083\\Desktop\\test.csv");
            try(BufferedReader reader = Files.newBufferedReader(path,Charsets.UTF_8)){
                //第一行 可以特殊处理
                for (; ; ) {
                    String line = reader.readLine();
                    if(line == null)
                        break;

                    //logic
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
