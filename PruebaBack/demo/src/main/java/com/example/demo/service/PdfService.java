package com.example.demo.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;
import com.itextpdf.text.pdf.draw.LineSeparator;


import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PdfService {

    public byte[] generarPdf(String nombre, String apellido) throws DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 60, 60, 80, 60);
        PdfWriter writer = PdfWriter.getInstance(document, baos);

        document.open();

        // Fuentes
        Font fuenteTitulo = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD, new BaseColor(33, 37, 41));
        Font fuenteSubtitulo = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(108, 117, 125));
        Font fuenteLabel = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(73, 80, 87));
        Font fuenteValor = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL, new BaseColor(33, 37, 41));
        Font fuentePie = new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC, new BaseColor(150, 150, 150));

        // ---- Encabezado ----
        PdfPTable headerTable = new PdfPTable(1);
        headerTable.setWidthPercentage(100);

        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(new BaseColor(13, 110, 253));
        headerCell.setBorder(Rectangle.NO_BORDER);
        headerCell.setPadding(20);

        Font fuenteHeader = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.WHITE);
        Paragraph headerPar = new Paragraph("Documento Generado", fuenteHeader);
        headerPar.setAlignment(Element.ALIGN_CENTER);
        headerCell.addElement(headerPar);
        headerTable.addCell(headerCell);

        document.add(headerTable);
        document.add(Chunk.NEWLINE);

        // ---- Título principal ----
        Paragraph titulo = new Paragraph("Información de la Persona", fuenteTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(6);
        document.add(titulo);

        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        Paragraph subtitulo = new Paragraph("Generado el " + fechaHora, fuenteSubtitulo);
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        subtitulo.setSpacingAfter(24);
        document.add(subtitulo);

        // ---- Separador ----
        LineSeparator separator = new LineSeparator();
        separator.setLineColor(new BaseColor(220, 220, 220));
        document.add(new Chunk(separator));
        document.add(Chunk.NEWLINE);

        // ---- Tabla de datos ----
        PdfPTable tabla = new PdfPTable(2);
        tabla.setWidthPercentage(80);
        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.setWidths(new float[]{1f, 2f});
        tabla.setSpacingBefore(10);
        tabla.setSpacingAfter(20);

        agregarFila(tabla, "NOMBRE", nombre, fuenteLabel, fuenteValor);
        agregarFila(tabla, "APELLIDO", apellido, fuenteLabel, fuenteValor);
        agregarFila(tabla, "NOMBRE COMPLETO", nombre + " " + apellido, fuenteLabel, fuenteValor);

        document.add(tabla);

        // ---- Separador inferior ----
        document.add(new Chunk(separator));
        document.add(Chunk.NEWLINE);

        // ---- Pie de página ----
        Paragraph pie = new Paragraph("Este documento fue generado automáticamente por el sistema PDF Generator.", fuentePie);
        pie.setAlignment(Element.ALIGN_CENTER);
        document.add(pie);

        document.close();
        return baos.toByteArray();
    }

    private void agregarFila(PdfPTable tabla, String label, String valor, Font fuenteLabel, Font fuenteValor) {
        PdfPCell celdaLabel = new PdfPCell(new Phrase(label, fuenteLabel));
        celdaLabel.setBackgroundColor(new BaseColor(248, 249, 250));
        celdaLabel.setPadding(10);
        celdaLabel.setBorderColor(new BaseColor(220, 220, 220));
        tabla.addCell(celdaLabel);

        PdfPCell celdaValor = new PdfPCell(new Phrase(valor, fuenteValor));
        celdaValor.setPadding(10);
        celdaValor.setBorderColor(new BaseColor(220, 220, 220));
        tabla.addCell(celdaValor);
    }
}