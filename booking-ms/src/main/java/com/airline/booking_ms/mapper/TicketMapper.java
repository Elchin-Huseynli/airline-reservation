package com.airline.booking_ms.mapper;


import com.airline.booking_ms.model.entity.Ticket;
import com.airline.common_notification.model.dto.response.TicketResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TicketMapper {


    List<TicketResponse> ticketListToTicketResponseList(List<Ticket> tickets);


    TicketResponse ticketToTicketResponse(Ticket ticket);

}
