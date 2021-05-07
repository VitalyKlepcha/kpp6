package com.example.test1.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Post.
 *
 * @author Vitaliy Klepcha
 */
/*
@Entity
@Table(schema = "test_db", name = "test_table1")
public class Post {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    public Post() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
*/

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
