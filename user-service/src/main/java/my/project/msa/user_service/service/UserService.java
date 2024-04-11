package my.project.msa.user_service.service;

import my.project.msa.user_service.domain_model.User;
import my.project.msa.user_service.dto.request.RequestCreateUser;
import my.project.msa.user_service.dto.request.RequestDeleteUser;

public interface UserService {
    User createUser(RequestCreateUser request);
    User getUserByUserId(String userId);
    User deleteUser(RequestDeleteUser request, String userId);
    Iterable<User> getUserByAll();
}
