package my.project.msa.user_service.controller;

import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.dto.response.ResponseGroups;
import my.project.msa.user_service.mapper.GroupMapper;
import my.project.msa.user_service.mapper.UserMapper;
import my.project.msa.user_service.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper = GroupMapper.INSTANCE;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @GetMapping("/groups")
    public ResponseEntity<ResponseGroups> getGroups() {
        Iterable<Group> groups = groupService.getGroups();

        return ResponseEntity.ok(
                new ResponseGroups(StreamSupport.stream(groups.spliterator(), false)
                .map(groupMapper::toResponseGroup)
                .toList()));
    }
}
