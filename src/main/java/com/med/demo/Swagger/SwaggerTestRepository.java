package com.med.demo.Swagger;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class SwaggerTestRepository {
    public Optional<SwaggerTest> findById(long id) {
        Optional<SwaggerTest> swaggerTest = Optional.empty();
        return swaggerTest;
    }

    public Collection<SwaggerTest> getBooks() {
        List<SwaggerTest> books = new ArrayList();
        return books;
    }
}
