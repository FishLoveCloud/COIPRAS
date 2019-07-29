package edu.seu.model;

public class CountryData {
    /**
     * @Author lec
     * @Date 2019-7-24 14:59
     */
    private Integer id;
    private String country;      //国家
    private String countryEng;  //英文名
    private String WBcode;      //国家代码
    private String year;        //年份
    private Double politicalRisk;   //政治风险
    private Double economicRisk;       //经济风险
    private Double socialRisk;      //社会风险
    private Double averageRisk;     //综合风险
    private String riskLevel;   //风险等级

    public CountryData() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryEng() {
        return countryEng;
    }

    public void setCountryEng(String countrEng) {
        this.countryEng = countryEng;
    }

    public String getWBcode() {
        return WBcode;
    }

    public void setWBcode(String WBcode) {
        this.WBcode = WBcode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getPoliticalRisk() {
        return politicalRisk;
    }

    public void setPoliticalRisk(Double politicalRisk) {
        this.politicalRisk = politicalRisk;
    }

    public Double getEconomicRisk() {
        return economicRisk;
    }

    public void setEconomicRisk(Double economicRisk) {
        this.economicRisk = economicRisk;
    }

    public Double getSocialRisk() {
        return socialRisk;
    }

    public void setSocialRisk(Double socialRisk) {
        this.socialRisk = socialRisk;
    }

    public Double getAverageRisk() {
        return averageRisk;
    }

    public void setAverageRisk(Double averageRisk) {
        this.averageRisk = averageRisk;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", year= '" + year + '\'' +
                ", country= '" + country + '\'' +
                ", countryEng = '" + countryEng + '\''+
                ", WBCode = '" + WBcode +
                ", politicalRisk= '" + politicalRisk + '\'' +
                ", economicRisk= '" + economicRisk + '\'' +
                ", socialRisk= '" + socialRisk +
                ", averageRisk= '" + averageRisk +
                ", riskLevel = '" + riskLevel +
                '}';
    }
}
