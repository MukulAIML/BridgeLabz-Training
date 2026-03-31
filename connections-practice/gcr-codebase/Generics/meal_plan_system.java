// Problem 4: Personalized Meal Plan Generator

// Base interface for meal plans
interface MealPlan {
    String getPlanType();
    boolean isValidMeal(String food);
}

// Specific meal plan types
class VegetarianMeal implements MealPlan {
    public String getPlanType() { return "Vegetarian"; }
    
    public boolean isValidMeal(String food) {
        String lowerFood = food.toLowerCase();
        return !lowerFood.contains("meat") && !lowerFood.contains("chicken") && 
               !lowerFood.contains("fish");
    }
}

class VeganMeal implements MealPlan {
    public String getPlanType() { return "Vegan"; }
    
    public boolean isValidMeal(String food) {
        String lowerFood = food.toLowerCase();
        return !lowerFood.contains("meat") && !lowerFood.contains("dairy") && 
               !lowerFood.contains("egg") && !lowerFood.contains("fish");
    }
}

class KetoMeal implements MealPlan {
    public String getPlanType() { return "Keto"; }
    
    public boolean isValidMeal(String food) {
        String lowerFood = food.toLowerCase();
        return !lowerFood.contains("bread") && !lowerFood.contains("rice") && 
               !lowerFood.contains("pasta");
    }
}

class HighProteinMeal implements MealPlan {
    public String getPlanType() { return "High-Protein"; }
    
    public boolean isValidMeal(String food) {
        String lowerFood = food.toLowerCase();
        return lowerFood.contains("chicken") || lowerFood.contains("fish") || 
               lowerFood.contains("eggs") || lowerFood.contains("tofu");
    }
}

// Generic Meal class with bounded type parameter
class Meal<T extends MealPlan> {
    private String mealName;
    private int calories;
    private T planType;
    
    public Meal(String mealName, int calories, T planType) {
        this.mealName = mealName;
        this.calories = calories;
        this.planType = planType;
    }
    
    public String getMealName() { return mealName; }
    public int getCalories() { return calories; }
    public T getPlanType() { return planType; }
    
    @Override
    public String toString() {
        return mealName + " (" + calories + " cal) - " + planType.getPlanType() + " Plan";
    }
}

// Meal plan generator with generic methods
class MealPlanGenerator {
    private List<Meal<? extends MealPlan>> meals;
    
    public MealPlanGenerator() {
        this.meals = new ArrayList<>();
    }
    
    // Generic method to validate and add meal
    public <T extends MealPlan> boolean validateAndAddMeal(Meal<T> meal) {
        if (meal.getPlanType().isValidMeal(meal.getMealName())) {
            meals.add(meal);
            System.out.println("✓ Valid meal added: " + meal);
            return true;
        } else {
            System.out.println("✗ Invalid meal for " + 
                meal.getPlanType().getPlanType() + " plan: " + meal.getMealName());
            return false;
        }
    }
    
    // Generic method to generate meal plan
    public <T extends MealPlan> List<Meal<T>> generatePlan(T planType, List<Meal<T>> candidateMeals) {
        List<Meal<T>> validMeals = new ArrayList<>();
        System.out.println("\n=== Generating " + planType.getPlanType() + " Meal Plan ===");
        
        for (Meal<T> meal : candidateMeals) {
            if (planType.isValidMeal(meal.getMealName())) {
                validMeals.add(meal);
                System.out.println("✓ " + meal.getMealName() + " (" + meal.getCalories() + " cal)");
            }
        }
        
        return validMeals;
    }
    
    // Calculate total calories
    public <T extends MealPlan> int calculateTotalCalories(List<Meal<T>> meals) {
        int total = 0;
        for (Meal<T> meal : meals) {
            total += meal.getCalories();
        }
        return total;
    }
    
    // Display all meals
    public void displayAllMeals() {
        System.out.println("\n=== All Meals in Database ===");
        for (Meal<? extends MealPlan> meal : meals) {
            System.out.println("- " + meal);
        }
    }
}

// Demo class
public class MealPlanDemo {
    public static void main(String[] args) {
        MealPlanGenerator generator = new MealPlanGenerator();
        
        // Create meals for different plans
        Meal<VegetarianMeal> vegMeal1 = new Meal<>(
            "Vegetable Salad", 250, new VegetarianMeal());
        Meal<VegetarianMeal> vegMeal2 = new Meal<>(
            "Grilled Chicken", 400, new VegetarianMeal()); // Invalid
        
        Meal<VeganMeal> veganMeal1 = new Meal<>(
            "Tofu Stir Fry", 300, new VeganMeal());
        Meal<VeganMeal> veganMeal2 = new Meal<>(
            "Dairy Smoothie", 200, new VeganMeal()); // Invalid
        
        Meal<KetoMeal> ketoMeal1 = new Meal<>(
            "Grilled Salmon", 450, new KetoMeal());
        Meal<KetoMeal> ketoMeal2 = new Meal<>(
            "Pasta Carbonara", 600, new KetoMeal()); // Invalid
        
        Meal<HighProteinMeal> proteinMeal1 = new Meal<>(
            "Chicken Breast", 350, new HighProteinMeal());
        
        // Validate and add meals
        System.out.println("=== Validating Meals ===");
        generator.validateAndAddMeal(vegMeal1);
        generator.validateAndAddMeal(vegMeal2);
        generator.validateAndAddMeal(veganMeal1);
        generator.validateAndAddMeal(veganMeal2);
        generator.validateAndAddMeal(ketoMeal1);
        generator.validateAndAddMeal(ketoMeal2);
        generator.validateAndAddMeal(proteinMeal1);
        
        // Generate specific meal plan
        List<Meal<KetoMeal>> ketoOptions = Arrays.asList(
            new Meal<>("Avocado Salad", 280, new KetoMeal()),
            new Meal<>("Grilled Steak", 500, new KetoMeal()),
            new Meal<>("Rice Bowl", 400, new KetoMeal()) // Will be filtered out
        );
        
        List<Meal<KetoMeal>> ketoPlan = generator.generatePlan(new KetoMeal(), ketoOptions);
        System.out.println("Total Calories: " + generator.calculateTotalCalories(ketoPlan));
        
        // Display all valid meals
        generator.displayAllMeals();
    }
}