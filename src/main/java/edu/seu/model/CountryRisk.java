package edu.seu.model;

public class CountryRisk {
    private Double politicalRisk;   //政治风险
    private Double economicRisk;       //经济风险
    private Double socialRisk;      //社会风险
    private Double averageRisk;     //综合风险


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

}
