package com.gmail.shilovich.stas.jd2.repositorymodule.model;

import javax.persistence.*;

@Entity
@Table
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column
    private String name;
    @Column
    private String unique;
    @Column
    private String price;
    @Column
    private Long itemInfo;
    @Column
    private boolean deleted;
}
