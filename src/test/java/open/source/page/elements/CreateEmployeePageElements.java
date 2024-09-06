package open.source.page.elements;

public class CreateEmployeePageElements {

	public static final String ADMIN_BUTTON = "//*[text()='Admin']";

	public static final String ADD_BUTTON = "//*[@class='orangehrm-header-container']/button";

	public static final String USER_ROLE_DROP_DOWN_BUTTON = "//*[text()='User Role']/parent::div/parent::div//i";

	public static final String USER_ROLE_DROP_DOWN_LIST = "//*[@role='listbox']//div";

	public static final String STATUS_DROP_DOWN_BUTTON = "//*[text()='Status']/parent::div/parent::div//i";

	public static final String STATUS_DROP_DOWN_LIST = "//*[@role='listbox']//div";

	public static final String EMPLOYEE_NAME_TEXT_FIELD = "//*[text()='Employee Name']/parent::div/parent::div//input";

	public static final String SECOND_EMPLOYEE = "(//*[@role='option'])[2]";

	public static final String EMPLOYEE_USER_NAME_TEXT_FIELD = "//*[text()='Username']/parent::div/parent::div//input";

	public static final String EMPLOYEE_PASSWORD_TEXT_FIELD = "//*[text()='Password']/parent::div/parent::div//input";

	public static final String EMPLOYEE_CONFIRM_PASSWORD_TEXT_FIELD = "//*[text()='Confirm Password']/parent::div/parent::div//input";

	public static final String SAVE_BUTTON = "//button[text()=' Save ']";

	public static final String SUCCESS_MESSAGE_LABEL = "//*[text()='Success']";

	public static final String SEARCH_BUTTON = "//button[@type='submit']";

	public static final String ONE_RECORD_FOUND_TEXT_LABEL = "//*[text()='(1) Record Found']";

}
