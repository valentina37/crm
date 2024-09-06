package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.demo.entity.Contrato;
import com.example.demo.repository.ContratoRepository;
import com.example.demo.service.ExcelService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


@RestController

public class ExportController {

	 @Autowired
	    private ExcelService excelService;

	    @Autowired
	    private ContratoRepository contratoRepository;

	    @GetMapping("/export/contratos/excel")
	    public StreamingResponseBody exportToExcel(HttpServletResponse response) throws IOException {
	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        response.setHeader("Content-Disposition", "attachment; filename=contratos.xlsx");

	        List<Contrato> contratos = contratoRepository.findAll();
	        return outputStream -> {
	            ByteArrayInputStream excelFile = excelService.exportContratosToExcel(contratos);
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = excelFile.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	        };
	    }
	}
	

