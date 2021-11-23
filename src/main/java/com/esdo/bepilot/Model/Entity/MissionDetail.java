package com.esdo.bepilot.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MissionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mission_id",referencedColumnName = "id")
    @JsonIgnore
    private Mission mission;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User users;

    @Column(columnDefinition = "VARCHAR(20)")
    private String status;

    @Column(columnDefinition = "VARCHAR(255)")
    private String nameMission;

    @CreatedDate
    @Column(columnDefinition = "timestamp default now()")
    private OffsetDateTime createAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default now()")
    private OffsetDateTime updateAt;
}
