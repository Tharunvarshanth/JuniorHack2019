public class Tickets {
    String id;
    String assigneeName;
    String submitterName;
    String organizationName;


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
}
