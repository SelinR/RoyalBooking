package com.epam.royalbooking.dao.springData;

import com.epam.royalbooking.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDaoData extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    boolean existsUserByEmail(String email);
    void deleteById(long id);
}
