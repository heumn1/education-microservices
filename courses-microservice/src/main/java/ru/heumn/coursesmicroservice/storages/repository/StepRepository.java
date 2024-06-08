package ru.heumn.coursesmicroservice.storages.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.heumn.coursesmicroservice.storages.entity.CourseEntity;
import ru.heumn.coursesmicroservice.storages.entity.StepEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StepRepository extends BaseRepository<Long, StepEntity> {

    SessionFactory sessionFactory;

    public StepRepository(SessionFactory sessionFactory) {
        super(sessionFactory, StepEntity.class);
        this.sessionFactory = sessionFactory;
    }

    public Set<StepEntity> getAllStepByIdCourse(Long id){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Set<StepEntity> answer = session.createQuery("FROM StepEntity se WHERE se.id = :idStep", StepEntity.class)
                .setParameter("idStep", id)
                .stream().collect(Collectors.toSet());
        session.close();
        return answer;
    }
}
