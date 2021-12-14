package com.android.asm2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    private String username;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String role;
    private ArrayList<String> joinedZones;
}
