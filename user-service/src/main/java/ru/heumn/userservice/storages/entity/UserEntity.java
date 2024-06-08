package ru.heumn.userservice.storages.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "public")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    Long id;

    @Column(name = "name_user")
    String name;

    @Column(name = "lastname_user")
    String lastname;

    @Column(name = "email_user", unique = true)
    String email;

    @Column(name = "password_user")
    String password;

    @Column(name = "date_create_user")
    Date dateCreate;

    @Column(name = "role_user")
    String role;

    @Column(name = "account_user")
    Double money;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_courses", joinColumns = {@JoinColumn(name = "id_course")}, inverseJoinColumns = {@JoinColumn(name = "id_user")})
    Set<CourseEntity> courses = new HashSet<>();
}
