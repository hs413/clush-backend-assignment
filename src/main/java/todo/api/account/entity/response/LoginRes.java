package todo.api.account.entity.response;

public record LoginRes(
        String accessToken,
        String refreshToken,
        String username
) {

}
