package com.example.myblog.Util;

import org.pegdown.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.jsoup.*;
import java.io.*;
import java.util.Arrays;

public class FileUtil {
    public static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static boolean String2file(String res,String filename){

        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try{
            File distFile = new File(filename);
            System.out.println(distFile.getAbsolutePath());
            System.out.println(distFile.getParentFile());
            if (!distFile.getParentFile().exists()) {
                System.out.println(distFile.getParentFile());
                distFile.getParentFile().mkdirs();
            }
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            char[] buf =new char[1024];
            int len = 1;
            while((len = bufferedReader.read(buf))!=-1){
                //不断的读取数据存到buff中,然后写，然后读取结束之后会返回-1
                bufferedWriter.write(buf,0,len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        }catch (IOException E){
            E.printStackTrace();
            flag = false;
            return flag;
        }finally {
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch (IOException E){
                    E.printStackTrace();
                }
            }

        }
        return flag;
    }
    public static String getExtensionName(String filename){
        if((filename != null)&&(filename.length()>0)){
            int pos = filename.lastIndexOf('.');
           if((pos>-1)&&(pos+1<filename.length())){
               return filename.substring(pos+1);
            }
        }
        return filename;
    }
    public static  String getFileNameNoExt(String filename){
        int pos = filename.lastIndexOf(".");
        if((pos>-1)){
            return filename.substring(0,pos);
        }
        return filename;
    }
    //上传图片
    public static File upload(MultipartFile file,String filepath){
        //Date date = new Date();
        //SimpleDateFormat format = new SimpleDateFormat("yyyymmddhhmmss");
        String suffix = getExtensionName(file.getOriginalFilename());
        String name = getFileNameNoExt(file.getOriginalFilename());
        //String nowstr =  format.format(date) ;
        try{
            String filename = name + "." + suffix;
            String path = filepath + filename;
            File dest = new File(path).getCanonicalFile();
            //检测是否存在目录
            if(!dest.getParentFile().exists()){
                if(!dest.getParentFile().mkdirs()){
                    System.out.println("create error");
                }
            }
            file.transferTo(dest);
            return dest;
        }catch (IOException E){
            E.printStackTrace();
        }
        return null;
    }
    public static String md2Html(String path){
        try {
            FileReader fileReader = new FileReader(path);
            char[] c = new char[1024];
            int len = 0;
            String html = new String("");
            while((len = fileReader.read(c))!=-1){
                html += new String(Arrays.copyOf(c,len));
            }
          // PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
          // html = pdp.markdownToHtml(html);
            // html = Jsoup.parse(html);
            return html;
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
//            String line = null;
//            String mdContent = "";
//            while ((line = br.readLine()) != null) {
//                mdContent += line + "\r\n";
//            }
//            //PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
//
//            //tring htmlContent = pdp.markdownToHtml(mdContent);
//            return mdContent;
        }
        catch (Exception E){
            E.printStackTrace();
        }
        return null;
    }

}
