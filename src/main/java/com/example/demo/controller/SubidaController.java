

package com.example.demo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SubidaController {

    @GetMapping("gerente/subir")
    public String mostrarFormulario() {
        return "subir-excel";
    }

    @PostMapping("/subir-excel")
    public String subirArchivo(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Por favor seleccione un archivo para subir.");
            return "subir-excel";
        }

        List<List<String>> datos = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                List<String> fila = new ArrayList<>();
                for (Cell cell : row) {
                    fila.add(cell.toString());
                }
                datos.add(fila);
            }

            model.addAttribute("datos", datos);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Error al procesar el archivo.");
            return "subir-excel";
        }

        return "resultado-subida";
    }
}
