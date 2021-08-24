package com.socialnetworkmonitoring.web.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportException extends Exception {

    private static final long serialVersionUID = 1L;

    @NonNull
    private String errorName;

    @NonNull
    private String message;

}
