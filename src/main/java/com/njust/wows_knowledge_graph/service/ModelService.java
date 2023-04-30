package com.njust.wows_knowledge_graph.service;

import com.njust.wows_knowledge_graph.domain.node.Component;
import com.njust.wows_knowledge_graph.domain.node.Country;
import com.njust.wows_knowledge_graph.domain.node.Model;
import com.njust.wows_knowledge_graph.domain.node.Ship;
import com.njust.wows_knowledge_graph.domain.projection.CountryProjection;
import com.njust.wows_knowledge_graph.domain.projection.ModelProjection;
import com.njust.wows_knowledge_graph.domain.relationship.BuildIn;
import com.njust.wows_knowledge_graph.domain.relationship.ConsistOf;
import com.njust.wows_knowledge_graph.repository.ComponentRepository;
import com.njust.wows_knowledge_graph.repository.CountryRepository;
import com.njust.wows_knowledge_graph.repository.ModelRepository;
import com.njust.wows_knowledge_graph.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ComponentRepository componentRepository;

    public List<Model> getAllModel(){
        return modelRepository.findAll();
    }

    public Model getModelByName(String name){
        return modelRepository.findFirstByName(name);
    }

    public List<ModelProjection> getAllModelProjection(){
        return modelRepository.findAllBy();
    }

    public Model addModel(String name){
        return modelRepository.save(Model.builder().name(name).build());
    }

//    public void deleteCountry(String name){
//        countryRepository.delete(getCountryByName(name));
//

    public Model addConsistOfRelationship( String modelName, String componentName,int weigh,float faultRate){
        Model model = modelRepository.findFirstByName(modelName);
        Component component = componentRepository.findFirstByName(componentName);
        long id = (long) 0;
        for (ConsistOf b : model.getConsistOf()){
            if (b.getComponent().getName().equals(componentName)){
                id = b.getId();
            }
        }
        if (id == (long)0){
            model.getConsistOf().add(ConsistOf.builder().component(component).weigh(weigh).faultRate(faultRate).build());
        }else{
            model.getConsistOf().add(ConsistOf.builder().id(id).component(component).faultRate(faultRate).weigh(weigh).build());
        }
        return modelRepository.save(model);
    }

    public Model addOneConsistOfRelationship(String modelName, String componentName){
        Model model = modelRepository.findFirstByName(modelName);
        Component component = componentRepository.findFirstByName(componentName);

        int w =0;
        Long id = (long)0;
        for (ConsistOf b : model.getConsistOf()){

            if (b.getComponent().getName().equals(componentName)){
               w = b.getWeigh();
               id = b.getId();
            }
        }
        w+=1;
        model.getConsistOf().add(ConsistOf.builder().id(id).component(component).weigh(w).build());
        return modelRepository.save(model);
    }
    public List<String> getComponentNamesSortByWeigh(String modelName){
        Model model = modelRepository.findFirstByName(modelName);

        List<ConsistOf> consistOfs = model.getConsistOf();
        Collections.sort(consistOfs);
        List<String> componentNames = new ArrayList<>();
        for (ConsistOf consistOf : consistOfs){
            componentNames.add(consistOf.getComponent().getName());
        }
        return componentNames;
    }

}
