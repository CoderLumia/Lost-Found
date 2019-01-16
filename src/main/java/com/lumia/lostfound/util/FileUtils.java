package com.lumia.lostfound.util;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class FileUtils {

    //图片的真实路径
    private static final String PIC_PATH = Constant.PATH_CLASS_ROOT + File.separator + "static" + File.separator + "images" + File.separator;

    /**
     * 上传文件
     * @param file  文件对象
     */
    public static  String uploadFile(MultipartFile file) throws IOException {
        //获取文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID()+suffix;
        File uploadFile = new File(PIC_PATH + fileName);
        file.transferTo(uploadFile);
        return fileName;
    }


    /**
     * 删除pic路径下的图片
     * @param picPath
     */
    public static void deleteFile(String picPath){
        String filePath = PIC_PATH + picPath;
        File file = new File(filePath);
        if (file.exists()){
            file.delete();
        }
    }
}
