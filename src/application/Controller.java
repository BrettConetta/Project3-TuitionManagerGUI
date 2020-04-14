package application;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
/**
 * This class contains every event handler method for the TuitionManagerGUI. It
 * declares the JavaFX objects in use in the GUI. Student subclass objects are
 * instantiated and calls to the StudentList class are made when the user
 * performs the appropriate action. Also, this class implements the
 * Initializable interface so that a StudentList object can be initialized at
 * runtime.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public class Controller implements Initializable
{
	@FXML
	private RadioButton inStateButton;
	@FXML
	private RadioButton outStateButton;
	@FXML
	private RadioButton internationalButton;
	@FXML
	private RadioButton extra;
	@FXML
	private CheckBox fundingCheckBox;
	@FXML
	private CheckBox tristateCheckBox;
	@FXML
	private CheckBox exchangeCheckBox;
	@FXML
	private TextField fundingTextField;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private TextField creditTextField;
	@FXML
	private TextArea outputTextArea;
	@FXML
	private Button addButton;
	@FXML
	private Button removeButton;
	@FXML
	private Button printButton;
	@FXML
	private Button clearTextButton;

	private StudentList enrolledStudents;

	/**
	 * This method disables out-of-state and international student options when
	 * in-state radio button is selected. Also, it enables in-state funding
	 * check box.
	 * 
	 * @param event ActionEvent object that triggers this method
	 */
	@FXML
	public void inStateClick(ActionEvent event)
	{
		this.tristateCheckBox.setDisable(true);
		this.tristateCheckBox.setSelected(false);
		this.exchangeCheckBox.setDisable(true);
		this.exchangeCheckBox.setSelected(false);

		this.fundingCheckBox.setDisable(false);
	}

	/**
	 * This method enables funding text field when the funding check box is
	 * selected. Also, it disables and clears the funding text field when the
	 * funding check box is unselected.
	 * 
	 * @param event ActionEvent object that triggers this method
	 */
	@FXML
	public void fundingClick(ActionEvent event)
	{
		if (this.fundingCheckBox.isSelected())
		{
			this.fundingTextField.setDisable(false);
		}
		else
		{
			this.fundingTextField.setDisable(true);
			this.fundingTextField.clear();
		}
	}

	/**
	 * This method disables/clears in-state and international student options
	 * when out-of-state radio button is selected. Also, it enables the
	 * tri-state check box.
	 * 
	 * @param event ActionEvent object that triggers this method
	 */
	@FXML
	public void outStateClick(ActionEvent event)
	{
		this.fundingTextField.setDisable(true);
		this.fundingTextField.clear();
		this.fundingCheckBox.setDisable(true);
		this.fundingCheckBox.setSelected(false);
		this.exchangeCheckBox.setDisable(true);
		this.exchangeCheckBox.setSelected(false);

		this.tristateCheckBox.setDisable(false);
	}

	/**
	 * This method disables/clears in-state and out-of-state student options
	 * when out-of-state radio button is selected. Also, it enables the exchange
	 * check box.
	 * 
	 * @param event ActionEvent object that triggers this method
	 */
	@FXML
	public void internationalStateClick(ActionEvent event)
	{
		this.fundingTextField.setDisable(true);
		this.fundingTextField.clear();
		this.fundingCheckBox.setDisable(true);
		this.fundingCheckBox.setSelected(false);
		this.tristateCheckBox.setDisable(true);
		this.tristateCheckBox.setSelected(false);

		this.exchangeCheckBox.setDisable(false);
	}

	/**
	 * This method adds a student to the student list using the provided input
	 * in the GUI following a press of the "Add" button. This method checks that
	 * first and last name fields are not blank (in case the user bypassed our
	 * text field event handler methods) and that the credit input is an integer
	 * greater than 0. Also, this method has additional checks that verify
	 * in-state, out-of-state and international student input adheres to the
	 * project's criteria. If the criteria is not met, the input remains in the
	 * GUI so the user can make the necessary changes. Otherwise, the student is
	 * added to the list, a success message is displayed and the GUI's options
	 * are reset. A warning message is displayed when a part-time in-state
	 * student with funding is added.
	 * 
	 * @param event ActionEvent object that triggers this method
	 */
	@FXML
	public void addButtonPress(ActionEvent event)
	{
		boolean errorOccurred = false;

		String firstNameInput = this.firstNameTextField.getText();
		firstNameInput = firstNameInput.trim();
		if (firstNameInput.equals(""))
		{
			this.outputTextArea.setText(this.outputTextArea.getText()
					+ "First name must be not be blank!\n");
			this.firstNameTextField.clear();
			errorOccurred = true;
		}

		String lastNameInput = this.lastNameTextField.getText();
		lastNameInput = lastNameInput.trim();
		if (lastNameInput.equals(""))
		{
			this.outputTextArea.setText(this.outputTextArea.getText()
					+ "Last name must be not be blank!\n");
			this.lastNameTextField.clear();
			errorOccurred = true;
		}

		int creditInput = 0;
		try
		{
			creditInput = Integer.parseInt(this.creditTextField.getText());
		}
		catch (NumberFormatException nfe)
		{
			this.outputTextArea.setText(this.outputTextArea.getText()
					+ "Credit must be an integer!\n");
			this.creditTextField.clear();
			errorOccurred = true;
		}

		if (!errorOccurred)
		{
			if (creditInput <= 0)
			{
				this.outputTextArea.setText(this.outputTextArea.getText()
						+ "Number of credits must be greater than 0!\n");
				return;
			}
			else
			{
				if (this.inStateButton.isSelected())
				{
					int fundingInput = 0;
					if (this.fundingCheckBox.isSelected())
					{
						try
						{
							fundingInput = Integer
									.parseInt(this.fundingTextField.getText());
						}
						catch (NumberFormatException nfe)
						{
							this.outputTextArea
									.setText(this.outputTextArea.getText()
											+ "Funding must be an integer!\n");
							return;
						}

						if (fundingInput < 0)
						{
							this.outputTextArea
									.setText(this.outputTextArea.getText()
											+ "Funding cannot be negative!\n");
							return;
						}

					}

					boolean partTimeFundingWarning = false;
					if (creditInput < Student.FULL_TIME_CREDITS
							&& fundingInput > 0)
					{
						partTimeFundingWarning = true;
					}

					Student studentToAdd = new Instate(firstNameInput,
							lastNameInput, creditInput, fundingInput);
					if (enrolledStudents.contains(studentToAdd))
					{
						this.outputTextArea
								.setText(this.outputTextArea.getText()
										+ firstNameInput + " " + lastNameInput
										+ " is already enrolled.\n");
						return;
					}
					enrolledStudents.add(studentToAdd);
					if (partTimeFundingWarning == false)
					{
						this.outputTextArea
								.setText(this.outputTextArea.getText()
										+ "Instate Student: " + firstNameInput
										+ " " + lastNameInput
										+ " has been added.\n");
					}
					else
					{
						this.outputTextArea.setText(this.outputTextArea
								.getText() + "Instate Student: "
								+ firstNameInput + " " + lastNameInput
								+ " has been added.\nWARNING! This student's"
								+ " funding is not deducted from tuition"
								+ "\nbecause part-time in-state students"
								+ " are NOT eligible for funding!\n");
					}
				}
				else if (this.outStateButton.isSelected())
				{
					boolean tristate = this.tristateCheckBox.isSelected();

					Student studentToAdd = new Outstate(firstNameInput,
							lastNameInput, creditInput, tristate);
					if (enrolledStudents.contains(studentToAdd))
					{
						this.outputTextArea
								.setText(this.outputTextArea.getText()
										+ firstNameInput + " " + lastNameInput
										+ " is already enrolled.\n");
						return;
					}
					enrolledStudents.add(studentToAdd);
					this.outputTextArea.setText(this.outputTextArea.getText()
							+ "Outstate Student: " + firstNameInput + " "
							+ lastNameInput + " has been added.\n");
				}
				else
				{
					boolean exchange = this.exchangeCheckBox.isSelected();
					if (creditInput < International.INTERNATIONAL_MINIMUM_CREDITS)
					{
						this.outputTextArea.setText(this.outputTextArea
								.getText()
								+ "International students must be registered for at least 9 credits!\n");
						return;
					}
					Student studentToAdd = new International(firstNameInput,
							lastNameInput, creditInput, exchange);
					if (enrolledStudents.contains(studentToAdd))
					{
						this.outputTextArea
								.setText(this.outputTextArea.getText()
										+ firstNameInput + " " + lastNameInput
										+ " is already enrolled.\n");
						return;
					}
					enrolledStudents.add(studentToAdd);
					this.outputTextArea.setText(this.outputTextArea.getText()
							+ "International Student: " + firstNameInput + " "
							+ lastNameInput + " has been added.\n");
				}
			}

			this.firstNameTextField.clear();
			this.lastNameTextField.setDisable(true);
			this.lastNameTextField.clear();
			this.creditTextField.clear();
			this.creditTextField.setDisable(true);
			this.fundingTextField.clear();
			this.fundingTextField.setDisable(true);
			this.fundingCheckBox.setSelected(false);

			this.addButton.setDisable(true);
			this.removeButton.setDisable(true);
		}

	}

	/**
	 * This method removes a student from the student list using the provided
	 * input in the GUI following a press of the "Remove" button. This method
	 * checks that first and last name fields are not blank (in case the user
	 * bypassed our text field event handler methods). If either of the name
	 * fields are blank, the input remains in the GUI so the user can make the
	 * necessary changes. Otherwise, the student is removed from the list, a
	 * success message is displayed and the GUI's options are reset.
	 * 
	 * @param event ActionEvent object that triggers this method
	 */
	@FXML
	public void removeButtonPress(ActionEvent event)
	{
		boolean errorOccurred = false;

		String firstNameInput = this.firstNameTextField.getText();
		firstNameInput = firstNameInput.trim();
		if (firstNameInput.equals(""))
		{
			this.outputTextArea.setText(this.outputTextArea.getText()
					+ "First name must be not be blank!\n");
			this.firstNameTextField.clear();
			errorOccurred = true;
		}

		String lastNameInput = this.lastNameTextField.getText();
		lastNameInput = lastNameInput.trim();
		if (lastNameInput.equals(""))
		{
			this.outputTextArea.setText(this.outputTextArea.getText()
					+ "Last name must be not be blank!\n");
			this.lastNameTextField.clear();
			errorOccurred = true;
		}

		if (!errorOccurred)
		{
			Student studentToRemove = new Instate(firstNameInput, lastNameInput,
					0, 0);

			if (enrolledStudents.remove(studentToRemove))
			{
				this.outputTextArea
						.setText(this.outputTextArea.getText() + firstNameInput
								+ " " + lastNameInput + " has dropped out.\n");
			}
			else
			{
				this.outputTextArea
						.setText(this.outputTextArea.getText() + firstNameInput
								+ " " + lastNameInput + " is not enrolled.\n");
			}

			this.firstNameTextField.clear();
			this.lastNameTextField.clear();
			this.creditTextField.clear();

			this.addButton.setDisable(true);
			this.removeButton.setDisable(true);
			this.lastNameTextField.setDisable(true);
			this.creditTextField.setDisable(true);
		}

		return;
	}

	/**
	 * This method appends the String returned by the StudentList toString()
	 * method to the output text area, thus displaying the current list of
	 * students. If the student list is empty, an empty message is appended to
	 * the output text area.
	 * 
	 * @param event ActionEvent object that triggers this method
	 */
	@FXML
	public void printButtonPress(ActionEvent event)
	{
		if (enrolledStudents.isEmpty())
		{
			this.outputTextArea.setText(this.outputTextArea.getText()
					+ "There are no students enrolled at this time.\n");
			return;
		}
		this.outputTextArea.setText(
				this.outputTextArea.getText() + enrolledStudents.toString());
	}

	/**
	 * This method keeps the add button, remove button, last name text field,
	 * and credit text field disabled and cleared until text is entered into the
	 * first name text field, thus ensuring a blank first name cannot be
	 * entered.
	 * 
	 * @param event InputEvent object that triggers this method
	 */
	@FXML
	public void onFirstNameEnter(InputEvent event)
	{
		if (!this.firstNameTextField.getText().equals(""))
		{
			this.lastNameTextField.setDisable(false);
		}
		if (this.firstNameTextField.getText().equals(""))
		{
			this.lastNameTextField.setDisable(true);
			this.lastNameTextField.clear();
			this.creditTextField.setDisable(true);
			this.creditTextField.clear();
			this.addButton.setDisable(true);
			this.removeButton.setDisable(true);
		}
	}

	/**
	 * This method keeps the add button, remove button and credit text field
	 * disabled and cleared until text is entered into the last name text field,
	 * thus ensuring a blank last name cannot be entered.
	 * 
	 * @param event InputEvent object that triggers this method
	 */
	@FXML
	public void onLastNameEnter(InputEvent event)
	{
		if (!this.lastNameTextField.getText().equals(""))
		{
			this.creditTextField.setDisable(false);
			this.removeButton.setDisable(false);
		}
		if (this.lastNameTextField.getText().equals(""))
		{
			this.creditTextField.setDisable(true);
			this.creditTextField.clear();
			this.addButton.setDisable(true);
			this.removeButton.setDisable(true);
		}
	}

	/**
	 * This method keeps the add button and remove button disabled until text is
	 * entered into the last name text field, thus ensuring a student cannot be
	 * added with a blank credit input.
	 * 
	 * @param event InputEvent object that triggers this method
	 */
	@FXML
	public void onNumCreditsEnter(InputEvent event)
	{
		if (!this.creditTextField.getText().equals(""))
		{
			this.addButton.setDisable(false);
		}
		if (this.creditTextField.getText().equals(""))
		{
			this.addButton.setDisable(true);
		}
	}

	/**
	 * This method clears the text in the output text area when the "Clear Text"
	 * button is pressed.
	 * 
	 * @param event ActionEvent object that triggers this method
	 */
	@FXML
	public void clearTextButtonPress(ActionEvent event)
	{
		this.outputTextArea.clear();
	}

	/**
	 * This method is called at runtime to initialize StudentList object. It is
	 * required to be implemented by Initializable interface.
	 * 
	 * @param arg0 URL object input as required by the Initializable interface
	 * @param arg1 ResourceBundle object input as required by the Initializable
	 *        interface
	 */
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		enrolledStudents = new StudentList();
	}
}
