package my.project.msa.user_service;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;

public class test {
    SecurityContextHolder securityContextHolder = new SecurityContextHolder();

    @Test
    public void test(){
        System.out.println(SecurityContextHolder.getContextHolderStrategy());
    }
}
