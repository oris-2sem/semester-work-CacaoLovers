package ru.itis.pethome.mappers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.pethome.dto.request.VolunteerRequest;
import ru.itis.pethome.dto.response.VolunteerResponse;
import ru.itis.pethome.model.Volunteer;

@Component
@AllArgsConstructor
public class VolunteerMapper implements EntityMapper<Volunteer, VolunteerRequest, VolunteerResponse> {

    private final AccountMapper accountMapper;

    @Override
    public Volunteer toEntity(VolunteerRequest volunteerRequest) {
        return Volunteer.builder().build();
    }

    @Override
    public VolunteerResponse toResponse(Volunteer object) {
        return VolunteerResponse.builder()
                .account(accountMapper.toResponse(object.getAccount()))
                .build();
    }
}
