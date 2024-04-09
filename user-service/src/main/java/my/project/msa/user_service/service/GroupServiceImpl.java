package my.project.msa.user_service.service;

import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.mapper.GroupMapper;
import my.project.msa.user_service.persistent.jpa.group.GroupEntity;
import my.project.msa.user_service.persistent.jpa.group.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper = GroupMapper.INSTANCE;

    @Override
    public Iterable<Group> getGroups() {
        List<GroupEntity> groupEntities = groupRepository.findAll();

        return groupEntities.stream()
                .map(groupMapper::GroupEntityToGroup)
                .toList();
    }
}
