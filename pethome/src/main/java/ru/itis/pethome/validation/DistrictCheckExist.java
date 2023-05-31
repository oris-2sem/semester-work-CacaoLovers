package ru.itis.pethome.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.pethome.model.City;
import ru.itis.pethome.model.District;
import ru.itis.pethome.service.DistrictService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class DistrictCheckExist implements ConstraintValidator<DistrictExist, District> {

    private final DistrictService districtService;

    @Override
    public boolean isValid(District value, ConstraintValidatorContext context) {

        City city = districtService.findCityByName(value.getCity().getName())
                .orElse(value.getCity());

        if (value.getPosX() == null || value.getPosY() == null){
            return false;
        }

        districtService.createDistrict(District.builder()
                        .name(value.getName())
                        .posX(value.getPosX())
                        .posY(value.getPosY())
                        .city(city)
                        .build());
        return true;
    }
}
