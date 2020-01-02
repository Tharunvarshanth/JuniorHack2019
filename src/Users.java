import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;

public class Users{
    String id=null;
    String assigneeTicketSubject=null;
    String submittedTicketSubject=null;
    String organizationName=null;
    String orgId=null;

    public Boolean checkTerm(String searchTerm){
        Boolean answer=false;
        String[] userTerm = {"_id", "url", "external_id", "name", "alias", "created_at", "active", "verified", "shared", "locale", "timezone", "last_login_at", "email", "phone", "signature", "organization_id", "tags", "suspended", "role"};
        for(int x=0;x<19;x++){
            if(searchTerm.equals(userTerm[x])){
                answer=true;
            }
        }
        return answer;
    }

    public void returnSearchResult(String searchTerm, String value){
        JSONParser parser = new JSONParser();
        try{
            //to read from user.json
            Reader reader = new FileReader("JsonStore\\users.json");
            JSONArray usersList = (JSONArray) parser.parse(reader);
            JSONObject jObj = null;


            for(int x = 0; x<usersList.size();x++){
                jObj = (JSONObject) usersList.get(x);
                String b =jObj.get(searchTerm).toString();
                if(b.equals(value)){
                    break;
                }else{
                    jObj = null;
                }
            }
            if(jObj==null){
                System.out.println("No results...");
            }else {

                this.orgId = jObj.get("organization_id").toString();
                this.id = jObj.get("_id").toString();

                reader.close();
                search2(id);
                search3(id);
                search4(orgId);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void search2(String id){
        JSONParser parser = new JSONParser();
        try{
            //to read from tickets for assignee
            Reader reader3 = new FileReader("JsonStore\\tickets.json");
            JSONArray ticketList1 = (JSONArray) parser.parse(reader3);
            JSONObject jObj2 = null;

            //Searching in tickets.json for assignee ticket subject
            for (int x = 0; x < ticketList1.size(); x++) {
                jObj2 = (JSONObject) ticketList1.get(x);
                String b = jObj2.get("assignee_id").toString();
                if (b.equals(this.id)) {
                    break;
                } else {
                    jObj2 = null;
                }
            }
            this.assigneeTicketSubject = jObj2.get("subject").toString();
            printSpaces("Assignee Ticket Subject ",assigneeTicketSubject);
            reader3.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void search3(String id){
        JSONParser parser = new JSONParser();
        try{
            //to read from tickets for submitter
            Reader reader4 = new FileReader("JsonStore\\tickets.json");
            JSONArray ticketList2 = (JSONArray) parser.parse(reader4);
            JSONObject jObj3 = null;

            //Searching in tickets.json for submitter ticket subject
            for (int x = 0; x < ticketList2.size(); x++) {
                jObj3 = (JSONObject) ticketList2.get(x);
                String b = jObj3.get("submitter_id").toString();
                if (b.equals(this.id)) {
                    break;
                } else {
                    jObj3 = null;
                }
            }
            this.submittedTicketSubject = jObj3.get("subject").toString();
            printSpaces("Submitted Ticket Subject",submittedTicketSubject);
            reader4.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void search4(String orgId){
        JSONParser parser = new JSONParser();
        try{
            //to read from organizations
            Reader reader2 = new FileReader("JsonStore\\organizations.json");
            JSONArray orgList = (JSONArray) parser.parse(reader2);
            JSONObject jObj1 = null;

            //Searching in organizations.json
            for (int y = 0; y < orgList.size(); y++) {
                jObj1 = (JSONObject) orgList.get(y);
                String b = jObj1.get("_id").toString();
                if (b.equals(orgId)) {
                    break;
                } else {
                    jObj1 = null;
                }
            }

            this.organizationName = jObj1.get("name").toString();
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
