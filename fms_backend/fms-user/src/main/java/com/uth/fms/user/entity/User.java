package com.uth.fms.user.entity;

import com.uth.fms.common.entity.BaseEntity;
import com.uth.fms.common.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false, length = 50)
    String username;

    @Column(nullable = false, length = 255)
    String password;

    @Column(unique = true, nullable = false, length = 100)
    String email;

    @Column(nullable = false, length = 100)
    String fullName;

    @Column(nullable = false, name = "phone_number", length = 20)
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    RolesEnum roles;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    Boolean enabled = true;

}
