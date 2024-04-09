package my.project.msa.user_service.service;

import my.project.msa.user_service.domain_model.Group;

public interface GroupService {

    Iterable<Group> getGroups();
}
