 package com.njust.wows_knowledge_graph.domain.relationship;

import com.njust.wows_knowledge_graph.domain.node.DiagnosisAlgorithm;
import com.njust.wows_knowledge_graph.domain.node.Feature;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.Objects;

 @RelationshipProperties
@Data
@Builder
public class Input implements Comparable<Input>{
    @Id
    @GeneratedValue
    Long id;

    @TargetNode
    private DiagnosisAlgorithm diagnosisAlgorithm;

    private int weigh;
    private String componentName;

     @Override
     public int compareTo(Input o) {
         if (this.weigh>o.weigh) return -1 ;
         else if (this.weigh<o.weigh) return 1 ;
         else return 0 ;
     }
     // 判断两个对象是否相等
     // 覆写Object中equals()方法
     @Override
     public boolean equals(Object o) {
         if (o instanceof Input){
             Input p = (Input) o ;
             return Objects.equals(this.weigh, p.weigh);
         }
         return false ;
     }
     // 覆写Object中hashCode()方法
     @Override
     public int hashCode() {
         return Objects.hash(id, weigh);
     }
}
