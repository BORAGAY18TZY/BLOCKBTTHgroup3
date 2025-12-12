package hahakdog;

public class employee {
	
	private int employee_Id = 0;
	private String employee_Name = "";
	private double employee_Salary = 0;

	//getter method
	public int getEmployee_Id() {
		return employee_Id;
	}
	//setter method
	public void setEmployee_Id(int id) {
		this.employee_Id = id;
	}
	
	//getter and setter for Name
	public String getEmployee_Name() {
		return employee_Name;
	}
	public void setEmplpoyee_Name(String name) {
		this.employee_Name = name;
	}
	
	// getter and setter for Salary
	public String getEmployee_Salary() {
		return String.format("$%.2f", employee_Salary);
	}
	public void setEmplpoyee_Salary(double Salary) {
		this.employee_Salary = Salary;
	}
	
	
	public static void main(String[] args) {

		employee empy = new employee();
		
		empy.setEmployee_Id(4); // set the id value to 2
		empy.setEmplpoyee_Name("Pilandok");// set the Name value to Philip
		empy.setEmplpoyee_Salary(300);// set the Salary value to 200
		
		System.out.println("ID: " + empy.getEmployee_Id()); //get the value
		System.out.println("Name: " + empy.getEmployee_Name());//get the value
		System.out.println("Salary: " + empy.getEmployee_Salary());//get the value
	}
}