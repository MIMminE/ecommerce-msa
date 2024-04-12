package my.project.msa.user_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.domain_model.Group;
import my.project.msa.user_service.dto.request.RequestGroup;
import my.project.msa.user_service.dto.request.RequestRemoveGroup;
import my.project.msa.user_service.dto.response.ResponseGroup;
import my.project.msa.user_service.dto.response.ResponseGroups;
import my.project.msa.user_service.service.GroupService;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/group-service")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/groups")
    public ResponseEntity<ResponseGroups> getGroups() {
        Iterable<Group> groups = groupService.getGroups();

        return ResponseEntity.ok(
                new ResponseGroups(StreamSupport.stream(groups.spliterator(), false)
                .map(ResponseGroup::fromGroup)
                .toList()));
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<ResponseGroup> getGroup(@PathVariable Long groupId){
        Group group = groupService.findGroup(groupId);

        return ResponseEntity.ok(ResponseGroup.fromGroup(group));
    }


    @PostMapping("/groups")
    public ResponseEntity<ResponseGroup> createGroup(@Valid @RequestBody RequestGroup requestGroup){

        Group group = RequestGroup.toGroup(requestGroup);
        Group createdGroup = groupService.createGroup(group);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{groupId}")
                .buildAndExpand(createdGroup.getGroupId()).toUri();

        return ResponseEntity.created(location).body(ResponseGroup.fromGroup(Objects.requireNonNull(createdGroup)));
    }

    @PutMapping("/groups/{groupId}")
    public ResponseEntity<ResponseGroup> modifyGroup(@PathVariable Long groupId,
                                                     @Valid @RequestBody RequestGroup requestGroup){

        Group group = RequestGroup.toGroup(requestGroup);

        Group modifiedGroup = groupService.modifyGroup(groupId, group, requestGroup.getSecretKey());

        return ResponseEntity.ok().body(ResponseGroup.fromGroup(modifiedGroup));
    }

    @DeleteMapping("groups/{groupId}")
    public ResponseEntity<ResponseGroup> removeGroup(@PathVariable Long groupId,
                                                     @Valid @RequestBody RequestRemoveGroup requestRemoveGroup){

        Group group = RequestRemoveGroup.toGroup(requestRemoveGroup);

        Group removedGroup = groupService.removeGroup(groupId, group, requestRemoveGroup.getSecretKey());

        return ResponseEntity.ok().body(ResponseGroup.fromGroup(removedGroup));
    }
}
