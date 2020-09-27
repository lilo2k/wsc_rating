
package nz.ac.aut.rateservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.repository.query.Param;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import nz.ac.aut.rateservice.model.Rate;

// @RepositoryRestResource(collectionResourceRel = "rate", path = "rate")
public interface RateRepository extends MongoRepository<Rate, String> { // Rate: type of model, String: type of ID
    // List<Rate> findByJobID(@Param("jobID") String jobID);
    Rate findByJobID(String jobID);
    List<Rate> findAllByOrderByJobID();
}