package fr.dorian_ferreira.cap_entreprise.exception;

import lombok.Getter;

@Getter
public class NotFoundEntityException extends RuntimeException {

    private final String type;

    private final String field;

    private final Object value;

    public NotFoundEntityException(String type, String field, Object value) {
        super("Entity not found");
        this.type = type;
        this.field = field;
        this.value = value;
    }
}
