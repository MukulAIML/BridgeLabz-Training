// Problem 3: Multi-Level University Course Management System

// Abstract base class for course types
abstract class CourseType {
    private String evaluationMethod;
    
    public CourseType(String evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
    }
    
    public String getEvaluationMethod() { return evaluationMethod; }
}

// Specific course types
class ExamCourse extends CourseType {
    private int examCount;
    
    public ExamCourse(int examCount) {
        super("Exam-Based");
        this.examCount = examCount;
    }
    
    public int getExamCount() { return examCount; }
}

class AssignmentCourse extends CourseType {
    private int assignmentCount;
    
    public AssignmentCourse(int assignmentCount) {
        super("Assignment-Based");
        this.assignmentCount = assignmentCount;
    }
    
    public int getAssignmentCount() { return assignmentCount; }
}

class ResearchCourse extends CourseType {
    private String researchTopic;
    
    public ResearchCourse(String researchTopic) {
        super("Research-Based");
        this.researchTopic = researchTopic;
    }
    
    public String getResearchTopic() { return researchTopic; }
}

// Generic Course class with bounded type parameter
class Course<T extends CourseType> {
    private String courseName;
    private String department;
    private T evaluationType;
    private int credits;
    
    public Course(String courseName, String department, T evaluationType, int credits) {
        this.courseName = courseName;
        this.department = department;
        this.evaluationType = evaluationType;
        this.credits = credits;
    }
    
    public String getCourseName() { return courseName; }
    public String getDepartment() { return department; }
    public T getEvaluationType() { return evaluationType; }
    public int getCredits() { return credits; }
    
    @Override
    public String toString() {
        return courseName + " [" + department + "] - " + 
               evaluationType.getEvaluationMethod() + " (" + credits + " credits)";
    }
}

// Course manager with wildcard methods
class CourseManager {
    private List<Course<? extends CourseType>> allCourses;
    
    public CourseManager() {
        this.allCourses = new ArrayList<>();
    }
    
    // Add course using wildcard
    public void addCourse(Course<? extends CourseType> course) {
        allCourses.add(course);
        System.out.println("Added: " + course);
    }
    
    // Display all courses using wildcard
    public void displayAllCourses(List<? extends Course<? extends CourseType>> courses) {
        System.out.println("\n=== Course Catalog ===");
        for (Course<? extends CourseType> course : courses) {
            System.out.println("- " + course);
        }
    }
    
    // Calculate total credits
    public int calculateTotalCredits(List<? extends Course<? extends CourseType>> courses) {
        int total = 0;
        for (Course<? extends CourseType> course : courses) {
            total += course.getCredits();
        }
        return total;
    }
    
    // Filter by department using wildcards
    public List<Course<? extends CourseType>> filterByDepartment(String department) {
        List<Course<? extends CourseType>> filtered = new ArrayList<>();
        for (Course<? extends CourseType> course : allCourses) {
            if (course.getDepartment().equalsIgnoreCase(department)) {
                filtered.add(course);
            }
        }
        return filtered;
    }
    
    public List<Course<? extends CourseType>> getAllCourses() {
        return allCourses;
    }
}

// Demo class
public class UniversityDemo {
    public static void main(String[] args) {
        CourseManager manager = new CourseManager();
        
        // Create courses with different evaluation types
        Course<ExamCourse> mathCourse = new Course<>(
            "Calculus I", "Mathematics", new ExamCourse(3), 4);
        
        Course<AssignmentCourse> csCourse = new Course<>(
            "Data Structures", "Computer Science", new AssignmentCourse(5), 3);
        
        Course<ResearchCourse> physicsCourse = new Course<>(
            "Quantum Mechanics", "Physics", 
            new ResearchCourse("Wave-Particle Duality"), 4);
        
        Course<ExamCourse> statsCourse = new Course<>(
            "Statistics", "Mathematics", new ExamCourse(2), 3);
        
        // Add courses to manager
        manager.addCourse(mathCourse);
        manager.addCourse(csCourse);
        manager.addCourse(physicsCourse);
        manager.addCourse(statsCourse);
        
        // Display all courses
        manager.displayAllCourses(manager.getAllCourses());
        
        // Filter by department
        System.out.println("\n=== Mathematics Department Courses ===");
        List<Course<? extends CourseType>> mathCourses = 
            manager.filterByDepartment("Mathematics");
        for (Course<? extends CourseType> course : mathCourses) {
            System.out.println("- " + course);
        }
        
        // Calculate total credits
        System.out.println("\nTotal Credits (Math Dept): " + 
            manager.calculateTotalCredits(mathCourses));
    }
}