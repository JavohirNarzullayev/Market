package uz.marokand.market.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "photo")
public class Photo implements Serializable {
    private static final long serialUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "file_size")
    private Long fileSize;
    @Column(name = "content_Type")
    private String contentType;
    @Column(name = "upload_Path")
    private String uploadPath;
    @Column(name = "photo_Bytes")
    @Lob
    private byte[] photoBytes;

    @OneToOne(mappedBy = "photo")
    private Product product;

    public Photo(String name, Long fileSize, String contentType, String uploadPath, byte[] photoBytes) {
        this.name = name;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.uploadPath = uploadPath;
        this.photoBytes = photoBytes;

    }

    public Photo(String name, Long fileSize, String contentType, byte[] photoBytes) {
        this.name = name;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.photoBytes = photoBytes;
    }

    public Photo() {
    }

    public byte[] getPhotoBytes() {
        return photoBytes;
    }

    public void setPhotoBytes(byte[] photoBytes) {
        this.photoBytes = photoBytes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
