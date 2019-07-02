package employee.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillsController {

	@Autowired
	private SkillsRepository skillsRepository;
	
	@Autowired
	private employeeRepository empRepository;

	
	@GetMapping("/get/skills/employeeID/{empID}")
	public Object getSKillsByempID(@Valid @PathVariable String empID) {
		return  skillsRepository.findById(empID);
	}


	@PostMapping("/add/skills")
	public Object addSkills(@Valid @RequestBody Skills skills) {
		//emp.setSkill(emp.getSkills()[0].split(",")); //replace it with @RequestBody In case input is in JSON (Ideal way to do)T
		return (skillsRepository.save(skills));
	}

	@PutMapping("/update/skills")
	public Object updateEmployee(@Valid @RequestBody Skills skills) {

		if (skillsRepository.findById(skills.empID).isPresent()) {
			//emp.setSkill(emp.getSkills()[0].split(",")); //replace it with @RequestBody In case input is in JSON (Ideal way to do)T
			return (skillsRepository.save(skills));
		}
		return ("record doesnt exists");
	}

	@DeleteMapping("/delete/skill/{Skill}/employeeID/{empID}")
	public String deleteSkillByEmployeeID(@Valid @PathVariable String empID, @Valid @PathVariable String Skill) {
		return 

			}
	
	


	
	//@GetMapping("/search/employee/skill/{empSkill}")
	//public employee searchBySkill(@PathVariable String empSkill) {
	//	return repository.findBySkill(empSkill);
	//}
	

}
