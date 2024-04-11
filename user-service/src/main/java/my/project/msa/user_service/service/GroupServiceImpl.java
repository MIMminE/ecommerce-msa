package my.project.msa.user_service.service;

import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;
import my.project.msa.user_service.persistent.jpa.group.GroupEntity;
import my.project.msa.user_service.persistent.jpa.group.GroupRepository;
import my.project.msa.user_service.util.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static my.project.msa.user_service.util.PasswordEncoder.*;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public Iterable<Group> getGroups() {
        List<GroupEntity> groupEntities = groupRepository.findAll();

        return groupEntities.stream()
                .map(GroupEntity::toGroupDomain)
                .toList();
    }

    @Override
    public Long createGroup(Group group) {

        GroupEntity groupEntity = GroupEntity.fromGroupDomain(group);
        groupEntity.setEncodedSecretKey(encodePassword(group.getSecretKey()));
        GroupEntity savedGroup = groupRepository.save(groupEntity);

        return savedGroup.getId();
    }

    @Override
    public Group findGroup(Long groupId) {
        GroupEntity groupEntity = groupRepository.findById(groupId).orElseThrow();
        return GroupEntity.toGroupDomain(groupEntity);
    }

    @Override
    public Group modifyGroup(Long groupId, Group group, String encodedSecretKey) {

        GroupAuthority groupAuthority = group.getGroupAuthority();
        GroupEntity groupEntity = groupRepository.updateGroupAuthority(groupId, encodePassword(encodedSecretKey), groupAuthority);
        return GroupEntity.toGroupDomain(groupEntity);
    }

    @Override
    public Group removeGroup(Long groupId, Group group, String encodedSecretKey) {


        return null;
    }
}
