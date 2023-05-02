package com.njust.wows_knowledge_graph.repository;

import com.njust.wows_knowledge_graph.domain.node.Country;
import com.njust.wows_knowledge_graph.domain.node.DiagnosisAlgorithm;
import com.njust.wows_knowledge_graph.domain.projection.CountryProjection;
import com.njust.wows_knowledge_graph.domain.projection.DiagnosisAlgorithmProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisAlgorithmRepository extends Neo4jRepository<DiagnosisAlgorithm, String> {
    /*
    按照name查询国家
     */
    DiagnosisAlgorithm findFirstByName(String name);

    List<DiagnosisAlgorithmProjection> findAllBy();
}
