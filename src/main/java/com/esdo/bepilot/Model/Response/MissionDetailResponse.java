package com.esdo.bepilot.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionDetailResponse {
    private Long id;
    private Long missionId;
    private Long userId;
    private String status;
    private String nameMission ;

    private OffsetDateTime createAt;

    private OffsetDateTime updateAt;
}
