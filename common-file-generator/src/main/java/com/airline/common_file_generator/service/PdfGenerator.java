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

        String passengerDetail = "Passenger details";
        Text passengerDetailElement = new Text(passengerDetail)
                .setFontColor(new DeviceRgb(0, 0, 0))
                .setFontSize(14)
                .setBold();

        String flightDetails = " Flight details";
        Text flightDetailsElement = new Text(flightDetails)
                .setFontColor(new DeviceRgb(0, 0, 0))
                .setFontSize(14)
                .setBold();


        String additionalText =  "Additional Information:";
        Text additionalTextElement = new Text(additionalText)
                .setFontColor(new DeviceRgb(0, 0, 0))
                .setFontSize(14)
                .setBold();


        ticketDetails
                .add(welcomeTextElement).add("\n\n")
                .add(passengerDetailElement).add("\n")
                .add("  First Name: " + content.getFirstName()).add("\n")
                .add("  Last Name: " + content.getLastName()).add("\n\n")
                .add(flightDetailsElement).add("\n")
                .add("  From: " + content.getFrom().getName() + ", " + content.getFrom().getCountry()).add("\n")
                .add("  To: " + content.getTo().getName() + ", " + content.getTo().getCountry()).add("\n")
                .add("  Arrival date: " + content.getArrivalDate().format(DateTimeFormatter.ISO_DATE_TIME)).add("\n")
                .add("  Departure date: " + content.getDepartureDate().format(DateTimeFormatter.ISO_DATE_TIME)).add("\n")
                .add("  Ticket price: " + content.getPrice() + " AZN").add("\n\n");

        String specialText = "Baggage allowance: Each passenger can check in the amount of baggage specified in the \"Baggage\" column above, free of charge. " +
                "Please note that additional airline baggage allowance restrictions may apply. For further information, please contact the airline representative. " +
                "Registration Information: Airport check-in and seat selection service are subject to a fee. Online check-in is available 24 hours before departure and closes 120 minutes before departure. " +
                "Online check-in is free, and a seat will be automatically assigned. To change the seat, additional payment may be required.";

        Text specialTextElement = new Text(specialText)
                .setFontColor(new DeviceRgb(0, 0, 0))
                .setFontSize(8)
                .setItalic();

        ticketDetails.add(additionalTextElement).add("\n").add(specialTextElement).add("\n\n");

        ticketDetails.setBackgroundColor(lightBlue)
                .setPadding(10);

        return ticketDetails;
    }
}
