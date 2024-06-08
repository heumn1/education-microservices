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
@Table(name = "step")
public class StepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_step")
    Long id;

    @Column(name = "name_step")
    String name;

    @Column(name = "file_step")
    String fileName;

    @JoinColumn(name = "course_step")
    @ManyToOne(cascade = CascadeType.ALL)
    CourseEntity course;
}
