package edu.seu.model;

public class Country {
    private Integer id;
    private CountryData countryData;
    private String year;
    private String countryName;
    private String WBCode;
    private CountryRisk countryRisk;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CountryData getCountryData() {
        return countryData;
    }

    public void setCountryData(CountryData countryData) {
        this.countryData = countryData;
        setYear();
        setWBCode();
        setCountryName();
        setCountryRisk();
    }
    public void setYear()
    {
        this.year = countryData.getYear();
    }
    public void setCountryName()
    {
        this.countryName = countryData.getCountry();
    }
    public void setCountryRisk()
    {
        this.countryRisk = new CountryRisk();
        countryRisk.setSocialRisk(countryData.getSocialRisk());
        countryRisk.setPoliticalRisk(countryData.getPoliticalRisk());
        countryRisk.setEconomicRisk(countryData.getEconomicRisk());
        countryRisk.setAverageRisk(countryData.getAverageRisk());
        countryRisk.setRiskLevel(countryData.getRiskLevel());
    }
    public void setWBCode(){
        this.WBCode = countryData.getWBcode();
    }
}
