package ru.heumn.paymentservice.storages.repository;

import org.hibernate.SessionFactory;
import ru.heumn.paymentservice.storages.entity.UserEntity;

public class UserRepository extends BaseRepository<Long, UserEntity> {

    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory, UserEntity.class);
    }
}
