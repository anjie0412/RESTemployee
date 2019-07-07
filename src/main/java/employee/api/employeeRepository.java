package employee.api;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;


public interface employeeRepository extends MongoRepository<employee, String> { 

}


