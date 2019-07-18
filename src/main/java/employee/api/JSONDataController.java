package employee.api;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JSONDataController {
	
	@Autowired 
	private JSONDataRepository JSONDataRepos;
	

	   @PostMapping("/add/{jsonData}")
	    JSONData addData(@RequestBody String jsonData) {

	       Document JSONdoc = Document.parse(jsonData);
	      return JSONDataRepos.save(JSONdoc);
	   }
	

}
