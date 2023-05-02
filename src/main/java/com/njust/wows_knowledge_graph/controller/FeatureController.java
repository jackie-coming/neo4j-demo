package com.njust.wows_knowledge_graph.controller;

import com.njust.wows_knowledge_graph.domain.node.Feature;
import com.njust.wows_knowledge_graph.domain.projection.FeatureProjection;
import com.njust.wows_knowledge_graph.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feature")
public class FeatureController {
    @Autowired
    private FeatureService featureService;


    @GetMapping("/get_all_feature")
    public List<Feature> getAllFeature(){
        return featureService.getAllFeature();
    }

    @GetMapping("/get_feature")
    public Feature getFeature(@RequestParam("name")String name){
        return featureService.getFeatureByName(name);
    }

    @GetMapping("/get_all_feature_projection")
    public List<FeatureProjection> getAllFeatureProjection(){
        return featureService.getAllFeatureProjection();
    }

    @PostMapping("/add_feature")
    public Feature addFeature(@RequestParam("name")String name){
        return featureService.addFeature(name);
    }

    @DeleteMapping("/delete_country")
//    public void deleteCountry(@RequestParam("name")String name){
//        countryService.deleteCountry(name);
//    }

    @PostMapping("/add_input_relationship")
    public Feature addConsistOfRelationship(
            @RequestParam("featureName")String featureName,
            @RequestParam("diagnosisAlgorithmName")String diagnosisAlgorithmName,
            @RequestParam("componentName")String componentName,
            @RequestParam("weigh")int weigh){
        return featureService.addInputRelationship(featureName, diagnosisAlgorithmName, weigh,componentName);
    }

    @PostMapping("/add_one_input_relationship")
    public Feature addOneConsistOfRelationship(
            @RequestParam("featureName")String featureName,
            @RequestParam("diagnosisAlgorithmName")String diagnosisAlgorithmName,
    @RequestParam("componentName")String componentName)

    {
        return featureService.addOneInputRelationship(featureName, diagnosisAlgorithmName,componentName);
    }

    @GetMapping("/get_diagnosis_algorithm_names_sort_by_weigh")
    public List<String> getDiagnosisAlgorithmNamesSortByWeigh(@RequestParam("name")String name){
        return featureService.getDiagnosisAlgorithmNamesSortByWeigh(name);
    }
}
