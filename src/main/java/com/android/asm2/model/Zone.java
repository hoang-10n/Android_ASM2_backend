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
public class Zone {
    @Id
    private String id;
    private String name;
    private float latitude;
    private float longitude;
    private float duration;
    private int quantity;
    private String leader;
    private String createdDate;
    private String closedDate;
    private String startDate;
    private String startTime;
    private String description;
}
