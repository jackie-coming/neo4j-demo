package com.njust.wows_knowledge_graph.domain.relationship;

import com.njust.wows_knowledge_graph.domain.node.Component;
import com.njust.wows_knowledge_graph.domain.node.Ship;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

    private float weigh;

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

    /**
     * 线性归一化 公式：X(norm) = (X - min) / (max - min)
     *
     * @param points 原始数据
     * @return 归一化后的数据
     */
    public static HashMap<Float, Float> normalize4Scale(float[] points) {
        if (points == null || points.length < 1) {
            return null;
        }
        HashMap<Float, Float> map = new HashMap();
        float maxV;
        float minV;
            maxV = maxV(points);
            minV = minV(points);
            for (int i = 0; i < points.length; i++) {
                float res = maxV == minV ? minV : (points[i] - minV) / (maxV - minV);
                map.put(points[i],res);
            }
        return map;
    }

    //解释以下代码的意思
    //1.首先定义一个float类型的数组matrixJ，长度为3
    //2.然后定义一个float类型的变量v，初始值为matrixJ[0]
    //3.然后遍历matrixJ数组，如果matrixJ[i]大于v，则v等于matrixJ[i]
    //4.最后返回v

    public static float maxV(float[] matrixJ) {
        float v = matrixJ[0];
        for (int i = 0; i < matrixJ.length; i++) {
            if (matrixJ[i] > v) {
                v = matrixJ[i];
            }
        }
        return v;
    }
    public static float minV(float[] matrixJ) {
        float v = matrixJ[0];
        for (int i = 0; i < matrixJ.length; i++) {
            if (matrixJ[i] < v) {
                v = matrixJ[i];
            }
        }
        return v;
    }
}
