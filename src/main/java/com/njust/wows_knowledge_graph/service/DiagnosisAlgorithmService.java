package com.njust.wows_knowledge_graph.service;

import com.njust.wows_knowledge_graph.domain.node.DiagnosisAlgorithm;
import com.njust.wows_knowledge_graph.domain.node.Feature;
import com.njust.wows_knowledge_graph.domain.projection.DiagnosisAlgorithmProjection;
import com.njust.wows_knowledge_graph.domain.projection.FeatureProjection;
import com.njust.wows_knowledge_graph.repository.DiagnosisAlgorithmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisAlgorithmService {
    @Autowired
    private DiagnosisAlgorithmRepository diagnosisAlgorithmRepository;



    public List<DiagnosisAlgorithm> getAllDiagnosisAlgorithm(){
        return diagnosisAlgorithmRepository.findAll();
    }

    public DiagnosisAlgorithm getDiagnosisAlgorithmByName(String name){
        return diagnosisAlgorithmRepository.findFirstByName(name);
    }

    public List<DiagnosisAlgorithmProjection> getAllDiagnosisAlgorithmProjection(){
        return diagnosisAlgorithmRepository.findAllBy();
    }

    public DiagnosisAlgorithm addDiagnosisAlgorithm(String name){
        return diagnosisAlgorithmRepository.save(DiagnosisAlgorithm.builder().name(name).build());
    }

//    public void deleteCountry(String name){
//        countryRepository.delete(getCountryByName(name));
//


}
