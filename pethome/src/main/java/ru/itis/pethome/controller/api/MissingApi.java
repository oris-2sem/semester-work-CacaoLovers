package ru.itis.pethome.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.pethome.controller.exception.ExceptionMessage;
import ru.itis.pethome.dto.request.MissingRequest;
import ru.itis.pethome.dto.request.ResponseMissingRequest;
import ru.itis.pethome.dto.response.MissingResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/missing")
@CrossOrigin(origins = "http://localhost:3000")
public interface MissingApi {


    @Operation(summary = "Create new missing")
    @PostMapping
    MissingResponse createMissing(@RequestBody MissingRequest missingRequest);

    @PutMapping
    MissingResponse updateMissing(@RequestBody MissingRequest missingRequest);

    @DeleteMapping("/{uuid}")
    void deleteMissing(@PathVariable UUID uuid);

    @GetMapping("/{uuid}")
    MissingResponse getMissingById(@PathVariable UUID uuid);



    @Operation(summary = "Get missing by parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MissingResponse.class)),
                    description = "Found the missing"),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionMessage.class)),
                    description = "Invalid parameters"
            )
        }
    )
    @GetMapping
    List<MissingResponse> getMissing(@RequestParam Map<String, String> parameters);

    @GetMapping("/owner/{id}")
    List<MissingResponse> getMissingListByOwnerId(@PathVariable UUID id);

    @GetMapping("/list/{page}")
    List<MissingResponse> getMissingPageableList(@PathVariable int page);

    @PutMapping("/response")
    void responseByMissing(@RequestBody ResponseMissingRequest request);


    @GetMapping("/fact")
    String randomFactAboutCat();
}
