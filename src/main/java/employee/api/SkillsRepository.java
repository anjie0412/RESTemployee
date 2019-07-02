package employee.api;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;


public interface SkillsRepository extends MongoRepository<Skills, String> {


}


