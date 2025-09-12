package org.example.entities;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "tokens")
public class RefreshToken {

    @Id // creating the id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Let the DB handle auto-increment IDs
    private int id;

    private String token;

    private Instant expireDate; // Instant is used to get the time and date

    @OneToOne
    @JoinColumn(name = "id" , referencedColumnName = "user_id")

    private UserInfo userInfo;

}
