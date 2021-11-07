package ir.maktab.exception;

public class ResourceNotFoundException extends RuntimeException {
    private String resource;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resource, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resource, fieldName, fieldValue));
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
