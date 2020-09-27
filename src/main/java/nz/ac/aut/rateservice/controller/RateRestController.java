package nz.ac.aut.rateservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import nz.ac.aut.rateservice.dto.RateDTO;
import nz.ac.aut.rateservice.exception.ExceptionResponse;
import nz.ac.aut.rateservice.exception.RateNotFoundException;
import nz.ac.aut.rateservice.model.Rate;
import nz.ac.aut.rateservice.service.RateService;
import nz.ac.aut.rateservice.util.ObjectMapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
// @RequestMapping("/")
public class RateRestController {

    @Autowired
    private RateService rateService;

    @GetMapping(value = "/")
    public List<RateDTO> getAllRates() {
        List<Rate> rates = rateService.findAll();

        if (rates.size() == 0)
            throw new RateNotFoundException("No data found");

        return ObjectMapperUtils.mapAll(rates, RateDTO.class);
    }

    @GetMapping(value = "/orderByJobID")
    public List<RateDTO> findAllByOrderByJobID() {
        List<Rate> rates = rateService.findAllByOrderByJobID();

        if (rates == null)
            throw new RateNotFoundException("No data found");

        return ObjectMapperUtils.mapAll(rates, RateDTO.class);
    }

    @GetMapping(value = "/{jobID}")
    public RateDTO getRateByJobID(@PathVariable("jobID") String jobID) {
        Rate rate = rateService.findByJobID(jobID);

        if (rate == null)
            throw new RateNotFoundException("Job ID: " + jobID);

        return ObjectMapperUtils.map(rate, RateDTO.class);
    }

    // @PostMapping(value = "/")
    // public ResponseEntity<?> saveOrUpdate(@RequestBody RateDTO rateDTO) {
    // rateService.saveOrUpdate(ObjectMapperUtils.map(rateDTO, Rate.class));
    // return new ResponseEntity("Rate added successfully", HttpStatus.OK);
    // }

    @PostMapping(value = "/{jobID}/{rate}")
    public ResponseEntity<?> createRate(@PathVariable("jobID") String jobID, @PathVariable("rate") int rate) {
        rateService.saveOrUpdate(ObjectMapperUtils.map(new RateDTO(jobID, rate), Rate.class));
        return new ResponseEntity("Rate added successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/{jobID}/{rate}")
    public ResponseEntity<?> modify(@PathVariable("jobID") String jobID, @PathVariable("rate") int rate) {
        Rate rateObject = rateService.findByJobID(jobID);

        if (rateObject == null)
            return ResponseEntity.notFound().build();

        rateObject.setRate(rate);
        rateService.saveOrUpdate(rateObject);
        return new ResponseEntity("Rate updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{jobID}")
    public ResponseEntity<?> deleteRateByJobID(@PathVariable("jobID") String jobID) {
        Rate rateObject = rateService.findByJobID(jobID);

        if (rateObject == null)
            return ResponseEntity.notFound().build();

        rateService.deleteById(rateObject.getId());
        return new ResponseEntity("Rate deleted successfully", HttpStatus.OK);
    }
}