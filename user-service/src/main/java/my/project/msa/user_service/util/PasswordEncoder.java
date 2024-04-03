package my.project.msa.user_service.util;


public class PasswordEncoder {

//    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password){
        return String.format("encoding %s", password);
    }
}
