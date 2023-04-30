package com.njust.wows_knowledge_graph.controller;

import com.njust.wows_knowledge_graph.domain.node.Country;
import com.njust.wows_knowledge_graph.domain.node.Model;
import com.njust.wows_knowledge_graph.domain.projection.CountryProjection;
import com.njust.wows_knowledge_graph.domain.projection.ModelProjection;
import com.njust.wows_knowledge_graph.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService modelService;


    @GetMapping("/get_all_model")
    public List<Model> getAllModel(){
        return modelService.getAllModel();
    }

    @GetMapping("/get_model")
    public Model getModel(@RequestParam("name")String name){
        return modelService.getModelByName(name);
    }

    @GetMapping("/get_all_model_projection")
    public List<ModelProjection> getAllModelProjection(){
        return modelService.getAllModelProjection();
    }

    @PostMapping("/add_model")
    public Model addModel(@RequestParam("name")String name){
        return modelService.addModel(name);
    }

    @DeleteMapping("/delete_country")
//    public void deleteCountry(@RequestParam("name")String name){
//        countryService.deleteCountry(name);
//    }

    @PostMapping("/add_consist_of_relationship")
    public Model addConsistOfRelationship(
            @RequestParam("modelName")String modelName,
            @RequestParam("componentName")String componentName,
            @RequestParam("weigh")int weigh){
        return modelService.addConsistOfRelationship(modelName, componentName, weigh,0);
    }

    @PostMapping("/add_one_consist_of_relationship")
    public Model addOneConsistOfRelationship(
            @RequestParam("modelName")String modelName,
            @RequestParam("componentName")String componentName)
           {
        return modelService.addOneConsistOfRelationship(modelName, componentName);
    }

    @GetMapping("/get_component_names_sort_by_weigh")
    public List<String> getComponentNamesSortByWeigh(@RequestParam("name")String name){
        return modelService.getComponentNamesSortByWeigh(name);
    }
}
