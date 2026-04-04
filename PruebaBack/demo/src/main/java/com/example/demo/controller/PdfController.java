package com.example.demo.controller;

import com.example.demo.dto.PersonRequest;
import com.example.demo.service.PdfService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "*")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    /**
     * POST /api/pdf/generar
     * Body: { "nombre": "Juan", "apellido": "Pérez" }
     * Retorna un PDF como bytes
     */
    @PostMapping("/generar")
    public ResponseEntity<byte[]> generarPdf(@RequestBody PersonRequest request) {
        try {
            if (request.getNombre() == null || request.getNombre().isBlank() ||
                request.getApellido() == null || request.getApellido().isBlank()) {
                return ResponseEntity.badRequest().build();
            }

            byte[] pdfBytes = pdfService.generarPdf(request.getNombre(), request.getApellido());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                ContentDisposition.inline()
                    .filename(request.getNombre() + "_" + request.getApellido() + ".pdf")
                    .build()
            );
            headers.setContentLength(pdfBytes.length);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (DocumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}