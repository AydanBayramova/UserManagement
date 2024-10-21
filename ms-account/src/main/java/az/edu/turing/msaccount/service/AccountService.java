package az.edu.turing.msaccount.service;

import az.edu.turing.msaccount.domain.entity.UserEntity;
import az.edu.turing.msaccount.model.dto.LoginRequest;
import az.edu.turing.msaccount.model.dto.RegisterRequest;

public interface AccountService {

    UserEntity register(RegisterRequest request);


}
