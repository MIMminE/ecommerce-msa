package my.project.msa.user_service.mapper;


import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.response.ResponseUser;
import my.project.msa.user_service.persistent.jpa.group.GroupEntity;
import my.project.msa.user_service.persistent.jpa.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    ResponseUser toResponseUser(User source);

    User fromUserJpaEntity(UserEntity source);

    User fromRequestCreateUser(RequestCreateUser source);

//    @Mapping(target = "group",ignore = true)
//    UserEntity toUserJpaEntity(User source);

    default String map(GroupEntity source) {
        return source.getGroupName();
    }


    default GroupEntity map(Group source) {
        return GroupEntity.builder()
                .groupName(source.getGroupName())
                .build();
    }
}