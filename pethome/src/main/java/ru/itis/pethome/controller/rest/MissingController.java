package ru.itis.pethome.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.pethome.controller.api.MissingApi;
import ru.itis.pethome.dto.request.MissingRequest;
import ru.itis.pethome.dto.response.MissingResponse;
import ru.itis.pethome.service.MissingService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MissingController implements MissingApi{

    private final MissingService missingService;

    @Override
    public MissingResponse createMissing(@RequestBody MissingRequest missingRequest) {
        return missingService.createMissing(missingRequest);
    }

    @Override
    public MissingResponse updateMissing(MissingRequest missingRequest) {
        return missingService.updateMissing(missingRequest);
    }

    @Override
    public void deleteMissing(UUID uuid) {
        missingService.deleteMissing(uuid);
    }

    @Override
    public MissingResponse getMissingById(UUID uuid) {
        return missingService.getMissingById(uuid);
    }

    @Override
    public List<MissingResponse> getMissingList() {
        return missingService.getMissingList();
    }

    @Override
    public List<MissingResponse> getMissingListByOwnerId(UUID id) {
        return missingService.getMissingListByOwner(id);
    }

    @Override
    public List<MissingResponse> getMissingPageableList(int page) {
        return missingService.getMissingPageableList(page);
    }
}
