package com.devstack.POS.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.persistence.metamodel.PluralAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

@Component
public class ErDiagramGenerator implements ApplicationRunner {

    private final EntityManager entityManager;

    public ErDiagramGenerator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void run(@NonNull ApplicationArguments args) {
        generateMermaidDiagram();
    }

    private void generateMermaidDiagram() {
        Metamodel metamodel = entityManager.getMetamodel();
        Set<EntityType<?>> entities = metamodel.getEntities();

        StringBuilder mermaid = new StringBuilder();
        mermaid.append("```mermaid\n");
        mermaid.append("erDiagram\n");

        // Define entities and their attributes
        for (EntityType<?> entity : entities) {
            String entityName = entity.getName();
            mermaid.append("    ").append(entityName).append(" {\n");

            for (Attribute<?, ?> attribute : entity.getAttributes()) {
                if (!attribute.isAssociation()) {
                    String type = attribute.getJavaType().getSimpleName();
                    String name = attribute.getName();
                    mermaid.append("        ").append(type).append(" ").append(name).append("\n");
                }
            }
            mermaid.append("    }\n\n");
        }

        // Define relationships
        for (EntityType<?> entity : entities) {
            for (Attribute<?, ?> attribute : entity.getAttributes()) {
                if (attribute.isAssociation()) {
                    String targetEntityName;
                    String relationshipType;

                    if (attribute.isCollection()) {
                        PluralAttribute<?, ?, ?> pluralAttr = (PluralAttribute<?, ?, ?>) attribute;
                        targetEntityName = pluralAttr.getElementType().getJavaType().getSimpleName();
                        relationshipType = "||--o{"; // one-to-many
                    } else {
                        SingularAttribute<?, ?> singularAttr = (SingularAttribute<?, ?>) attribute;
                        targetEntityName = singularAttr.getJavaType().getSimpleName();
                        relationshipType = "||--||"; // one-to-one
                    }

                    mermaid.append("    ").append(entity.getName())
                            .append(" ").append(relationshipType).append(" ")
                            .append(targetEntityName)
                            .append(" : \"").append(attribute.getName()).append("\"\n");
                }
            }
            mermaid.append("\n");
        }

        mermaid.append("```\n");

        try (FileWriter writer = new FileWriter("er-diagram.md")) {
            writer.write(mermaid.toString());
            System.out.println("ER Diagram generated successfully at er-diagram.md");
        } catch (IOException e) {
            System.err.println("Failed to generate ER Diagram: " + e.getMessage());
        }
    }
}
