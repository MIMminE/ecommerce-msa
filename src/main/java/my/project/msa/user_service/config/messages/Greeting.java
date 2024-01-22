package my.project.msa.user_service.config.messages;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Greeting {
    @Value("${greeting.message}")
    private String message;
}
