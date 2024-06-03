package at.htlleonding.Kinalyze.Entity;

import java.sql.Timestamp;

public class KSession {
    private int s_id;
    private Timestamp s_date;
    private int c_id;

    public KSession(int s_id, Timestamp s_date, int c_id) {
        this.s_id = s_id;
        this.s_date = s_date;
        this.c_id = c_id;
    }

    // Getters and Setters
    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public Timestamp getS_date() {
        return s_date;
    }

    public void setS_date(Timestamp s_date) {
        this.s_date = s_date;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }
}

