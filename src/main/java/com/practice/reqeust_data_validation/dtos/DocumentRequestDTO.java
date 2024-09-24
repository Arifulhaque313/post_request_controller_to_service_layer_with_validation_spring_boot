package com.practice.reqeust_data_validation.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequestDTO {
    @NotBlank(message = "Document ID is required.")
    private String document_id;

    @NotBlank(message = "Language is required.")
    private String language;

    @NotBlank(message = "Type is required.")
    private String type;

    public String getDocumentId() {
        return document_id;
    }
}
