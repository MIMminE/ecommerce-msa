//package my.project.msa.user_service.persistent.jpa.user;
//
//import lombok.extern.slf4j.Slf4j;
//import my.project.msa.user_service.persistent.jpa.group.GroupEntity;
//import my.project.msa.user_service.persistent.jpa.group.GroupRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@Slf4j
//@SpringBootTest
//@Transactional
//@Rollback(value = false)
//class UserEntityTest {
//
//    @Autowired
//    UserRepository UserRepository;
//
//    @Autowired
//    GroupRepository departmentRepository;
//
//
//    @Test
//    void test() {
//        departmentRepository.save(GroupEntity.builder()
//                .groupName("영업부")
//                .leaderNumber("리더A")
//                .leaderName("리더A")
//                .build());
//        Optional<GroupEntity> byId = departmentRepository.findById(1L);
//        System.out.println(byId);
//
//        UserEntity userEntity1 = UserEntity.builder()
//                .email("test@example.com")
//                .name("userName")
//                .userId("userId")
//                .department(byId.get())
//                .encryptPwd("encryptedPassword")
//                .build();
//
//        UserEntity userEntity2 = UserEntity.builder()
//                .email("test2@example.com")
//                .name("testName")
//                .userId("testId")
//                .department(byId.get())
//                .encryptPwd("encryptedPassword2")
//                .build();
//        UserRepository.save(userEntity1);
//        UserRepository.save(userEntity2);
//
//        List<GroupEntity> all = departmentRepository.findAll();
//        for (GroupEntity departmentEntity : all) {
//            System.out.println(departmentEntity);
//            List<UserEntity> members = departmentEntity.getMembers();
//            for (UserEntity member : members) {
//                System.out.println(member);
//            }
//        }
//    }
//
//    @Test
//    void test2() {
//        List<UserEntity> all = UserRepository.findAll();
//        List<GroupEntity> all1 = departmentRepository.findAll();
//        for (GroupEntity departmentEntity : all1) {
//            log.info("{}", departmentEntity);
//            log.info("{}", departmentEntity.getMembers());
//        }
//        for (UserEntity userEntity : all) {
//            log.info("{}", userEntity);
//        }
//    }
//}