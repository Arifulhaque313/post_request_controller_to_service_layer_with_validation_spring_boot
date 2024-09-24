package com.practice.reqeust_data_validation.services;

import com.practice.reqeust_data_validation.dtos.DocumentRequestDTO;
import org.springframework.validation.BindingResult;

public abstract class AbstractDocumentService {
    public abstract void processDocumentRequest(DocumentRequestDTO request, BindingResult bindingResult);
}
