package com.njust.wows_knowledge_graph.service;

import com.njust.wows_knowledge_graph.domain.node.*;
import com.njust.wows_knowledge_graph.domain.projection.ComponentProjection;
import com.njust.wows_knowledge_graph.domain.projection.CountryProjection;
import com.njust.wows_knowledge_graph.domain.relationship.BuildIn;
import com.njust.wows_knowledge_graph.domain.relationship.ConsistOf;
import com.njust.wows_knowledge_graph.domain.relationship.Have;
import com.njust.wows_knowledge_graph.repository.ComponentRepository;
import com.njust.wows_knowledge_graph.repository.FeatureRepository;
import com.njust.wows_knowledge_graph.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ComponentService {
    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private FeatureRepository featureRepository;

    public List<Component> getAllComponent(){
        return componentRepository.findAll();
    }

    public Component getComponentByName(String name){
        return componentRepository.findFirstByName(name);
    }

    public List<ComponentProjection> getAllComponentProjection(){
        return componentRepository.findAllBy();
    }

    public Component addComponent(String name){
        return componentRepository.save(Component.builder().name(name).build());
    }


//    public void deleteCountry(String name){
//        countryRepository.delete(getCountryByName(name));
//

    public Component addHaveRelationship(String componentlName, String featureName, int weigh){
        Component component = componentRepository.findFirstByName(componentlName);
        Feature feature = featureRepository.findFirstByName(featureName);

        for (Have b : component.getHave()){
            if (b.getFeature().getName().equals(featureName)){
                return component;
            }
        }
        component.getHave().add(Have.builder().feature(feature).weigh(weigh).build());
        return componentRepository.save(component);
    }

    public Component addOneHaveRelationship(String componentlName, String featureName){
        Component component = componentRepository.findFirstByName(componentlName);
        Feature feature = featureRepository.findFirstByName(featureName);
        float w =0;
        Long id = (long)0;
        for (Have b : component.getHave()){
            if (b.getFeature().getName().equals(featureName)){
                w = b.getWeigh();
                id=b.getId();
            }
        }
        w+=1;
        component.getHave().add(Have.builder().id(id).feature(feature).weigh(w).build());
        return componentRepository.save(component);
    }

    public List<String> getFeatureNamesSortByWeigh(String componentlName){
        Component component = componentRepository.findFirstByName(componentlName);

        List<Have> haves = component.getHave();
        Collections.sort(haves);
        List<String> featureNames = new ArrayList<>();
        for (Have have : haves){
            featureNames.add(have.getFeature().getName());
        }
        return featureNames;
    }
}
