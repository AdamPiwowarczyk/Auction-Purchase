package com.auction.history.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Subject {
    private String code;
    private String caption;
    private String description;
    private Date publishDate;
    private Date endDate;
    private Double basicPrice;
    private Double soldPrice;
    private Boolean archive = false;
    private byte[] picByte;
    private Set<Category> categories = new HashSet<>();
}
