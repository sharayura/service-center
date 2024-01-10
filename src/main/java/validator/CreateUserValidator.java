package validator;

import dao.UserDao;
import entity.Role;
import dto.CreateUserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
    private final UserDao userDao = UserDao.getInstance();

    public ValidationResult isValid(CreateUserDto createUserDto) {
        var validationResult = new ValidationResult();
        if (createUserDto.getLogin().isEmpty()) {
            validationResult.add(Error.of("invalid.login", "Login is invalid"));
        }
        if (userDao.findByLogin(createUserDto.getLogin()).isPresent()) {
            validationResult.add(Error.of("taken.login", "Login is already taken"));
        }
        if (createUserDto.getPwd().isEmpty()) {
            validationResult.add(Error.of("invalid.password", "Password is invalid"));
        }
        if (Role.find(createUserDto.getRole()).isEmpty()) {
            validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }
        return validationResult;
    }
}
