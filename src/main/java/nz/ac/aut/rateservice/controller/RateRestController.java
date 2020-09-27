package nz.ac.aut.rateservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nz.ac.aut.rateservice.dto.RateDTO;
import nz.ac.aut.rateservice.model.Rate;
import nz.ac.aut.rateservice.service.RateService;
import nz.ac.aut.rateservice.util.ObjectMapperUtils;

import java.util.List;

@RestController
@RequestMapping("/")
public class RateRestController {

    @Autowired
    private RateService rateService;

    @GetMapping(value = "/")
    public List<RateDTO> getAllRates() {
        return ObjectMapperUtils.mapAll(rateService.findAll(), RateDTO.class);
    }

    @GetMapping(value = "/{jobID}")
    public RateDTO getRateByJobID(@PathVariable("jobID") String jobID) {
        return ObjectMapperUtils.map(rateService.findByJobID(jobID), RateDTO.class);
    }

    @GetMapping(value = "/orderByJobID")
    public List<RateDTO> findAllByOrderByJobID() {
        return ObjectMapperUtils.mapAll(rateService.findAllByOrderByJobID(), RateDTO.class);
    }

    // @PostMapping(value = "/")
    // public ResponseEntity<?> saveOrUpdate(@RequestBody RateDTO rateDTO) {
    //     rateService.saveOrUpdate(ObjectMapperUtils.map(rateDTO, Rate.class));
    //     return new ResponseEntity("Rate added successfully", HttpStatus.OK);
    // }
    
    @PostMapping(value = "/{jobID}/{rate}")
    public ResponseEntity<?> createRate(@PathVariable("jobID") String jobID, @PathVariable("rate") int rate) {
        rateService.saveOrUpdate(ObjectMapperUtils.map(new RateDTO(jobID, rate), Rate.class));
        return new ResponseEntity("Rate added successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/{jobID}/{rate}")
    public ResponseEntity<?> modify(@PathVariable("jobID") String jobID, @PathVariable("rate") int rate) {
        Rate rateObject = rateService.findByJobID(jobID);
        rateObject.setRate(rate);
        rateService.saveOrUpdate(rateObject);
        return new ResponseEntity("Rate updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{jobID}")
    public ResponseEntity<?> deleteRateByJobID(@PathVariable("jobID") String jobID) {
        rateService.deleteById(rateService.findByJobID(jobID).getId());
        return new ResponseEntity("Rate deleted successfully", HttpStatus.OK);
    }
}