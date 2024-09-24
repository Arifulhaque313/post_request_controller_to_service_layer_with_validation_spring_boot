package com.practice.reqeust_data_validation.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private String message;
    private Object errors;
    private String status;
}
