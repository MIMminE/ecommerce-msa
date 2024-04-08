package my.project.msa.user_service.persistent.jpa.group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    GroupEntity findByGroupName(String departmentName);
}
