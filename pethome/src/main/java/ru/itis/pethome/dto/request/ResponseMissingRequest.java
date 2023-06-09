package ru.itis.pethome.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseMissingRequest {
    private UUID accountId;
    private UUID missingId;
}
