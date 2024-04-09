package my.project.msa.user_service.mapper;

import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.response.ResponseGroup;
import my.project.msa.user_service.persistent.jpa.group.GroupEntity;
import my.project.msa.user_service.persistent.jpa.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);
    UserMapper userMapper = UserMapper.INSTANCE;

    @Mappings({
            @Mapping(target = "groupId", ignore = true),
//            @Mapping(target = "groupAuthority", ignore = true)
    })
    ResponseGroup toResponseGroup(Group source);

    GroupEntity toGroupEntity(Group source);

    Group fromGroupEntity(GroupEntity source);

    default String userToString(User source) {
        return source.getName();
    }

    default String userEntityToString(UserEntity source) {
        return source.getName();
    }

    default User UserEntityToGroup(UserEntity entity) {
        return userMapper.fromUserJpaEntity(entity);
    }

    default Group GroupEntityToGroup(GroupEntity entity) {
        return Group.builder()
                .groupName(entity.getGroupName())
                .groupAuthority(entity.getGroupAuthority())
                .members(entity.getMembers().stream()
                        .map(userMapper::fromUserJpaEntity)
                        .toList())
                .build();
    }


}
