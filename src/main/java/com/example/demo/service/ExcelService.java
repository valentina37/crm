package com.example.demo.service;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contrato;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    public ByteArrayInputStream exportContratosToExcel(List<Contrato> contratos) throws IOException {
        String[] columns = {"ID", "Valor", "Tipo Equipo", "Estado", "Usuario", "Cliente"};

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Contratos");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Create data rows
            int rowIdx = 1;
            for (Contrato contrato : contratos) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(contrato.getId());
                row.createCell(1).setCellValue(contrato.getValor());
                row.createCell(2).setCellValue(contrato.getTipoEquipo());
                row.createCell(3).setCellValue(contrato.getEstado());
                row.createCell(4).setCellValue(contrato.getUsuario().getNombre());
                row.createCell(5).setCellValue(contrato.getCliente().getNombre());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}