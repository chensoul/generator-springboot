package <%= packageName %>.service;

<%_ if (persistenceType === 'mybatis-plus') { _%>
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
<%_ } _%>
import <%= packageName %>.entity.<%= entityName %>;
import <%= packageName %>.exception.<%= entityName %>NotFoundException;
import <%= packageName %>.mapper.<%= entityName %>Mapper;
import <%= packageName %>.model.query.Find<%= entityName %>Query;
import <%= packageName %>.model.request.<%= entityName %>Request;
import <%= packageName %>.model.response.<%= entityName %>Response;
import <%= packageName %>.model.response.PagedResult;
import <%= packageName %>.repository.<%= entityName %>Repository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
<%_ if (persistenceType === 'jpa') { _%>
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
<%_ } _%>
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

<%_ if (persistenceType === 'jpa') { _%>
public class <%= entityName %>Service {
<%_ } _%>
<%_ if (persistenceType === 'mybatis-plus') { _%>
public class <%= entityName %>Service extends ServiceImpl<<%= entityName %>Repository, <%= entityName %>> {
<%_ } _%>
    <%_ if (persistenceType === 'jpa') { _%>
    private final <%= entityName %>Repository <%= entityVarName %>Repository;
    <%_ } _%>
    private final <%= entityName %>Mapper <%= entityVarName %>Mapper;

    public PagedResult<<%= entityName %>Response> findAll<%= entityName %>s(
        Find<%= entityName %>Query find<%= entityName %>Query) {

        // create Pageable instance
        <%_ if (persistenceType === 'jpa') { _%>
        Pageable pageable = createPageable(find<%= entityName %>Query);

        Page<<%= entityName %>> <%= entityVarName %>Page = <%= entityVarName %>Repository.findAll(pageable);
        List<<%= entityName %>Response> <%= entityVarName %>ResponseList = <%= entityVarName %>Mapper.toResponseList(<%= entityVarName %>Page.getContent());
        <%_ } _%>
        <%_ if (persistenceType === 'mybatis-plus') { _%>
        Page pageable = createPageable(find<%= entityName %>Query);

        IPage<Customer> <%= entityVarName %>Page = page(pageable);
        List<<%= entityName %>Response> <%= entityVarName %>ResponseList = <%= entityVarName %>Mapper.toResponseList(<%= entityVarName %>Page.getRecords());
        <%_ } _%>
        return new PagedResult<>(<%= entityVarName %>Page, <%= entityVarName %>ResponseList);
    }

    <%_ if (persistenceType === 'jpa') { _%>
    private Pageable createPageable(Find<%= entityName %>Query find<%= entityName %>Query) {
        int pageNo = Math.max(find<%= entityName %>Query.pageNumber() - 1, 0);
        Sort sort =
                Sort.by(
                        find<%= entityName %>Query.sortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
                                ? Sort.Order.asc(find<%= entityName %>Query.sortBy())
                                : Sort.Order.desc(find<%= entityName %>Query.sortBy()));
        return PageRequest.of(pageNo, find<%= entityName %>Query.pageSize(), sort);
    }
    <%_ } _%>
    <%_ if (persistenceType === 'mybatis-plus') { _%>
    private Page<<%= entityName %>> createPageable(Find<%= entityName %>Query find<%= entityName %>Query) {
        int pageNo = Math.max(find<%= entityName %>Query.pageNumber() - 1, 0);
        OrderItem orderItem = find<%= entityName %>Query.sortDir().equalsIgnoreCase("asc")
                ? OrderItem.asc(find<%= entityName %>Query.sortBy()) : OrderItem.desc(find<%= entityName %>Query.sortBy());
        Page page = Page.of(pageNo, find<%= entityName %>Query.pageSize(), true);
        page.setOptimizeCountSql(false);
        page.setOrders(List.of(orderItem));
        return page;
    }
    <%_ } _%>

    public Optional<<%= entityName %>Response> find<%= entityName %>ById(Long id) {
        <%_ if (persistenceType === 'jpa') { _%>
        return <%= entityVarName %>Repository.findById(id).map(<%= entityVarName %>Mapper::toResponse);
        <%_ } _%>
        <%_ if (persistenceType === 'mybatis-plus') { _%>
        return Optional.ofNullable(getById(id)).map(<%= entityVarName %>Mapper::toResponse);
        <%_ } _%>
    }

    @Transactional
    public <%= entityName %>Response save<%= entityName %>(<%= entityName %>Request <%= entityVarName %>Request) {
        <%= entityName %> <%= entityVarName %> = <%= entityVarName %>Mapper.toEntity(<%= entityVarName %>Request);
        <%_ if (persistenceType === 'jpa') { _%>
        <%= entityName %> saved<%= entityName %> = <%= entityVarName %>Repository.save(<%= entityVarName %>);
        return <%= entityVarName %>Mapper.toResponse(saved<%= entityName %>);
        <%_ } _%>
        <%_ if (persistenceType === 'mybatis-plus') { _%>
        baseMapper.insert(<%= entityVarName %>);
        return <%= entityVarName %>Mapper.toResponse(<%= entityVarName %>);
        <%_ } _%>
    }

    @Transactional
    public <%= entityName %>Response update<%= entityName %>(Long id, <%= entityName %>Request <%= entityVarName %>Request) {
        <%_ if (persistenceType === 'jpa') { _%>
        <%= entityName %> <%= entityVarName %> =
        <%= entityVarName %>Repository
                        .findById(id)
                        .orElseThrow(() -> new <%= entityName %>NotFoundException(id));
        <%_ } _%>
        <%_ if (persistenceType === 'mybatis-plus') { _%>
        <%= entityName %> <%= entityVarName %> =
                Optional.of(baseMapper.selectById(id))
                .orElseThrow(() -> new <%= entityName %>NotFoundException(id));
        <%_ } _%>

        // Update the <%= entityVarName %> object with data from <%= entityVarName %>Request
        <%= entityVarName %>Mapper.map<%= entityName %>WithRequest(<%= entityVarName %>, <%= entityVarName %>Request);

        // Save the updated <%= entityVarName %> object
        <%_ if (persistenceType === 'jpa') { _%>
        <%= entityName %> updated<%= entityName %> = <%= entityVarName %>Repository.save(<%= entityVarName %>);
        return <%= entityVarName %>Mapper.toResponse(updated<%= entityName %>);
        <%_ } _%>
        <%_ if (persistenceType === 'mybatis-plus') { _%>
        baseMapper.updateById(<%= entityVarName %>);
        return <%= entityVarName %>Mapper.toResponse(<%= entityVarName %>);
        <%_ } _%>
    }

    @Transactional
    public void delete<%= entityName %>ById(Long id) {
        <%_ if (persistenceType === 'jpa') { _%>
        <%= entityVarName %>Repository.deleteById(id);
        <%_ } _%>
        <%_ if (persistenceType === 'mybatis-plus') { _%>
        baseMapper.deleteById(id);
        <%_ } _%>
    }
}
