package dto;

import entity.Role;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class UserDto {
    Long id;
    String login;
    Role role;
    int profitPercent;
}
