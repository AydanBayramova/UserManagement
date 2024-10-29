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


        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists with this email");
        }


        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        UserEntity newUser = new UserEntity();
        newUser.setEmail(request.getEmail());
        newUser.setFullName(request.getFullName());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(newUser);
    }

    @Override
    public String login(LoginRequest request) {

        UserEntity user = userRepository.findByEmail(request.getEmail());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }


        return jwtUtil.generateToken(user.getEmail());
    }
}
