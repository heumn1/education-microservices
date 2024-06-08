package ru.heumn.userservice.storages.repository;

import org.hibernate.SessionFactory;
import ru.heumn.userservice.storages.entity.UserEntity;

public class UserRepository extends BaseRepository<Long, UserEntity> {

    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory, UserEntity.class);
    }
}
