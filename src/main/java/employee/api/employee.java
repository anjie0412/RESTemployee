package employee.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;

@Document(collection= "Employees")
public class employee {
	
	@Id @Field("ID") String empID;
	@Field("empName") @Indexed @NotBlank(message = "Employee Name cannot be null") String empName;
	@Field("managerName") @Indexed @NotBlank(message = "Employee's Manager's Name cannot be null") String managerName;
	
	
	public employee( String empID, String empName, String managerName) {
		this.empID = empID;
		this.empName = empName;	
		this.managerName = managerName;	
	}
		
	public String getempID() {
		return empID;	
	}
	
	public void setempID(String empID) {
		this.empID = empID;
	}
	
	public String getName() {
		return empName;	
	}
	
	public void setName(String empName) {
		this.empName = empName;
	}
	
	public String getManagerName() {
		return managerName;	
	}
	
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

}
