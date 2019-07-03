package employee.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;

@Document(collection= "Skills")
public class Skills {
	
	@Id @Field("ID") String skillID;
	@Field("empID") @NotBlank(message = "Employee ID cannot be null") String empID;
	@Field("empSkills") @DBRef @Indexed @NotBlank(message = "Atleast one skill should be added for the employee") String empSkills ; 
	
	
	public Skills( String empID, String empSkills) {
		this.empID = empID;
		this.empSkills = empSkills;		
	}
		
	public String getempID() {
		return empID;	
	}
	
	public void setempID(String empID) {
		this.empID = empID;
	}

	
	public String getSkills() {
		return empSkills;	
	}
	
	public void setSkill(String empSkills) {
		this.empSkills = empSkills;
	}

}
