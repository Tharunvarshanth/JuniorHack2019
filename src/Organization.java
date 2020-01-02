import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Organization {
    String id;
    String orgName;
    String domName;
    String details;
    String[] ticketSubjects;

    public Organization(){

    }

    public Organization(String keyword){

    }

    public Boolean checkTerm(String searchTerm){
        Boolean answer=false;
        String[] orgTerm = {"_id", "url", "external_id", "name", "domain_names", "created_at", "details", "shared_tickets", "tags"};
        for(int x=0;x<9;x++){
            if(searchTerm.equals(orgTerm[x])){
                answer=true;
            }
        }
        return answer;
    }

    public void returnSearchResult(String searchTerm, String value){
        JSONParser parser = new JSONParser();
        try{
            Reader reader = new FileReader("JsonStore\\organizations.json");
            JSONArray orgList = (JSONArray) parser.parse(reader);
            JSONObject jObj = null;

            for(int x = 0; x<orgList.size();x++){
                jObj = (JSONObject) orgList.get(x);
                String b =jObj.get(searchTerm).toString();
                if(b.equals(value)){
                    break;
                }else{
                    jObj = null;
                }
            }
            if(jObj==null){
                System.out.println("No results...");
            }else{
                this.id=jObj.get("_id").toString();
                this.orgName = jObj.get("name").toString();
                this.domName = jObj.get("domain_names").toString();
                this.details = jObj.get("details").toString();

                System.out.println(orgName);
                System.out.println(domName);
                System.out.println(details);

                reader.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
