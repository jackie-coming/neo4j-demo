package com.njust.wows_knowledge_graph;

import com.njust.wows_knowledge_graph.domain.node.Model;
import com.njust.wows_knowledge_graph.repository.ModelRepository;
import com.njust.wows_knowledge_graph.service.ComponentService;
import com.njust.wows_knowledge_graph.service.DiagnosisAlgorithmService;
import com.njust.wows_knowledge_graph.service.FeatureService;
import com.njust.wows_knowledge_graph.service.ModelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SpringBootTest
class WowsKnowledgeGraphNeo4jApplicationTests {
    @Autowired
    ModelService modelService;
    @Autowired
    ComponentService componentService;
    @Autowired
    FeatureService featureService;
    @Autowired
    DiagnosisAlgorithmService diagnosisAlgorithmService;
    @Autowired
    ModelRepository modelRepository;

    @Test
    void contextLoads() throws IOException {
        Example<Model> example = Example.of(Model.builder().name("风力发电机").build());
        System.out.println(modelRepository.exists(example));
        File file = new File("./initGraphNeo4j.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        int flag = 0;
        while ((line=bufferedReader.readLine()) != null){
            if (line.equals("model")){
                flag = 0;
                continue;
            }
            else if (line.equals("component")){
                flag = 1;
                continue;
            }
            else if (line.equals("feature")){
                flag = 2;
                continue;
            }
            else if (line.equals("consistOf")){
                flag = 3;
                continue;
            }
            else if (line.equals("have")){
                flag = 4;
                continue;
            }
            else if (line.equals("diagnosisAlgorithm")){
                flag = 5;
                continue;
            }
            else if (line.equals("input")){
                flag = 6;
                continue;
            }
            else {
                switch (flag){
                    case 0:{
                        modelService.addModel(line);
                        break;
                    }
                    case 1:{
                      componentService.addComponent(line);
                        break;
                    }
                    case 2:{
                        featureService.addFeature(line);
                        break;
                    }
                    case 3:{
                        String[] s = line.split("-");
                        String[] subs= s[1].split("i");
                        modelService.addConsistOfRelationship(s[0], s[2], Integer.parseInt(subs[0]),Float.parseFloat(subs[1]));
                        break;
                    }
                    case 4:{
                        String[] s = line.split("-");
                        componentService.addHaveRelationship(s[0], s[2], Integer.parseInt(s[1]));
                        break;
                    }
                    case 5:{
                        diagnosisAlgorithmService.addDiagnosisAlgorithm(line);
                        break;
                    }
                    case 6:{
                        String[] s = line.split("-");

                        String[] subs= s[1].split("i");

                        featureService.addInputRelationship(s[0], s[2], Integer.parseInt(subs[0]),subs[1]);
                        break;
                    }
                }
            }
        }
    }

}
