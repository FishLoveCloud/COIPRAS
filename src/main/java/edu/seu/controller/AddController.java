package edu.seu.controller;

import edu.seu.base.CodeEnum;
import edu.seu.base.CommonResponse;
import edu.seu.exceptions.COIPRASExceptions;
import edu.seu.model.HostHolder;
import edu.seu.model.User;
import edu.seu.service.CountryDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/addCountry")
public class AddController {
    private static Log log = LogFactory.getLog(AddController.class);
    @Autowired
    private CountryDataService countryDataService;

    @Autowired
    private HostHolder hostHolder;
    @RequestMapping(value = "batchimport", method = RequestMethod.POST)
    public String batchimport(@RequestParam(value = "filename") MultipartFile file,
                              HttpServletRequest request, HttpServletResponse response) {
        log.info("AddController ..batchimport() start");
        //判断文件是否为空
        if (file == null) return null;
        //获取文件名
        String name = file.getOriginalFilename();
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size = file.getSize();
        if (name == null || ("").equals(name) && size == 0) return null;
        User user = hostHolder.getUser();
        if(user.getLevel() < 1)
        {
            String Msg = "只有管理员可以上传数据！";
            request.getSession().setAttribute("msg", Msg);
            return "/addCountry";
        }
        //批量导入。参数：文件名，文件。
        try {
            boolean b = countryDataService.batchImport(name, file);
            if (b) {
                String Msg = "批量导入EXCEL成功！";
                request.getSession().setAttribute("msg", Msg);
            } else {
                String Msg = "批量导入EXCEL失败！";
                request.getSession().setAttribute("msg", Msg);
            }
            return "/index";
        }catch (COIPRASExceptions e){
           // LOGGER.info(e.getMessage() + " parameter: document={}", file.getName());
            return new CommonResponse(CodeEnum.DOCUMENT_ERROR.getValue(), e.getMessage()).toJSONString();
        }catch(Exception e){
            return new CommonResponse(CodeEnum.UNKNOWN_ERROR.getValue(), e.getMessage()).toJSONString();
        }
    }

}
