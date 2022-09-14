package kms.bootcamp.footballturfmanagementservice.service;


import kms.bootcamp.footballturfmanagementservice.entity.UserEntity;
import kms.bootcamp.footballturfmanagementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) return user;
        return new UserEntity();
    }

    public List<UserEntity> findAll() {
        List<UserEntity> users = userRepository.findAll();
        if (!CollectionUtils.isEmpty(users)) return users;
        return new ArrayList<>();
    }
}
