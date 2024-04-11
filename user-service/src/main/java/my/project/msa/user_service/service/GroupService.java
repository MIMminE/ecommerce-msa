package my.project.msa.user_service.service;

import my.project.msa.user_service.domain_model.Group;

public interface GroupService {

    Iterable<Group> getGroups();

    Long createGroup(Group group);

    Group findGroup(Long groupId);

    Group modifyGroup(Long groupId, Group group, String encodedSecretKey);

    Group removeGroup(Long groupId, Group group, String encodedSecretKey);
}
