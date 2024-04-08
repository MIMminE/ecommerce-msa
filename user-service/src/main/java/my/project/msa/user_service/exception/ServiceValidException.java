package my.project.msa.user_service.exception;

public class ServiceValidException extends RuntimeException {
    public ServiceValidException(String message) {
        super(message);
    }
}
