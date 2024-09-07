package todo.api.todo.service;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import todo.api.account.entity.Users;
import todo.api.todo.entity.Todos;
import todo.api.todo.entity.TodosSharing;
import todo.api.todo.entity.enums.TodosPriority;
import todo.api.todo.entity.enums.TodosStatus;
import todo.api.todo.entity.request.SharedReq;

@SpringBootTest
@Log4j2
@Transactional
class SharedServiceTest {

    @Autowired
    private SharedService sharedService;

    @PersistenceContext
    private EntityManager em;

    Users user1;
    Users user2;

    Todos user1Todo1;
    Todos user1Todo2;
    Todos user2todo1;

    @BeforeEach
    void setUp() {
        user1 = Users.builder()
                .username("유저1")
                .email("email1@example.com")
                .password("1234")
                .build();

        user2 = Users.builder()
                .username("유저2")
                .email("email2@example.com")
                .password("1234")
                .build();

        user1Todo1 = Todos.builder()
                .title("user1 todo1")
                .description("user1 todo1")
                .status(TodosStatus.PENDING)
                .priority(TodosPriority.MID)
                .user(user1)
                .build();

        user1Todo2 = Todos.builder()
                .title("user1 todo2")
                .description("user1 todo2")
                .status(TodosStatus.PENDING)
                .priority(TodosPriority.MID)
                .user(user1)
                .build();

        user2todo1 = Todos.builder()
                .title("user2 todo1")
                .description("user2 todo1")
                .status(TodosStatus.PENDING)
                .priority(TodosPriority.MID)
                .user(user2)
                .build();

        em.persist(user1);
        em.persist(user2);
        em.persist(user1Todo1);
        em.persist(user1Todo2);
        em.persist(user2todo1);
        em.flush();
        em.clear();
    }

    @Test
    public void 할일_공유() {
        // give
        SharedReq req = new SharedReq(user2.getEmail(), null);

        // when
        Long sharedId = sharedService.todoShare(user1.getId(), user1Todo1.getId(), req);

        // then
        TodosSharing shared = em.find(TodosSharing.class, sharedId);
        assertThat(shared.getTodos().getId()).isEqualTo(user1Todo1.getId());
    }
}