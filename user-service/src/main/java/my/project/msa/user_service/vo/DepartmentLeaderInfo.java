package my.project.msa.user_service.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepartmentLeaderInfo {

    private String leaderName;
    private String leaderNumber;

    @Builder
    private DepartmentLeaderInfo(String leaderName, String leaderNumber) {
        this.leaderName = leaderName;
        this.leaderNumber = leaderNumber;
    }
}
