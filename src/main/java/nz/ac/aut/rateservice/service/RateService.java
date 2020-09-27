package nz.ac.aut.rateservice.service;

import nz.ac.aut.rateservice.model.Rate;

import java.util.List;

public interface RateService {

    List<Rate> findAll();

    Rate findByJobID(String jobID);

    Rate saveOrUpdate(Rate rate);

    void deleteById(String id);

    Rate save(Rate rate);

    List<Rate> findAllByOrderByJobID();
}
