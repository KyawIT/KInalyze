package at.htlleonding.Kinalyze.Entity;

public class KAnalysisDetail {
    private int ad_id;
    private int ad_row;
    private String ad_content;
    private int a_id;

    public KAnalysisDetail(int ad_id, int ad_row, String ad_content, int a_id) {
        this.ad_id = ad_id;
        this.ad_row = ad_row;
        this.ad_content = ad_content;
        this.a_id = a_id;
    }

    // Getters and Setters
    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public int getAd_row() {
        return ad_row;
    }

    public void setAd_row(int ad_row) {
        this.ad_row = ad_row;
    }

    public String getAd_content() {
        return ad_content;
    }

    public void setAd_content(String ad_content) {
        this.ad_content = ad_content;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }
}

