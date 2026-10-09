package com.jsp.scms;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StudentsDao student = new StudentsDao();
		CoursesDao course = new CoursesDao();
		EnrollementsDao enrollement = new EnrollementsDao();
		MetadataDemo metadata = new MetadataDemo();
		while (true) {
			System.out.println("\n========== STUDENT COURSE MANAGEMENT SYSTEM ==========");
			System.out.println("1. Admin");
			System.out.println("2. Student");
			System.out.println("3. System Features");
			System.out.println("4. Exit");
			System.out.print("Enter Choice : ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				while (true) {
					System.out.println("\n========== ADMIN MENU ==========");
					System.out.println("1. Students Details");
					System.out.println("2. Courses Details");
					System.out.println("3. Enrollments Details");
					System.out.println("4. Logout");
					System.out.print("Enter Choice : ");
					int adminChoice = sc.nextInt();
					switch (adminChoice) {
					case 1:
						while(true) {
							System.out.println("\n========== ADMIN MENU ==========");
							System.out.println("1. Add Course");
							System.out.println("2. Update Course");
							System.out.println("3. Delete Course");
							System.out.println("4. View All Students");
							System.out.println("5. View All Courses");
							System.out.println("6. View All Enrollments");
							System.out.println("7. Assign Grades");
							System.out.println("8. Back");
							System.out.print("Enter Choice : ");
							int adminChoice1=sc.nextInt();
							switch(adminChoice1) {
							case 1:
								course.insertCourse();
								break;
							case 2:
								course.updateCourse();
								break;
							case 3:
								course.deleteCourse();
								break;
							case 4:
								student.fetchAllStudents();
								break;
							case 5:
								course.fetchAllCourses();
								break;
							case 6:
								enrollement.fetchStudentEnrollments();
								break;
							case 7:
								enrollement.updateGrade();
								break;
							case 8:
								break;
							default:
								System.out.println("Invalid Choice");
							}

							if(adminChoice1==8)
								break;
						}
						break;
					case 2:
						while(true) {
							System.out.println("\n========== STUDENT MENU ==========");
							System.out.println("1. Register New Student");
							System.out.println("2. Update Student Profile");
							System.out.println("3. Enroll in Course");
							System.out.println("4. View Enrolled Courses");
							System.out.println("5. View Grades");
							System.out.println("6. View GPA");
							System.out.println("7. Logout");
							System.out.print("Enter Choice : ");
							int studentMenu=sc.nextInt();
							switch(studentMenu) {
							case 1:
								student.insertStudent();
								break;
							case 2:
								student.updateStudent();
								break;
							case 3:
								enrollement.enrollStudent();
								break;
							case 4:
								enrollement.fetchStudentEnrollments();
								break;
							case 5:
								enrollement.viewGrades();
								break;
							case 6:
								student.calculateGPA();
								break;
							case 7:
								System.out.println("Logout Successfully");
								break;
							default:
								System.out.println("Invalid Choice");
							}
							if(studentMenu==7)
								break;
						}
						break;
					case 3:
					    while(true) {
					        System.out.println("\n========== ENROLLMENT MENU ==========");
					        System.out.println("1. View All Enrollments");
					        System.out.println("2. Assign Grades");
					        System.out.println("3. Delete Enrollment");
					        System.out.println("4. Back");
					        System.out.print("Enter Choice : ");
					        int enrollmentChoice = sc.nextInt();
					        switch(enrollmentChoice) {
					        case 1:
					            enrollement.fetchStudentEnrollments();
					            break;
					        case 2:
					            enrollement.updateGrade();
					            break;
					        case 3:
					            enrollement.deleteEnrollment();
					            break;
					        case 4:
					            break;
					        default:
					            System.out.println("Invalid Choice");
					        }
					        if(enrollmentChoice == 4)
					            break;
					    }
					    break;
					case 4:
						System.out.println("Logged Out Successfully...");
						break;
					default:
						System.out.println("Invalid Choice");
					}
					if (adminChoice == 4)
						break;
				}
				break;
			case 2:
				while (true) {
					System.out.println("\n========== STUDENT MENU ==========");
					System.out.println("1. Enroll into Course");
					System.out.println("2. View My Enrollments");
					System.out.println("3. Logout");
					System.out.print("Enter Choice : ");
					int studentMenu = sc.nextInt();
					switch (studentMenu) {
					case 1:
						enrollement.enrollStudent();
						break;
					case 2:
						enrollement.fetchStudentEnrollments();
						break;
					case 3:
						System.out.println("Logged Out Successfully...");
						break;
					default:
						System.out.println("Invalid Choice");
					}
					if (studentMenu == 3)
						break;
				}
				break;
			case 3:
				while(true) {
					System.out.println("\n========== SYSTEM FEATURES ==========");
					System.out.println("1. Batch Add Students");
					System.out.println("2. Connection Pooling Demo");
					System.out.println("3. Database Metadata");
					System.out.println("4. ResultSet Metadata");
					System.out.println("5. Back");
					System.out.print("Enter Choice : ");
					int systemChoice=sc.nextInt();
					switch(systemChoice) {
					case 1:
					    BatchInsertDemo.batchInsertStudents();
					    break;
					case 2:
						ConnectionPoolDemo.showConnection();
						break;
					case 3:
						metadata.databaseMetadata();
						break;
					case 4:
						metadata.resultSetMetadata();
						break;
					case 5:
						break;
					default:
						System.out.println("Invalid Choice");
					}

					if(systemChoice==5)
						break;
				}
				break;
			case 4:
				System.out.println("Thank You...");
				System.exit(0);
			default:
				System.out.println("Invalid Choice");
			}
		}
	}
}
