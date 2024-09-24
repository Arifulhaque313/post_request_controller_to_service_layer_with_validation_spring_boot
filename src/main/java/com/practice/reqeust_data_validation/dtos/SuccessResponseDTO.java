package com.practice.reqeust_data_validation.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponseDTO {
    private String message;
    private Object data;
    private String status;
}
