/**
 * Problem 3 - CS603
 * Created by Jialu (Max) Xu
 * on 9/25/2016.
 */

/* This program can be used to calculate college costs for a two- or four-year public (in-state or
 * out-of-state) college. It can also be used to calculate the merit aid based on a student's GPA
 * and SAT score.
 */

import java.util.Scanner;

public class CollegeCost {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        final int TWOYEARTUITION     = 3440;            // two-year tuition
        final int FOURYEARINTUITION  = 9410;            // four-year in-state tuition
        final int FOURYEAROUTTUITION = 23890;           // four-year out-of-state tuition
        final int TWOYEARROOM        = 7050;            // two-year room and board fee
        final int FOURYEARINROOM     = 10174;           // four-year in-state room and board fee
        final int FOURYEAROUTROOM    = 10500;           // four-year out-of-state room and board fee
        int       stateStatus        = 1;               // initialize state status
        int       tuition            = 0;               // initialize tuition
        int       roomFee            = 0;               // initialize  room and board fee
        double    meritAid           = 0;               // initialize merit aid
        boolean   valid              = true;            // boolean for user input validation
        int       collegeDuration;
        int       feeType;
        int       gpa;
        int       satScore;
        int       meritAidonGPA;                        // merit aid based on GPA
        int       meritAidonSAT;                        // merit aid based on SAT score
        double    totalCost;
        double    netCost;

        /* Though I find that using 'while(true) {... break;...}" loops to validate user inputs would 
         * result in less code, I am not sure if it is good practice. I end up using do-while loops.
         * The logic in the loops is quite simple: if the user input is invalid, keep prompting.
         * Otherwise the valid boolean turns true and exits the current loop.
         */
        do {
            System.out.println("Enter 2 for two-year program or 4 for a four-year program: ");
            collegeDuration = kb.nextInt();
            if (collegeDuration != 2 && collegeDuration != 4) {
                System.out.println("Invalid number of years.");
                valid = false;
            } else {
                valid = true;
            }
        } while (!valid);

        // for a four-year college type, prompt for the state status
        if (collegeDuration == 4) {
            do {
                System.out.println("Enter 1 for in-state or 2 for out-of-state: ");
                stateStatus = kb.nextInt();
                if (stateStatus != 1 && stateStatus != 2) {
                    System.out.println("Invalid value for state status.");
                    valid = false;
                } else {
                    valid = true;
                }
            } while (!valid);
        }

        do {
            System.out.println("Enter 1 for tuition and fees only or 2 for full cost: ");
            feeType = kb.nextInt();
            if (feeType != 1 && feeType != 2) {
                System.out.println("Invalid value for type of tuition.");
                valid = false;
            } else {
                valid = true;
            }
        } while (!valid);

        // prompt different GPA ranges based on college duration type
        if (collegeDuration == 4) {
           do {
               System.out.println("Enter a weighted GPA between 2 and 20: ");
               gpa = kb.nextInt();
               if (gpa < 2 || gpa > 20){
                   System.out.println("Invalid GPA");
                   valid = false;
               } else {
                   valid = true;
               }
           } while (!valid);
        } else {
            do {
                System.out.println("Enter a weighted GPA between 1 and 10: ");
                gpa = kb.nextInt();
                if (gpa < 1 || gpa > 10){
                    System.out.println("Invalid GPA");
                    valid = false;
                } else {
                    valid = true;
                }
            } while (!valid);
        }

        do {
            System.out.println("Enter a combined SAT score between 400 and 1600: ");
            satScore = kb.nextInt();
            if (satScore < 400 || satScore > 1600){
                System.out.println("Invalid SAT score.");
                valid = false;
            } else {
                valid = true;
            }
        } while (!valid);

        /*
         * Instead of worrying about which minimum criteria the student meets, I find it much easier to
         * simply calculate the merit aid based on GPA and SAT score respectively, then choose the lower.
         * According to the table there is a certain linear relationship between the GPA/SAT and the merit aid.
         */
        if (collegeDuration == 2) {
            tuition = TWOYEARTUITION;
            roomFee = TWOYEARROOM;
            meritAidonGPA = (gpa - 6) * 500 + 600;
            meritAidonSAT = ((satScore - 1350) / 50) * 500 + 600;
            meritAid = (meritAidonGPA < meritAidonSAT) ? meritAidonGPA : meritAidonSAT;
            if (meritAid > 2100) {
                // If the merit aid calculated turns out bigger than 2100, set it to 2100;
                meritAid = 2100;
            } else if (meritAid < 600) {
                // Similarly, if the result is less than the smallest merit aid offered, set it to 0;
                meritAid = 0;
            }
        } else if (stateStatus == 1) {
            tuition = FOURYEARINTUITION;
            roomFee = FOURYEARINROOM;
            meritAidonGPA = (gpa / 3) * 1000 - 800;
            meritAidonSAT = ((satScore - 1435) / 35) * 1000 + 2200;
            meritAid = (meritAidonGPA < meritAidonSAT) ? meritAidonGPA : meritAidonSAT;
            // In this case there's no need to put a top limit because the result is not possible to be
            // bigger than 5200.
            if (meritAid < 2200) {
                meritAid = 0;
            }
        } else {                        
            tuition = FOURYEAROUTTUITION;
            roomFee = FOURYEAROUTROOM;
            meritAidonGPA = ((gpa - 10) / 4) * 1200 + 6400;
            meritAidonSAT = ((satScore - 1450) / 50) * 1200 + 6400;
            meritAid = (meritAidonGPA < meritAidonSAT) ? meritAidonGPA : meritAidonSAT;
            // It's not necessary to put a top limit here either because the result is not possible to be
            // bigger than 8800.
            if (meritAid < 6400) {
                meritAid = 0;
            }
        }

        // Determine what cost type the user wants.
        if (feeType == 1) {
            totalCost = tuition;
        } else {
            totalCost = tuition + roomFee;
        }
        netCost = totalCost - meritAid;

        System.out.printf("Cost of 1 year of college: $%,.2f %n", totalCost);
        System.out.printf("Amount of merit aid: $%,.2f %n", meritAid);
        System.out.printf("Net cost: $%,.2f %n", netCost);

        kb.close();
    }

}