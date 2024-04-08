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
public interface UserDomainMapper {
    UserDomainMapper INSTANCE = Mappers.getMapper(UserDomainMapper.class);

    ResponseUser toResponseUser(User source);

    User fromUserJpaEntity(UserEntity source);

    @Mapping(target = "group",ignore = true)
    User fromRequestCreateUser(RequestCreateUser source);

    UserEntity toUserJpaEntity(User source);

    default Group map(GroupEntity source) {
        return Group.builder()
                .groupName(source.getGroupName())
                .leaderName(source.getLeaderInfo().getLeaderName())
                .leaderNumber(source.getLeaderInfo().getLeaderNumber())
                .members(source.getMembers().stream()
                        .map(UserEntity::getName)
                        .toList())
                .build();
    }

    default GroupEntity map(Group source) {
        return GroupEntity.builder()
                .groupName(source.getGroupName())
                .leaderName(source.getLeaderName())
                .leaderNumber(source.getLeaderNumber())
                .build();
    }
}