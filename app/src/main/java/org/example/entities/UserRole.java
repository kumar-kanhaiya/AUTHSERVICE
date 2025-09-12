package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // it gives builder
@NoArgsConstructor // creating constructor without any argument
@AllArgsConstructor // creating constructor with all argument
@Table() //
public class UserRole {

    @Id // make id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto generate the value
    @Column(name = "role_id") // access in sql by using name (role_id) not roleId
    private long roleId;

    private String name;



}
