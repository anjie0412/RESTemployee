package employee.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		// System.out.println(empID);

		//Optional<employee> empObject = repository.findById(empID);

		//if (empObject.isPresent()) {
			//return empObject;
		//}
		//return ("No employee has found with the id");
		
		return repository.findById(empID);
	}


	@PostMapping("/Add/employee")
	public Object addEmployee(@Valid employee emp) {
		emp.setSkill(emp.getSkills()[0].split(",")); //replace it with @RequestBody In case input is in JSON (Ideal way to do)T
		return (repository.save(emp));
	}

	@PutMapping("/update/employee")
	public Object updateEmployee(@Valid employee emp) {

		if (repository.findById(emp.empID).isPresent()) {
			emp.setSkill(emp.getSkills()[0].split(",")); //replace it with @RequestBody In case input is in JSON (Ideal way to do)T
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
}
