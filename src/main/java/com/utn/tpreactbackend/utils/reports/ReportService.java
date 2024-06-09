package com.utn.tpreactbackend.utils.reports;

import com.utn.tpreactbackend.entities.PedidoDetalle;
import com.utn.tpreactbackend.repository.PedidoDetalleRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    public byte[] generateReport(LocalDate startDate, LocalDate endDate) throws IOException {
        List<PedidoDetalle> pedidoDetalles = pedidoDetalleRepository.findPedidosBetweenDates(startDate, endDate);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte de Pedidos");

            // Encabezado
            String[] headers = {"Fecha Pedido", "Instrumento", "Marca", "Modelo", "Cantidad", "Precio", "Subtotal"};
            Row headerRow = sheet.createRow(0);

            //Estilos para el texto en negrita
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerCellStyle.setFont(headerFont);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Datos
            int rowNum = 1;
            for (PedidoDetalle pedidoDetalle : pedidoDetalles) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(pedidoDetalle.getPedido().getFechaPedido().toString());
                row.createCell(1).setCellValue(pedidoDetalle.getInstrumento().getInstrumento());
                row.createCell(2).setCellValue(pedidoDetalle.getInstrumento().getMarca());
                row.createCell(3).setCellValue(pedidoDetalle.getInstrumento().getModelo());
                row.createCell(4).setCellValue(pedidoDetalle.getCantidad());
                row.createCell(5).setCellValue(pedidoDetalle.getInstrumento().getPrecio().doubleValue());
                row.createCell(6).setCellValue(pedidoDetalle.getCantidad() * pedidoDetalle.getInstrumento().getPrecio().doubleValue());
            }

            // Auto tamaño de columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }
}

