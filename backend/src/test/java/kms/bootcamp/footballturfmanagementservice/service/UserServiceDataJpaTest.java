package kms.bootcamp.footballturfmanagementservice.service;

import kms.bootcamp.footballturfmanagementservice.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserServiceDataJpaTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

}
