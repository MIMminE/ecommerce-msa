package my.project.msa.user_service.persistent.jpa.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    GroupEntity findByGroupName(String groupName);

    @Query("SELECT g FROM GroupEntity g where g.id = :id and g.encodedSecretKey = :encodedKey")
    GroupEntity findGroupEntityForModify(Long id, String encodedKey);
}
