package com.example.demo.repository;

import com.example.demo.entity.User;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByNameLike(String like);

    @Query(nativeQuery = true, value = "select user.name from user, user_role, role where user.id=user_role.user_id and user_role.role_id=role.id and role.id=?1")
    List<String> findByRoleID(int id);
}
