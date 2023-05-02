package com.njust.wows_knowledge_graph.domain.node;

import com.njust.wows_knowledge_graph.domain.relationship.BuildIn;
import com.njust.wows_knowledge_graph.domain.relationship.Input;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node(primaryLabel = "Feature")
@Data
@Builder
public class Feature {
    @Id
    @Property(name = "name")
    private String name;

    @Relationship(type = "input", direction = Relationship.Direction.OUTGOING)
    private List<Input> input;
}
