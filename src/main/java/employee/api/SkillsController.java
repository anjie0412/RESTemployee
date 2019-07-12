package employee.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.result.DeleteResult;



@RestController
public class SkillsController {
	
	@Autowired 
	private MongoOperations mongoOps;	
    @Autowired
    private MongoTemplate mongoTemplate;
	@Autowired
	private SkillsRepository SkillsRepository;
	@Autowired
	private employeeRepository  EmployeesRepository;

	

	@GetMapping("/get/skills/employeeID/{empID}")
	public Object getSKillsByempID(@Valid @PathVariable String empID) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("empID").is(empID));
		List<Skills> empSkills = mongoOps.find(query, Skills.class);
		return empSkills;
				}
	

	@PostMapping("/add/skill")
	public Object addSkills(@Valid Skills skills) {
		if (EmployeesRepository.findById(skills.empID).isPresent()) {
			if (SkillsRepository.objectExists(skills.empID, skills.empSkill) == null){
				return (SkillsRepository.save(skills));
			}
			else
				return ("employee Skill already present in database");
		}
		else
			return("no employee exists with employee id " + skills.empID + " to add skills");
	}

	@DeleteMapping("/delete/skill")
	public DeleteResult deleteSkill(@Valid Skills skills) {
		Query query = new Query();
		query.addCriteria(Criteria.where("empID").is(skills.empID).and("empSkill").is(skills.empSkill));
		return (mongoOps.remove(query, Skills.class));
	}

	@GetMapping("/search/employee/skill/{empSkill}")
	 public List<EmpSkillResult> searchBySkill(@PathVariable String empSkill) {
		
	    LookupOperation lookupOps = LookupOperation.newLookup()
                .from("Employees")
                .localField("empID")
                .foreignField("empID")
                .as("employee");
                		
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("empSkill").is(empSkill)) , lookupOps);
	        
        List<EmpSkillResult> results = mongoTemplate.aggregate(aggregation, "Skills", EmpSkillResult.class).getMappedResults();
        
        return results;
        
	}

}
