package com.socialnetworkmonitoring.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class EntityAlreadyExistException extends Exception{

    @NonNull
    private String errorName;

    @NonNull
    private String message;

}
