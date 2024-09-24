package com.practice.reqeust_data_validation.controllers;

import com.practice.reqeust_data_validation.dtos.DocumentRequestDTO;
import com.practice.reqeust_data_validation.dtos.ErrorResponseDTO;
import com.practice.reqeust_data_validation.dtos.SuccessResponseDTO;
import com.practice.reqeust_data_validation.services.DocumentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentServiceImpl documentServiceImpl;

    @PostMapping("/documents")
    public ResponseEntity<?> createDocument(@Valid @RequestBody DocumentRequestDTO request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                    "Validation failed",
                    bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList(),
                    "400"
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        documentServiceImpl.processDocumentRequest(request, bindingResult);

        SuccessResponseDTO successResponse = new SuccessResponseDTO(
                "Document processed successfully",
                null,
                "200"
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}