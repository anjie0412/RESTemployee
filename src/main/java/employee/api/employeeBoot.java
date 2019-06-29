package employee.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class employeeBoot implements CommandLineRunner {
	
	@Autowired
	private employeeRepository repository;

	public static void main(String[] args) {

		SpringApplication.run(employeeBoot.class, args);
	}
	
	 @Override
	    public void run(String... args) throws Exception {
	     
		
				System.out.println("Allemployees");
				System.out.println("-------------------------------");
				for (employee employee : repository.findAll()) {
					System.out.println(employee.empName);
				}
				System.out.println();
		
	}

}
