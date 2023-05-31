package ru.itis.pethome.controller.api;

import org.springframework.web.bind.annotation.*;
import ru.itis.pethome.dto.request.MissingRequest;
import ru.itis.pethome.dto.response.MissingResponse;

import java.util.List;
import java.util.UUID;

@RequestMapping("/missing")
@CrossOrigin(origins = "http://localhost:3000")
public interface MissingApi {

    @PostMapping
    MissingResponse createMissing(@RequestBody MissingRequest missingRequest);

    @PutMapping
    MissingResponse updateMissing(@RequestBody MissingRequest missingRequest);

    @DeleteMapping("/{uuid}")
    void deleteMissing(@PathVariable UUID uuid);

    @GetMapping("/{uuid}")
    MissingResponse getMissingById(@PathVariable UUID uuid);


    @GetMapping("/list")
    List<MissingResponse> getMissingList();

    @GetMapping("/owner/{id}")
    List<MissingResponse> getMissingListByOwnerId(@PathVariable UUID id);

    @GetMapping("/list/{page}")
    List<MissingResponse> getMissingPageableList(@PathVariable int page);

}
