package at.htlleonding.Kinalyze.Entity;

public class KAnalysis {
    private int a_id;
    private String a_type;
    private String a_result;
    private int s_id;

    public KAnalysis(int a_id, String a_type, String a_result, int s_id) {
        this.a_id = a_id;
        this.a_type = a_type;
        this.a_result = a_result;
        this.s_id = s_id;
    }

    // Getters and Setters
    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_type() {
        return a_type;
    }

    public void setA_type(String a_type) {
        this.a_type = a_type;
    }

    public String getA_result() {
        return a_result;
    }

    public void setA_result(String a_result) {
        this.a_result = a_result;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }
}
