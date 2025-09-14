package org.example.repository;

import org.example.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo , Long> {

    public  UserInfo findByUsername(String username);


}
