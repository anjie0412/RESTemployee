package employee.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.client.result.DeleteResult;



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
	

	@PostMapping(value = "/add/skill")
	public Object addSkills(@RequestBody @Valid Skills skills) {
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

	@GetMapping("/search/bySkill/{empSkill}")
	 public EmpSkillResult searchBySkill(@RequestBody @PathVariable String empSkill) {
		
		 List<String> empIDs = new ArrayList<String>();
		 List <Skills> skillObjects = SkillsRepository.searchBySkill(empSkill);
		 Iterator<Skills> itr = skillObjects.iterator();
		 while (itr.hasNext()) {
			 empIDs.add(itr.next().empID);
		 }
		 
		 List<employee> emp = (List<employee>) EmployeesRepository.findAllById(empIDs);
		
		 return (new EmpSkillResult(empSkill , emp));
	
	}
	



}
