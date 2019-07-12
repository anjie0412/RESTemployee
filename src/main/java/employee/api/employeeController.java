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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class employeeController {

	@Autowired 
	private MongoOperations mongoOps;
	@Autowired
	private employeeRepository empRepository;
	@Autowired                                    
	private SkillsRepository SkillsRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    


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
			
			Query query = new Query();
			query.addCriteria(Criteria.where("empID").is(empID));
			mongoOps.findAllAndRemove(query, Skills.class);   
			
		return new ResponseEntity<String>("Employee Deleted succcessfully", HttpStatus.OK);}
		else
			return ("no record found with employeeID "+ empID+" to be deleted ");
	}
	

	@DeleteMapping("/deleteAll/employees")
	public ResponseEntity<String> deleteAllEmployees() {
		empRepository.deleteAll();
		SkillsRepository.deleteAll();    
			return new ResponseEntity<String>("All Employees Deleted succcessfully", HttpStatus.OK);
	}
	
	@GetMapping("/search/employeesBy/manager/{managerName}")
	 public List<employee> searchByManager(@PathVariable String managerName) {
		
		employee manager = empRepository.findManagerIDByName(managerName);
		
	    LookupOperation lookupOps = LookupOperation.newLookup()
               .from("Employees")
               .localField("empID")
               .foreignField("managerID")
               .as("employee");
	    
	    
	    Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("managerID").is(manager.empID)) , lookupOps);
	        
	    List<employee> results = mongoTemplate.aggregate(aggregation, "Employees", employee.class).getMappedResults();
       
       return results;
       
	}
}
