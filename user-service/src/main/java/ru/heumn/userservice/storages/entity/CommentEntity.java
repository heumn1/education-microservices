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
@Table(name = "step_comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    Long id;

    @Column(name = "text_comment")
    String text;

    @JoinColumn(name = "step_comment")
    @ManyToOne
    StepEntity step;

    @JoinColumn(name = "user_comment")
    @ManyToOne(cascade = CascadeType.ALL)
    UserEntity user;
}
