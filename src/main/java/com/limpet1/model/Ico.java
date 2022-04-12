package com.limpet1.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ico")
@Data
public class Ico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rated")
    private String rated;

    @Column(name = "type")
    private String type;

    @Column(name = "token")
    private String token;

    @Column(name = "date")
    private String date;

    @Column(name = "privatesale")
    private String privatesale;

    @Column(name = "publicsale")
    private String publicsale;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "about")
    private String about;

    @Column(name = "marketcap")
    private String marketcap;

}
