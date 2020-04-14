package application;
/**
 * This abstract class defines a Student as having a first name, last name and
 * number of enrolled credit hours. Functionality is provided for comparing
 * Student objects and obtaining the String representation of a Student. The
 * method tutionDue() is abstract and must be implemented by subclasses since
 * calculations depend on student type.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public abstract class Student implements Comparable
{
    private String fname;
    private String lname;
    protected int credit;

    public static final int MAX_CREDIT_HOURS = 15;
    public static final int FULL_TIME_CREDITS = 12;
    public static final int PART_TIME_FEE = 846;
    public static final int FULL_TIME_FEE = 1441;

    /**
     * This constructor creates a Student object using the supplied first name, last
     * name and credit hours.
     * 
     * @param fname  First name of student
     * @param lname  Last name of student
     * @param credit number of credit hours the student is enrolled in
     */
    public Student(String fname, String lname, int credit)
    {
        this.fname = fname;
        this.lname = lname;
        this.credit = credit;
    }

    /**
     * This method compares the input to the invoking Student object's first and
     * last names. If the input is a Student object and the invoking Student object
     * have identical first and last names, 0 is returned to denote equality.
     * Otherwise, the first names of both students are compared using the String
     * compareTo() method. -1 will be returned if the first name of the invoking
     * Student object is lexicographically less than that of the input student's
     * first name. If the students share identical first names, then the comparison
     * checks their last names and has the same return criteria. This method is NOT
     * case-sensitive.
     * 
     * @return 0 if the first and last names of both Student objects match, -1 if
     *         the name of the invoking Student object is less than that of the name
     *         of the input Student object and 1 otherwise. -1 is returned for input
     *         that is not a Student object
     */
    public int compareTo(Object obj)
    {
        if (obj instanceof Student)
        {
            Student inputStudent = (Student) obj;
            int compareToResult;
            if (this.fname.equalsIgnoreCase(inputStudent.fname))
            {
                compareToResult = this.lname.compareToIgnoreCase(inputStudent.lname);
            }
            else
            {
                compareToResult = this.fname.compareToIgnoreCase(inputStudent.fname);
            }

            if (compareToResult < 0)
            {
                return -1;
            }
            else if (compareToResult > 0)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            return -1;
        }
    }

    /**
     * This method returns the String representation of a Student object. The
     * student's first name, last name and number of credits are included in the
     * String.
     * 
     * @return String representation of Student object
     */
    @Override
    public String toString()
    {
        return "Name: " + fname + " " + lname + "  Credit(s): " + Integer.toString(credit);
    }

    /**
     * Abstract method for calculating tuition that must be implemented by
     * subclasses since every type of student has varying tuition prices and fees.
     * 
     * @return integer calculation of tuition due
     */
    public abstract int tuitionDue();

}
