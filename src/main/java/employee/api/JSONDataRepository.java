package employee.api;


import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JSONDataRepository extends MongoRepository<JSONData, Document>{

	JSONData save(Document jSONdoc);

}


