package com.njust.wows_knowledge_graph.domain.node;

import com.njust.wows_knowledge_graph.domain.relationship.BuildIn;
import com.njust.wows_knowledge_graph.domain.relationship.ConsistOf;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node(primaryLabel = "Model")
@Data
@Builder
public class Model {
    @Id
    @Property(name = "name")
    private String name;

    @Relationship(type = "consistOf", direction = Relationship.Direction.OUTGOING)
    private List<ConsistOf> consistOf;
}
