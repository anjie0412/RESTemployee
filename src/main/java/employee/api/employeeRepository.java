package employee.api;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface employeeRepository extends MongoRepository<employee, String> { 
	
	@Query("{'empName':?0}")
	employee findManagerIDByName(String managerName);

	@Query("{'grade':?0}")
	employee findBygrade(String grade);}


