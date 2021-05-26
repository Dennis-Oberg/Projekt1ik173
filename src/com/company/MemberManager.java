package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;

public class MemberManager {
    private static final Logger logger = LogManager.getLogger(MemberManager.class.getName());

    User user = null;
    BookManager bookManager;
    MemberStore mStore;

    public MemberManager(User user, MemberStore memberStore) {
        this.user = user;
        this.mStore = memberStore;
    }

    public boolean getMemberStatus(User user) {
        boolean b1 = checkActiveSuspension(user);
        boolean b2 = checkSuspension(user);
        boolean b3 = checkBan(user, new BookStore());
        return b1 && b2 && b3;
    }

    public boolean checkActiveSuspension(User user) {

        long millis = System.currentTimeMillis();
        Date currentDate = new Date(millis);

        if (user.suspensionDate != null) {
            if (currentDate.after(user.suspensionDate)) {
                mStore.removeSuspension(user);
                user.suspended = false;
            }
        }

        return !user.suspended;

    }

    public boolean checkSuspension(User user) {
        if (user.getStrikes() == 3) {
            mStore.addSuspension(user);
            user.suspended = true;
            return false;
        }
        return true;
    }

    public boolean checkBan(User user, BookStore bookStore) {


        if (user.getSuspendedCount() == 3) {

            for (Book b: bookStore.getBookByMember(user.getIDCode())
            ) {
                bookStore.returnBook(b, user);

            }

            mStore.moveToBannedMember(user);
            mStore.removeMember(user);
            return false;
        }
        return true;
    }

    public void removeMember(User user, BookStore bookStore) {
        //Måste kolla om man har böcker lånade innan man kan ta bort. Om inte blir det fel i databas
        bookManager = new BookManager(user, bookStore);
        int numberOfBooks = bookManager.numberOfBorrowedBooks();

        if (numberOfBooks > 0) {
            System.out.println("Behöver lämna tillbaks följande böcker innan medlem kan tas bort:");
            Book[] books = bookManager.getMemberLoans();
            for (Book book : books) {
                System.out.println(book.getTitle() + " (ISBN: " + book.getIsbn() + ")");

            }
        } else {
            mStore.removeMember(user);
            logger.info("Användare " + user.firstName + " borttagen");
        }
    }

    public User searchMember(int id) {
        user = mStore.getMemberById(id);
        return user;
    }

    public void addUser(int ssn, String fName, String lName, int title) {

        //Ska senare kolla om man varit medlem tidigare eller är bannad
        mStore.creatNewMember(ssn, fName, lName, title);
        logger.info("Användare " + fName + " " + lName + " " + title + " tillagd");
    }

    public void addStrike() {
        mStore.addStrike(this.user);
    }

    public boolean checkSsn(int ssn) {

        Integer tempSsn = ssn;

        Integer[] bannandeMedlemar = mStore.checkSsn(ssn);

        if (bannandeMedlemar.length == 0){
            return true;
        }
        else {
            for (Integer integer: bannandeMedlemar){
                if (tempSsn.equals(integer)){
                    return false;
                }
            }
            return true;
        }
    }
}