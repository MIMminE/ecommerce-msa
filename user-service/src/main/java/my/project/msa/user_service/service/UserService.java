package my.project.msa.user_service.service;

import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.request.RequestCreateUser;

public interface UserService {
    User createUser(RequestCreateUser user);
    User getUserByUserId(String userId);
    Iterable<User> getUserByAll();
}
