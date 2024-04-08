package my.project.msa.user_service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import my.project.msa.user_service.persistent.jpa.group.GroupEntity;
import my.project.msa.user_service.persistent.jpa.group.GroupRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Init {

    private final GroupRepository departmentRepository;

    @PostConstruct
    void init() {
        InitDepartmentRepository();
        List<GroupEntity> all = departmentRepository.findAll();
        for (GroupEntity departmentEntity : all) {
            System.out.println(departmentEntity);
        }
    }


    private void InitDepartmentRepository() {

        departmentRepository.saveAll(
                List.of(
                        GroupEntity.builder()
                                .groupName("groupA")
                                .leaderName("A")
                                .leaderNumber("123-123")
                                .build(),
                        GroupEntity.builder()
                                .groupName("groupB")
                                .leaderName("B")
                                .leaderNumber("456-456")
                                .build())
        );
    }
}