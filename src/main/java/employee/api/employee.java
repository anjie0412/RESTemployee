package employee.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Document(collection= "Employee")
public class employee {
	
	@Id @Field("ID") String empID;
	@Field("Name") @Indexed @NotBlank(message = "Employee Name cannot be null") String empName;
	@Field("Skills") @Indexed @NotEmpty(message = "Atleast one skill should be added for the employee") String[] empSkills = new String[10]; 
	
	
	public employee( String empID, String empName, String[] empSkills) {
		this.empID = empID;
		this.empName = empName;
		this.empSkills = empSkills;		
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
	
	public String[] getSkills() {
		return empSkills;	
	}
	
	public void setSkill(String[] empSkills) {
		this.empSkills = empSkills;
	}

}
