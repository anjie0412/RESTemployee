package employee.api;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Query;


public interface SkillsRepository extends MongoRepository<Skills, String>{

	
	@Query("{'empID':?0, 'empSkill' :?1}")
	Skills objectExists(String empID, String empSkill);


}


