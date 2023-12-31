package com.airline.flight_ms.mapper;

import com.airline.flight_ms.model.dto.request.FlightRequest;
import com.airline.flight_ms.model.entity.Flight;
import org.mapstruct.*;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FlightMapping {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "enable",ignore = true)
    @Mapping(target = "fly",ignore = true)
    Flight flightRequestDtoToFlight (FlightRequest flightRequest);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "fly",ignore = true)
    @Mapping(target = "enable",ignore = true)
    Flight flightUpdateRequestToFlight (@MappingTarget Flight flight, FlightRequest flightRequest);



}
