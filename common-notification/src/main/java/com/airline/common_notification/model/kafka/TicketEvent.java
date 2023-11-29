package com.airline.common_notification.model.kafka;

import com.airline.common_notification.model.dto.response.TicketResponse;
import lombok.Builder;

@Builder
public class TicketEvent {

    String message;
    String status;
    TicketResponse ticket;

    public TicketEvent(String message, String status, TicketResponse ticket) {
        this.message = message;
        this.status = status;
        this.ticket = ticket;
    }

    public TicketEvent() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TicketResponse getTicket() {
        return ticket;
    }

    public void setTicket(TicketResponse ticket) {
        this.ticket = ticket;
    }


    @Override
    public String toString() {
        return "TicketEvent{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
