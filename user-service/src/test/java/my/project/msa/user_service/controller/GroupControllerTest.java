package my.project.msa.user_service.controller;

import my.project.msa.user_service.dto.response.ResponseGroups;
import my.project.msa.user_service.test_support.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GroupControllerTest extends ControllerTestSupport {

    @DisplayName("/user-service/groups EndPoint 요청에 대한 결과값을 정상적으로 받는다.")
    @Test
    void getGroups() {
        // given
        String targetUrl = createUrlToEndpoint("groups");

        // when
        ResponseEntity<ResponseGroups> result = restTemplate.getForEntity(targetUrl, ResponseGroups.class);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(result.getBody()).getSize()).isEqualTo(2);
        assertThat(result.getBody().getResults()).extracting("groupName").contains("groupA","groupB");
    }
}