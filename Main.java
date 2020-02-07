import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Mobile mobile = new Mobile("123 456 678");
    public static void main(String[] args) {


        boolean quit = false;
        startPhone();
        printActions();
        while(!quit){
            System.out.println("\nEnter actions: (6 to show actions)");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action){
                case 0:
                    System.out.println("'Shutting down..");
                    quit=true;
                    break;

                case 1:
                    printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printActions();
                    break;
            }
        }
    }


    private static void addNewContact(){
        System.out.println("Enter new contact name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        Contact newContact = Contact.createContact(name,phone);
        if(mobile.addNewContact(newContact)){
            System.out.println("New contact added: " + name + ", phone = "+ phone);
        }else{
            System.out.println("Cannot add, " + name + "already on file");
        }
    }

    private static void updateContact(){
        System.out.println("Enter contact name: ");
        String name =scanner.nextLine();
        Contact exisitingContactRecord = mobile.queryContact(name);
        if(exisitingContactRecord == null){
            System.out.println("Contact not found.");
            return;
        }

        System.out.print("Enter new contact name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new contact number: ");
        String newPhone = scanner.nextLine();
        Contact newContact = Contact.createContact(newName,newPhone);
        if(mobile.updateContact(exisitingContactRecord, newContact)){
            System.out.println("Succesfully update record");
        }else{
            System.out.println("Error updating record");
        }
    }

    private static void removeContact(){
        System.out.print("Enter contact name: ");
        String name =scanner.nextLine();
        Contact exisitingContactRecord = mobile.queryContact(name);
        if(exisitingContactRecord == null){
            System.out.println("Contact not found.");
            return;
        }

       if(mobile.removeContact(exisitingContactRecord)){
           System.out.println("Successfully deleted");
       }else{
           System.out.println("Error deleting contact");
       }
    }

    private static void queryContact(){
        System.out.print("Enter contact name: ");
        String name =scanner.nextLine();
        Contact exisitingContactRecord = mobile.queryContact(name);
        if(exisitingContactRecord == null){
            System.out.println("Contact not found.");
            return;
        }
        System.out.println("Name: " + exisitingContactRecord.getName() + " phone number is " + exisitingContactRecord.getPhoneNumber());
    }

    private static void printContacts(){
        mobile.printContacts();
    }

    private static void startPhone() {
        System.out.println("Starting phone..");
    }

    private static void printActions(){
        System.out.println("\nAvailable actions:\n press");
        System.out.println("0 - to shutdown\n" +
                            "1 - to print contacts\n"+
                            "2 - to add new contact\n"+
                            "3 - to update existing contact\n"+
                            "4 - to remove existing contact\n"+
                            "5 - query if contact exist\n"+
                            "6 - to print list of contacts");
        System.out.println("Choose your action: ");
    }
}

