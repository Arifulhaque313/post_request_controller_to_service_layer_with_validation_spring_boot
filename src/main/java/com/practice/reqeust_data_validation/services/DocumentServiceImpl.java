package com.practice.reqeust_data_validation.services;

import com.practice.reqeust_data_validation.dtos.DocumentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl extends AbstractDocumentService {

    private final TemplateEngine templateEngine;

    // Remove the manually defined constructor.
    // public DocumentServiceImpl(TemplateEngine templateEngine) {
    //     this.templateEngine = templateEngine;
    // }

    @Override
    public void processDocumentRequest(DocumentRequestDTO request, BindingResult bindingResult) {
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Validation failed");
        }

        // Generate PDF from HTML template
        try {
            String pdfName = request.getDocumentId() + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".pdf";
            generatePDF(pdfName, request);
        } catch (IOException e) {
            throw new RuntimeException("Error while generating PDF");
        }
    }

    private void generatePDF(String pdfName, DocumentRequestDTO request) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            // Use Thymeleaf to generate HTML
            Context context = new Context();
            context.setVariable("document", request);
            String htmlContent = templateEngine.process("document_template", context);

            contentStream.beginText();
            contentStream.showText(htmlContent);
            contentStream.endText();
        }

        Path outputPath = Path.of(pdfName);
        document.save(Files.newOutputStream(outputPath));
        document.close();
    }
}