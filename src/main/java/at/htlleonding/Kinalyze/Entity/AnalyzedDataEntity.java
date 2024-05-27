package at.htlleonding.Kinalyze.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "ANALYZED_DATA")
public class AnalyzedDataEntity {
    @Id
    private String user_uid;

    @Id
    private String file_name;

    private int VAR_ENG;

    private double complex;

    private int data_type;

    private int bad_inits;

    @Lob
    private String bad_pracs;

    @Lob
    private String comments;

    @Lob
    private String constructs;

    @Lob
    private String unreadables;

    @Lob
    private String naming_convs;

    private String performance;

    private String idents;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String varEng_Long;

    public AnalyzedDataEntity(String user_uid, String fileName,
                      int varEng, double complex, int dataType, int badInits,
                      String badPracs, String comments, String constructs, String unreadables,
<<<<<<< HEAD
                      String namingConvs, String performance, double lineMethodRatio, String varEng_Long) {
=======
                      String namingConvs, String performance, String spaces) {
>>>>>>> 3b436d9cf7e8e6c9d0a80f4dd4b3b73c77e2f639
        this.user_uid = user_uid;
        this.file_name = fileName;
        this.VAR_ENG = varEng;
        this.complex = complex;
        this.data_type = dataType;
        this.bad_inits = badInits;
        this.bad_pracs = badPracs;
        this.comments = comments;
        this.constructs = constructs;
        this.unreadables = unreadables;
        this.naming_convs = namingConvs;
        this.performance = performance;
<<<<<<< HEAD
        this.linemethodratio = lineMethodRatio;
        this.varEng_Long = varEng_Long;
=======
        this.idents = spaces;
>>>>>>> 3b436d9cf7e8e6c9d0a80f4dd4b3b73c77e2f639
    }
    public AnalyzedDataEntity() {

    }

<<<<<<< HEAD
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
=======
    @Override
    public String toString() {
        return "FileEntity{" +
                "user_uid='" + user_uid + '\'' +
                ", file_name='" + file_name + '\'' +
                ", var_eng=" + VAR_ENG +
                ", complex=" + complex +
                ", data_type=" + data_type +
                ", bad_inits=" + bad_inits +
                ", bad_pracs='" + bad_pracs + '\'' +
                ", comments='" + comments + '\'' +
                ", constructs='" + constructs + '\'' +
                ", unreadables='" + unreadables + '\'' +
                ", naming_convs='" + naming_convs + '\'' +
                ", performance='" + performance + '\'' +
                ", spaces='" + idents + '\'' +
>>>>>>> 3b436d9cf7e8e6c9d0a80f4dd4b3b73c77e2f639
                '}';
    }
}
