package <%= packageName %>.model.query;

public record Find<%= entityName %>sQuery(int pageNumber, int pageSize, String sortBy, String sortDir) {}