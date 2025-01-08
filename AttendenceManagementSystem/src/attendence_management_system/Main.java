package attendence_management_system;

import java.util.Scanner;

class Node {
    int admno;
    String name;
    Attend atd;
    Node next;

    Node(int admno, String name) {
        this.admno = admno;
        this.name = name;
        this.atd = null;
        this.next = null;
    }
}

class Attend {
    char attendance;
    Attend add;

    Attend(char attendance) {
        this.attendance = attendance;
        this.add = null;
    }
}

public class Main {
    static Node head = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int b = 0;

        do {
            System.out.println("\n**MAIN MENU");
            System.out.println("Press the serial number for their respective operation");
            System.out.println("1. Add student\n2. Delete student\n3. Display students list\n" +
                    "4. Take attendance of students\n5. Display last attendance list\n6. Class register\n" +
                    "7. Search student attendance\n8. Exit");
            System.out.print("Select your operation you want to perform: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    displayStudents();
                    break;
                case 4:
                    takeAttendance();
                    break;
                case 5:
                    lastAttendanceList();
                    break;
                case 6:
                    classRegister();
                    break;
                case 7:
                    searchAttendance();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    b = 1;
                    break;
                default:
                    System.out.println("\nSelect a valid operation!!");
            }
            if (choice != 8) {
                System.out.print("\nPress 1 to continue, 8 to exit: ");
                b = scanner.nextInt();
                if (b != 1 && b != 8) {
                    System.out.println("Invalid input. Exiting...");
                    b = 1;
                }
            }
        } while (b == 1);
    }

    static void addStudent() {
        Scanner scanner = new Scanner(System.in);
        Node newnode;
        System.out.print("\nEnter admission number of student: ");
        int admno = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter name of student: ");
        String name = scanner.nextLine();

        newnode = new Node(admno, name);
        if (head == null) {
            head = newnode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newnode;
        }
        System.out.println("Student successfully added");
    }

    static void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        if (head == null) {
            System.out.println("\nThere are no students!");
            return;
        }

        System.out.print("\nEnter admission number of student you want to remove: ");
        int rno = scanner.nextInt();
        Node temp = head;
        Node prev = null;

        while (temp != null) {
            if (temp.admno == rno) {
                if (prev == null) {
                    head = temp.next;
                } else {
                    prev.next = temp.next;
                }
                System.out.println(temp.name + " is removed");
                return;
            }
            prev = temp;
            temp = temp.next;
        }
        System.out.println("This student is not in the list!");
    }

    static void displayStudents() {
        if (head == null) {
            System.out.println("\nNo students!");
            return;
        }

        Node temp = head;
        System.out.println("\nStudents list:");
        while (temp != null) {
            System.out.println(temp.admno + "\t\t\t" + temp.name);
            temp = temp.next;
        }
    }

    static void takeAttendance() {
        if (head == null) {
            System.out.println("\nNo students to take attendance for!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Node temp = head;
        char m;

        System.out.println("Take attendance by pressing 'p' for present student and 'a' for absent student");

        while (temp != null) {
            System.out.print(temp.admno + "\t\t\t" + temp.name + "\t\t");
            m = scanner.next().charAt(0);

            Attend newAttend = new Attend(m);
            if (temp.atd == null) {
                temp.atd = newAttend;
            } else {
                Attend trav = temp.atd;
                while (trav.add != null) {
                    trav = trav.add;
                }
                trav.add = newAttend;
            }
            temp = temp.next;
        }
        System.out.println("Attendance taken successfully");
    }

    static void lastAttendanceList() {
        if (head == null) {
            System.out.println("\nNo students!");
            return;
        }

        Node temp = head;
        System.out.println("Last attendance list of students:");
        while (temp != null) {
            System.out.print(temp.admno + "\t\t\t" + temp.name + "\t\t\t");
            Attend trav = temp.atd;

            while (trav.add != null) {
                trav = trav.add;
            }
            System.out.println(trav.attendance);
            temp = temp.next;
        }
    }

    static void classRegister() {
        if (head == null) {
            System.out.println("\nNo students!");
            return;
        }

        Node temp = head;
        System.out.println("Attendance of all students:");
        while (temp != null) {
            System.out.print(temp.admno + "\t\t\t" + temp.name + "\t\t\t");
            Attend trav = temp.atd;

            while (trav != null) {
                System.out.print(trav.attendance + "\t\t\t");
                trav = trav.add;
            }
            System.out.println();
            temp = temp.next;
        }
    }

    static void searchAttendance() {
        if (head == null) {
            System.out.println("\nNo students!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student admission number: ");
        int n = scanner.nextInt();

        Node temp = head;
        while (temp != null) {
            if (temp.admno == n) {
                break;
            } else {
                temp = temp.next;
            }
        }

        if (temp != null) {
            int t = 0, p = 0, a = 0;
            float per;
            Attend trav = temp.atd;

            while (trav != null) {
                t++;
                if (trav.attendance == 'p') {
                    p++;
                } else if (trav.attendance == 'a') {
                    a++;
                }
                trav = trav.add;
            }
            System.out.println("Total number of days: " + t);
            System.out.println("Number of days present"+p);
            System.out.println("Number of days absent"+a);
            double per1=((double)p/(double)t)*100;
            System.out.println("attendence percentage of student is :"+per1);
        }
    }
}
