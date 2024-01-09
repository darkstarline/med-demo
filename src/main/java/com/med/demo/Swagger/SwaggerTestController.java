package com.med.demo.Swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/swaggerTest")
public class SwaggerTestController {

    @Autowired
    private SwaggerTestRepository repository;

    @Operation(summary = "Get  by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SwaggerTest.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public SwaggerTest findById(@Parameter(description = "id of * to be searched")
                         @PathVariable long id) {
        return repository.findById(id).orElseThrow(() -> new SwaggerTestException());
    }

    @GetMapping("/")
    public Collection<SwaggerTest> findBooks() {
        return repository.getBooks();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SwaggerTest updateBook(
            @PathVariable("id") final String id, @RequestBody final SwaggerTest book) {
        return book;
    }
}

