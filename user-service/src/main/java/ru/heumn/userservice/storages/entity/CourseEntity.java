package ru.heumn.userservice.storages.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    Long id;

    @Column(name = "name_course", unique = true)
    String name;

    @Column(name = "description_course")
    String description;

    @Column(name = "time_course")
    String timeCourse;

    @Column(name = "price_course")
    Double price;

    @Column(name = "category_course")
    String category;

    @Column(name = "rating_course")
    Float rating;
}
