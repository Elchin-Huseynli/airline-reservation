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

    @Override
    public String toString() {
        return "TicketEvent{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
