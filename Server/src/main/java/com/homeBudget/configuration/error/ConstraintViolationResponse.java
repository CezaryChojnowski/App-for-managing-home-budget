package com.homeBudget.configuration.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConstraintViolationResponse {

    private String message;
    private List<String> details;
    private int status;

}
