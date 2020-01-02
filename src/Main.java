import java.io.FileReader;
import java.io.Reader;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    Scanner sc=new Scanner(System.in);

    public static void main(String args[]){
        Main ob = new Main();
        String x = "hello";
        while(!x.equals("quit")){
            x = ob.printMenu();
            switch (x) {
                case "1":
                    ob.searchMenu();
                    break;
                case "2":
                    ob.listMenu();
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("Invalid Option...");
                    break;
            }
        }


    }

    public void searchOrganization(String searchTerm, String value){
        Organization ob = new Organization();
        if(ob.checkTerm(searchTerm)){
            ob.returnSearchResult(searchTerm,value);
        }else{
            System.out.println("Enter a valid search term....");
        }
    }

    public void searchUser(String searchTerm, String value){
        Users ob = new Users();
        if(ob.checkTerm(searchTerm)){
            ob.returnSearchResult(searchTerm,value);
        }else{
            System.out.println("Enter a valid search term.....");
        }
    }

    public void searchTicket(String searchTerm, String value){
        Tickets ob = new Tickets();
        if(ob.checkTerm(searchTerm)){
            ob.returnSearchResult(searchTerm,value);
        }else{
            System.out.println("Enter a valid search term.....");
        }
    }

    public String printMenu(){
        String option;
        System.out.println("\nType 'quit' to exit at any time,Press 'Enter' to continue");
        System.out.println("Select Search Option: ");
        System.out.println(" * Press 1 to Search ");
        System.out.println(" * Press 2 to view a list of Searchable fields");
        System.out.println(" * Type 'quit' to exit");
        option= sc.nextLine();
        return option;
    }


    public void searchMenu(){
        System.out.println("Select 1) Users or 2) Tickets or 3) Organizations");
        int x = sc.nextInt();
        if(x==1||x==2||x==3){
            System.out.println("Enter Search Term :");
            String temp = sc.nextLine();
            String  searchTerm = sc.nextLine();
            System.out.println("Enter Search value :");
            String value = sc.nextLine();
            switch (x) {
                case 1:
                    searchUser(searchTerm,value);
                    break;
                case 2:
                    searchTicket(searchTerm,value);
                    break;
                case 3:
                    searchOrganization(searchTerm,value);
                    break;
                default:
                    System.out.println("Invalid Option ");
                    break;
            }
        }else{
            System.out.println("Invalid input....");
        }
    }

    public void listMenu(){

        System.out.println("============================================================");

        System.out.println("Search Users with\n");
        System.out.println("id\nurl\nexternal_id\nname\nalias\ncreated_at\nactive\nverified\nshared\ntimezone"
                +"last_login_at\nemail\nphone\nsignature\norganization_id\ntags\nsuspended\nrole");

        System.out.println("============================================================");

        System.out.println("Search Tickets with\n");
        System.out.println("_id\nurl\nexternal_id\ncreated_at\ntype\nnsubject\nndescription\nnpriority\nstatus\n"
                +"submitter_id\nassignee_id\norganization_id\ntags\nhas_incidents\ndue_at\nvia ");

        System.out.println("============================================================");

        System.out.println("Search Organizations with\n");
        System.out.println("_id\nurl\nexternal_id\nname\ndomain_names\ncreated_at\ndetails\nshared_tickets\ntags");

        System.out.println("============================================================");

    }

}

