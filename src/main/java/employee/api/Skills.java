package employee.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.NotBlank;

@Document(collection= "Skills")
public class Skills {
	
	@Id String _id;
	@Field("empID") @Indexed @NotBlank(message = "Employee ID cannot be null") String empID;
	@Field("empSkill") @NotBlank(message = "Atleast one skill should be added for the employee") String empSkill ; 
	
	
	public Skills(String empID, String empSkill) {
		this.empID = empID;
		this.empSkill = empSkill;		
	}
		
	public String getempID() {
		return empID;	
	}
	
	public void setempID(String empID) {
		this.empID = empID;
	}

	
	public String getSkills() {
		return empSkill;	
	}
	
	public void setSkill(String empSkill) {
		this.empSkill = empSkill;
	}


}
