import java.sql.*;
import java.util.Scanner;

public class StudentManager {

    static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/studentdb";
    static final String USER = "root"; // Change to your MySQL username
    static final String PASS = "your_password"; // Change to your MySQL password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
            System.out.println("Connected to MySQL database successfully.");

            while (true) {
                System.out.println("\n1. Add Student\n2. View Students\n3. Delete Student\n4. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addStudent(conn, sc);
                        break;
                    case 2:
                        viewStudents(conn);
                        break;
                    case 3:
                        deleteStudent(conn, sc);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        conn.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(Connection conn, Scanner sc) throws SQLException {
        sc.nextLine(); // consume leftover newline
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter grade: ");
        String grade = sc.nextLine();
        System.out.print("Enter attendance (%): ");
        int attendance = sc.nextInt();

        String query = "INSERT INTO student (name, grade, attendance) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, grade);
        ps.setInt(3, attendance);

        int result = ps.executeUpdate();
        if (result > 0) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student.");
        }
    }

    public static void viewStudents(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM student");

        System.out.println("\nID | Name | Grade | Attendance");
        System.out.println("--------------------------------------");
        while (rs.next()) {
            System.out.printf("%d | %s | %s | %d%%\n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("grade"),
                    rs.getInt("attendance"));
        }
    }

    public static void deleteStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM student WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        int result = ps.executeUpdate();
        if (result > 0) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("No student found with that ID.");
        }
    }
}
