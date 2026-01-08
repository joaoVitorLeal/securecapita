package io.github.joaovitorleal.securecapita.service.implementation;

import io.github.joaovitorleal.securecapita.domain.User;
import io.github.joaovitorleal.securecapita.dto.UserDTO;
import io.github.joaovitorleal.securecapita.dtomapper.UserMapper;
import io.github.joaovitorleal.securecapita.repository.UserRepository;
import io.github.joaovitorleal.securecapita.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository<User> userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Transactional
    @Override
    public UserDTO createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return UserMapper.toUserDTO(userRepository.save(user));
    }

    /**
     * @param email user email (username)
     * @return {@link UserDTO}
     */
   @Transactional(readOnly = true)
    @Override
    public UserDTO getUserByEmail(String email) {
        return UserMapper.toUserDTO(userRepository.findUserByEmail(email));
    }
}
