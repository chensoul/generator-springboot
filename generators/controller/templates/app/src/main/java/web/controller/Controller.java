package <%= packageName %>.web.controller;

import <%= packageName %>.exception.<%= entityName %>NotFoundException;
import <%= packageName %>.model.query.<%= entityName %>Query;
import <%= packageName %>.model.request.<%= entityName %>Request;
import <%= packageName %>.model.response.<%= entityName %>Response;
import <%= packageName %>.service.<%= entityName %>Service;
import java.util.List;
import java.net.URI;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("<%= basePath %>")
@Slf4j
@RequiredArgsConstructor
class <%= entityName %>Controller {

    private final <%= entityName %>Service <%= entityVarName %>Service;

    @GetMapping
    Page<<%= entityName %>Response> getAll<%= entityName %>s(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        <%= entityName %>Query <%= entityVarName %>Query = new <%= entityName %>Query(pageable);
        return <%= entityVarName %>Service.findAll<%= entityName %>s(<%= entityVarName %>Query);
    }

    @GetMapping("/{id}")
    ResponseEntity<<%= entityName %>Response> get<%= entityName %>ById(@PathVariable Long id) {
        return <%= entityVarName %>Service
                .find<%= entityName %>ById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new <%= entityName %>NotFoundException(id));
    }

    @PostMapping
    ResponseEntity<<%= entityName %>Response> create<%= entityName %>(@RequestBody @Validated <%= entityName %>Request <%= entityVarName %>Request) {
        <%= entityName %>Response response = <%= entityVarName %>Service.save<%= entityName %>(<%= entityVarName %>Request);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("<%= basePath %>/{id}")
                        .buildAndExpand(response.id())
                        .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<<%= entityName %>Response> update<%= entityName %>(
            @PathVariable Long id, @RequestBody @Valid <%= entityName %>Request <%= entityVarName %>Request) {
        return ResponseEntity.ok(<%= entityVarName %>Service.update<%= entityName %>(id, <%= entityVarName %>Request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<<%= entityName %>Response> delete<%= entityName %>(@PathVariable Long id) {
        return <%= entityVarName %>Service
                .find<%= entityName %>ById(id)
                .map(
                        <%= entityVarName %> -> {
                            <%= entityVarName %>Service.delete<%= entityName %>ById(id);
                            return ResponseEntity.ok(<%= entityVarName %>);
                        })
                .orElseThrow(() -> new <%= entityName %>NotFoundException(id));
    }
}
