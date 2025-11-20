import java.util.*;

//         Aiman

// Main SchoolManagement class
class SchoolManagement {
    String schoolName, address, contactNumber, mediumOfStudy;
    Auditorium auditorium;
    Playground playground;
    NoticeBoard noticeBoard;
    List<Employee> employees = new ArrayList<>();
    List<Classroom> classrooms = new ArrayList<>();
    List<Lab> labs = new ArrayList<>();
    List<Bus> buses = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<Department> departments = new ArrayList<>();

    public SchoolManagement(String schoolName, String address, String contactNumber, String mediumOfStudy) {
        this.schoolName = schoolName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.mediumOfStudy = mediumOfStudy;
    }

    public void initialize(Auditorium a, Playground p, NoticeBoard n, List<Employee> e, List<Classroom> c, List<Lab> l) {
        auditorium = a;
        playground = p;
        noticeBoard = n;
        employees = e;
        classrooms = c;
        labs = l;
    }

    public void schoolDetails() {
        System.out.println("\n--- School Details ---");
        System.out.println("School Name: " + schoolName);
        System.out.println("Address: " + address);
        System.out.println("Contact: " + contactNumber);
        System.out.println("Medium: " + mediumOfStudy);
        System.out.println("Total Employees: " + employees.size());
        System.out.println("Total Students: " + students.size());
        System.out.println("Total Classrooms: " + classrooms.size());
        System.out.println("Total Labs: " + labs.size());
    }

    public boolean isOpen() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        boolean open = (day != Calendar.FRIDAY && day != Calendar.SATURDAY);
        System.out.println("School is " + (open ? "Open" : "Closed"));
        return open;
    }

    // main interactive UI
    public void runSchool() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nUser Interface:");
            System.out.println("A- Bus\nB- Student\nC- Employee\nD- Class\nE- NoticeBoard\nF- Auditorium\nG- ShowSchoolDetails\nH- Exit");
            String choice = sc.nextLine().toUpperCase();
            switch (choice) {
                case "A":
                    busMenu(sc);
                    break;
                case "B":
                    studentMenu(sc);
                    break;
                case "C":
                    employeeMenu(sc);
                    break;
                case "D":
                    classMenu(sc);
                    break;
                case "E":
                    noticeBoardMenu(sc);
                    break;
                case "F":
                    auditoriumMenu(sc);
                    break;
                case "G":
                    schoolDetails();
                    break;
                case "H":
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ---- Menus ----
    private void busMenu(Scanner sc) {
        while (true) {
            System.out.println("\nBus Menu:\n1- Add Bus\n2- Show Bus Details\n3- Show Seats\n4- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 1) {
                System.out.print("Bus ID: "); String bid = sc.nextLine();
                System.out.print("Driver ID: "); String did = sc.nextLine();
                System.out.print("Bus Number: "); String bno = sc.nextLine();
                System.out.print("Capacity: "); int cap = sc.nextInt(); sc.nextLine();
                Bus b = new Bus(bid, did, bno, cap);
                buses.add(b);
                System.out.println("Bus added.");
            } else if (opt == 2) {
                for (Bus b : buses) b.busDetails();
            } else if (opt == 3) {
                for (Bus b : buses) b.showSeats();
            } else break;
        }
    }

    private void studentMenu(Scanner sc) {
        while (true) {
            System.out.println("\nStudent Menu:\n1- Add Student\n2- Show Student Details\n3- Pay Fee\n4- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 1) {
                System.out.print("Student ID: "); String sid = sc.nextLine();
                System.out.print("Name: "); String name = sc.nextLine();
                System.out.print("Class ID: "); String cid = sc.nextLine();
                System.out.print("Section: "); String sec = sc.nextLine();
                System.out.print("Bus ID: "); String bus = sc.nextLine();
                System.out.print("Level (P=Primary, H=Higher): ");
                String lvl = sc.nextLine().toUpperCase();
                Student s = lvl.equals("P")
                        ? new PrimaryStudent(sid, name, cid, sec, bus)
                        : new HigherSecondaryStudent(sid, name, cid, sec, bus);
                students.add(s);
                System.out.println("Student added successfully.");
            } else if (opt == 2) {
                for (Student s : students) s.studentDetails();
            } else if (opt == 3) {
                for (Student s : students) s.payFees();
            } else break;
        }
    }

    private void employeeMenu(Scanner sc) {
        while (true) {
            System.out.println("\nEmployee Menu:\n1- Teacher\n2- Support Staff\n3- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 1) {
                for (Employee e : employees)
                    if (e instanceof Teacher) e.employeeDetails();
            } else if (opt == 2) {
                for (Employee e : employees)
                    if (e instanceof SupportStaff) e.employeeDetails();
            } else break;
        }
    }

    private void classMenu(Scanner sc) {
        while (true) {
            System.out.println("\nClass Menu:\n1- Add Students\n2- Assign Teacher\n3- Show Details\n4- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 3) {
                for (Classroom c : classrooms) c.classDetails();
            } else if (opt == 4) break;
            else System.out.println("Feature not implemented yet (demo mode).");
        }
    }

    private void noticeBoardMenu(Scanner sc) {
        while (true) {
            System.out.println("\nNoticeBoard Menu:\n1- Display\n2- Add Content\n3- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 1) noticeBoard.display();
            else if (opt == 2) {
                System.out.print("Enter new notice: ");
                String content = sc.nextLine();
                noticeBoard.addContent(content);
            } else break;
        }
    }

    private void auditoriumMenu(Scanner sc) {
        while (true) {
            System.out.println("\nAuditorium Menu:\n1- Book Auditorium\n2- Show Event Details\n3- Show Seats\n4- Go back");
            int opt = sc.nextInt(); sc.nextLine();
            if (opt == 1) auditorium.bookAuditorium(sc);
            else if (opt == 2) auditorium.eventDetails();
            else if (opt == 3) auditorium.displaySeats();
            else break;
        }
    }
}
class Auditorium {
    int totalSeats, seatsOccupied;
    String eventName, eventDate, eventTime;

    public Auditorium(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seatsOccupied = 0;
    }

    public void bookAuditorium(Scanner sc) {
        System.out.print("Event Name: ");
        eventName = sc.nextLine();
        System.out.print("Event Date: ");
        eventDate = sc.nextLine();
        System.out.print("Event Time: ");
        eventTime = sc.nextLine();
        System.out.print("Number of participants: ");
        seatsOccupied = sc.nextInt(); sc.nextLine();
        System.out.println("Auditorium booked for " + eventName);
    }

    public void eventDetails() {
        if (eventName == null) System.out.println("No event booked yet.");
        else System.out.println("Event: " + eventName + " on " + eventDate + " at " + eventTime);
    }

    public void displaySeats() {
        System.out.println("Total Seats: " + totalSeats + " | Occupied: " + seatsOccupied +
                           " | Available: " + (totalSeats - seatsOccupied));
    }
}
//Abdurrahman 
class Playground {
    String area;
    boolean occupied;

    public Playground(String area) {
        this.area = area;
        this.occupied = false;
    }

    public void isOccupied() {
        System.out.println("Playground is " + (occupied ? "occupied" : "free") + " in area: " + area);
    }
}
//Abdurrahman 
class NoticeBoard {
    List<String> newsList = new ArrayList<>();
    String inchargeName;

    public NoticeBoard(String inchargeName) {
        this.inchargeName = inchargeName;
    }

    public void display() {
        System.out.println("\n--- Notice Board (" + inchargeName + ") ---");
        if (newsList.isEmpty()) System.out.println("No news available.");
        else for (String n : newsList) System.out.println("- " + n);
    }

    public void addContent(String content) {
        newsList.add(content);
        System.out.println("Notice added successfully.");
    }
}

//aiman
// Abstract Equipment class
abstract class Equipment {
    String equipmentId;
    double cost;

    public Equipment(String equipmentId, double cost) {
        this.equipmentId = equipmentId;
        this.cost = cost;
    }

    abstract void equipmentDetails();
    abstract void purchaseEquipment();
    abstract void repair();
}
//aiman
class LabEquipment extends Equipment {
    String equipmentName;
    int equipmentCount;

    public LabEquipment(String id, double cost, String name, int count) {
        super(id, cost);
        this.equipmentName = name;
        this.equipmentCount = count;
    }

    @Override
    void equipmentDetails() {
        System.out.println("Lab Equipment: " + equipmentName + " | Count: " + equipmentCount + " | Cost: " + cost);
    }

    @Override
    void purchaseEquipment() {
        System.out.println("Purchased new lab equipment: " + equipmentName);
    }

    @Override
    void repair() {
        System.out.println("Repairing lab equipment: " + equipmentName);
    }
}
//aiman
class ClassEquipment extends Equipment {
    int benchCount, fanCount, lightCount;

    public ClassEquipment(String id, double cost, int benchCount, int fanCount, int lightCount) {
        super(id, cost);
        this.benchCount = benchCount;
        this.fanCount = fanCount;
        this.lightCount = lightCount;
    }

    @Override
    void equipmentDetails() {
        System.out.println("Class Equipment -> Benches: " + benchCount + ", Fans: " + fanCount + ", Lights: " + lightCount);
    }

    @Override
    void purchaseEquipment() {
        System.out.println("Purchased classroom equipment.");
    }

    @Override
    void repair() {
        System.out.println("Repairing classroom equipment.");
    }
}
//aiman
class Department {
    String departmentId, departmentName, inchargeName;
    List<Employee> members = new ArrayList<>();

    public Department(String departmentId, String departmentName, String inchargeName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.inchargeName = inchargeName;
    }

    public void addMember(Employee emp) { members.add(emp); }

    public void departmentDetails() {
        System.out.println("Department: " + departmentName + " | Incharge: " + inchargeName);
        for (Employee e : members) System.out.println(" - " + e.name);
    }
}


