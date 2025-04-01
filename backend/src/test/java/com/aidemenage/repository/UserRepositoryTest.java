package com.aidemenage.repository;

import com.aidemenage.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindByEmail() {
        // Given
        User user = new User();
        user.setFullName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("password");
        entityManager.persist(user);
        entityManager.flush();

        // When
        Optional<User> found = userRepository.findByEmail(user.getEmail());

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void shouldCheckEmailExists() {
        // Given
        User user = new User();
        user.setFullName("Test User");
        user.setEmail("test@example.com");
        user.setPassword("password");
        entityManager.persist(user);
        entityManager.flush();

        // When
        boolean exists = userRepository.existsByEmail(user.getEmail());

        // Then
        assertThat(exists).isTrue();
    }
}