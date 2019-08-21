package edu.seu.dao;

import edu.seu.model.CountryData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface CountryDataDAO {

    String TABLE_NAME = "countrydata";
    String SEARCH_FIELD = "id,country_eng,country,WBCode,year,political_risk,economic_risk,social_risk,average_risk,risk_level";

    Integer insert(CountryData countryData);

    Integer update(@Param("countryData") CountryData countryData);

    @Delete({"delete from", TABLE_NAME, "where id=#{id}"})
    Integer delete(@Param("id") Integer id);

    @Select({"select * from", TABLE_NAME, "where id = #{id}"})
    CountryData selectById(Integer id);

    @Select({"select", SEARCH_FIELD, "from", TABLE_NAME, "where WBCode = #{WBCode} and year = #{year}"})
    CountryData selectByYearAndCountryCode(@Param("WBCode") String WBCode, @Param("year") String year);

    @Select({"select * from", TABLE_NAME, "where year = #{year}"})
    List<CountryData> selectByYear(String year);

    @Select({"select * from", TABLE_NAME, "where  country = #{country}"})
    List<CountryData> selectByCountry(String country);

}
