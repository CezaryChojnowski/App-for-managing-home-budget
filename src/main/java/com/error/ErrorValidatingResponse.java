package com.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorValidatingResponse {
    public ErrorValidatingResponse(String message, List<String> details, int status) {
        super();
        this.status = status;
        this.message = message;
        this.details = details;
    }

    private String message;
    private List<String> details;
    private int status;

}
