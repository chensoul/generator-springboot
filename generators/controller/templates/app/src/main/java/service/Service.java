package <%= packageName %>.service;

<%_ if (persistence === 'mybatis') { _%>
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
<%_ } _%>
import <%= packageName %>.entity.<%= entityName %>;
import <%= packageName %>.exception.<%= entityName %>NotFoundException;
import <%= packageName %>.mapper.<%= entityName %>Mapper;
import <%= packageName %>.model.query.<%= entityName %>Query;
import <%= packageName %>.model.request.<%= entityName %>Request;
import <%= packageName %>.model.response.<%= entityName %>Response;
import <%= packageName %>.repository.<%= entityName %>Repository;
import <%= packageName %>.util.PageUtils;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
<%_ if (persistence === 'jpa') { _%>
public class <%= entityName %>Service {
<%_ } _%>
<%_ if (persistence === 'mybatis') { _%>
public class <%= entityName %>Service extends ServiceImpl<<%= entityName %>Repository, <%= entityName %>> {
<%_ } _%>
    <%_ if (persistence === 'jpa') { _%>
    private final <%= entityName %>Repository <%= entityVarName %>Repository;
    <%_ } _%>
    private final <%= entityName %>Mapper <%= entityVarName %>Mapper;

    public Page<<%= entityName %>Response> findAll<%= entityName %>s(
        <%= entityName %>Query <%= entityVarName %>Query) {
        <%_ if (persistence === 'jpa') { _%>
        Page<<%= entityName %>> <%= entityVarName %>Page = <%= entityVarName %>Repository.findAll(<%= entityVarName %>Query.pageable());
        List<<%= entityName %>Response> <%= entityVarName %>ResponseList = <%= entityVarName %>Mapper.toResponseList(<%= entityVarName %>Page.getContent());
        return new PageImpl(<%= entityVarName %>ResponseList, <%= entityVarName %>Query.pageable(), <%= entityVarName %>Page.getTotalElements());
        <%_ } _%>
        <%_ if (persistence === 'mybatis') { _%>
        IPage<<%= entityName %>> <%= entityVarName %>Page = page(PageUtils.fromPageRequest(<%= entityVarName %>Query.pageable()));
        List<<%= entityName %>Response> <%= entityVarName %>ResponseList = <%= entityVarName %>Mapper.toResponseList(<%= entityVarName %>Page.getRecords());
        return new PageImpl(<%= entityVarName %>ResponseList, <%= entityVarName %>Query.pageable(), <%= entityVarName %>Page.getTotal());
        <%_ } _%>
    }

    public Optional<<%= entityName %>Response> find<%= entityName %>ById(Long id) {
        <%_ if (persistence === 'jpa') { _%>
        return <%= entityVarName %>Repository.findById(id).map(<%= entityVarName %>Mapper::toResponse);
        <%_ } _%>
        <%_ if (persistence === 'mybatis') { _%>
        return Optional.ofNullable(getById(id)).map(<%= entityVarName %>Mapper::toResponse);
        <%_ } _%>
    }

    @Transactional
    public <%= entityName %>Response save<%= entityName %>(<%= entityName %>Request <%= entityVarName %>Request) {
        <%= entityName %> <%= entityVarName %> = <%= entityVarName %>Mapper.toEntity(<%= entityVarName %>Request);
        <%_ if (persistence === 'jpa') { _%>
        <%= entityName %> saved<%= entityName %> = <%= entityVarName %>Repository.save(<%= entityVarName %>);
        return <%= entityVarName %>Mapper.toResponse(saved<%= entityName %>);
        <%_ } _%>
        <%_ if (persistence === 'mybatis') { _%>
        baseMapper.insert(<%= entityVarName %>);
        return <%= entityVarName %>Mapper.toResponse(<%= entityVarName %>);
        <%_ } _%>
    }

    @Transactional
    public <%= entityName %>Response update<%= entityName %>(Long id, <%= entityName %>Request <%= entityVarName %>Request) {
        <%_ if (persistence === 'jpa') { _%>
        <%= entityName %> <%= entityVarName %> =
        <%= entityVarName %>Repository
                        .findById(id)
                        .orElseThrow(() -> new <%= entityName %>NotFoundException(id));
        <%_ } _%>
        <%_ if (persistence === 'mybatis') { _%>
        <%= entityName %> <%= entityVarName %> =
                Optional.of(baseMapper.selectById(id))
                .orElseThrow(() -> new <%= entityName %>NotFoundException(id));
        <%_ } _%>

        // Update the <%= entityVarName %> object with data from <%= entityVarName %>Request
        <%= entityVarName %>Mapper.map<%= entityName %>WithRequest(<%= entityVarName %>, <%= entityVarName %>Request);

        // Save the updated <%= entityVarName %> object
        <%_ if (persistence === 'jpa') { _%>
        <%= entityName %> updated<%= entityName %> = <%= entityVarName %>Repository.save(<%= entityVarName %>);
        return <%= entityVarName %>Mapper.toResponse(updated<%= entityName %>);
        <%_ } _%>
        <%_ if (persistence === 'mybatis') { _%>
        baseMapper.updateById(<%= entityVarName %>);
        return <%= entityVarName %>Mapper.toResponse(<%= entityVarName %>);
        <%_ } _%>
    }

    @Transactional
    public void delete<%= entityName %>ById(Long id) {
        <%_ if (persistence === 'jpa') { _%>
        <%= entityVarName %>Repository.deleteById(id);
        <%_ } _%>
        <%_ if (persistence === 'mybatis') { _%>
        baseMapper.deleteById(id);
        <%_ } _%>
    }
}
