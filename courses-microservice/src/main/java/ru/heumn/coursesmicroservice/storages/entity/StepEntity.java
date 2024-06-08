package ru.heumn.coursesmicroservice.storages.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "step", schema = "public")
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
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    CourseEntity course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "step", fetch = FetchType.EAGER)
    Set<CommentEntity> comments = new HashSet<>();
}
