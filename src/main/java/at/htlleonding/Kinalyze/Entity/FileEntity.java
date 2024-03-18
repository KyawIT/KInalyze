package at.htlleonding.Kinalyze.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "FILE_ATTRIBUTES")
public class FileEntity {
    @Id
    private String user_uid;
    private String file_name;

    private String file_type;

    private Long file_size;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String file_content;

    private String file_lang;
    public FileEntity() {

    }

    public FileEntity(String user_uid, String fileName, String fileType, Long fileSize, String fileContent, String fileLang){
        this.user_uid = user_uid;
        this.file_name = fileName;
        this.file_type = fileType;
        this.file_size = fileSize;
        this.file_content = fileContent;
        this.file_lang = fileLang;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getFile_type() {
        return file_type;
    }

    public Long getFile_size() {
        return file_size;
    }

    public String getFile_content() {
        return file_content;
    }

    public String getFile_lang() {
        return file_lang;
    }
}
