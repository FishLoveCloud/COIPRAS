package edu.seu.cpopirs_util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class getFileInfo {
    // 自定义上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";

    //MultipartFile上传文件
    public static String getFileInfo(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
        String filePath = new String();
        String uploadPath = request.getSession().getServletContext().getRealPath("./") + UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        //判断文件是否为空
        if (!file.isEmpty()) {
            try {
                //文件的保存路径
                filePath = request.getSession().getServletContext().getRealPath("/") + UPLOAD_DIRECTORY + File.separator + file.getOriginalFilename();

                //转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }//函数结束符

}
