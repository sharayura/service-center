package entity;

import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String pwd;
    private Role role;
    private int profitPercent;

    public User(Long id, String login, String pwd, Role role, int profitPercent) {
        this.id = id;
        this.login = login;
        this.pwd = pwd;
        this.role = role;
        this.profitPercent = profitPercent;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getProfitPercent() {
        return profitPercent;
    }

    public void setProfitPercent(int profitPercent) {
        this.profitPercent = profitPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return profitPercent == user.profitPercent && Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(pwd, user.pwd) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, pwd, role, profitPercent);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                ", role=" + role +
                ", profitPercent=" + profitPercent +
                '}';
    }
}
