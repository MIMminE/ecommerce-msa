package my.project.msa.user_service.test_support;

import my.project.msa.user_service.persistent.jpa.group.GroupRepository;
import my.project.msa.user_service.persistent.jpa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTestSupport {

    @LocalServerPort
    protected int port;

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected GroupRepository groupRepository;

    @Autowired
    protected UserRepository userRepository;


    protected String createUrlToEndpoint(String endpoint) {
        return "http://localhost:" + port + "/user-service/" + endpoint;
    }
}
