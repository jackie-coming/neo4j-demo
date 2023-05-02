package com.njust.wows_knowledge_graph.service;

import com.njust.wows_knowledge_graph.domain.node.Component;
import com.njust.wows_knowledge_graph.domain.node.DiagnosisAlgorithm;
import com.njust.wows_knowledge_graph.domain.node.Feature;
import com.njust.wows_knowledge_graph.domain.projection.ComponentProjection;
import com.njust.wows_knowledge_graph.domain.projection.FeatureProjection;
import com.njust.wows_knowledge_graph.domain.relationship.Have;
import com.njust.wows_knowledge_graph.domain.relationship.Input;
import com.njust.wows_knowledge_graph.repository.DiagnosisAlgorithmRepository;
import com.njust.wows_knowledge_graph.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FeatureService {
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private DiagnosisAlgorithmRepository diagnosisAlgorithmRepository;


    public List<Feature> getAllFeature(){
        return featureRepository.findAll();
    }

    public Feature getFeatureByName(String name){
        return featureRepository.findFirstByName(name);
    }

    public List<FeatureProjection> getAllFeatureProjection(){
        return featureRepository.findAllBy();
    }

    public Feature addFeature(String name){
        return featureRepository.save(Feature.builder().name(name).build());
    }

//    public void deleteCountry(String name){
//        countryRepository.delete(getCountryByName(name));
//
    
    public Feature addInputRelationship(String featureName, String diagnosisAlgorithmName, int weigh, String componentName){
        Feature feature = featureRepository.findFirstByName(featureName);
        DiagnosisAlgorithm diagnosisAlgorithm = diagnosisAlgorithmRepository.findFirstByName(diagnosisAlgorithmName);

    for (Input b : feature.getInput()){
        if (b.getDiagnosisAlgorithm().getName().equals(diagnosisAlgorithmName)){
            return feature;
        }
    }
        feature.getInput().add(Input.builder().diagnosisAlgorithm(diagnosisAlgorithm).weigh(weigh).componentName(componentName).build());
    return featureRepository.save(feature);
}
    public Feature addOneInputRelationship(String featureName, String diagnosisAlgorithmName, String componentName){
        Feature feature = featureRepository.findFirstByName(featureName);
        DiagnosisAlgorithm diagnosisAlgorithm = diagnosisAlgorithmRepository.findFirstByName(diagnosisAlgorithmName);

        float w = (float)0;
        Long id = (long)0;
        for (Input b : feature.getInput()){
            if (b.getDiagnosisAlgorithm().getName().equals(diagnosisAlgorithmName)){
                w=b.getWeigh();
                id = b.getId();
            }
        }
        w+=1;
        feature.getInput().add(Input.builder().diagnosisAlgorithm(diagnosisAlgorithm).id(id).weigh(w).componentName(componentName).build());
        return featureRepository.save(feature);
    }

    public List<String> getDiagnosisAlgorithmNamesSortByWeigh(String featurelName){
        Feature feature = featureRepository.findFirstByName(featurelName);

        List<Input> inputs = feature.getInput();
        Collections.sort(inputs);
        List<String> diagnosisAlgorithms = new ArrayList<>();
        for (Input input : inputs){
            diagnosisAlgorithms.add(input.getDiagnosisAlgorithm().getName());
        }
        return diagnosisAlgorithms;
    }

}
