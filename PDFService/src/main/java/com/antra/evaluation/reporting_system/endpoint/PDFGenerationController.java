package com.antra.evaluation.reporting_system.endpoint;

import com.antra.evaluation.reporting_system.pojo.api.PDFRequest;
import com.antra.evaluation.reporting_system.pojo.api.PDFResponse;
import com.antra.evaluation.reporting_system.pojo.report.PDFFile;
import com.antra.evaluation.reporting_system.service.PDFService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
public class PDFGenerationController {

    private static final Logger log = LoggerFactory.getLogger(PDFGenerationController.class);

    private final PDFService pdfService;

    @Autowired
    public PDFGenerationController(PDFService pdfService) {
        this.pdfService = pdfService;
    }


    @PostMapping("/pdf")
    public ResponseEntity<PDFResponse> createPDF(@RequestBody @Validated PDFRequest request) {
        log.info("Got request to generate PDF: {}", request);

        PDFResponse response = new PDFResponse();
        PDFFile file = null;
        response.setReqId(request.getReqId());

        try {
            file = pdfService.createPDF(request);
            response.setFileId(file.getId());
            response.setFileLocation(file.getFileLocation());
            response.setFileSize(file.getFileSize());
            log.info("Generated: {}", file);
        } catch (Exception e) {
            response.setFailed(true);
            log.error("Error in generating pdf", e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/pdf/{id}")
    public ResponseEntity<PDFResponse> deleteExcel(@PathVariable String id) throws FileNotFoundException, NullPointerException {
        log.debug("Got Request to Delete PDF:{}", id);
        var response = new PDFResponse();
        PDFFile fileDeleted = pdfService.deletePDF(id);
        log.debug("pdf fileDeleted -> " + fileDeleted);
        if (fileDeleted != null) {
            BeanUtils.copyProperties(fileDeleted, response);
            response.setFailed(false);
        }
        else {
            response.setFailed(true);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
