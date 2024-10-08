package todo.api.todo.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.StringUtils;
import todo.api.todo.entity.enums.TodosStatus;

public record TodoListReq(
        @Schema(description = "검색어")
        String keyword,

        @Schema(description = "검색어 타입", example = "title || description || title,description")
        String type, // title,description

        @Schema(description = "상태", example = "DONE")
        TodosStatus status
) {

    public String[] getTypes() {
        if (StringUtils.hasText(type)) {
            return type.split(",");
        }
        return null;
    }
}
