package ru.itis.pethome.service;

import org.springframework.web.bind.annotation.*;
import ru.itis.pethome.dto.request.MissingRequest;
import ru.itis.pethome.dto.response.MissingResponse;

import java.util.List;
import java.util.UUID;

public interface MissingService {
    MissingResponse createMissing(MissingRequest missingRequest);

    MissingResponse updateMissing(MissingRequest missingRequest);

    void deleteMissing(UUID uuid);

    MissingResponse getMissingById(UUID uuid);

    List<MissingResponse> getMissingList();

    List<MissingResponse> getMissingPageableList(int page);

    List<MissingResponse> getMissingListByOwner(UUID id);
}
