package edu.seu.controller;

import com.alibaba.fastjson.JSON;
import edu.seu.base.CodeEnum;
import edu.seu.base.CommonResponse;
import edu.seu.exceptions.COIPRASExceptions;
import edu.seu.model.CountryData;
import edu.seu.model.HostHolder;
import edu.seu.model.User;
import edu.seu.service.CountryDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/country")
public class CountryController {
    public static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    CountryDataService countryDataService;

    @Autowired
    HostHolder hostHolder;
    @ResponseBody
    @RequestMapping("/search")
    public String searchCountry(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = hostHolder.getUser();
            int authority = 0;
            if(user.getName() != null)
            {
                authority = 1;
            }
            String type = request.getParameter("type");
            String time = request.getParameter("time");
            String countries = request.getParameter("countries");
            String result = null;
            if (type.equals("0")) {
                result = ByTime(countries, time,authority);
            } else {
                result = BySpace(countries, time,authority);
            }
            response.addHeader("result", result);
            return JSON.toJSONString(result);
            // return new CommonResponse(CodeEnum.SUCCESS.getValue(), "数据查询成功").toJSONString();
//        }catch(COIPRASExceptions e)
//        {
//            //LOGGER.info(e.getMessage() + "parameter: name = {}",name);
//            return new CommonResponse(CodeEnum.DOCUMENT_ERROR.getValue(),e.getMessage()).toJSONString();
        } catch (Exception e) {
            //LOGGER.info(e.getMessage() + "/country/searchByTime,parameter: name = {},year = {}",name,year);
            return new CommonResponse(CodeEnum.USER_ERROR.getValue(), e.getMessage()).toJSONString();
        }
    }

    private String ByTime(String WBCode, String year,int authority) throws COIPRASExceptions {

        List<CountryData> countryDataList = new ArrayList<>();
        String code = WBCode.substring(2, WBCode.length() - 2);
        String[] years = year.substring(1, year.length() - 1).split(",");
        for (int i = 0; i < years.length; i++) {
            CountryData countryData = countryDataService.CheckCountryNameAndYear(code, years[i].substring(1, years[i].length() - 1));
            countryDataList.add(countryData);
        }
        return toJSONString(countryDataList,"0",year, WBCode,authority+"");
    }

    private String BySpace(String WBCode, String year,int authority) throws COIPRASExceptions {
        List<CountryData> countryDataList = new ArrayList<>();
        String[] countries = WBCode.substring(1, WBCode.length() - 1).split(",");
        for (int i = 0; i < countries.length; i++) {
            CountryData countryData = countryDataService.CheckCountryNameAndYear(countries[i].substring(1, countries[i].length() - 1), year.substring(2, year.length() - 2));
            countryDataList.add(countryData);
        }
        System.out.println(countryDataList);
        return toJSONString(countryDataList,"1",year,WBCode,authority+"");
    }
//    private String toJSONString(List<CountryData> countryDataList,String type,String year,String country,String authority)
//    {
//        String risk = "";
//        String riskLevel = "";
//        for(CountryData cd :countryDataList)
//        {
//            risk += cd.getCountryRisk();
//            risk += ",";
//            riskLevel += cd.getRiskLevel();
//            riskLevel += ",";
//        }
//        return String.format("{'type': %s, 'time': %s, 'country': %s, 'data':[%s], 'level':[%s] ,'auth':%s}", type,year,"'"+country+"'",risk.substring(0,risk.length()-1),riskLevel.substring(0,riskLevel.length()-1),authority);
//    }
    private String toJSONString(List<CountryData> countryDataList,String type,String year,String country,String authority)
    {
        String risk = "";
        String riskLevel = "";
        for(CountryData cd :countryDataList)
        {
            risk += cd.getCountryRisk();
            risk += ",";
            riskLevel += cd.getRiskLevel();
            riskLevel += ",";
        }
        return String.format("{'type': %s, 'time': %s, 'country': %s, 'data':[%s],'level':[%s] ,'auth':%s}", type,year,country,risk.substring(0,risk.length()-1),riskLevel.substring(0,riskLevel.length()-1),authority);
    }


}
