package project.dto;

public class UserDto {
    private int user_id;
    private String username;
    private int post_count;

    public UserDto(int user_id, String username, int post_count) {
        this.username = username;
        this.user_id = user_id;
        this.post_count = post_count;
    }

    public int getPostCount() {
        return this.post_count;
    }
}
