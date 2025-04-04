package com.muzam.poc.task_management.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class JsonSchemaValidator {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static JsonSchema schema;

    static {
        try {
            JsonNode schemaNode = JsonLoader.fromFile(new ClassPathResource("schema.json").getFile());
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            schema = factory.getJsonSchema(schemaNode);
        } catch (IOException | ProcessingException e) {
            throw new RuntimeException("Failed to load JSON schema", e);
        }
    }

    public static void validate(String json) throws ProcessingException, IOException {
        JsonNode jsonNode = OBJECT_MAPPER.readTree(json);
        ProcessingReport report = schema.validate(jsonNode);
        if (!report.isSuccess()) {
            throw new ProcessingException("Invalid JSON: " + report);
        }
    }
}


