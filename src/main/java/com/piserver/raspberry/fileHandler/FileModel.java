package com.piserver.raspberry.fileHandler;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileModel {

    @Id
    @SequenceGenerator(
            name = "file_seq",
            sequenceName = "file_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "file_seq"
    )
    private Long id;
    private String filename;
    private String filetype;
    @Lob
    private byte[] data;

    public FileModel(Long id, String filename, String filetype, byte[] data) {
        this.id = id;
        this.filename = filename;
        this.filetype = filetype;
        this.data = data;
    }

    public FileModel(String filename, String filetype, byte[] data) {
        this.filename = filename;
        this.filetype = filetype;
        this.data = data;
    }

    public FileModel() {
    }

    public String idToString(){ return id.toString(); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", filetype='" + filetype + '\'' +
                '}';
    }
}

