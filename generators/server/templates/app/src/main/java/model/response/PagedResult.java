package <%= packageName %>.model.response;

import java.util.List;

<%_ if (persistenceType === 'jpa') { _%>
import org.springframework.data.domain.Page;
<%_ } _%>
<%_ if (persistenceType === 'mybatis-plus') { _%>
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
<%_ } _%>

public record PagedResult<T>(
        List<T> data,
        long totalElements,
        int pageNumber,
        int totalPages,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious
) {
    <%_ if (persistenceType === 'jpa') { _%>
    public <R> PagedResult(Page<R> page, List<T> data) {
        this(data,
                page.getTotalElements(),
                page.getNumber() + 1,
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious());
    }
    <%_ } _%>
    <%_ if (persistenceType === 'mybatis-plus') { _%>
    public <R> PagedResult(IPage<R> page, List<T> data) {
        this(
                data,
                page.getTotal(),
                (int) page.getCurrent(),
                (int) page.getPages(),
                page.getCurrent()==1,
                page.getCurrent()==page.getPages(),
                ((Page)page).hasNext(),
                ((Page)page).hasPrevious());
    }
    <%_ } _%>
}