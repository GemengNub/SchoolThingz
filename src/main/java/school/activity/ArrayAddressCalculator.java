package school.activity; /* Comment */

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Array Address Calculator
 * This program calculates the exact memory address of elements in 2D or 3D arrays
 * based on the discussed formula on the lesson and user inputted starting address.
 */
public class ArrayAddressCalculator {
    public static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            System.out.println("=== Array Address Calculator ===");
            System.out.println("This program calculates memory addresses for 2D/3D array elements");
            System.out.println();
            
            // Get array type from user
            int arrayType = getArrayType();
            
            if (arrayType == 2) {
                handle2DArray();
            } else {
                handle3DArray();
            }
            
        } catch (InputMismatchException e) {
            System.err.println("Error: Invalid input format. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Gets the array type (2D or 3D) from user input
     */
    static int getArrayType() {
        while (true) {
            try {
                System.out.print("Enter array type (2 for 2D, 3 for 3D): ");
                int type = scanner.nextInt();
                
                if (type == 2 || type == 3) {
                    return type;
                }
                else{
                    throw new InputMismatchException("Please enter either 2 or 3.");
                }
                
                
                
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (2 or 3).");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    /**
     * Handles 2D array address calculation
     */
    static void handle2DArray() {
        System.out.println("\n=== 2D Array Address Calculator ===");
        
        // Get array dimensions
        int rows = getPositiveInteger("Enter number of rows: ");
        int cols = getPositiveInteger("Enter number of columns: ");
        
        // Get element size and starting address
        System.out.println("\nCommon data type sizes: int=4, long=8, char=2, short=2, double=8, float=4 bytes");
        int elementSize = getPositiveInteger("Enter size of each element in bytes : ");
        long startingAddress = getPositiveLong("Enter starting address (in decimal): ");
        
        // Display array structure for reference
        display2DArrayStructure(rows, cols);
        
        // Get element position from user
        int[] position = get2DElementPosition(rows, cols);
        int row = position[0];
        int col = position[1];
        
        /* 
        Calculate address using the given formula
        Address = Base + ((row * cols) + col) * elementSize
        */ 
        long address = startingAddress + ((long)row * cols + col) * elementSize;
        
        // Display results
        System.out.println("\n=== Results ===");
        System.out.println("Array dimensions: " + rows + " x " + cols);
        System.out.println("Element position: [" + row + "][" + col + "]");
        System.out.println("Element size: " + elementSize + " bytes");
        System.out.println("Starting address: " + startingAddress);
        System.out.println("Calculated address: " + address);
        System.out.println("Address in hexadecimal: 0x" + Long.toHexString(address).toUpperCase());
    }
    
    /**
     * Handles 3D array address calculation
     */
    static void handle3DArray() {
        System.out.println("\n=== 3D Array Address Calculator ===");
        
        // Get array dimensions
        int depth = getPositiveInteger("Enter number of layers (depth): ");
        int rows = getPositiveInteger("Enter number of rows: ");
        int cols = getPositiveInteger("Enter number of columns: ");
        
        // Get element size and starting address
        System.out.println("\nCommon data type sizes: int=4, long=8, char=2, short=2, double=8, float=4 bytes");
        int elementSize = getPositiveInteger("Enter size of each element in bytes (applies to ALL elements): ");
        long startingAddress = getPositiveLong("Enter starting address (in decimal): ");
        
        // Display array structure for reference
        display3DArrayStructure(depth, rows, cols);
        
        // Get element position from user
        int[] position = get3DElementPosition(depth, rows, cols);
        int layer = position[0];
        int row = position[1];
        int col = position[2];
        
        // Calculate address using row-major order formula for 3D
        // Address = Base + ((layer * rows * cols) + (row * cols) + col) * elementSize
        long address = startingAddress + ((long)layer * rows * cols + (long)row * cols + col) * elementSize;
        
        // Display results
        System.out.println("\n=== Results ===");
        System.out.println("Array dimensions: " + depth + " x " + rows + " x " + cols);
        System.out.println("Element position: [" + layer + "][" + row + "][" + col + "]");
        System.out.println("Element size: " + elementSize + " bytes");
        System.out.println("Starting address: " + startingAddress);
        System.out.println("Calculated address: " + address);
        System.out.println("Address in hexadecimal: 0x" + Long.toHexString(address).toUpperCase());
    }
    
    /**
     * Gets element position for 2D array from user input
     */
    static int[] get2DElementPosition(int maxRows, int maxCols) {
        System.out.println("\nEnter the element position you want to calculate:");
        System.out.println("Note: You can enter either 'element numbers' (1-based) or 'indices' (0-based)");
        
        while (true) {
            try {
                System.out.print("Are you entering element numbers (1-based) or indices (0-based)? (e/i): ");
                String inputType = scanner.next().toLowerCase();
                
                if (inputType.equals("e") || inputType.equals("element")) {
                    int row = getPositiveInteger("Enter row element number (1 to " + maxRows + "): ");
                    int col = getPositiveInteger("Enter column element number (1 to " + maxCols + "): ");
                    
                    if (row > maxRows || col > maxCols) {
                        throw new IllegalArgumentException("Element numbers exceed array dimensions");
                    }
                    
                    return new int[]{row - 1, col - 1}; // Convert to 0-based indices
                    
                } else if (inputType.equals("i") || inputType.equals("index")) {
                    int row = getNonNegativeInteger("Enter row index (0 to " + (maxRows - 1) + "): ");
                    int col = getNonNegativeInteger("Enter column index (0 to " + (maxCols - 1) + "): ");
                    
                    if (row >= maxRows || col >= maxCols) {
                        throw new IllegalArgumentException("Indices exceed array bounds");
                    }
                    
                    return new int[]{row, col};
                    
                } else {
                    System.out.println("Please enter 'e' for element numbers or 'i' for indices.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format. Please try again.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    /**
     * Gets element position for 3D array from user input
     */
    static int[] get3DElementPosition(int maxDepth, int maxRows, int maxCols) {
        System.out.println("\nEnter the element position you want to calculate:");
        System.out.println("Note: You can enter either 'element numbers' (1-based) or 'indices' (0-based)");
        
        while (true) {
            try {
                System.out.print("Are you entering element numbers (1-based) or indices (0-based)? (e/i): ");
                String inputType = scanner.next().toLowerCase();
                
                if (inputType.equals("e") || inputType.equals("element")) {
                    int layer = getPositiveInteger("Enter layer element number (1 to " + maxDepth + "): ");
                    int row = getPositiveInteger("Enter row element number (1 to " + maxRows + "): ");
                    int col = getPositiveInteger("Enter column element number (1 to " + maxCols + "): ");
                    
                    if (layer > maxDepth || row > maxRows || col > maxCols) {
                        throw new IllegalArgumentException("Element numbers exceed array dimensions");
                    }
                    
                    return new int[]{layer - 1, row - 1, col - 1}; // Convert to 0-based indices
                    
                } else if (inputType.equals("i") || inputType.equals("index")) {
                    int layer = getNonNegativeInteger("Enter layer index (0 to " + (maxDepth - 1) + "): ");
                    int row = getNonNegativeInteger("Enter row index (0 to " + (maxRows - 1) + "): ");
                    int col = getNonNegativeInteger("Enter column index (0 to " + (maxCols - 1) + "): ");
                    
                    if (layer >= maxDepth || row >= maxRows || col >= maxCols) {
                        throw new IllegalArgumentException("Indices exceed array bounds");
                    }
                    
                    return new int[]{layer, row, col};
                    
                } else {
                    System.out.println("Please enter 'e' for element numbers or 'i' for indices.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format. Please try again.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    /**
     * Displays 2D array structure for user reference
     */
    static void display2DArrayStructure(int rows, int cols) {
        System.out.println("\nArray structure reference:");
        System.out.println("Element numbers (1-based):");
        
        for (int i = 1; i <= rows; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 1; j <= cols; j++) {
                System.out.printf("[%d,%d] ", i, j);
            }
            System.out.println();
        }
        
        System.out.println("\nIndices (0-based):");
        for (int i = 0; i < rows; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < cols; j++) {
                System.out.printf("[%d][%d] ", i, j);
            }
            System.out.println();
        }
    }
    
    /**
     * Displays 3D array structure for user reference
     */
    static void display3DArrayStructure(int depth, int rows, int cols) {
        System.out.println("\nArray structure reference (showing first layer only):");
        System.out.println("Element numbers (1-based) - Layer 1:");
        
        for (int i = 1; i <= rows; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 1; j <= cols; j++) {
                System.out.printf("[1,%d,%d] ", i, j);
            }
            System.out.println();
        }
        
        System.out.println("\nIndices (0-based) - Layer 0:");
        for (int i = 0; i < rows; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < cols; j++) {
                System.out.printf("[0][%d][%d] ", i, j);
            }
            System.out.println();
        }
        
        System.out.println("(Similar pattern for all " + depth + " layers)");
    }
    
    /**
     * Gets a positive integer from user input with validation
     */
    static int getPositiveInteger(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                
                if (value <= 0) {
                    throw new IllegalArgumentException("Value must be positive (greater than 0)");
                }
                
                return value;
                
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.nextLine(); // Clear invalid input
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Gets a non-negative integer from user input with validation
     */
    static int getNonNegativeInteger(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                
                if (value < 0) {
                    throw new IllegalArgumentException("Value must be non-negative (0 or greater)");
                }
                
                return value;
                
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a non-negative integer.");
                scanner.nextLine(); // Clear invalid input
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Gets a positive long from user input with validation
     */
    static long getPositiveLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                long value = scanner.nextLong();
                
                if (value <= 0) {
                    throw new IllegalArgumentException("Address must be positive (greater than 0)");
                }
                
                return value;
                
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive number.");
                scanner.nextLine(); // Clear invalid input
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
