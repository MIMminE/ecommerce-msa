package my.project.msa.user_service.mapper;


import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.response.ResponseUser;
import my.project.msa.user_service.persistent.jpa.UserJpaEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDomainMapper {

    UserDomainMapper INSTANCE = Mappers.getMapper(UserDomainMapper.class);
    ResponseUser toResponseUser(User source);
    UserJpaEntity toUserJpaEntity(User source);
    User fromUserJpaEntity(UserJpaEntity source);
    User fromRequestCreateUser(RequestCreateUser source);
}