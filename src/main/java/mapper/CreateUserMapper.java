package mapper;

import dto.CreateUserDto;
import entity.Role;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<User, CreateUserDto> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public User mapFrom(CreateUserDto createUserDto) {
        return User.builder()
                .login(createUserDto.getLogin())
                .pwd(createUserDto.getPwd())
                .role(Role.valueOf(createUserDto.getRole()))
                .profitPercent(0)
                .build();
    }
}
