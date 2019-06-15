package employee.api;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface employeeRepository extends MongoRepository<employee, String> { 
}


