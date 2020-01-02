import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;

public class Tickets {
    String id;
    String assigneeName;
    String submitterName;
    String organizationName;
    String assigneeId;
    String submitterId;
    String orgId;


    public Boolean checkTerm(String searchTerm){
        Boolean answer=false;
        String[] ticketTerm = {"_id", "url", "external_id", "created_at", "type", "subject", "description", "priority", "status", "reciptent", "submitter_id", "assignee_id", "organization_id", "tags", "has_incidents", "due_at", "via", "requester_id"};
        for(int x=0;x<18;x++){
            if(searchTerm.equals(ticketTerm[x])){
                answer=true;
            }
        }
        return answer;
    }

    public void returnSearchResult(String searchTerm, String value){
        JSONParser parser = new JSONParser();
        try{
            //Searching in tickets.json
            Reader reader = new FileReader("JsonStore\\tickets.json");
            JSONArray ticketList = (JSONArray) parser.parse(reader);
            JSONObject jObj = null;

            for(int x = 0; x<ticketList.size();x++){
                jObj = (JSONObject) ticketList.get(x);
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
                this.assigneeId=jObj.get("assignee_id").toString();
                this.submitterId=jObj.get("submitter_id").toString();
                this.orgId=jObj.get("organization_id").toString();
                reader.close();
                search2(this.assigneeId);
                search3(this.submitterId);
                search4(this.orgId);
            }
        }catch (Exception e){
            System.out.println("Error "+e);
        }
    }

    public void search2(String assigneeID){
        JSONParser parser = new JSONParser();
        try{
            Reader reader2 = new FileReader("JsonStore\\users.json");
            JSONArray userList1 = (JSONArray) parser.parse(reader2);
            JSONObject jObj1 = null;

            for(int x = 0; x<userList1.size();x++){
                jObj1 = (JSONObject) userList1.get(x);
                String b =jObj1.get("_id").toString();
                if(b.equals(assigneeId)){
                    break;
                }else{
                    jObj1 = null;
                }
            }
            this.assigneeName=jObj1.get("name").toString();
            printSpaces("Assignee Name",assigneeName);
            reader2.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void search3(String submitterID){
        JSONParser parser = new JSONParser();
        try{
            Reader reader2 = new FileReader("JsonStore\\users.json");
            JSONArray userList1 = (JSONArray) parser.parse(reader2);
            JSONObject jObj1 = null;

            for(int x = 0; x<userList1.size();x++){
                jObj1 = (JSONObject) userList1.get(x);
                String b =jObj1.get("_id").toString();
                if(b.equals(submitterID)){
                    break;
                }else{
                    jObj1 = null;
                }
            }
            this.submitterName=jObj1.get("name").toString();
            printSpaces("Submitter Name",submitterName);
            reader2.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void search4(String orgId){
        JSONParser parser = new JSONParser();
        try{
            Reader reader2 = new FileReader("JsonStore\\organizations.json");
            JSONArray userList1 = (JSONArray) parser.parse(reader2);
            JSONObject jObj1 = null;

            for(int x = 0; x<userList1.size();x++){
                jObj1 = (JSONObject) userList1.get(x);
                String b =jObj1.get("_id").toString();
                if(b.equals(orgId)){
                    break;
                }else{
                    jObj1 = null;
                }
            }
            this.organizationName=jObj1.get("name").toString();
            printSpaces("Organization Name",organizationName);
            reader2.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void printSpaces(String str1, String str2){
        int space = 30 - str1.length();
        System.out.print(str1);
        for(int i=0 ; i < space ; i++){
            System.out.print(" ");
        }
        System.out.println(str2);
    }
}
