package application;
/**
 * Container class to hold Students as a StudentList and provides functionality
 * to add, remove, and print Students. Additionally, can determine if the
 * StudentList is empty, determine if a Student is in the team and will
 * dynamically grow the array when necessary.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public class StudentList
{
    private final int NOT_FOUND = -1;
    private final int GROW_SIZE = 4; // initial and grow size
    private Student[] students;
    private int numStudents;

    /**
     * Constructor for a StudentList object, which instantiates a Student array,
     * students, and the number of members.
     */
    public StudentList()
    {
        students = new Student[GROW_SIZE];
        numStudents = 0;
    }

    /**
     * Private method to search StudentList array looking for Student inputStudent.
     * 
     * @param inputStudent Student to search for.
     * @return -1 if Student not in the StudentList, else index where inputStudent
     *         is found.
     */
    private int find(Student inputStudent)
    {
        for (int i = 0; i < numStudents; ++i)
        {
            if (students[i].compareTo(inputStudent) == 0)
            {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Private method to create a new array which is GROW_SIZE elements larger than
     * current students array, then copies over the data and sets students equal to
     * this array.
     */
    private void grow()
    {
        Student[] newStudentList = new Student[numStudents + GROW_SIZE];
        for (int i = 0; i < numStudents; ++i)
        {
            newStudentList[i] = students[i];
        }
        students = newStudentList;
    }

    /**
     * Determines if the StudentList is empty.
     * 
     * @return true if students is empty, otherwise false.
     */
    public boolean isEmpty()
    {
        if (numStudents < 1)
            return true;
        else
            return false;
    }

    /**
     * Adds Student inputStudent to the team, growing the size of the students array
     * if necessary.
     * 
     * @param inputStudent Student to add to the StudentList.
     */
    public void add(Student inputStudent)
    {
        if (numStudents % 4 == 0)
        {
            grow();
        }
        students[numStudents] = inputStudent;
        ++numStudents;

        return;
    }

    /**
     * Replaces Student inputStudent in the students array with the latest Student
     * to enroll, and clears the newest Student's entry in the students array.
     * 
     * @param inputStudent Student to remove from the StudentList.
     * @return True if Student inputStudent is in the StudentList and is
     *         successfully removed, otherwise false.
     */
    public boolean remove(Student inputStudent)
    {
        int found = find(inputStudent);

        if (found != NOT_FOUND)
        {
            students[found] = students[numStudents - 1];
            students[numStudents - 1] = null;
            --numStudents;
            return true;
        }

        return false;
    }

    /**
     * Determines if Student inputStudent is in the students array.
     * 
     * @param inputStudent Student to be searched for.
     * @return True if Student inputStudent is in the students array, otherwise
     *         false.
     */
    public boolean contains(Student inputStudent)
    {
        if (find(inputStudent) != NOT_FOUND)
        {
            return true;
        }

        return false;
    }

    /**
     * Calls toString for each Student in the StudentList and additionally calls
     * tuitionDue() for each Student.
     */
    public void print()
    {
        System.out.println("The following students are currently enrolled:");
        for (int i = 0; i < numStudents; ++i)
        {
            System.out.println(students[i].toString() + "  tuition due: $" + students[i].tuitionDue());
        }
        System.out.println("-- end of the list --");

        return;
    }
	
	/**
	 * Calls toString for each Student in the StudentList and additionally calls
	 * tuitionDue() for each Student.
	 * 
	 * @return String representation of StudentList object.
	 */
	public String toString()
	{
		String output = "";
		output = output + "The following students are currently enrolled:\n";
		for (int i = 0; i < numStudents; ++i)
		{
			output = output + students[i].toString() + " tuition due: $"
					+ students[i].tuitionDue() + "\n";
		}
		output = output + "-- end of the list --\n";

		return output;
	}
}
