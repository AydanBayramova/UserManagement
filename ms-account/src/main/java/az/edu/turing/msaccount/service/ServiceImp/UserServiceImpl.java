package az.edu.turing.msaccount.service.ServiceImp;

import az.edu.turing.msaccount.config.JwtUtil;
import az.edu.turing.msaccount.domain.entity.UserEntity;
import az.edu.turing.msaccount.domain.repository.UserRepository;
import az.edu.turing.msaccount.model.dto.LoginRequest;
import az.edu.turing.msaccount.model.dto.RegisterRequest;
import az.edu.turing.msaccount.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserEntity register(RegisterRequest request) {

        UserEntity existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists with this username");
        }


        UserEntity newUser = new UserEntity();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(newUser);
    }

}
