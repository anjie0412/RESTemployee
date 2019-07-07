package employee.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Path("consume")
public class employeeController {

	@Autowired
	private employeeRepository empRepository;
	@Autowired                                    
	private SkillsRepository SkillsRepository;     
	


	@GetMapping("/getAll/employees")
	public Object getAllEmployees() {
		if (empRepository.findAll().isEmpty()) {
			return ("no employee data found");
		}
		return empRepository.findAll();
	}

	@GetMapping("/get/employee/{empID}")
	public Object getEmployeeByID(@Valid @PathVariable String empID) {
		return empRepository.findById(empID);
	}


	@PostMapping("/add/employee")
	//@Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded;charset=UTF-8"})
	public Object addEmployee(@Valid employee emp) {
		//emp.setSkill(emp.getSkills()[0].split(",")); //replace it with @RequestBody In case input is in JSON (Ideal way to do)T
		return (empRepository.save(emp));
	}

	@PutMapping("/update/employee")
	public Object updateEmployee(@Valid employee emp) {

		if (empRepository.findById(emp.empID).isPresent()) {
			return empRepository.save(emp);			
		}
		return ("record doesnt exists");
	}

	@DeleteMapping("/delete/employee/{empID}")
	public Object deleteEmployee(@Valid @PathVariable String empID) {
		if (empRepository.findById(empID).isPresent()) {
			empRepository.deleteById(empID);
			SkillsRepository.deleteAllSkillsByEmployeeID(empID);    
			
		return new ResponseEntity<String>("Employee Deleted succcessfully", HttpStatus.OK);}
		else
			return ("no record found with employeeID {empID} to be deleted ");
	}
	

	@DeleteMapping("/deleteAll/employees")
	public ResponseEntity<String> deleteAllEmployees() {
		empRepository.deleteAll();
		SkillsRepository.deleteAll();    
			return new ResponseEntity<String>("All Employees Deleted succcessfully", HttpStatus.OK);
	}
	
	//@GetMapping("/search/employee/skill/{empSkill}")
	//public employee searchBySkill(@PathVariable String empSkill) {
	//	return repository.findBySkill(empSkill);
	//}
	
	/*
	 * @RequestMapping("/error") public String error() { return
	 * ("you must provide valid http request"); }
	 */
}
