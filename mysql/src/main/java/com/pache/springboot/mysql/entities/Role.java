package com.pache.springboot.mysql.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PACHE_ROLE")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",length = 32)
    private Long id;
    @Column(name="NAME",length = 32)
    private String name;

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
}
