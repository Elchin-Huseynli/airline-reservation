package com.airline.airplane_ms.mapping;

import com.airline.airplane_ms.model.dto.request.AirplaneRequest;
import com.airline.airplane_ms.model.dto.request.AirplaneUpdateRequest;
import com.airline.airplane_ms.model.dto.response.AirplaneResponse;
import com.airline.airplane_ms.model.entity.Airplane;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AirplaneMapping {

    List<AirplaneResponse> airplaneListToAirplaneResponseList(List<Airplane> airplaneRequest);

    AirplaneResponse airplaneToAirplaneResponse(Airplane airplane);


    @Mappings({@Mapping(target = "id", ignore = true),
            @Mapping(target = "busy", ignore = true),
            @Mapping(target = "status", ignore = true)})
    Airplane airplaneRequestToAirplane(AirplaneRequest airplaneRequest);

    @Mappings({@Mapping(target = "busy", ignore = true),
            @Mapping(target = "status", ignore = true)})
    Airplane airplaneResponseToAirplane(AirplaneResponse airplaneResponse);

    @Mappings({@Mapping(target = "busy", ignore = true),
            @Mapping(target = "status", ignore = true)})
    Airplane airplaneUpdateRequestToUpdateAirplane(@MappingTarget Airplane airplane, AirplaneUpdateRequest airplaneRequest);
}
