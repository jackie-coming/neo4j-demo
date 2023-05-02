package com.njust.wows_knowledge_graph.repository;

import com.njust.wows_knowledge_graph.domain.node.Component;
import com.njust.wows_knowledge_graph.domain.node.Feature;
import com.njust.wows_knowledge_graph.domain.projection.ComponentProjection;
import com.njust.wows_knowledge_graph.domain.projection.FeatureProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends Neo4jRepository<Feature, String> {
    /*
    按照name查询部件
     */
    Feature findFirstByName(String name);

    List<FeatureProjection> findAllBy();
}
