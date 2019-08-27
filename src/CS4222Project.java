import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CS4222Project {

	public static void main(String[] args) {
		
		final String url = "jdbc:postgresql://cs1.calstatela.edu:5432/cs4222s19"; // REMEMBER TO CHANGE URL TO NON-LOCAL SERVER
		final String user = "cs4222s19";
		final String password = "rCnmgpmW";
		
		try {
			// Connect to the database
			Connection c = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to server successfully.");
			
			// Request option input from user
			Scanner input = new Scanner(System.in);
			int choice = -1;
			while(choice != 0) { // Continue looping the program until the user closes it with option 0
				System.out.println("\n\nMenu:\n0 - Close program\n1 - Add a faculty or project\n2 - Remove a faulty or project\n3 - Display all tables");
				do {
					if(!input.hasNextInt()) { // The input cannot be parsed into an integer
						input.nextLine();
						System.out.println("Not a valid input.");
					}else {
						choice = input.nextInt();
						input.nextLine();
						if(choice < 0 || choice > 3) { // The input is not a valid option number
							System.out.println("Not a valid option.");
						}
					}
				}while(choice < 0 || choice > 3);
				switch(choice) { // Execute the choice
					case 1: // Add
						add(c, input);
						break;
					case 2: // Remove
						remove(c, input);
						break;
					case 3: // Display tables
						display(c);
				}
			}
			
			// Close the scanner and connection
			input.close();
			c.close();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	// The method to add to the table
	public static void add(Connection c, Scanner input) {
		
		int choice = -1;
		System.out.println("Add to which table?\n1 - Faculty\n2 - Projects");
		do {
			if(!input.hasNextInt()) { // The input cannot be parsed into an integer
				input.nextLine();
				System.out.println("Not a valid input.");
			}else {
				choice = input.nextInt();
				input.nextLine();
				if(choice < 1 || choice > 2) { // The input is not a valid option number
					System.out.println("Not a valid option.");
				}
			}
		}while(choice < 1 || choice > 2);
		
		switch(choice) {
			case 1: // Add to Professors table
				// SSN
				int ssn = -1;
				do {
					System.out.print("SSN: ");
					if(!input.hasNextInt()) {
						input.nextLine();
						System.out.println("Not a valid SSN. It must contain only numbers.");
					}else {
						ssn = input.nextInt();
						input.nextLine();
						if(ssn < 0) {
							System.out.println("Not a valid SSN. It must be nonnegative.");
						}
					}
				}while(ssn < 0);
				
				// Name
				System.out.print("Name: ");
				String name = input.nextLine();
				
				
				// Rank
				int rank = -1;
				do {
					System.out.print("Rank: ");
					if(!input.hasNextInt()) {
						input.nextLine();
						System.out.println("Not a valid rank. It must contain only numbers.");
					}else {
						rank = input.nextInt();
						input.nextLine();
					}
				}while(rank == -1);
				
				// Age
				int age = -1;
				do {
					System.out.print("Age: ");
					if(!input.hasNextInt()) {
						input.nextLine();
						System.out.println("Not a valid age. It must contain only numbers.");
					}else {
						age = input.nextInt();
						input.nextLine();
						if(age < 0) {
							System.out.println("Not a valid age. It must be nonnegative.");
						}
					}
				}while(age < 0);
				
				// Research Specialty
				System.out.print("Research Specialty: ");
				String researchSpec = input.nextLine();
				
				// Gender
				char gender;
				do {
					System.out.print("Gender (Only the first character will be read): ");
					gender = Character.toUpperCase(input.nextLine().charAt(0));
					if(gender != 'M' && gender != 'F') {
						System.out.println("Not a valid character. It must be either male (M) or female (F).");
					}
				}while(gender != 'M' && gender != 'F');
				
				// Working Department's ID (no database validation check)
				int deptId = -1;
				do {
					System.out.print("Working Department's ID (leave as 0 for None): ");
					if(!input.hasNextInt()) {
						input.nextLine();
						System.out.println("Not a valid id. It must contain only numbers.");
					}else {
						deptId = input.nextInt();
						input.nextLine();
						if(deptId < 0) {
							System.out.println("Not a valid input value. It must be 0 or greater.");
						}
					}
				}while(deptId < 0);
				
				// Insert into Professors table
				try{
					PreparedStatement ppstmt = c.prepareStatement("INSERT INTO Professors VALUES (?, ?, ?, ?, ?, ?, ?)");
					ppstmt.setInt(1, ssn);
					ppstmt.setString(2, name);
					ppstmt.setInt(3, rank);
					ppstmt.setInt(4, age);
					ppstmt.setString(5, researchSpec);
					ppstmt.setString(6, Character.toString(gender));
					if(deptId == 0) {
						ppstmt.setNull(7, Types.INTEGER);
					}else {
						ppstmt.setInt(7, deptId);
					}
					ppstmt.execute();
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 2: // Add to Projects table
				// Project id
				int pId = -1;
				do {
					System.out.print("Project ID: ");
					if(!input.hasNextInt()) {
						input.nextLine();
						System.out.println("Not a valid ID. It must contain only numbers.");
					}else {
						pId = input.nextInt();
						input.nextLine();
						if(pId < 0) {
							System.out.println("Not a valid input value. It must be nonnegative.");
						}
					}
				}while(pId < 0);
				
				// Sponsor
				System.out.print("Sponsor: ");
				String sponsor = input.nextLine();
				
				// Starting Date (no input checks)
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				Date startDate = null;
				String startDateString;
				System.out.print("Starting Date (Use pattern mm/dd/yyyy): ");
				startDateString = input.nextLine();
				try {
					startDate = formatter.parse(startDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				// End Date (no input checks)
				Date endDate = null;
				String endDateString;
				System.out.print("End Date (Use pattern mm/dd/yyyy): ");
				endDateString = input.nextLine();
				try {
					endDate = formatter.parse(endDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				// Budget (no decimal place validation check)
				float budget = -1;
				do {
					System.out.print("Budget (Decimal will NOT be truncated): ");
					if(!input.hasNextFloat()) {
						input.nextLine();
						System.out.println("Not a valid budget. Must be a float.");
					}else {
						budget = input.nextFloat();
						input.nextLine();
						if(budget < 0) {
							System.out.println("Not a valid input value. Must be nonnegative.");
						}
					}
				}while(budget < 0);
				
				// Manager SSN
				int managerSsn = -1;
				do {
					System.out.print("Manager SSN (Leave as 0 for None): ");
					if(!input.hasNextInt()) {
						input.nextLine();
						System.out.println("Not a valid SSN. Must be an integer.");
					}else {
						managerSsn = input.nextInt();
						input.nextLine();
						if(managerSsn < 0) {
							System.out.println("Not a valid input value. Must be nonnegative.");
						}
					}
				}while(managerSsn < 0);
				
				// Insert into Projects table
				try{
					PreparedStatement ppstmt = c.prepareStatement("INSERT INTO Projects VALUES(?, ?, ?, ?, ?, ?)");
					ppstmt.setInt(1, pId);
					ppstmt.setString(2, sponsor);
					ppstmt.setDate(3, new java.sql.Date(startDate.getTime()));
					ppstmt.setDate(4, new java.sql.Date(endDate.getTime()));
					ppstmt.setFloat(5, budget);
					if(managerSsn == 0) {
						ppstmt.setNull(6, Types.INTEGER);
					}else {
						ppstmt.setInt(6, managerSsn);
					}
					ppstmt.execute();
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		
	}
	
	// The method to remove from the table
	public static void remove(Connection c, Scanner input) {
		
		int choice = -1;
		System.out.println("Remove from which table?\n1 - Faculty\n2 - Projects");
		do {
			if(!input.hasNextInt()) { // The input cannot be parsed into an integer
				input.nextLine();
				System.out.println("Not a valid input.");
			}else {
				choice = input.nextInt();
				input.nextLine();
				if(choice < 1 || choice > 2) { // The input is not a valid option number
					System.out.println("Not a valid option.");
				}
			}
		}while(choice < 1 || choice > 2);
		
		switch(choice) {
			// Remove a faculty
			case 1:
				try {
					ArrayList<Integer> ssnOptions = new ArrayList<Integer>();
					Statement stmt = c.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM Professors");
					System.out.println("Enter an SSN value from the list of faculty:");
					System.out.println("TABLE Professors(ssn, name, rank, age, research_spec, gender, manage_project_id, works_dept_id)");
					while(rs.next()) {
						int ssn = rs.getInt("ssn");
						String name = rs.getString("name");
						int rank = rs.getInt("rank");
						int age = rs.getInt("age");
						String researchSpec = rs.getString("research_spec");
						String gender = rs.getString("gender");
						String worksDeptIdString = "NULL";
						if(rs.getInt("works_dept_id") != 0) {
							int worksDeptId = rs.getInt("works_dept_id");
							worksDeptIdString = Integer.toString(worksDeptId);
						}
						ssnOptions.add(ssn);
						System.out.println("[" + ssn + ", " + name + ", " + rank + ", " + age + ", " + researchSpec + ", " + gender + ", " + worksDeptIdString + "]");
					}
					
					Integer ssnChoice = -1;
					System.out.println("Delete SSN: ");
					do {
						if(!input.hasNextInt()) { // The input cannot be parsed into an integer
							input.nextLine();
							System.out.println("Not a valid input.");
						}else {
							ssnChoice = (Integer) input.nextInt();
							input.nextLine();
							if(!ssnOptions.contains(ssnChoice)) { // The input is not a valid option number
								System.out.println("Not a valid option.");
							}
						}
					}while(!ssnOptions.contains(ssnChoice));
					
					PreparedStatement ppstmt = c.prepareStatement("DELETE FROM Professors WHERE ssn = ?");
					ppstmt.setInt(1, ssnChoice);
					ppstmt.execute();
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			// Remove a project
			case 2:
				
				try {
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					ArrayList<Integer> idOptions = new ArrayList<Integer>();
					Statement stmt = c.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM Projects");
					System.out.println("Enter an ID value from the list of projects:");
					System.out.println("TABLE Projects(p_id, sponsor, start_date, end_date, budget, manager_ssn_string)");
					while(rs.next()) {
						int id = rs.getInt("p_id");
						String sponsor = rs.getString("sponsor");
						Date startDate = rs.getDate("start_date");
						String startDateString = formatter.format(startDate);
						Date endDate = rs.getDate("end_date");
						String endDateString = formatter.format(endDate);
						float budget = rs.getFloat("budget");
						String managerSsnString = "NULL";
						if(rs.getInt("manager_ssn") != 0) {
							int managerSsn = rs.getInt("manager_ssn");
							managerSsnString = Integer.toString(managerSsn);
						}
						idOptions.add(id);
						System.out.println("[" + id + ", " + sponsor + ", " + startDateString + ", " + endDateString + ", " + budget + ", " + managerSsnString + "]");
					}
					
					Integer idChoice = -1;
					System.out.println("Delete ID: ");
					do {
						if(!input.hasNextInt()) { // The input cannot be parsed into an integer
							input.nextLine();
							System.out.println("Not a valid input.");
						}else {
							idChoice = (Integer) input.nextInt();
							input.nextLine();
							if(!idOptions.contains(idChoice)) { // The input is not a valid option number
								System.out.println("Not a valid option.");
							}
						}
					}while(!idOptions.contains(idChoice));
					
					PreparedStatement ppstmt = c.prepareStatement("DELETE FROM Projects WHERE p_id = ?");
					ppstmt.setInt(1, idChoice);
					ppstmt.execute();
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
		}
	}
	
	// The method to display all tables
	public static void display(Connection c) {
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = null;
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			
			// Professors
			rs = stmt.executeQuery("SELECT * FROM Professors");
			System.out.println("TABLE Professors(ssn, name, rank, age, research_spec, gender, manage_project_id, works_dept_id)");
			while(rs.next()) {
				int ssn = rs.getInt("ssn");
				String name = rs.getString("name");
				int rank = rs.getInt("rank");
				int age = rs.getInt("age");
				String researchSpec = rs.getString("research_spec");
				String gender = rs.getString("gender");
				String worksDeptIdString = "NULL";
				if(rs.getInt("works_dept_id") != 0) {
					int worksDeptId = rs.getInt("works_dept_id");
					worksDeptIdString = Integer.toString(worksDeptId);
				}
				System.out.println("[" + ssn + ", " + name + ", " + rank + ", " + age + ", " + researchSpec + ", " + gender + ", " + worksDeptIdString + "]");
			}
			System.out.println();
			
			// Departments
			rs = stmt.executeQuery("SELECT * FROM Departments");
			System.out.println("TABLE Departments(dept_no, name, main_office, chair_ssn)");
			while(rs.next()) {
				int deptNo = rs.getInt("dept_no");
				String name = rs.getString("name");
				String mainOffice = rs.getString("main_office");
				String chairSsnString = "NULL";
				if(rs.getInt("chair_ssn") != 0) {
					int chairSsn = rs.getInt("chair_ssn");
					chairSsnString = Integer.toString(chairSsn);
				}
				System.out.println("[" + deptNo + ", " + name + ", " + mainOffice + ", " + chairSsnString + "]");
			}
			System.out.println();
			
			// Projects
			rs = stmt.executeQuery("SELECT * FROM Projects");
			System.out.println("TABLE Projects(p_id, sponsor, start_date, end_date, budget, manager_ssn_string)");
			while(rs.next()) {
				int id = rs.getInt("p_id");
				String sponsor = rs.getString("sponsor");
				Date startDate = rs.getDate("start_date");
				String startDateString = formatter.format(startDate);
				Date endDate = rs.getDate("end_date");
				String endDateString = formatter.format(endDate);
				float budget = rs.getFloat("budget");
				String managerSsnString = "NULL";
				if(rs.getInt("manager_ssn") != 0) {
					int managerSsn = rs.getInt("manager_ssn");
					managerSsnString = Integer.toString(managerSsn);
				}
				System.out.println("[" + id + ", " + sponsor + ", " + startDateString + ", " + endDateString + ", " + budget + ", " + managerSsnString + "]");
			}
			System.out.println();
			
			// Graduates
			rs = stmt.executeQuery("SELECT * FROM Graduates");
			System.out.println("TABLE Graduates(ssn, name, age, gender, degree, major_id)");
			while(rs.next()) {
				int ssn = rs.getInt("ssn");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String degree = rs.getString("degree");
				String majorIdString = "NULL";
				if(rs.getInt("major_id") != 0) {
					int majorId = rs.getInt("major_id");
					majorIdString = Integer.toString(majorId);
				}
				System.out.println("[" + ssn + ", " + name + ", " +  age + ", " +  gender + ", " +  degree + ", " +  majorIdString + "]");
			}
			System.out.println();
			
			// Graduate_advises
			rs = stmt.executeQuery("SELECT * FROM Graduate_advises");
			System.out.println("TABLE Graduate_advises(advisee_ssn, advisor_ssn)");
			while(rs.next()) {
				int adviseeSsn = rs.getInt("advisee_ssn");
				String advisorSsnString = "NULL";
				if(rs.getInt("advisor_ssn") != 0) {
					int advisorSsn = rs.getInt("advisor_ssn");
					advisorSsnString = Integer.toString(advisorSsn);
				}
				System.out.println("[" + adviseeSsn + ", " + advisorSsnString + "]");
			}
			System.out.println();
			
			// Co_investigators
			rs = stmt.executeQuery("SELECT * FROM Co_investigators");
			System.out.println("TABLE Co_investigators(professor_ssn, project_id)");
			while(rs.next()) {
				int professorSsn = rs.getInt("professor_ssn");
				int projectId = rs.getInt("project_id");
				System.out.println("[" + professorSsn + ", " + projectId + "]");
			}
			System.out.println();
			
			// Graduate_works_on_projects
			rs = stmt.executeQuery("SELECT * FROM Graduate_works_on_projects");
			System.out.println("TABLE Graduate_works_on_projects(graduate_ssn, project_id)");
			while(rs.next()) {
				int graduateSsn = rs.getInt("graduate_ssn");
				int projectId = rs.getInt("project_id");
				System.out.println("[" + graduateSsn + ", " + projectId + "]");
			}
			System.out.println();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
