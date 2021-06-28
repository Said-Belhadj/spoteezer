package musichub.util;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class offering methods to prompt a policy agreement form to the user.
 *
 * @author Anthony BOULANT
 * @version 1.0
 */

public final class Policy {

    /**
     * Method that just throws an AssertionError if the class is called
     *
     * @throws AssertionError you can't instantiate this class
     * @author Anthony BOULANT
     */

    public Policy() {
        throw new AssertionError("You just can't instantiate this class.");
    }

    /**
     * Method prompting the whole policy agreement process
     *
     * @author Anthony BOULANT
     */
    public static void showTerm() {

        String termsAndConditions = """
                Terms and Conditions
                In using this website you are deemed to have read and agreed to the following terms and conditions:
                Privacy Statement
                We are committed to protecting your privacy. Authorized employees within the company on a need to know basis only use any information collected from individual customers. We constantly review our systems and data to ensure the best possible service to our customers. Parliament has created specific offences for unauthorised actions against computer systems and data. We will investigate any such actions with a view to prosecuting and/or taking civil proceedings to recover damages against those responsible
                These terms and conditions form part of the Agreement between the Client and ourselves. Your accessing of this website and/or undertaking of a booking or Agreement indicates your understanding, agreement to and acceptance, of the Disclaimer Notice and the full Terms and Conditions contained herein. Your statutory Consumer Rights are unaffected.   s
                © Company Name 2021 All Rights Reserved
                """;

        printAgreement();
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine().toLowerCase();

        while (!choice.equals("y")) {
            switch (choice.charAt(0)) {
                case 'n' -> System.exit(0);
                case 'r' -> {
                    System.out.println(termsAndConditions);
                    printAgreement();
                    choice = scan.nextLine().toLowerCase();
                }
                default -> {
                    printAgreement();
                    choice = scan.nextLine().toLowerCase();
                }
            }
        }
        try {
            LogHandler.write("Terms accepted by the user.", "INFO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method printing the commands the user can use.
     *
     * @author Anthony BOULANT
     */
    private static void printAgreement() {
        System.out.println("By using our app, do you accept our Terms and Conditions ?");
        System.out.println("[YES : y    NO :  n    READ : r]");
    }
}
