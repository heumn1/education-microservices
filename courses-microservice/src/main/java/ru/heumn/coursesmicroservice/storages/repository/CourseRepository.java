package ru.heumn.coursesmicroservice.storages.repository;

import org.hibernate.SessionFactory;
import ru.heumn.coursesmicroservice.storages.entity.CourseEntity;

public class CourseRepository extends BaseRepository<Long, CourseEntity> {

    public CourseRepository(SessionFactory sessionFactory) {
        super(sessionFactory, CourseEntity.class);
    }
}
