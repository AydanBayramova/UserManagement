package az.edu.turing.msaccount.service.ServiceImp;

import az.edu.turing.msaccount.domain.entity.UserEntity;
import az.edu.turing.msaccount.domain.repository.UserRepository;
import az.edu.turing.msaccount.model.dto.LoginRequest;
import az.edu.turing.msaccount.model.dto.RegisterRequest;
import az.edu.turing.msaccount.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements AccountService {

    private UserRepository userRepository;

    @Override
    public UserEntity register(RegisterRequest request) {
        return null;
    }

    @Override
    public String login(LoginRequest request) {
        return "";
    }

    @Override
    public void logout(String token) {

    }
}
