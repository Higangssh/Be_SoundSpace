package com.the.soundspace.user.model.entities;

import com.the.soundspace.common.domain.entities.BaseEntity;
import com.the.soundspace.common.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(
        name = "user_tbl",
        uniqueConstraints = {
                @UniqueConstraint(
                        name="nick_unique",
                        columnNames = {"nick"}
                ),
                @UniqueConstraint(
                        name = "email_unique",
                        columnNames = {"email"}
                )
        }
)
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true , nullable = false)
    private String email;
    @Column(unique = true , nullable = false)
    private String nick;
    @Column(name = "profile")
    private String profile;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;


}
