package az.edu.turing.msaccount.model.mapper;

import az.edu.turing.msaccount.domain.entity.UserEntity;
import az.edu.turing.msaccount.model.dto.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterRequest userEntityToRegisterRequest(UserEntity userEntity);

    UserEntity registerRequestToUserEntity(RegisterRequest registerRequest);
}
