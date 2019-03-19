package com.fendou.service.impl;

import com.fendou.commen.ServerResponse;
import com.fendou.service.UploadFileService;
import com.fendou.utils.PropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class UploadFileServiceImpl implements UploadFileService {


    @Override
    public ServerResponse uploadFile(MultipartFile file) {

        if (file == null){
            return ServerResponse.ResponseWhenError("文件不能为空");
        }
        String originalFilename = file.getOriginalFilename();
        String sufstring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String random = UUID.randomUUID().toString();
        String newName = random+sufstring;
        String desPath = "D:/Spring_workspace/SpringMVC_workspace/ftpfile";
        File file1 = new File(desPath);
        if(!file1.exists()){
            file1.setWritable(true);
            file1.mkdirs();
        }
        Map<String,String> map = new HashMap();
        try {
            file.transferTo(new File(desPath+"/"+newName));
            map.put("uri", newName);
            map.put("url", PropertiesUtils.getByKey("localImg")+"/"+newName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.ResponseWhenSuccess(map);
    }
}
