package application;
import java.util.Scanner;
/**
 * Handles a StudentList and takes input from the user to determine whether to
 * add, remove, or print all of the Students in the StudentList. The user can
 * input "I [fname] [lname] [credits] [aidReceived]" for an Instate Student, "O
 * [fname] [lname] [credits] [inTriState]" for an Outstate Student, "N [fname]
 * [lname] [credits] [isExchangeStudent]" for an International Student, "R
 * [fname] [lname]" to remove a student, "P" to print all students, or "Q" to
 * quit.
 * 
 * @author Brett Conetta, Stephen Prospero
 */
public class TuitionManager
{
    private Scanner stdin;
    private StudentList enrolledStudents;

    /**
     * Gets called to wait for user input and call appropriate methods to perform
     * tasks for the user regarding a StudentList (add, remove, print).
     */
    public void run()
    {
        System.out.println("Enroll some students!");
        enrolledStudents = new StudentList();
        stdin = new Scanner(System.in);
        boolean done = false;
        while (!done)
        {
            String command = stdin.next();
            char charCommand = command.charAt(0);

            if (command.length() != 1)
            {
                charCommand = '1';
            }

            switch (charCommand)
            {

            case 'I':
                addInState();
                break;
            case 'O':
                addOutOfState();
                break;
            case 'N':
                addInternational();
                break;
            case 'R':
                remove();
                break;
            case 'P':
                print();
                break;
            case 'Q':
                done = true;
                break;
            default:
                System.out.println("Command '" + command + "' not supported!");
                stdin.nextLine();
                break;
            }
        }
        System.out.println("Program terminated.");
        return;
    }

    /**
     * Takes in the correct input consisting of a firstName, lastName,
     * numberOfCredits, and aid to create an Instate object. Ensures that the
     * student is taking at least one credit and is not already enrolled. Calls
     * StudentList add(Student s) to add the Instate student to a Student array
     * (StudentList object).
     */
    private void addInState()
    {
        String firstName = stdin.next();
        String lastName = stdin.next();
        int numberOfCredits = stdin.nextInt();
        int aid = stdin.nextInt();

        if (numberOfCredits < 1)
        {
            System.out.println("Error. Insufficient number of credits. Must have at least 1 credit.");
            return;
        }

        if (aid < 0)
        {
            System.out.println("Error. Aid received cannot be less than $0.");
            return;
        }

        Student studentToAdd = new Instate(firstName, lastName, numberOfCredits, aid);

        if (enrolledStudents.contains(studentToAdd))
        {
            System.out.println(firstName + " " + lastName + " is already enrolled.");
            return;
        }
        enrolledStudents.add(studentToAdd);

        return;
    }

    /**
     * Takes in the correct input consisting of a firstName, lastName,
     * numberOfCredits, and inTriState indicator to create an Outstate object.
     * Ensures that the student is taking at least one credit and is not already
     * enrolled. Calls StudentList add(Student s) to add the Outstate student to a
     * Student array (StudentList object).
     */
    private void addOutOfState()
    {
        String firstName = stdin.next();
        String lastName = stdin.next();
        int numberOfCredits = stdin.nextInt();
        char inTriState = stdin.next().charAt(0);

        if (numberOfCredits < 1)
        {
            System.out.println("Error. Insufficient number of credits. Must have at least 1 credit.");
            return;
        }

        Student studentToAdd;
        if (inTriState == 'T')
        {
            studentToAdd = new Outstate(firstName, lastName, numberOfCredits, true);
        }
        else if (inTriState == 'F')
        {
            studentToAdd = new Outstate(firstName, lastName, numberOfCredits, false);
        }
        else
        {
            System.out.println("Error. Invalid character. Expected 'T' or 'F'.");
            return;
        }

        if (enrolledStudents.contains(studentToAdd))
        {
            System.out.println(firstName + " " + lastName + " is already enrolled.");
            return;
        }
        enrolledStudents.add(studentToAdd);

        return;
    }

    /**
     * Takes in the correct input consisting of a firstName, lastName,
     * numberOfCredits, and isExchangeStudent indicator to create an International
     * object. Ensures that the student is taking at least nine credit and is not
     * already enrolled. Calls StudentList add(Student s) to add the International
     * student to a Student array (StudentList object).
     */
    private void addInternational()
    {
        String firstName = stdin.next();
        String lastName = stdin.next();
        int numberOfCredits = stdin.nextInt();
        char isExchangeStudent = stdin.next().charAt(0);

        if (numberOfCredits < International.INTERNATIONAL_MINIMUM_CREDITS)
        {
            System.out.println("Error. International students cannot take less than 9 credits.");
            return;
        }

        Student studentToAdd;
        if (isExchangeStudent == 'T')
        {
            studentToAdd = new International(firstName, lastName, numberOfCredits, true);
        }
        else if (isExchangeStudent == 'F')
        {
            studentToAdd = new International(firstName, lastName, numberOfCredits, false);
        }
        else
        {
            System.out.println("Error. Invalid character. Expected 'T' or 'F'.");
            return;
        }

        if (enrolledStudents.contains(studentToAdd))
        {
            System.out.println(firstName + " " + lastName + " is already enrolled.");
            return;
        }
        enrolledStudents.add(studentToAdd);

        return;
    }

    /**
     * Takes in the correct input consisting of a firstName and lastName to create a
     * dummy Student object to remove the student with the same name. Calls
     * StudentList remove(Student m) to search for the Student with the same
     * firstName and lastName as the dummy Student object.
     */
    private void remove()
    {
        String firstName = stdin.next();
        String lastName = stdin.next();

        Student studentToRemove = new Instate(firstName, lastName, 0, 0);

        if (enrolledStudents.remove(studentToRemove))
        {
            System.out.println(firstName + " " + lastName + " has dropped out.");
            return;
        }

        System.out.println(firstName + " " + lastName + " is not enrolled.");
        return;
    }

    /**
     * Checks if the StudentList is empty and prints a message if it is, otherwise
     * calls StudentList print().
     */
    private void print()
    {
        if (enrolledStudents.isEmpty())
        {
            System.out.println("There are no students enrolled at this time.");
            return;
        }

        enrolledStudents.print();
        return;
    }

    /**
     * Creates a new TuitionManager object to call the run() method.
     * 
     * @param args default parameter for the main method.
     */
    public static void main(String[] args)
    {
        new TuitionManager().run();

        return;
    }
}
