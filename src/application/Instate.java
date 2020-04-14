package application;
/**
 * This subclass defines an in-state student that has a first name, last name,
 * number of enrolled credit hours and amount of funds. Functionality exists for
 * returning the String representation of an in-state student and calculating
 * the tuition due according to the in-state student requirements. This class
 * also contains a main method for testing.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public class Instate extends Student
{
    private int funds;
    public static final int INSTATE_CREDIT_COST = 433;

    /**
     * This constructor creates an in-state student using the supplied first name,
     * last name, credit hours and funds amount. The first name, last name and
     * credit hours parameters are supplied to the constructor of the parent class
     * Student.
     * 
     * @param fname  first name of student
     * @param lname  last name of student
     * @param credit number of credit hours the student is enrolled in
     * @param funds  amount of money that will be deducted from the tuition due
     */
    public Instate(String fname, String lname, int credit, int funds)
    {
        super(fname, lname, credit);
        this.funds = funds;
    }

    /**
     * This method calculates the tuition owed by an in-state student. It uses the
     * constant INSTATE_CREDIT_COST for the price of each credit and follows the fee
     * requirements for part time and full time status. If the student is full time,
     * any funds he or she has is subtracted from the tuition cost.
     * 
     * @return integer amount of the tuition the in-state student owes
     */
    public int tuitionDue()
    {
        if (this.credit >= Student.MAX_CREDIT_HOURS)
        {
            return (INSTATE_CREDIT_COST * Student.MAX_CREDIT_HOURS) + Student.FULL_TIME_FEE - funds;
        }
        else
        {
            if (this.credit < Student.FULL_TIME_CREDITS)
            {
                return (INSTATE_CREDIT_COST * this.credit) + Student.PART_TIME_FEE;
            }
            else
            {
                return (INSTATE_CREDIT_COST * this.credit) + Student.FULL_TIME_FEE - funds;
            }
        }
    }

    /**
     * This method returns the String representation of an in-state student object
     * and overrides the toString() method by provided by the parent class Student.
     * It appends Strings containing information that denotes the student's in-state
     * status and amount of funds to the String that is returned by the parent class
     * Student.
     * 
     * @return String representation of an in-state student object
     */
    public String toString()
    {
        return super.toString() + "  Student Type: In-state  Funds: $" + Integer.toString(funds);
    }

    /**
     * Main method used for testing this subclass. Test cases are documented in the
     * design document.
     * 
     * @param args default input for main method
     */
    public static void main(String[] args)
    {
        Instate test1 = new Instate("Stephen", "Prospero", 17, 1000);
        System.out.println("Test Case 1: " + test1.toString());

        Instate test2 = new Instate("Brett", "Conetta", 19, 2000);
        System.out.println("Test Case 2: " + test2.tuitionDue());

        Instate test3 = new Instate("Brad", "Pitt", 15, 0);
        System.out.println("Test Case 3: " + test3.tuitionDue());

        Instate test4 = new Instate("George", "Clooney", 11, 0);
        System.out.println("Test Case 4: " + test4.tuitionDue());

        Instate test5 = new Instate("George", "Clooney", 11, 1000);
        System.out.println("Test Case 5: " + test5.tuitionDue());

        Instate test6 = new Instate("Lester", "Holt", 14, 1000);
        System.out.println("Test Case 6: " + test6.tuitionDue());

        Instate test7 = new Instate("Scarlet", "Knight", 14, 0);
        System.out.println("Test Case 7: " + test7.tuitionDue());

        Instate test8 = new Instate("Scarlet", "Knight", 14, 0);
        System.out.println("Test Case 8: " + test8.compareTo(test7));

        Instate test9 = new Instate("Scarlet", "Chameleon", 14, 0);
        System.out.println("Test Case 9: " + test9.compareTo(test8));

        Instate test10 = new Instate("Scarlet", "Raptor", 14, 0);
        System.out.println("Test Case 10: " + test10.compareTo(test8));
    }
}
