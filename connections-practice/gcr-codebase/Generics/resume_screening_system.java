// Problem 5: AI-Driven Resume Screening System

// Abstract base class for job roles
abstract class JobRole {
    private String roleName;
    private int requiredExperience;
    
    public JobRole(String roleName, int requiredExperience) {
        this.roleName = roleName;
        this.requiredExperience = requiredExperience;
    }
    
    public String getRoleName() { return roleName; }
    public int getRequiredExperience() { return requiredExperience; }
    
    // Abstract method to check required skills
    public abstract boolean hasRequiredSkills(List<String> skills);
}

// Specific job roles
class SoftwareEngineer extends JobRole {
    public SoftwareEngineer() {
        super("Software Engineer", 2);
    }
    
    @Override
    public boolean hasRequiredSkills(List<String> skills) {
        return skills.contains("Java") || skills.contains("Python") || 
               skills.contains("JavaScript");
    }
}

class DataScientist extends JobRole {
    public DataScientist() {
        super("Data Scientist", 3);
    }
    
    @Override
    public boolean hasRequiredSkills(List<String> skills) {
        return skills.contains("Python") && 
               (skills.contains("Machine Learning") || skills.contains("Statistics"));
    }
}

class ProductManager extends JobRole {
    public ProductManager() {
        super("Product Manager", 4);
    }
    
    @Override
    public boolean hasRequiredSkills(List<String> skills) {
        return skills.contains("Product Strategy") || skills.contains("Agile") || 
               skills.contains("Roadmap Planning");
    }
}

// Generic Resume class with bounded type parameter
class Resume<T extends JobRole> {
    private String candidateName;
    private int yearsOfExperience;
    private List<String> skills;
    private T targetRole;
    private boolean screened;
    private String screeningResult;
    
    public Resume(String candidateName, int yearsOfExperience, 
                  List<String> skills, T targetRole) {
        this.candidateName = candidateName;
        this.yearsOfExperience = yearsOfExperience;
        this.skills = skills;
        this.targetRole = targetRole;
        this.screened = false;
    }
    
    public String getCandidateName() { return candidateName; }
    public int getYearsOfExperience() { return yearsOfExperience; }
    public List<String> getSkills() { return skills; }
    public T getTargetRole() { return targetRole; }
    public boolean isScreened() { return screened; }
    public String getScreeningResult() { return screeningResult; }
    
    public void setScreeningResult(String result) {
        this.screeningResult = result;
        this.screened = true;
    }
    
    @Override
    public String toString() {
        return candidateName + " -> " + targetRole.getRoleName() + 
               " (" + yearsOfExperience + " yrs exp)";
    }
}

// Resume screening system with generic methods
class ResumeScreeningSystem {
    private List<Resume<? extends JobRole>> allResumes;
    
    public ResumeScreeningSystem() {
        this.allResumes = new ArrayList<>();
    }
    
    // Generic method to process resume
    public <T extends JobRole> void processResume(Resume<T> resume) {
        allResumes.add(resume);
        
        T role = resume.getTargetRole();
        boolean hasSkills = role.hasRequiredSkills(resume.getSkills());
        boolean hasExperience = resume.getYearsOfExperience() >= role.getRequiredExperience();
        
        if (hasSkills && hasExperience) {
            resume.setScreeningResult("SELECTED");
            System.out.println("✓ SELECTED: " + resume);
        } else if (hasSkills) {
            resume.setScreeningResult("MAYBE");
            System.out.println("⚠ MAYBE: " + resume + " (Needs more experience)");
        } else {
            resume.setScreeningResult("REJECTED");
            System.out.println("✗ REJECTED: " + resume + " (Missing required skills)");
        }
    }
    
    // Wildcard method to handle multiple job roles
    public void screenMultipleResumes(List<? extends Resume<? extends JobRole>> resumes) {
        System.out.println("\n=== Batch Resume Screening ===");
        for (Resume<? extends JobRole> resume : resumes) {
            if (!resume.isScreened()) {
                processResumeWildcard(resume);
            }
        }
    }
    
    // Helper method for wildcard processing
    private void processResumeWildcard(Resume<? extends JobRole> resume) {
        JobRole role = resume.getTargetRole();
        boolean hasSkills = role.hasRequiredSkills(resume.getSkills());
        boolean hasExperience = resume.getYearsOfExperience() >= role.getRequiredExperience();
        
        if (hasSkills && hasExperience) {
            resume.setScreeningResult("SELECTED");
            System.out.println("✓ SELECTED: " + resume);
        } else if (hasSkills) {
            resume.setScreeningResult("MAYBE");
            System.out.println("⚠ MAYBE: " + resume);
        } else {
            resume.setScreeningResult("REJECTED");
            System.out.println("✗ REJECTED: " + resume);
        }
    }
    
    // Display screening results
    public void displayResults() {
        System.out.println("\n=== Screening Results Summary ===");
        int selected = 0, maybe = 0, rejected = 0;
        
        for (Resume<? extends JobRole> resume : allResumes) {
            System.out.println(resume + " -> " + resume.getScreeningResult());
            
            if ("SELECTED".equals(resume.getScreeningResult())) selected++;
            else if ("MAYBE".equals(resume.getScreeningResult())) maybe++;
            else rejected++;
        }
        
        System.out.println("\nSummary: Selected=" + selected + 
                         ", Maybe=" + maybe + ", Rejected=" + rejected);
    }
}

// Demo class
public class ResumeScreeningDemo {
    public static void main(String[] args) {
        ResumeScreeningSystem system = new ResumeScreeningSystem();
        
        // Create resumes for different roles
        Resume<SoftwareEngineer> resume1 = new Resume<>(
            "Alice Johnson", 3, 
            Arrays.asList("Java", "Spring", "SQL"), 
            new SoftwareEngineer());
        
        Resume<SoftwareEngineer> resume2 = new Resume<>(
            "Bob Smith", 1, 
            Arrays.asList("Python", "Django"), 
            new SoftwareEngineer());
        
        Resume<DataScientist> resume3 = new Resume<>(
            "Carol White", 5, 
            Arrays.asList("Python", "Machine Learning", "TensorFlow"), 
            new DataScientist());
        
        Resume<DataScientist> resume4 = new Resume<>(
            "David Lee", 2, 
            Arrays.asList("Java", "Spring Boot"), 
            new DataScientist());
        
        Resume<ProductManager> resume5 = new Resume<>(
            "Emma Davis", 6, 
            Arrays.asList("Product Strategy", "Agile", "User Research"), 
            new ProductManager());
        
        // Process individual resumes
        System.out.println("=== Individual Resume Processing ===");
        system.processResume(resume1);
        system.processResume(resume2);
        system.processResume(resume3);
        
        // Batch process using wildcards
        List<Resume<? extends JobRole>> batchResumes = Arrays.asList(resume4, resume5);
        system.screenMultipleResumes(batchResumes);
        
        // Display final results
        system.displayResults();
    }
}