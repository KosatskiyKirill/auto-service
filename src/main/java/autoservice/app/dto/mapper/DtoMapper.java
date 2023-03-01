package autoservice.app.dto.mapper;

public interface DtoMapper<D, T> {
    D toDto(T t);

    T toModel(D dto);
}
