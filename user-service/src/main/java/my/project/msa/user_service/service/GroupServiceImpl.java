package my.project.msa.user_service.service;

import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;
import my.project.msa.user_service.exception.ServiceValidException;
import my.project.msa.user_service.persistent.jpa.group.GroupEntity;
import my.project.msa.user_service.persistent.jpa.group.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static my.project.msa.user_service.exception.ExceptionHolder.SERVICE_VALID_EX_FAIL_USER_FIND;
import static my.project.msa.user_service.util.PasswordEncoder.encodePassword;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
    @Transactional
    public Group createGroup(Group group) {

        GroupEntity groupEntity = GroupEntity.fromGroupDomain(group);
        groupEntity.setEncodedSecretKey(encodePassword(group.getSecretKey()));
        GroupEntity savedGroup = groupRepository.save(groupEntity);
        return GroupEntity.toGroupDomain(savedGroup);
    }

    @Override
    public Group findGroup(Long groupId) {
        GroupEntity groupEntity = groupRepository.findById(groupId).orElseThrow();
        return GroupEntity.toGroupDomain(groupEntity);
    }

    @Override
    @Transactional
    public Group modifyGroup(Long groupId, Group group, String secretKey) {

        GroupAuthority groupAuthority = group.getGroupAuthority();
        GroupEntity groupEntity = groupRepository.findGroupEntityForModify(groupId, encodePassword(secretKey));

        if (groupEntity == null) {
            throw new ServiceValidException(SERVICE_VALID_EX_FAIL_USER_FIND);
        }
        groupEntity.setGroupAuthority(groupAuthority);

        return GroupEntity.toGroupDomain(groupEntity);
    }

    @Override
    @Transactional
    public Group removeGroup(Long groupId, Group group, String secretKey) {
        GroupEntity groupEntity = groupRepository.findGroupEntityForModify(groupId, encodePassword(secretKey));
        if (groupEntity == null) {
            throw new ServiceValidException(SERVICE_VALID_EX_FAIL_USER_FIND);
        }
        groupRepository.delete(groupEntity);
        return GroupEntity.toGroupDomain(groupEntity);
    }
}
