package ru.heumn.coursesmicroservice.storages.repository;

import org.hibernate.SessionFactory;
import ru.heumn.coursesmicroservice.storages.entity.CommentEntity;

public class CommentRepository extends BaseRepository<Long, CommentEntity> {

    public CommentRepository(SessionFactory sessionFactory) {
        super(sessionFactory, CommentEntity.class);
    }
}
