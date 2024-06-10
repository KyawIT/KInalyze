package at.htlleonding.Kinalyze.Entity;

public class KCode {
    private int c_id;
    private byte[] c_data;
    private String c_filename;
    private String c_fileending;

    public KCode(int c_id, byte[] c_data, String c_filename, String c_fileending) {
        this.c_id = c_id;
        this.c_data = c_data;
        this.c_filename = c_filename;
        this.c_fileending = c_fileending;
    }

    // Getters and Setters
    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public byte[] getC_data() {
        return c_data;
    }

    public void setC_data(byte[] c_data) {
        this.c_data = c_data;
    }

    public String getC_filename() {
        return c_filename;
    }

    public void setC_filename(String c_filename) {
        this.c_filename = c_filename;
    }

    public String getC_fileending() {
        return c_fileending;
    }

    public void setC_fileending(String c_fileending) {
        this.c_fileending = c_fileending;
    }
}
