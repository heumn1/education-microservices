package ru.heumn.coursesmicroservice.storages.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "step_comments", schema = "public")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    Long id;

    @Column(name = "text_comment")
    String text;

    @JoinColumn(name = "step_comment")
    @ManyToOne(cascade = CascadeType.MERGE)
    StepEntity step;

    @JoinColumn(name = "user_comment")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    UserEntity user;
}
