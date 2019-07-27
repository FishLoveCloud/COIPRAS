package edu.seu.dao;

import edu.seu.model.CountryData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface  CountryDataDAO {

    String TABLE_NAME = "country_data";
   // String SEARCH_FIELD = "id，country_eng,country,WBCode,year,political_risk,economic_risk,social_risk,average_risk,risk_level";

    public Integer insert(CountryData countryData);

    public Integer update(CountryData countryData);

    @Delete({"delete from", TABLE_NAME, "where id=#{id}"})
    public Integer delete(@Param("id") Integer id);

    @Select({"select * from", TABLE_NAME, "where id = #{id}"})
    public CountryData selectById(Integer id);
    @Select({"select * from", TABLE_NAME, "where year = #{year} and country = #{country}"})
    public CountryData selectByYearAndCountry(String yaer,String country);
    @Select({"select * from", TABLE_NAME, "where year = #{year}"})
    public CountryData selectByYear(String yaer);
    @Select({"select * from", TABLE_NAME, "where  country = #{country}"})
    public CountryData selectByCountry(String country);

}
