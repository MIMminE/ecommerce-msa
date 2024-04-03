package my.project.msa.user_service.persistent.jpa;

import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<UserJpaEntity, Long> {
    UserJpaEntity findByUserId(String userId);
}
