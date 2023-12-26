package validator;

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

    public ValidationResult isValid(CreateUserDto uscreateUserDto) {
        var validationResult = new ValidationResult();
        if (uscreateUserDto.getLogin().isEmpty()) {
            validationResult.add(Error.of("invalid.login", "Login is invalid"));
        }
        if (uscreateUserDto.getPwd().isEmpty()) {
            validationResult.add(Error.of("invalid.password", "Password is invalid"));
        }
        if (Role.find(uscreateUserDto.getRole()).isEmpty()) {
            validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }
        return validationResult;
    }
}
