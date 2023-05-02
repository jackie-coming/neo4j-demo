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

import java.util.*;

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

        float w =0;
        float faultRate =0;
        Long id = (long)0;
        for (ConsistOf b : model.getConsistOf()){

            if (b.getComponent().getName().equals(componentName)){
               w = b.getWeigh();
               id = b.getId();
               faultRate= b.getFaultRate();
            }
        }
        w+=1;
        model.getConsistOf().add(ConsistOf.builder().id(id).faultRate(faultRate).component(component).weigh(w).build());
        return modelRepository.save(model);
    }
    public Model updateFaultRateConsistOfRelationship(String modelName, String componentName,float faultRate){
        Model model = modelRepository.findFirstByName(modelName);
        Component component = componentRepository.findFirstByName(componentName);

        float w =0;
        Long id = (long)0;
        for (ConsistOf b : model.getConsistOf()){

            if (b.getComponent().getName().equals(componentName)){
                w = b.getWeigh();
                id = b.getId();
            }
        }
        model.getConsistOf().add(ConsistOf.builder().id(id).faultRate(faultRate).component(component).weigh(w).build());
        return modelRepository.save(model);
    }


    public List<String> getComponentNamesSortByWeigh(String modelName){
        Model model = modelRepository.findFirstByName(modelName);
        List<ConsistOf> consistOfs = model.getConsistOf();

        float[] faultRates = new float[consistOfs.size()+1];
        int i = 0;
        for (ConsistOf consistOf : consistOfs){
            faultRates[i]=consistOf.getFaultRate();
            i++;
        }
        HashMap<Float, Float>faultRatesMp = ConsistOf.normalize4Scale(faultRates);

        float[] weighs = new float[consistOfs.size()+1];
        int j = 0;
        for (ConsistOf consistOf : consistOfs){
            weighs[j]=consistOf.getWeigh();
            j++;
        }
        HashMap<Float, Float> weighsMp = ConsistOf.normalize4Scale(weighs);

        for (ConsistOf consistOf : consistOfs){
           float w = weighsMp.get(consistOf.getWeigh());
           w+=faultRatesMp.get(consistOf.getFaultRate());

           consistOf.setWeigh(w);
        }

        Collections.sort(consistOfs);
        List<String> componentNames = new ArrayList<>();
        for (ConsistOf consistOf : consistOfs){
            componentNames.add(consistOf.getComponent().getName());
        }
        return componentNames;
    }


}
