package employee.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	private SkillsRepository skillsRepository;

	@GetMapping("/get/skills/employeeID/{empID}")
	public Object getSKillsByempID(@Valid @PathVariable String empID) {
		return skillsRepository.findById(empID);
	}

	@PostMapping("/add/skill")
	public Object addSkills(@Valid @RequestBody Skills skills) {
		return (skillsRepository.save(skills));
	}

	@DeleteMapping("/delete/skill")
	public String deleteSkill(@Valid @RequestBody Skills skills) {
		skillsRepository.delete(skills);
		if (skillsRepository.findById(skills.skillID).isPresent())
			return ("recordexists");
		else
			return ("no record found");
	}

	// @GetMapping("/search/employee/skill/{empSkill}")
	// public employee searchBySkill(@PathVariable String empSkill) {
	// return repository.findBySkill(empSkill);
	// }

}
