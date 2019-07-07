package employee.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SkillsController {
	
	@Autowired 
	private MongoOperations mongoOps;

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
	public String deleteSkill(@Valid @RequestBody Skills skills) {
		SkillsRepository.delete(skills);
		if (SkillsRepository.findById(skills._id).isPresent())
			return ("recordexists");
		else
			return ("no record found");
	}

	// @GetMapping("/search/employee/skill/{empSkill}")
	// public employee searchBySkill(@PathVariable String empSkill) {
	// return repository.findBySkill(empSkill);
	// }

}
