package <%= packageName %>.model.query;

public record Find<%= entityName %>Query(int pageNumber, int pageSize, String sortBy, String sortDir) {}