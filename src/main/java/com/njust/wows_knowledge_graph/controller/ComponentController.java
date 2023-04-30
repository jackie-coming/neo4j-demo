package com.njust.wows_knowledge_graph.controller;

import com.njust.wows_knowledge_graph.domain.node.Component;
import com.njust.wows_knowledge_graph.domain.projection.ComponentProjection;
import com.njust.wows_knowledge_graph.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/component")
public class ComponentController {
    @Autowired
    private ComponentService componentService;


    @GetMapping("/get_all_component")
    public List<Component> getAllcomponent(){
        return componentService.getAllComponent();
    }

    @GetMapping("/get_component")
    public Component getcomponent(@RequestParam("name")String name){
        return componentService.getComponentByName(name);
    }

    @GetMapping("/get_all_component_projection")
    public List<ComponentProjection> getAllcomponentProjection(){
        return componentService.getAllComponentProjection();
    }

    @PostMapping("/add_component")
    public Component addcomponent(@RequestParam("name")String name){
        return componentService.addComponent(name);
    }

    @DeleteMapping("/delete_country")
//    public void deleteCountry(@RequestParam("name")String name){
//        countryService.deleteCountry(name);
//    }

    @PostMapping("/add_have_relationship")
    public Component addConsistOfRelationship(
            @RequestParam("componentName")String componentName,
            @RequestParam("featureName")String featureName,
            @RequestParam("weigh")int weigh){
        return componentService.addHaveRelationship(componentName, featureName, weigh);
    }

    @PostMapping("/add_One_have_relationship")
    public Component addOneConsistOfRelationship(
            @RequestParam("componentName")String componentName,
            @RequestParam("featureName")String featureName)
           {
        return componentService.addOneHaveRelationship(componentName, featureName);
    }

    @GetMapping("/get_feature_names_sort_by_weigh")
    public List<String> getFeatureNamesSortByWeigh(@RequestParam("name")String name){
        return componentService.getFeatureNamesSortByWeigh(name);
    }
}
