package com.airline.common_file_generator.service;

import com.airline.common_notification.model.dto.response.TicketResponse;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.time.format.DateTimeFormatter;

@Service
public class PdfGenerator {
    @SneakyThrows
    public static byte[] generatePdfContent(TicketResponse content) {
        ByteArrayOutputStream ticketFile = new ByteArrayOutputStream();

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(ticketFile));
        Document document = new Document(pdfDocument);

        Paragraph title = new Paragraph("Airline Ticket Details")
                .setFontColor(new DeviceRgb(255, 165, 0))
                .setBold()
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER);
        Image backgroundImage = new Image(ImageDataFactory.create(new URL("https://flyingmag.sfo3.digitaloceanspaces.com/flyingma/wp-content/uploads/2022/06/23090933/AdobeStock_249454423-scaled.jpeg")))
                .scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());

        document.add(title);
        document.add(createTicketDetails(content));
        pdfDocument.addNewPage();
        document.add(backgroundImage);
        document.close();


        return ticketFile.toByteArray();
    }

    @SneakyThrows
    private static Paragraph createTicketDetails(TicketResponse content) {
        Color darkBlue = new DeviceRgb(1, 0, 102);
        Color lightBlue = new DeviceRgb(173, 216, 230);

        Paragraph ticketDetails = new Paragraph()
                .setFontColor(darkBlue)
                .setFontSize(12);


        String welcomeText = "Welcome to the EXN air reservation system!";

        Text welcomeTextElement = new Text(welcomeText)
                .setFontColor(new DeviceRgb(0, 0, 0))
                .setFontSize(14)
                .setBold()
                .setItalic();

        ticketDetails
                .add(welcomeTextElement).add("\n")
                .add("\n  First Name: " + content.getFirstName())
                .add("\n  Last Name: " + content.getLastName())
                .add("\n\nFrom:")
                .add("\n  Country: " + content.getFrom().getCountry())
                .add("\n  Name: " + content.getFrom().getName())
                .add("\n\nTo:")
                .add("\n  Country: " + content.getTo().getCountry())
                .add("\n  Name: " + content.getTo().getName())
                .add("\n\nArrival Date: " + content.getArrivalDate().format(DateTimeFormatter.ISO_DATE_TIME))
                .add("\nDeparture Date: " + content.getDepartureDate().format(DateTimeFormatter.ISO_DATE_TIME))
                .add("\nPrice: " + content.getPrice() + " AZN");


        String specialText = "Each passenger can check in the amount of baggage specified in the \"Baggage\" column above, free of charge. " +
                "In addition to airline baggage allowance restrictions may apply. Please contact your airline representative for more information. " +
                "Registration information " +
                "Registration at the airport and seat selection service is paid. Online check-in for the flight is activated 24 hours before the departure time and 120 minutes before " +
                "closes. Online registration is free, and at this time you will be provided with an automatically selected free seat in the aircraft cabin. " +
                "To change the automatically assigned seat appropriate payment must be made.";

        Text specialTextElement = new Text(specialText)
                .setFontColor(new DeviceRgb(0, 0, 0))
                .setFontSize(8)
                .setItalic();

        ticketDetails.add("\n\n").add(specialTextElement);


        ticketDetails.setBackgroundColor(lightBlue)
                .setPadding(5);

        return ticketDetails;
    }

}
