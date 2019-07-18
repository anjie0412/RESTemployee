package employee.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;

@Document(collection= "JSONData")
public class JSONData {
	
	@Id @Field("ID") String ID;
	@Field("JSONData") @NotBlank(message = "ADD JSON data to field") String JSONData; 
    
	
	public JSONData(String JSONData) {
		this.JSONData = JSONData;		
	}
	
	public String getID() {
		return ID;	
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	
	public String getdata() {
		return JSONData;	
	}
	
	public void setdata(String JSONData) {
		this.JSONData = JSONData;
	}


}
