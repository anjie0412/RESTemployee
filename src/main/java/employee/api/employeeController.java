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
public class employeeController {

	@Autowired
	private employeeRepository repository;

	@GetMapping("/getAll/employees")
	public Object getAllEmployees() {
		if (repository.findAll().isEmpty()) {
			return ("no employee data found");
		}
		return repository.findAll();
	}

	@GetMapping("/get/employee/{empID}")
	public Object getEmployeeByID(@Valid @PathVariable String empID) {
		return repository.findById(empID);
	}


	@PostMapping("/add/employee")
	public Object addEmployee(@Valid @RequestBody employee emp) {
		//emp.setSkill(emp.getSkills()[0].split(",")); //replace it with @RequestBody In case input is in JSON (Ideal way to do)T
		return (repository.save(emp));
	}

	@PutMapping("/update/employee")
	public Object updateEmployee(@Valid @RequestBody employee emp) {

		if (repository.findById(emp.empID).isPresent()) {
			//emp.setSkill(emp.getSkills()[0].split(",")); //replace it with @RequestBody In case input is in JSON (Ideal way to do)T
			return (repository.save(emp));
		}
		return ("record doesnt exists");
	}

	@DeleteMapping("/delete/employee/{empID}")
	public String deleteEmployee(@Valid @PathVariable String empID) {
		repository.deleteById(empID);

		if (repository.findById(empID).isPresent()) {
			return ("employee record exists");
		} else
			return ("no record found of employee with employee ID" + "-" + empID);
	}
	
	


	@DeleteMapping("/deleteAll/employees")
	public String deleteAllEmployees() {
		repository.deleteAll();

		if (repository.findAll().isEmpty())
			return ("records has been deleted successfully");
		else
			return ("records are present for all employees");
	}
	
	
	/*
	 * @RequestMapping("/error") public String error() { return
	 * ("you must provide valid http request"); }
	 */
}
