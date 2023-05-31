package ru.itis.pethome.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.pethome.dto.request.DistrictRequest;
import ru.itis.pethome.dto.response.DistrictResponse;
import ru.itis.pethome.model.District;

@Mapper(componentModel = "spring")
public interface DistrictMapper extends EntityMapper<District, DistrictRequest, DistrictResponse> {

    @Override
    @Mapping(target = "city", expression = "java(null)")
    District toEntity(DistrictRequest districtRequest);

    @Override
    DistrictResponse toResponse(District object);
}
