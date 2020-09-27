package nz.ac.aut.rateservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.ac.aut.rateservice.model.Rate;
import nz.ac.aut.rateservice.repository.RateRepository;
import nz.ac.aut.rateservice.service.RateService;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public List<Rate> findAll() {
        return rateRepository.findAll();
    }

    @Override
    public Rate findByJobID(String jobID) {
        return rateRepository.findByJobID(jobID);
    }

    @Override
    public List<Rate> findAllByOrderByJobID() {
        return rateRepository.findAllByOrderByJobID();
    }

    @Override
    public Rate saveOrUpdate(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public void deleteById(String id) {
        rateRepository.deleteById(id);
    }

    @Override
    public Rate save(Rate rate) {
        return rateRepository.save(rate);
    }
}
