package <%= packageName %>.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.willDoNothing;

import <%= packageName %>.entity.<%= entityName %>;
import <%= packageName %>.mapper.<%= entityName %>Mapper;
import <%= packageName %>.model.query.Find<%= entityName %>Query;
import <%= packageName %>.model.response.<%= entityName %>Response;
import <%= packageName %>.model.response.PagedResult;
import <%= packageName %>.repository.<%= entityName %>Repository;
import java.util.List;
import java.util.Optional;
<%_ if (persistence === 'mybatis-plus') { _%>
import org.junit.jupiter.api.BeforeEach;
<%_ } _%>
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
<%_ if (persistence === 'mybatis-plus') { _%>
import org.springframework.test.util.ReflectionTestUtils;
<%_ } _%>

@ExtendWith(MockitoExtension.class)
class <%= entityName %>ServiceTest {

    @Mock private <%= entityName %>Repository <%= entityVarName %>Repository;
    @Mock private <%= entityName %>Mapper <%= entityVarName %>Mapper;

    @InjectMocks private <%= entityName %>Service <%= entityVarName %>Service;

    <%_ if (persistence === 'mybatis-plus') { _%>
    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(customerService, "baseMapper", customerRepository);
    }
    <%_ } _%>

    @Test
    void find<%= entityName %>ById() {
        // given
        <%_ if (persistence === 'jpa') { _%>
        given(<%= entityVarName %>Repository.findById(1L)).willReturn(Optional.of(get<%= entityName %>()));
        <%_ } _%>
        <%_ if (persistence === 'mybatis-plus') { _%>
        given(<%= entityVarName %>Repository.selectById(1L)).willReturn(getCustomer());
        <%_ } _%>
        given(<%= entityVarName %>Mapper.toResponse(any(<%= entityName %>.class))).willReturn(get<%= entityName %>Response());
        // when
        Optional<<%= entityName %>Response> optional<%= entityName %> = <%= entityVarName %>Service.find<%= entityName %>ById(1L);
        // then
        assertThat(optional<%= entityName %>).isPresent();
        <%= entityName %>Response <%= entityVarName %> = optional<%= entityName %>.get();
        assertThat(<%= entityVarName %>.id()).isEqualTo(1L);
        assertThat(<%= entityVarName %>.text()).isEqualTo("junitTest");
    }

    @Test
    void delete<%= entityName %>ById() {
        <%_ if (persistence === 'jpa') { _%>
        // given
        willDoNothing().given(<%= entityVarName %>Repository).deleteById(1L);
        <%_ } _%>
        // when
        <%= entityVarName %>Service.delete<%= entityName %>ById(1L);
        // then
        verify(<%= entityVarName %>Repository, times(1)).deleteById(1L);
    }

    private <%= entityName %> get<%= entityName %>() {
        <%= entityName %> <%= entityVarName %> = new <%= entityName %>();
        <%= entityVarName %>.setId(1L);
        <%= entityVarName %>.setText("junitTest");
        return <%= entityVarName %>;
    }

    private <%= entityName %>Response get<%= entityName %>Response() {
        return new <%= entityName %>Response(1L, "junitTest");
    }
}
