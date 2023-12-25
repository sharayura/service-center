package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String login;
    private String pwd;
    private Role role;
    private int profitPercent;
}
