package employee.api;

import java.util.List;

public class EmpSkillResult {
	String empSkill;
	List<employee> employees;
    
	
	
	public EmpSkillResult( String empSkill, List<employee> employees) {	
		this.empSkill = empSkill;
		this.employees = employees;
	}
	
	public String getSkills() {
		return empSkill;	
	}
	
	public void setSkill(String empSkill) {
		this.empSkill = empSkill;
	}
		
	public List<employee> getemployees() {
		return employees;	
	}
	
	public void setemployees(List<employee> employees) {
		this.employees = employees;
	}




}
