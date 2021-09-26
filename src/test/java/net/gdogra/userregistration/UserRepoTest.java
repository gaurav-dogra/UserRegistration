package net.gdogra.userregistration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserRepoTest {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testCreateUser() {
        User user = new User();
        user.setEmail("gauru.dogra@gmail.com");
        user.setPassword("MyFakePassword");
        user.setFirstName("Gaurav");
        user.setLastName("Dogra");

        User savedUser = userRepo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

}