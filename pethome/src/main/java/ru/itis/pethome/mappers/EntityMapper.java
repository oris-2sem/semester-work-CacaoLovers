package ru.itis.pethome.mappers;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityMapper<Object,Request,Response> {
    Object toEntity(Request request);
    Response toResponse(Object object);

    default List<Response> toListResponse(List<Object> objects){
        return objects.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
