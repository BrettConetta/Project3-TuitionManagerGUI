package application;
/**
 * This subclass defines an out-of-state student that has a first name, last
 * name, number of enrolled credit hours and boolean status that indicates if he
 * or she lives in the tri-state area. Functionality exists for returning the
 * String representation of an out-of-state student and calculating the tuition
 * due according to the out-of-state student requirements. This class also
 * contains a main method for testing.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public class Outstate extends Student
{
    private boolean tristate;
    public static final int OUTSTATE_CREDIT_COST = 756;
    public static final int DISCOUNT_OUTSTATE_CREDIT_COST = OUTSTATE_CREDIT_COST - 200;

    /**
     * This constructor creates an out-of-state student using the supplied first
     * name, last name, credit hours and tri-state boolean. The first name, last
     * name and credit hours parameters are supplied to the constructor of the
     * parent class Student.
     * 
     * @param fname    first name of student
     * @param lname    last name of student
     * @param credit   number of credit hours the student is enrolled in
     * @param tristate boolean denoting whether the student lives in the tri-state
     *                 or not
     */
    public Outstate(String fname, String lname, int credit, boolean tristate)
    {
        super(fname, lname, credit);
        this.tristate = tristate;
    }

    /**
     * This method calculates the tuition owed by an out-of-state student. It uses
     * the constants OUTSTATE_CREDIT_COST and DISCOUNT_OUTSTATE_CREDIT_COST for the
     * price of each credit and follows the fee requirements for part time and full
     * time status. If the student lives in the tri-state area, then the cost of
     * each credit is DISCOUNT_OUTSTATE_CREDIT_COST. Otherwise, the cost is
     * OUTSTATE_CREDIT_COST.
     * 
     * @return integer amount of the tuition the out-of-state student owes
     */
    public int tuitionDue()
    {
        if (this.credit >= Student.MAX_CREDIT_HOURS)
        {
            if (tristate == true)
            {
                return (DISCOUNT_OUTSTATE_CREDIT_COST * Student.MAX_CREDIT_HOURS) + Student.FULL_TIME_FEE;
            }
            else
            {
                return (OUTSTATE_CREDIT_COST * Student.MAX_CREDIT_HOURS) + Student.FULL_TIME_FEE;
            }
        }
        else
        {
            if (this.credit < Student.FULL_TIME_CREDITS)
            {
                return (OUTSTATE_CREDIT_COST * this.credit) + Student.PART_TIME_FEE;
            }
            else
            {
                if (tristate == true)
                {
                    return (DISCOUNT_OUTSTATE_CREDIT_COST * this.credit) + Student.FULL_TIME_FEE;
                }
                else
                {
                    return (OUTSTATE_CREDIT_COST * this.credit) + Student.FULL_TIME_FEE;
                }
            }
        }
    }

    /**
     * This method returns the String representation of an out-of-state student
     * object and overrides the toString() method by provided by the parent class
     * Student. It appends Strings containing information that denotes the student's
     * out-of-state and tri-state statuses to the String that is returned by the
     * parent class Student.
     * 
     * @return String representation of an out-of-state student object
     */
    public String toString()
    {
        String tristateString = "";
        if (tristate == true)
        {
            tristateString = "Yes";
        }
        else
        {
            tristateString = "No";
        }

        return super.toString() + "  Student Type: Out-of-state  From the Tri-state?: " + tristateString;
    }

    /**
     * Main method used for testing this subclass. Test cases are documented in the
     * design document.
     * 
     * @param args default input for main method
     */
    public static void main(String[] args)
    {
        Outstate test1 = new Outstate("Stephen", "Prospero", 17, true);
        System.out.println("Test Case 1: " + test1.toString());

        Outstate test2 = new Outstate("Stephen", "Prospero", 17, false);
        System.out.println("Test Case 2: " + test2.toString());

        Outstate test3 = new Outstate("Brett", "Conetta", 19, true);
        System.out.println("Test Case 3: " + test3.tuitionDue());

        Outstate test4 = new Outstate("Brad", "Pitt", 15, false);
        System.out.println("Test Case 4: " + test4.tuitionDue());

        Outstate test5 = new Outstate("George", "Clooney", 11, false);
        System.out.println("Test Case 5: " + test5.tuitionDue());

        Outstate test6 = new Outstate("George", "Clooney", 11, true);
        System.out.println("Test Case 6: " + test6.tuitionDue());

        Outstate test7 = new Outstate("Lester", "Holt", 14, true);
        System.out.println("Test Case 7: " + test7.tuitionDue());

        Outstate test8 = new Outstate("Scarlet", "Knight", 14, false);
        System.out.println("Test Case 8: " + test8.tuitionDue());

        Outstate test9 = new Outstate("Scarlet", "Knight", 14, false);
        System.out.println("Test Case 9: " + test9.compareTo(test8));

        Outstate test10 = new Outstate("Scarlet", "Chameleon", 14, false);
        System.out.println("Test Case 10: " + test10.compareTo(test9));

        Outstate test11 = new Outstate("Scarlet", "Raptor", 14, false);
        System.out.println("Test Case 11: " + test11.compareTo(test8));
    }
}
