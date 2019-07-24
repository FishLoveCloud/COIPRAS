package edu.seu.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)

public class CountryData {
    /**
     * @Author lec
     * @Date 2019-7-24 14:59
     */
    private Integer id;
    private String country;      //国家
    private String country_eng;  //英文名
    private String WBcode;      //国家代码
    private String year;        //年份
    private String politicalRisk;   //政治风险
    private String economicRisk;       //经济风险
    private String socialRisk;      //社会风险
    private String averageRisk;     //综合风险
    private String riskLevel;   //风险等级

    public CountryData() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", country=" + country + '\'' +
                ", politicalRisk='" + politicalRisk + '\'' +
                ", economicRisk='" + economicRisk + '\'' +
                ", socialRisk=" + socialRisk +
                ", averageRisk=" + averageRisk +
                '}';
    }
}
