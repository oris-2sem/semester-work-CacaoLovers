package ru.itis.pethome.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.pethome.dao.AccountDao;
import ru.itis.pethome.dto.request.MissingRequest;
import ru.itis.pethome.dto.response.MissingResponse;
import ru.itis.pethome.model.Missing;
import ru.itis.pethome.service.DistrictService;


@Component
@AllArgsConstructor
public class MissingMapper implements EntityMapper<Missing, MissingRequest, MissingResponse>{

    private final AccountDao accountDao;
    private final DistrictService districtService;
    private final AccountMapper accountMapper;
    private final DistrictMapper districtMapper;

    @Override
    public Missing toEntity(MissingRequest missingRequest){
        return Missing.builder()
                .owner(accountDao.getReferenceById(missingRequest.getOwner().getId()))
                .district(districtService.resolutionDistrict(missingRequest.getDistrict()).get())
                .posY(missingRequest.getPosY())
                .posX(missingRequest.getPosX())
                .name(missingRequest.getName())
                .type(missingRequest.getType())
                .status(missingRequest.getStatus())
                .description(missingRequest.getDescription())
                .kind(missingRequest.getKind())
                .gender(missingRequest.getGender())
                .imagePath(missingRequest.getImage().getPath())
                .address(missingRequest.getAddress())
                .build();
    }

    @Override
    public MissingResponse toResponse(Missing object){
        return MissingResponse.builder()
                .name(object.getName())
                .posX(object.getPosX())
                .posY(object.getPosY())
                .description(object.getDescription())
                .owner(accountMapper.toResponse(object.getOwner()))
                .status(object.getStatus())
                .district(districtMapper.toResponse(object.getDistrict()))
                .type(object.getType())
                .id(object.getId())
                .kind(object.getKind())
                .gender(object.getGender())
                .imagePath(object.getImagePath())
                .address(object.getAddress())
                .build();
    }
}
