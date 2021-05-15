package com.example.test1.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;



@Entity
@Table(schema = "test_db", name = "test_table2")
public class Post {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFstring() {
        return fstring;
    }

    public void setFstring(String fstring) {
        this.fstring = fstring;
    }

    public String getSstring() {
        return sstring;
    }

    public void setSstring(String sstring) {
        this.sstring = sstring;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", length = 6, nullable = false)
    private long id;

    @Column(name = "fstring")
    private String fstring;

    @Column(name = "sstring")
    private String sstring;


    public Post() { }


}
