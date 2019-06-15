package employee.api;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class employeeController {

	@Autowired
	private employeeRepository repository;

	@RequestMapping("/GetAll/employees")
	public Object getAllEmployees() {
		if (repository.findAll().isEmpty()) {
			return ("no employee data found");
		}
		return repository.findAll();
	}

	@RequestMapping("/Get/employee/{empID}")
	public Object getEmployeeByID(@Valid @PathVariable String empID) {
		// System.out.println(empID);

		//Optional<employee> empObject = repository.findById(empID);

		//if (empObject.isPresent()) {
			//return empObject;
		//}
		//return ("No employee has found with the id");
		
		return repository.findById(empID);
	}


	@RequestMapping("/Add/employee")
	public Object addEmployee(@Valid employee emp) {

		return (repository.save(emp));
	}

	@RequestMapping("/Update/employee")
	public Object updateEmployee(@Valid employee emp) {

		if (repository.findById(emp.empID).isPresent()) {
			return (repository.save(emp));
		}
		return ("record doesnt exists");
	}

	@RequestMapping("/Delete/employee/{empID}")
	public String deleteEmployee(@Valid @PathVariable String empID) {
		repository.deleteById(empID);

		if (repository.findById(empID).isPresent()) {
			return ("employee record exists");
		} else
			return ("no record found of employee with employee ID" + "-" + empID);
	}


	@RequestMapping("/DeleteAll/employees")
	public String deleteAllEmployees() {
		repository.deleteAll();

		if (repository.findAll().isEmpty())
			return ("records has been deleted successfully");
		else
			return ("records are present for all employees");
	}
}
