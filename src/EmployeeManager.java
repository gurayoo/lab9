import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

class Employee {
    private String name;
    private String department;
    private String email;

    public Employee(String name, String department, String email) {
        this.name = name;
        this.department = department;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Name: " + name + ", Department: " + department + ", Email: " + email;
    }
}

public class EmployeeManager {
    Employee[] employees = new Employee[100];
    int count = 0;
    String currentFile = "";

    public void loadEmployeesFromFile(String filePath) {
        this.currentFile = filePath;
        this.count = 0;

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File not found. A new file will be created when you add an employee.");
                return;
            }

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String dept = parts[1].trim();
                    String email = parts[2].trim();

                    Employee emp = new Employee(name, dept, email);
                    employees[count] = emp;
                    count++;
                }
            }
            reader.close();
            System.out.println("Employees loaded from file.");
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public void displayEmployees() {
        System.out.println("--- Employee List ---");
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i].toString());
        }
    }
    public void addEmployee(String name, String department, String email) {
        Employee newEmp = new Employee(name, department, email);
        employees[count] = newEmp;
        count++;


        try {
            FileWriter writer = new FileWriter(currentFile, true);
            writer.write(name + "," + department + "," + email + "\n");
            writer.close();
            System.out.println("Employee added and saved to file.");
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void searchEmployee(String name) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (employees[i].getName().equalsIgnoreCase(name)) {
                System.out.println("Employee Found: " + employees[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Employee with name '" + name + "' not found.");
        }
    }


    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        manager.loadEmployeesFromFile("employees.txt");
        manager.displayEmployees();

    }
}