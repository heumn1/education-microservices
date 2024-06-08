package ru.heumn.paymentservice.storages.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.heumn.paymentservice.storages.entity.CourseEntity;

public class CourseRepository extends BaseRepository<Long, CourseEntity> {

    public CourseRepository(SessionFactory sessionFactory) {
        super(sessionFactory, CourseEntity.class);
    }
}
