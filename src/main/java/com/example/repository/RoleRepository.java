package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.RoleEntity;

@Repository

public interface  RoleRepository extends CrudRepository<RoleEntity,Long> {

}
