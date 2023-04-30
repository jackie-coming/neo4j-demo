package com.njust.wows_knowledge_graph.domain.relationship;

import com.njust.wows_knowledge_graph.domain.node.Component;
import com.njust.wows_knowledge_graph.domain.node.Ship;
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
public class ConsistOf implements Comparable<ConsistOf> {
    @Id
    @GeneratedValue
    Long id;

    @TargetNode
    private Component component;

    private int weigh;

    private float faultRate;

    @Override
    public int compareTo(ConsistOf o) {
        if (this.weigh>o.weigh) return -1 ;
        else if (this.weigh<o.weigh) return 1 ;
        else return 0 ;
    }
    // 判断两个对象是否相等
    // 覆写Object中equals()方法
    @Override
    public boolean equals(Object o) {
        if (o instanceof ConsistOf){
            ConsistOf p = (ConsistOf) o ;
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
