package my.project.msa.user_service.persistent.jpa.group;

import my.project.msa.user_service.domain_model.vo.GroupAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    GroupEntity findByGroupName(String groupName);

    @Query("update GroupEntity g SET g.groupAuthority = :groupAuthority where g.id = :id and g.encodedSecretKey = :encodedKey")
    GroupEntity updateGroupAuthority(Long id, String encodedKey, GroupAuthority groupAuthority);

}
