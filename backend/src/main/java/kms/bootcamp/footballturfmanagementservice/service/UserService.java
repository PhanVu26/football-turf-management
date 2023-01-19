package kms.bootcamp.footballturfmanagementservice.service;


import kms.bootcamp.footballturfmanagementservice.dto.UserRequest;
import kms.bootcamp.footballturfmanagementservice.dto.UserResponse;
import kms.bootcamp.footballturfmanagementservice.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity findByUsername(String username);

    List<UserEntity> findAll();

    UserResponse createUser(UserRequest request);

    UserResponse updateUser(UserRequest request);
}
