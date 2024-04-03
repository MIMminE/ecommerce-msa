package my.project.msa.user_service.service;

import my.project.msa.user_service.domain_model.User;

public interface UserService {
    User createUser(User userDto);
    User getUserByUserId(String userId);
    Iterable<User> getUserByAll();
}
