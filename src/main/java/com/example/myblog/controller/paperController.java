package com.example.myblog.controller;

import com.example.myblog.mapper.PaperMapper;
import com.example.myblog.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import com.example.myblog.entity.paper;
import com.example.myblog.dto.RespBean;
import com.example.myblog.Util.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@CrossOrigin(originPatterns = {"*"}, allowedHeaders = "*")
@RestController
public class paperController {
    @Value("${file.upload.abspath}")
    private String abspath;
    @Value("${file.upload.mdImageDir}")
    private String mdImageDir;
    @Value("${server.port}")
    private String port;
    @Autowired
    PaperService paperService;

    @Autowired
    PaperMapper paperMapper;
   // @CrossOrigin(allowedHeaders =("*"),originPatterns = "**")
    @GetMapping("/mainpage")
    @ResponseBody
    public Object mainpage(HttpServletResponse response){
            try {
                return paperService.getPaper();
            }catch (Exception e){
                return new RespBean(500,"后台出错了,稍后再试");
            }

    }
    @ResponseBody
    @PostMapping("/paper")
    public Object writepaper(HttpServletRequest request, @RequestBody Map params){
        try {
//            获取必要信息先擦入数据库
            paper new_paper = new paper();
            String author = (String) params.get("author");
            String title = (String) params.get("title");
            String tags = (String) params.get("tag");
            String text = (String)params.get("textarea");
            new_paper.setTextarea(text);
            new_paper.setAuthor(author);
            new_paper.setTag(tags);
            new_paper.setTitle(title);
            paperMapper.setpaper(new_paper);
//            接下来需要处理上传的md文件
            String filename = abspath + title + ".md";
            FileUtil.String2file(text,filename);
            return new RespBean(200,"编写成功");
        }catch (Exception e){
            e.printStackTrace();
            return new RespBean(500,"提交失败");
        }
    }
    @PostMapping("/uploadfile")
    public Object uploadfile(@RequestParam("image") MultipartFile file,HttpServletRequest request ){

            String imgabspath = abspath + "img/";
            String imgurldir = "http:" + request.getHeader("Origin").split(":")[1] + ":" +port + "/img/";
            File f = FileUtil.upload(file,imgabspath);
            Map<String,String> map = new HashMap<>();
            map.put("url",imgurldir + f.getName());
            return map;
    }
    @GetMapping("/getmd/{name}")
    public String getmd(@PathVariable String name){

        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        return FileUtil.md2Html(path + "static/markdown/" + name + ".md");

    }
}
