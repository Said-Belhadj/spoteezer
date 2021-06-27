package musichub.util;

import java.util.Scanner;

/**
 * Class ...
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

    public static void showTerm() {

        String termsAndConditions = "Terms and Conditions\n" +
                "\n" +
                "\n" +
                "In using this website you are deemed to have read and agreed to the following terms and conditions:\n" +
                "\n" +
                "\n" +
                "The following terminology applies to these Terms and Conditions, Privacy Statement and Disclaimer Notice and any or all Agreements: \"Client\", “You” and “Your” refers to you, the person accessing this website and accepting the Company’s terms and conditions. \"The Company\", “Ourselves”, “We” and \"Us\", refers to our Company. “Party”, “Parties”, or “Us”, refers to both the Client and ourselves, or either the Client or ourselves. All terms refer to the offer, acceptance and consideration of payment necessary to undertake the process of our assistance to the Client in the most appropriate manner, whether by formal meetings of a fixed duration, or any other means, for the express purpose of meeting the Client’s needs in respect of provision of the Company’s stated services/products, in accordance with and subject to, prevailing English Law. Any use of the above terminology or other words in the singular, plural, capitalisation and/or he/she or they, are taken as interchangeable and therefore as referring to same.\n" +
                "\n" +
                "\n" +
                "Privacy Statement\n" +
                "We are committed to protecting your privacy. Authorized employees within the company on a need to know basis only use any information collected from individual customers. We constantly review our systems and data to ensure the best possible service to our customers. Parliament has created specific offences for unauthorised actions against computer systems and data. We will investigate any such actions with a view to prosecuting and/or taking civil proceedings to recover damages against those responsible\n" +
                "\n" +
                "\n" +
                "Confidentiality\n" +
                "We are registered under the Data Protection Act 1998 and as such, any information concerning the Client and their respective Client Records may be passed to third parties. However, Client records are regarded as confidential and therefore will not be divulged to any third party, other than if legally required to do so to the appropriate authorities. Clients have the right to request sight of, and copies of any and all Client Records we keep, on the proviso that we are given reasonable notice of such a request. Clients are requested to retain copies of any literature issued in relation to the provision of our services. Where appropriate, we shall issue Client’s with appropriate written information, handouts or copies of records as part of an agreed contract, for the benefit of both parties.\n" +
                "\n" +
                "We will not sell, share, or rent your personal information to any third party or use your e-mail address for unsolicited mail. Any emails sent by this Company will only be in connection with the provision of agreed services and products.\n" +
                "\n" +
                "\n" +
                "Disclaimer\n" +
                "Exclusions and Limitations \n" +
                "The information on this web site is provided on an \"as is\" basis. To the fullest extent permitted by law, this Company:\n" +
                "\uF0A7\texcludes all representations and warranties relating to this website and its contents or which is or may be provided by any affiliates or any other third party, including in relation to any inaccuracies or omissions in this website and/or the Company’s literature; and \n" +
                "\uF0A7\texcludes all liability for damages arising out of or in connection with your use of this website. This includes, without limitation, direct loss, loss of business or profits (whether or not the loss of such profits was foreseeable, arose in the normal course of things or you have advised this Company of the possibility of such potential loss), damage caused to your computer, computer software, systems and programs and the data thereon or any other direct or indirect, consequential and incidental damages. \n" +
                "This Company does not however exclude liability for death or personal injury caused by its negligence. The above exclusions and limitations apply only to the extent permitted by law. None of your statutory rights as a consumer are affected. \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Cancellation Policy\n" +
                "Minimum 24 hours’ notice of cancellation required. Notification for instance, in person, via email, mobile phone ‘text message’ and/or fax, or any other means will be accepted subject to confirmation in writing. We reserve the right to levy a £30 charge to cover any subsequent administrative expenses.\n" +
                "\n" +
                "\n" +
                "Termination of Agreements and Refunds Policy\n" +
                "Both the Client and we have the right to terminate any Services Agreement for any reason, including the ending of services that are already underway. No refunds shall be offered, where a Service is deemed to have begun and is, for all intents and purposes, underway. Any monies that have been paid to us which constitute payment in respect of the provision of unused Services, shall be refunded.\n" +
                "\n" +
                "\n" +
                "Availability \n" +
                "Unless otherwise stated, the services featured on this website are only available in France, or in relation to postings from France. All advertising is intended solely for the France market. You are solely responsible for evaluating the fitness for a particular purpose of any downloads, programs and text available through this site. Redistribution or republication of any part of this site or its content is prohibited, including such by framing or other similar or any other means, without the express written consent of the Company. The Company does not warrant that the service from this site will be uninterrupted, timely or error free, although it is provided to the best ability. By using this service you thereby indemnify this Company, its employees, agents and affiliates against any loss or damage, in whatever manner, howsoever caused.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Copyright Notice \n" +
                "Copyright and other relevant intellectual property rights exists on all text relating to the Company’s services and the full content of this application. \n" +
                "\n" +
                "\n" +
                "Communication\n" +
                "We have several different e-mail addresses for different queries. These, & other contact information, can be found on our Contact Us link on our website or via Company literature or via the Company’s stated telephone, facsimile or mobile telephone numbers.\n" +
                "\n" +
                "\n" +
                "Force Majeure\n" +
                "Neither party shall be liable to the other for any failure to perform any obligation under any Agreement which is due to an event beyond the control of such party including but not limited to any Act of God, terrorism, war, Political insurgence, insurrection, riot, civil unrest, act of civil or military authority, uprising, earthquake, flood or any other natural or manmade eventuality outside of our control, which causes the termination of an agreement or contract entered into, nor which could have been reasonably foreseen. Any Party affected by such event shall forthwith inform the other Party of the same and shall use all reasonable endeavours to comply with the terms and conditions of any Agreement contained herein.\n" +
                "\n" +
                "\n" +
                "Waiver\n" +
                "Failure of either Party to insist upon strict performance of any provision of this or any Agreement or the failure of either Party to exercise any right or remedy to which it, he or they are entitled hereunder shall not constitute a waiver thereof and shall not cause a diminution of the obligations under this or any Agreement. No waiver of any of the provisions of this or any Agreement shall be effective unless it is expressly stated to be such and signed by both Parties.\n" +
                "\n" +
                "\n" +
                "General\n" +
                "The laws of France govern these terms and conditions. By accessing this application, you consent to these terms and conditions and to the exclusive jurisdiction of the English courts in all disputes arising out of such access. If any of these terms are deemed invalid or unenforceable for any reason (including, but not limited to the exclusions and limitations set out above), then the invalid or unenforceable provision will be severed from these terms and the remaining terms will continue to apply. Failure of the Company to enforce any of the provisions set out in these Terms and Conditions and any Agreement, or failure to exercise any option to terminate, shall not be construed as waiver of such provisions and shall not affect the validity of these Terms and Conditions or of any Agreement or any part thereof, or the right thereafter to enforce each and every provision. These Terms and Conditions shall not be amended, modified, varied or supplemented except in writing and signed by duly authorised representatives of the Company.\n" +
                "\n" +
                "\n" +
                "Notification of Changes\n" +
                "The Company reserves the right to change these conditions from time to time as it sees fit and your continued use of the site will signify your acceptance of any adjustment to these terms. If there are any changes to our privacy policy, we will announce that these changes have been made on our home page and on other key pages on our site. If there are any changes in how we use our site customers’ Personally Identifiable Information, notification by e-mail or postal mail will be made to those affected by this change. Any changes to our privacy policy will be posted on our web site 30 days prior to these changes taking place. You are therefore advised to re-read this statement on a regular basis\n" +
                "\n" +
                "\n" +
                "These terms and conditions form part of the Agreement between the Client and ourselves. Your accessing of this website and/or undertaking of a booking or Agreement indicates your understanding, agreement to and acceptance, of the Disclaimer Notice and the full Terms and Conditions contained herein. Your statutory Consumer Rights are unaffected.    \n" +
                "© Company Name 2021 All Rights Reserved\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n";

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
    }

    private static void printAgreement() {
        System.out.println("By using our app, do you accept our Terms and Conditions ?");
        System.out.println("[YES : y    NO :  n    READ : r]");
    }
}
