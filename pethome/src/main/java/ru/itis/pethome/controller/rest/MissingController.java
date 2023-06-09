package ru.itis.pethome.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.itis.pethome.controller.api.MissingApi;
import ru.itis.pethome.dto.request.MissingRequest;
import ru.itis.pethome.dto.request.ResponseMissingRequest;
import ru.itis.pethome.dto.response.MissingResponse;
import ru.itis.pethome.service.MissingService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MissingController implements MissingApi{

    private final MissingService missingService;
    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

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
    public List<MissingResponse> getMissing(Map<String, String> parameters) {
        return missingService.getMissing(parameters);
    }


    @Override
    public List<MissingResponse> getMissingListByOwnerId(UUID id) {
        return missingService.getMissingListByOwner(id);
    }

    @Override
    public List<MissingResponse> getMissingPageableList(int page) {
        return missingService.getMissingPageableList(page);
    }

    @Override
    public void responseByMissing(ResponseMissingRequest request) {
        missingService.responseByMissing(request.getMissingId(), request.getAccountId());
    }

    @Override
    public String randomFactAboutCat() {
        try {
            ResponseEntity<String> fact = restTemplate.exchange("https://catfact.ninja/fact", HttpMethod.GET, null, String.class);
            String response = fact.getBody();

            JsonNode jsonNode = objectMapper.readTree(response);

            return jsonNode.get("fact").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
