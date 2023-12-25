package project.dto;

public class AccountDto {
    private String id;
    private String password;
    private String lastLoginDate;

    public AccountDto(String id, String password, String lastLoginDate) {
        this.id = id;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
