package model;

import lombok.Data;

@Data
public class ErrorResponsePetModel {
    private int code;
    private String type;
    private String message;
}
