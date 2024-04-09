package my.project.msa.user_service.domain_model.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupAuthority {
    private boolean aquatic;
    private boolean livestock;
    private boolean agricultural;

    @Builder
    private GroupAuthority(boolean aquatic, boolean livestock, boolean agricultural) {
        this.aquatic = aquatic;
        this.livestock = livestock;
        this.agricultural = agricultural;
    }
}
