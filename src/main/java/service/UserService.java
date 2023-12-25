package service;

import dao.UserDao;
import dto.CreateUserDto;
import dto.UserDto;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.CreateUserMapper;
import mapper.UserMapper;
import validator.CreateUserValidator;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    public static UserService getINSTANCE() {
        return INSTANCE;
    }

    public Long create(CreateUserDto createUserDto) {
        var validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var user = createUserMapper.mapFrom(createUserDto);
        userDao.save(user);
        return user.getId();
    }

    public Optional<UserDto> login(String login, String pwd) {
        return userDao.findByLoginAndPassword(login, pwd).map(userMapper::mapFrom);
    }
}
