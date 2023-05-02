package com.njust.wows_knowledge_graph.repository;

import com.njust.wows_knowledge_graph.domain.node.Component;
import com.njust.wows_knowledge_graph.domain.node.Country;
import com.njust.wows_knowledge_graph.domain.projection.ComponentProjection;
import com.njust.wows_knowledge_graph.domain.projection.CountryProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends Neo4jRepository<Component, String> {
    /*
    按照name查询部件
     */
    Component findFirstByName(String name);

    List<ComponentProjection> findAllBy();
}
