package my.project.msa.user_service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.domain_model.vo.GroupAuthority;
import my.project.msa.user_service.persistent.jpa.group.GroupEntity;
import my.project.msa.user_service.persistent.jpa.group.GroupRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Init {

    private final GroupRepository groupRepository;

    @PostConstruct
    void init() {
        InitDepartmentRepository();
        List<GroupEntity> all = groupRepository.findAll();
        for (GroupEntity departmentEntity : all) {
            System.out.println(departmentEntity);
        }
    }

    private void InitDepartmentRepository() {

        groupRepository.saveAll(
                List.of(
                        GroupEntity.builder()
                                .groupName("groupA")
                                .groupAuthority(
                                        GroupAuthority.builder()
                                                .agricultural(true)
                                                .aquatic(true)
                                                .livestock(false)
                                                .build()
                                )
                                .build(),
                        GroupEntity.builder()
                                .groupName("groupB")
                                .groupAuthority(
                                        GroupAuthority.builder()
                                                .agricultural(false)
                                                .aquatic(false)
                                                .livestock(true)
                                                .build())
                                .build())
        );
    }
}
