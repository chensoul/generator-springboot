package <%= packageName %>.model.query;

import org.springframework.data.domain.PageRequest;

public record <%= entityName %>Query(PageRequest pageRequest) {}