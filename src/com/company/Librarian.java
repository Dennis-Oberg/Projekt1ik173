package com.company;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Librarian extends User {


        User user;
        Book book;

        BookManager bManager;
        MemberManager mManager;

    public Librarian(int idCode, int ssn, String firstname, String lastname, int titel) {
        super(idCode, ssn, firstname, lastname, titel);
    }

    public Librarian(User user){

        this.user = user;
        mManager = new MemberManager();
        bManager = new BookManager( user);
        librarianOption(libMenu());
    }

    public void librarianOption(int option) {
        System.out.println("");
        switch (option){
            case 0:
                AuthService auth = new AuthService();
                auth.start();
                break;
            case 1:
                System.out.println("Lägg till bok i system metod");
                break;
            case 2:
                addMember();
                librarianOption(libMenu());
                break;
            case 3:
                System.out.println("Söka efter medlem metod");
                break;
            case 4:
                removeMember();
                librarianOption(libMenu());
                break;
            case 5:
                returnBook();
                librarianOption(libMenu());
                break;
            case 6:
                loanByLibrerian();
                librarianOption(libMenu());
                break;
            default:
                System.out.println("Inget giltig val\n");
                librarianOption(libMenu());
                break;
        }
    }


    public int libMenu(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nVälj ett alternativ:\n1.Lägg till bok i system\n2.Registrerar medlem\n3.Söka efter medlem\n4.Ta bort medlem" +
                "\n5.Lämna tillbak bok\n6.Låna bok\n0.Logga ut");
        System.out.print("Val: ");

        return input.nextInt();
    }

    public void loanByLibrerian(){
        System.out.println("Ange medlems ID för den som ska låna bok: ");
        System.out.print("ID: ");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();

        user = mManager.searchMember(id);
        //user = mStore.getMemberById(id);
        if (user.getIDCode() == id){
            bManager.setMember(user);
            System.out.println("Ange ISBN för bok");
            System.out.print("ISBN: ");
            long isbn = input.nextLong();

            System.out.println("");

            try {
                book = bManager.loan(isbn,user.getIDCode());
                System.out.println("Du har nu lånat " + book.getTitle());

            }
            catch (Exception inteBra){
                System.out.println(inteBra.getMessage());

                //System.out.println("Ingen bok med ISBN finns");
            }
            //System.out.println("Inga lediga böcker att låna ut");


        }
        else {
            librarianOption(libMenu());
        }

    }

    void addMember() {
        int id;
        int ssn;
        String fName;
        String lName;
        int title;
        Scanner input = new Scanner(System.in);
        System.out.println("Lägg till medlem");
        System.out.print("Ange id: ");
        id = input.nextInt();
        System.out.print("Ange personnummer: ");
        ssn = input.nextInt();
        System.out.print("Ange förnamn: ");
        fName = input.next();
        System.out.print("Ange efternamn: ");
        lName = input.next();
        System.out.print("Ange titel: ");
        title = input.nextInt();

        mManager.addUser(id, ssn,  fName,  lName,  title);
    }
    void returnBook(){
        Scanner input = new Scanner(System.in);
        System.out.println("Lämna tillbaka bok");
        System.out.print("Ange meldems id: ");
        int id = input.nextInt();
        user = mManager.searchMember(id);
        bManager.setMember(user);
        System.out.print("Ange isbn: ");
        long isbn = input.nextLong();

        try {
            book = bManager.returnBook(isbn);
        }
        catch (Exception error){
            System.out.println(error.getMessage());
        }

    }

    void removeMember() {
        Scanner input = new Scanner(System.in);
        System.out.println("Ta bort användare");
        System.out.print("Ange id: ");
        int id = input.nextInt();
        user = mManager.searchMember(id);
        mManager.removeMember(user);
        bManager.setMember(user);

    }
    void suspendMember(){

    }
}
