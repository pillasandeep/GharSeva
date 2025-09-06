package com.example.Webapp.Util;
import com.example.Webapp.Entity.BookingEntity;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.awt.Color;
import java.io.OutputStream;
import java.util.List;

public class DownloadUtil {

    public static void exportTable(
            OutputStream outputStream,
            String titleText,
            String[] headers,
            List<?> data
    ) throws Exception {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph(titleText, titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" ")); // blank line

        // Table
        PdfPTable table = new PdfPTable(headers.length);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Headers
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);
        }

        // Data
        int serial = 1;
        for (Object row : data) {
            table.addCell(String.valueOf(serial++));

            if (row instanceof BookingEntity booking) {
                // Example for entity
                table.addCell(booking.getName());
                table.addCell(booking.getServicename());
                table.addCell(booking.getPhonenumber());
                table.addCell(booking.getAddress());
                table.addCell(booking.getLandmark());
                table.addCell(String.valueOf(booking.getTime()));
                table.addCell(String.valueOf(booking.getPrice()));
                table.addCell(booking.getState());

            } else if (row instanceof Object[] arr) {
                // Example for Object[] from native query
                for (Object col : arr) {
                   // table.addCell(col != null ? col.toString() : "");
                	table.addCell(String.valueOf(arr[0]));
                	table.addCell(String.valueOf(arr[1]));
                	table.addCell(String.valueOf(arr[2]));


                	
                }
//                table.addCell(String.valueOf(arr[3]));
//            	table.addCell(String.valueOf(arr[4]));
//            	table.addCell(String.valueOf(arr[5]));


            } else {
                // Fallback - just print toString()
                table.addCell(row.toString());
            }
        }

        document.add(table);
        document.close();
    }
}

