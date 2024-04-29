package at.htlleonding.Kinalyze.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ANALYZED_DATA")
public class AnalyzedDataEntity {
    @Id
    private String user_uid;
    private String file_name;

    private int VAR_ENG;

    private String indents;

    private double complex;

    private int data_type;

    private int bad_inits;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String bad_pracs;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String comments;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String constructs;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String unreadables;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String naming_convs;

    private String performance;

    private double linemethodratio;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String varEng_Long;

    public AnalyzedDataEntity(String user_uid, String fileName,
                      int varEng, String indents, double complex, int dataType, int badInits,
                      String badPracs, String comments, String constructs, String unreadables,
                      String namingConvs, String performance, double lineMethodRatio, String varEng_Long) {
        this.user_uid = user_uid;
        this.file_name = fileName;
        this.VAR_ENG = varEng;
        this.indents = indents;
        this.complex = complex;
        this.data_type = dataType;
        this.bad_inits = badInits;
        this.bad_pracs = badPracs;
        this.comments = comments;
        this.constructs = constructs;
        this.unreadables = unreadables;
        this.naming_convs = namingConvs;
        this.performance = performance;
        this.linemethodratio = lineMethodRatio;
        this.varEng_Long = varEng_Long;
    }
    public AnalyzedDataEntity() {

    }

    public String getUser_uid() {
        return user_uid;
    }

    public String getFile_name() {
        return file_name;
    }

    public int getVAR_ENG() {
        return VAR_ENG;
    }

    public String getIndents() {
        return indents;
    }

    public double getComplex() {
        return complex;
    }

    public int getData_type() {
        return data_type;
    }

    public int getBad_inits() {
        return bad_inits;
    }

    public String getBad_pracs() {
        return bad_pracs;
    }

    public String getComments() {
        return comments;
    }

    public String getConstructs() {
        return constructs;
    }

    public String getUnreadables() {
        return unreadables;
    }

    public String getNaming_convs() {
        return naming_convs;
    }

    public String getPerformance() {
        return performance;
    }

    public double getLinemethodratio() {
        return linemethodratio;
    }

    public String getVarEng_Long() {
        return varEng_Long;
    }

    @Override
    public String toString() {
        return "AnalyzedDataEntity{" + '\n' + '\n' +
                "user_uid=" + user_uid + '\n' + '\n' +
                "file_name=" + file_name + '\n' + '\n' +
                "VAR_ENG=" + VAR_ENG + '\n' + '\n' +
                "indents=" + indents + '\n' + '\n' +
                "complex=" + complex + '\n' + '\n' +
                "data_type=" + data_type + '\n' + '\n' +
                "bad_inits=" + bad_inits + '\n' + '\n' +
                "bad_pracs=" + bad_pracs + '\n' + '\n' +
                "comments=" + comments + '\n' + '\n' +
                "constructs=" + constructs + '\n' + '\n' +
                "unreadables=" + unreadables + '\n' + '\n' +
                "naming_convs=" + naming_convs + '\n' + '\n' +
                "performance=" + performance + '\n' + '\n' +
                "lmr=" + linemethodratio + '\n' + '\n' +
                "varEng_Long=" + varEng_Long + '\n' + '\n' +
                '}';
    }
}
