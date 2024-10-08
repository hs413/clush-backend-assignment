package todo.api.todo.entity;

import static org.springframework.util.StringUtils.hasText;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import todo.api.account.entity.Users;
import todo.api.common.BaseEntity;
import todo.api.todo.entity.converter.PriorityConverter;
import todo.api.todo.entity.enums.TodosPriority;
import todo.api.todo.entity.enums.TodosStatus;
import todo.api.todo.entity.request.TodoUpdateReq;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = @Index(name = "idx_todo_priority_and_id", columnList = "priority, id"))
public class Todos extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TodosStatus status;

    @Convert(converter = PriorityConverter.class)
    @Column(nullable = false)
    private TodosPriority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;


    public void update(TodoUpdateReq req) {
        if (hasText(req.title())) {
            title = req.title();
        }

        if (hasText(req.description())) {
            description = req.description();
        }

        if (req.status() != null) {
            status = req.status();
        }

        if (req.priority() != null) {
            priority = req.priority();
        }

    }
}
