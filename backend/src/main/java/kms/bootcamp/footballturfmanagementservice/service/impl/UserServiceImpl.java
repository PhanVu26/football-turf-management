package kms.bootcamp.footballturfmanagementservice.service.impl;

import kms.bootcamp.footballturfmanagementservice.dto.UserDetail;
import kms.bootcamp.footballturfmanagementservice.dto.UserRequest;
import kms.bootcamp.footballturfmanagementservice.dto.UserResponse;
import kms.bootcamp.footballturfmanagementservice.entity.UserEntity;
import kms.bootcamp.footballturfmanagementservice.repository.UserRepository;
import kms.bootcamp.footballturfmanagementservice.service.BaseService;
import kms.bootcamp.footballturfmanagementservice.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseService implements UserService {

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

    @Override
    public UserResponse createUser(UserRequest request) {
        UserResponse response = new UserResponse();
        UserDetail userDetail = request.getUserDetail();
        if (Objects.isNull(userDetail)) {
            mapFailResponseValue(response, "userDetail can not be empty");
            return response;
        }
        UserEntity entity = userRepository.findByUsername(userDetail.getUsername());
        if (Objects.nonNull(entity)) {
            mapFailResponseValue(response, "There is an user with username: " + userDetail.getUsername());
            return response;
        }
        entity = new UserEntity();
        BeanUtils.copyProperties(userDetail, entity);
        entity.setPassword(encodePassword(userDetail.getPassword()));
        mapEntityAuditCreating(request, entity);
        userRepository.save(entity);
        mapSuccessResponseValue(response);
        return response;
    }

    @Override
    public UserResponse updateUser(UserRequest request) {
        UserResponse response = new UserResponse();
        UserDetail userDetail = request.getUserDetail();
        if (Objects.isNull(userDetail)) {
            mapFailResponseValue(response, "userDetail can not be empty");
            return response;
        }
        Optional<UserEntity> entity = userRepository.findById(userDetail.getId());
        if (!entity.isPresent()) {
            mapFailResponseValue(response, "user is not found with id: " + userDetail.getId());
            return response;
        }
        BeanUtils.copyProperties(userDetail, entity.get());
        mapEntityAuditUpdating(request, entity.get());
        userRepository.save(entity.get());
        mapSuccessResponseValue(response);
        return response;
    }

    private String encodePassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

}
