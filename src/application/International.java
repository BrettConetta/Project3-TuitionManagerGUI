package application;
/**
 * This subclass defines an international student that has a first name, last
 * name, number of enrolled credit hours and boolean status that indicates if he
 * or she is an exchange student. Functionality exists for returning the String
 * representation of an international student and calculating the tuition due
 * according to the international student requirements. This class also contains
 * a main method for testing.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public class International extends Student
{
    private boolean exchange;
    public static final int INTERNATIONAL_CREDIT_COST = 945;
    public static final int INTERNATIONAL_STUDENT_FEE = 350;
    public static final int INTERNATIONAL_MINIMUM_CREDITS = 9;

    /**
     * This constructor creates an international student using the supplied first
     * name, last name, credit hours and exchange boolean. The first name, last name
     * and credit hours parameters are supplied to the constructor of the parent
     * class Student.
     * 
     * @param fname    first name of student
     * @param lname    last name of student
     * @param credit   number of credit hours the student is enrolled in
     * @param exchange boolean denoting whether the student is in the exchange
     *                 program or not
     */
    public International(String fname, String lname, int credit, boolean exchange)
    {
        super(fname, lname, credit);
        this.exchange = exchange;
    }

    /**
     * This method calculates the tuition owed by an international student. It uses
     * the constant INTERNATIONAL_CREDIT_COST for the price of each credit and
     * follows the fee requirements for part time and full time status. All
     * international students are also charged the INTERNATIONAL_STUDENT_FEE. If the
     * student is part of the exchange program, then he or she only pays the
     * FULL_TIME_FEE and the INTERNATIONAL_STUDENT_FEE.
     * 
     * @return integer amount of the tuition the international student owes
     */
    public int tuitionDue()
    {
        if (exchange == true)
        {
            return Student.FULL_TIME_FEE + INTERNATIONAL_STUDENT_FEE;
        }
        else
        {
            if (this.credit >= Student.MAX_CREDIT_HOURS)
            {
                return (INTERNATIONAL_CREDIT_COST * Student.MAX_CREDIT_HOURS) + Student.FULL_TIME_FEE
                        + INTERNATIONAL_STUDENT_FEE;
            }
            else
            {
                if (this.credit < Student.FULL_TIME_CREDITS)
                {
                    return (INTERNATIONAL_CREDIT_COST * this.credit) + Student.PART_TIME_FEE
                            + INTERNATIONAL_STUDENT_FEE;
                }
                else
                {
                    return (INTERNATIONAL_CREDIT_COST * this.credit) + Student.FULL_TIME_FEE
                            + INTERNATIONAL_STUDENT_FEE;
                }
            }
        }
    }

    /**
     * This method returns the String representation of an international student
     * object and overrides the toString() method by provided by the parent class
     * Student. It appends Strings containing information that denotes the student's
     * international and exchange statuses to the String that is returned by the
     * parent class Student.
     * 
     * @return String representation of an international student object
     */
    public String toString()
    {
        String exchangeString = "";
        if (exchange == true)
        {
            exchangeString = "Yes";
        }
        else
        {
            exchangeString = "No";
        }

        return super.toString() + "  Student Type: International  Exchange Student?: " + exchangeString;
    }

    /**
     * Main method used for testing this subclass. Test cases are documented in the
     * design document.
     * 
     * @param args default input for main method
     */
    public static void main(String[] args)
    {
        International test1 = new International("Stephen", "Prospero", 17, true);
        System.out.println("Test Case 1: " + test1.toString());

        International test2 = new International("Stephen", "Prospero", 17, false);
        System.out.println("Test Case 2: " + test2.toString());

        International test3 = new International("Henry", "Rutgers", 12, true);
        System.out.println("Test Case 3: " + test3.tuitionDue());

        International test4 = new International("Brett", "Conetta", 19, false);
        System.out.println("Test Case 4: " + test4.tuitionDue());

        International test5 = new International("Brad", "Pitt", 9, false);
        System.out.println("Test Case 5: " + test5.tuitionDue());

        International test6 = new International("Lester", "Holt", 14, false);
        System.out.println("Test Case 6: " + test6.tuitionDue());

        International test7 = new International("henry", "rutgers", 12, true);
        System.out.println("Test Case 7: " + test7.compareTo(test3));

        International test8 = new International("Henry", "Princeton", 12, true);
        System.out.println("Test Case 8: " + test8.compareTo(test7));

        International test9 = new International("Henry", "Wisconsin", 12, true);
        System.out.println("Test Case 9: " + test9.compareTo(test7));
    }
}
