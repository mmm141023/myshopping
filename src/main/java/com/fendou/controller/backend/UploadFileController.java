package com.fendou.controller.backend;


import com.fendou.commen.ServerResponse;
import com.fendou.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/manage")
public class UploadFileController {


    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 跳转到上传图片的网页
     * @return
     */
    @RequestMapping(value = "/upload" , method = RequestMethod.GET)
    public  String uploadTo(){
        return "UploadFile";
    }

    /**
     * 真正上传的功能
     * @param file
     * @return
     */

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse uploadFile(@RequestParam("file_upload")MultipartFile file){
        ServerResponse serverResponse = uploadFileService.uploadFile(file);
        return serverResponse;
    }
}
