public class SearchIn2DMatrix {
    
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / cols;
            int col = mid % cols;
            int midValue = matrix[row][col];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        
        int target1 = 3;
        boolean result1 = searchMatrix(matrix, target1);
        System.out.println("Target: " + target1);
        System.out.println("Found: " + result1);
        
        int target2 = 13;
        boolean result2 = searchMatrix(matrix, target2);
        System.out.println("Target: " + target2);
        System.out.println("Found: " + result2);
    }
}

