package com.company;
import java.util.Scanner;

public class Main  {

    public static void main(String[] args) {

        boolean end = false;
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Välkommen till biblioteket!");
        System.out.println("-------------------------\n");
        System.out.println("1 - Logga in");
        System.out.println("2 - Registrera");

        selection = input.nextInt();


            switch (selection) {
                case 1:
                    System.out.println("Ange ID");
                    int id = input.nextInt();
                    System.out.println("Ange lösenord");
                    String password = input.next();

                    LoginService loginService = new LoginService();

                    if (loginService.verifyLogin(id, password)) {
                        System.out.println("inloggad som: " + loginService.getName());
                    }

                    break;

                case 2:
        }
    }
}
