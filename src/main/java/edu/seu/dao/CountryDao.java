package edu.seu.dao;

import edu.seu.model.Country;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountryDao {
     Integer insertCountry(Country country);
}
