package com.android.asm2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Report {
    @Id
    private String zoneId;
    private String created;
    private int tested;
    private int volunteer;
    private int sample;
    private int positive1st;
    private int positive;
    private String note;

}
