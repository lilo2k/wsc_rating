package nz.ac.aut.rateservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nz.ac.aut.rateservice.dto.RateDTO;
import nz.ac.aut.rateservice.exception.RateAlreadyExistsException;
import nz.ac.aut.rateservice.exception.RateNotFoundException;
import nz.ac.aut.rateservice.model.Rate;
import nz.ac.aut.rateservice.service.RateService;
import nz.ac.aut.rateservice.util.ObjectMapperUtils;

@RestController
// @RequestMapping("/")
public class RateRestController {

    /**
     *
     */
    private static final String NO_RECORD_FOUND_JOB_ID = "No record found";

    @Autowired
    private RateService rateService;

    @GetMapping(value = "/")
    public List<RateDTO> getAllRates() {
        List<Rate> rates = rateService.findAll();

        if (rates.size() == 0)
            throw new RateNotFoundException("No record found");

        return ObjectMapperUtils.mapAll(rates, RateDTO.class);
    }

    @GetMapping(value = "/orderByJobID")
    public List<RateDTO> findAllByOrderByJobID() {
        List<Rate> rates = rateService.findAllByOrderByJobID();

        if (rates == null)
            throw new RateNotFoundException("No record found");

        return ObjectMapperUtils.mapAll(rates, RateDTO.class);
    }

    @GetMapping(value = "/{jobID}")
    public RateDTO getRateByJobID(@PathVariable("jobID") String jobID) {
        Rate rate = rateService.findByJobID(jobID);

        if (rate == null)
            handleRatedNotFoundException(jobID);
            
        return ObjectMapperUtils.map(rate, RateDTO.class);
    }

    private void handleRatedNotFoundException(String jobID) {
        String msg = (jobID == null ) ? NO_RECORD_FOUND_JOB_ID: NO_RECORD_FOUND_JOB_ID + " - Job ID: " + jobID;
        throw new RateNotFoundException(msg);
    }
    
    @PostMapping(value = "/")
    public ResponseEntity<?> saveOrUpdate(@RequestBody RateDTO rateDTO) {
        Rate rate = rateService.findByJobID(rateDTO.getJobID());

        if (rate == null) {
            rateService.saveOrUpdate(ObjectMapperUtils.map(rateDTO, Rate.class));
        }
        else {
            handleRateAlreadyExistsException(rateDTO.getJobID());
            // rate.setRate(rateDTO.getRate());
            // rateService.saveOrUpdate(ObjectMapperUtils.map(rate, Rate.class));
        }
        return new ResponseEntity("Rate added successfully - Job ID: " + rateDTO.getJobID(), HttpStatus.CREATED);
    }
    
    private void handleRateAlreadyExistsException(String jobID) {
        throw new RateAlreadyExistsException("Job ID: " + jobID + " already exists.");
    }
    
    @PostMapping(value = "/{jobID}")
    public ResponseEntity<?> saveOrUpdate(@PathVariable("jobID") String jobID, @RequestBody RateDTO rateDTO) {
        Rate rate = rateService.findByJobID(jobID);
        
        if (rate == null) {
            rateDTO.setJobID(jobID);
            rateService.saveOrUpdate(ObjectMapperUtils.map(rateDTO, Rate.class));
        }
        else {
            handleRateAlreadyExistsException(jobID);
            // rate.setRate(rateDTO.getRate());
            // rateService.saveOrUpdate(ObjectMapperUtils.map(rate, Rate.class));
            
        }
        return new ResponseEntity("Rate added successfully - Job ID: " + jobID, HttpStatus.CREATED);
    }
    
    @PostMapping(value = "/{jobID}/{rate}")
    public ResponseEntity<?> createRate(@PathVariable("jobID") String jobID, @PathVariable("rate") int rateNumber) {
        Rate rate = rateService.findByJobID(jobID);
        
        if (rate == null) {
            rateService.saveOrUpdate(ObjectMapperUtils.map(new RateDTO(jobID, rateNumber), Rate.class));
        }
        else {
            handleRateAlreadyExistsException(jobID);
            // rate.setRate(rateNumber);
            // rateService.saveOrUpdate(ObjectMapperUtils.map(rate, Rate.class));
        }
        return new ResponseEntity("Rate added successfully - Job ID: " + jobID, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{jobID}/{rate}")
    public ResponseEntity<?> modify(@PathVariable("jobID") String jobID, @PathVariable("rate") int rate) {
        Rate rateObject = rateService.findByJobID(jobID);
        
        if (rateObject == null)
            handleRatedNotFoundException(jobID);

        rateObject.setRate(rate);
        rateService.saveOrUpdate(rateObject);
        return new ResponseEntity("Rate updated successfully - Job ID: " + jobID, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{jobID}")
    public ResponseEntity<?> deleteRateByJobID(@PathVariable("jobID") String jobID) {
        Rate rateObject = rateService.findByJobID(jobID);
        
        if (rateObject == null)
            handleRatedNotFoundException(jobID);
        
        rateService.deleteById(rateObject.getId());
        return new ResponseEntity("Rate deleted successfully - Job ID: " + jobID, HttpStatus.OK);
    }
}