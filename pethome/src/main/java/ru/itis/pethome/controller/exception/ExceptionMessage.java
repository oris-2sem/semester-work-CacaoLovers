package ru.itis.pethome.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ExceptionMessage {
    private Integer code;
    private String error;
    private String name;
}
