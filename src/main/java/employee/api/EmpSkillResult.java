package employee.api;


public class EmpSkillResult {
	String empID;
	String empName;
	String managerName;
	String empSkill;
	
	
	public EmpSkillResult( String empID, String empName, String managerName, String empSkill) {
		this.empID = empID;
		this.empName = empName;	
		this.managerName = managerName;	
		this.empSkill = empSkill;
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
	public String getSkills() {
		return empSkill;	
	}
	
	public void setSkill(String empSkill) {
		this.empSkill = empSkill;
	}

}
