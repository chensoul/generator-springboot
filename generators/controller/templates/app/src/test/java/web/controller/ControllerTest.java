package <%= packageName %>.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import static <%= packageName %>.config.AppConstants.PROFILE_TEST;
import <%= packageName %>.exception.<%= entityName %>NotFoundException;
import <%= packageName %>.model.query.<%= entityName %>Query;
import <%= packageName %>.model.request.<%= entityName %>Request;
import <%= packageName %>.model.response.<%= entityName %>Response;
import <%= packageName %>.service.<%= entityName %>Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = <%= entityName %>Controller.class)
@ActiveProfiles(PROFILE_TEST)
class <%= entityName %>ControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private <%= entityName %>Service <%= entityVarName %>Service;

    @Autowired private ObjectMapper objectMapper;

    private List<<%= entityName %>Response> <%= entityVarName %>ResponseList;

    @BeforeEach
    void setUp() {
        this.<%= entityVarName %>ResponseList = new ArrayList<>();
        this.<%= entityVarName %>ResponseList.add(new <%= entityName %>Response(1L, "text 1"));
        this.<%= entityVarName %>ResponseList.add(new <%= entityName %>Response(2L, "text 2"));
        this.<%= entityVarName %>ResponseList.add(new <%= entityName %>Response(3L, "text 3"));
    }

    @Test
    void shouldFetchAll<%= entityName %>s() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<<%= entityName %>Response> <%= entityVarName %>PagedResult = new PageImpl<>(<%= entityVarName %>ResponseList, pageRequest, customerResponseList.size());
        <%= entityName %>Query <%= entityVarName %>Query = new <%= entityName %>Query(pageRequest);
        given(<%= entityVarName %>Service.findAll<%= entityName %>s(<%= entityVarName %>Query)).willReturn(<%= entityVarName %>PagedResult);

        this.mockMvc
                .perform(get("<%= basePath %>"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(<%= entityVarName %>ResponseList.size())))
                .andExpect(jsonPath("$.totalElements", is(customerResponseList.size())))
                .andExpect(jsonPath("$.number", is(pageRequest.getPageNumber())))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.first", is(true)))
                .andExpect(jsonPath("$.last", is(true)));
    }

    @Test
    void shouldFind<%= entityName %>ById() throws Exception {
        Long <%= entityVarName %>Id = 1L;
        <%= entityName %>Response <%= entityVarName %> = new <%= entityName %>Response(<%= entityVarName %>Id, "text 1");
        given(<%= entityVarName %>Service.find<%= entityName %>ById(<%= entityVarName %>Id)).willReturn(Optional.of(<%= entityVarName %>));

        this.mockMvc
                .perform(get("<%= basePath %>/{id}", <%= entityVarName %>Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(<%= entityVarName %>.text())));
    }

    @Test
    void shouldReturn404WhenFetchingNonExisting<%= entityName %>() throws Exception {
        Long <%= entityVarName %>Id = 1L;
        given(<%= entityVarName %>Service.find<%= entityName %>ById(<%= entityVarName %>Id)).willReturn(Optional.empty());

        this.mockMvc.perform(get("<%= basePath %>/{id}", <%= entityVarName %>Id))
        .andExpect(status().isNotFound())
        .andExpect(header().string("Content-Type", is(MediaType.APPLICATION_PROBLEM_JSON_VALUE)))
        .andExpect(jsonPath("$.type", is("http://api.<%= appName %>.com/errors/not-found")))
        .andExpect(jsonPath("$.title", is("Not Found")))
        .andExpect(jsonPath("$.status", is(404)))
        .andExpect(
                jsonPath("$.detail")
                        .value("<%= entityName %> with Id '%d' not found".formatted(<%= entityVarName %>Id)));
    }

    @Test
    void shouldCreateNew<%= entityName %>() throws Exception {

        <%= entityName %>Response <%= entityVarName %> = new <%= entityName %>Response(1L, "some text");
        <%= entityName %>Request <%= entityVarName %>Request = new <%= entityName %>Request("some text");
        given(<%= entityVarName %>Service.save<%= entityName %>(any(<%= entityName %>Request.class)))
                .willReturn(<%= entityVarName %>);

        this.mockMvc
                .perform(
                        post("<%= basePath %>")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(<%= entityVarName %>Request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.text", is(<%= entityVarName %>.text())));
    }

    @Test
    void shouldReturn400WhenCreateNew<%= entityName %>WithoutText() throws Exception {
        <%= entityName %>Request <%= entityVarName %>Request = new <%= entityName %>Request(null);

        this.mockMvc
                .perform(
                        post("<%= basePath %>")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(<%= entityVarName %>Request)))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.type", is("about:blank")))
                .andExpect(jsonPath("$.title", is("Constraint Violation")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.detail", is("Invalid request content.")))
                .andExpect(jsonPath("$.instance", is("<%= basePath %>")))
                .andExpect(jsonPath("$.violations", hasSize(1)))
                .andExpect(jsonPath("$.violations[0].field", is("text")))
                .andExpect(jsonPath("$.violations[0].message", is("Text cannot be empty")))
                .andReturn();
    }

    @Test
    void shouldUpdate<%= entityName %>() throws Exception {
        Long <%= entityVarName %>Id = 1L;
        <%= entityName %>Response <%= entityVarName %> = new <%= entityName %>Response(<%= entityVarName %>Id, "Updated text");
        <%= entityName %>Request <%= entityVarName %>Request = new <%= entityName %>Request("Updated text");
        given(<%= entityVarName %>Service.update<%= entityName %>(eq(<%= entityVarName %>Id), any(<%= entityName %>Request.class)))
                .willReturn(<%= entityVarName %>);

        this.mockMvc
                .perform(
                        put("<%= basePath %>/{id}", <%= entityVarName %>Id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(<%= entityVarName %>Request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(<%= entityVarName %>Id), Long.class))
                .andExpect(jsonPath("$.text", is(<%= entityVarName %>.text())));
    }

    @Test
    void shouldReturn404WhenUpdatingNonExisting<%= entityName %>() throws Exception {
        Long <%= entityVarName %>Id = 1L;
        <%= entityName %>Request <%= entityVarName %>Request = new <%= entityName %>Request("Updated text");
        given(<%= entityVarName %>Service.update<%= entityName %>(eq(<%= entityVarName %>Id), any(<%= entityName %>Request.class)))
                .willThrow(new <%= entityName %>NotFoundException(<%= entityVarName %>Id));

        this.mockMvc
                .perform(
                        put("<%= basePath %>/{id}", <%= entityVarName %>Id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(<%= entityVarName %>Request)))
                                .andExpect(status().isNotFound())
                                .andExpect(header().string("Content-Type", is(MediaType.APPLICATION_PROBLEM_JSON_VALUE)))
                                .andExpect(jsonPath("$.type", is("http://api.<%= appName %>.com/errors/not-found")))
                                .andExpect(jsonPath("$.title", is("Not Found")))
                                .andExpect(jsonPath("$.status", is(404)))
                                .andExpect(
                                        jsonPath("$.detail")
                                                .value("<%= entityName %> with Id '%d' not found".formatted(<%= entityVarName %>Id)));
    }

    @Test
    void shouldDelete<%= entityName %>() throws Exception {
        Long <%= entityVarName %>Id = 1L;
        <%= entityName %>Response <%= entityVarName %> = new <%= entityName %>Response(<%= entityVarName %>Id, "Some text");
        given(<%= entityVarName %>Service.find<%= entityName %>ById(<%= entityVarName %>Id)).willReturn(Optional.of(<%= entityVarName %>));
        doNothing().when(<%= entityVarName %>Service).delete<%= entityName %>ById(<%= entityVarName %>Id);

        this.mockMvc
                .perform(delete("<%= basePath %>/{id}", <%= entityVarName %>Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is(<%= entityVarName %>.text())));
    }

    @Test
    void shouldReturn404WhenDeletingNonExisting<%= entityName %>() throws Exception {
        Long <%= entityVarName %>Id = 1L;
        given(<%= entityVarName %>Service.find<%= entityName %>ById(<%= entityVarName %>Id)).willReturn(Optional.empty());

        this.mockMvc
                .perform(delete("<%= basePath %>/{id}", <%= entityVarName %>Id))
                .andExpect(header().string("Content-Type", is(MediaType.APPLICATION_PROBLEM_JSON_VALUE)))
        .andExpect(jsonPath("$.type", is("http://api.<%= appName %>.com/errors/not-found")))
        .andExpect(jsonPath("$.title", is("Not Found")))
        .andExpect(jsonPath("$.status", is(404)))
        .andExpect(
                jsonPath("$.detail")
                        .value("<%= entityName %> with Id '%d' not found".formatted(<%= entityVarName %>Id)));
    }

    List<<%= entityName %>Response> get<%= entityName %>ResponseList() {
        return <%= entityVarName %>ResponseList.stream()
        .map(<%= entityVarName %> -> new <%= entityName %>Response(<%= entityVarName %>.id(), <%= entityVarName %>.text()))
        .toList();
    }
}
