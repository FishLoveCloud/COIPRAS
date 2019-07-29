package edu.seu.service;

import edu.seu.base.CodeEnum;
import edu.seu.cpopirs_util.ProcessCSV;
import edu.seu.dao.CountryDao;
import edu.seu.dao.CountryDataDAO;
import edu.seu.dao.UserDAO;
import edu.seu.exceptions.COIPRASExceptions;
import edu.seu.model.Country;
import edu.seu.model.CountryData;
import edu.seu.model.HostHolder;
import edu.seu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
@Transactional
public class CountryDataService {

    @Autowired
    private CountryDataDAO countryDataDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private CountryDao countryDao;

    private void checkAuthority()throws COIPRASExceptions
    {
        User user = hostHolder.getUser();
        if (user.getLevel() != 2)
        {
            throw new COIPRASExceptions(CodeEnum.DOCUMENT_ERROR,"只有管理员可以上传文档");
        }
    }

    public void insertNewCountryData(CountryData countryData) throws COIPRASExceptions
    {
        checkAuthority();
        if(countryDataDAO.insert(countryData) != 1)
        {
            throw new COIPRASExceptions(CodeEnum.DOCUMENT_ERROR,"数据插入失败");
        }
    }
    public void updateCountryData(CountryData countryData) throws COIPRASExceptions
    {
        checkAuthority();
        if(countryDataDAO.update(countryData) != 1)
        {
            throw new COIPRASExceptions(CodeEnum.DOCUMENT_ERROR,"数据更新失败");

        }
    }
    private void checkCountryYear(String year)throws COIPRASExceptions
    {
        if(year == null)
        {
            throw new COIPRASExceptions(CodeEnum.DOCUMENT_ERROR,"参数不能为空");
        }
        List<CountryData> countryDataList = countryDataDAO.selectByYear(year);
        if(countryDataList.isEmpty())
        {
            throw new COIPRASExceptions(CodeEnum.DOCUMENT_ERROR,"没有该年数据");

        }

    }
    private void CheckCountryName(String name) throws COIPRASExceptions
    {
        if(name == null)
        {
            throw new COIPRASExceptions(CodeEnum.DOCUMENT_ERROR,"参数不能为空");
        }
        List<CountryData> countryDataList = countryDataDAO.selectByCountry(name);
        if(countryDataList.isEmpty())
        {
            throw new COIPRASExceptions(CodeEnum.DOCUMENT_ERROR,"没有该国家/地区数据");

        }
    }
    private void CheckCountryNameAndYear(String name,String year) throws COIPRASExceptions
    {
        if(name == null || year == null)
        {
            throw new COIPRASExceptions(CodeEnum.DOCUMENT_ERROR,"参数不能为空");

        }
        CountryData countryData = countryDataDAO.selectByYearAndCountry(year,name);
        if(countryData == null)
        {
            throw new COIPRASExceptions(CodeEnum.DOCUMENT_ERROR,"没有条件要求数据");

        }
    }
    public boolean batchImport(String name, MultipartFile file)
    {
        boolean b = false;
        ProcessCSV processCSV = new ProcessCSV();
        List<CountryData> countryDataList = processCSV.getExcelInfo(name,file);
        if(countryDataList != null){
            b = true;
        }
        for(CountryData countryData :countryDataList)
        {
              //countryDataDAO.insert(countryData);
             Country country = new Country();
             country.setCountryData(countryData);
             countryDao.insertCountry(country);
              System.out.println(countryData);
        }
        return b;
    }


}
