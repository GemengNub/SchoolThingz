package school.activity;

import java.util.Scanner;

public class MathFunction {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String[] options = {
            "1. Find Floor.",
            "2. Find Ceiling.",
            "3. Find Modulo."
        };

        for (String option : options) {
            System.out.println(option);
        }

        String input = getUserInput(scanner, "Choose an operator you want to run: ");

        try {
            switch (input.toLowerCase()) {
                case "1":
                case "floor": {
                    String inp = getUserInput(scanner, "Enter a number: ");
                    
                    // converting string to double.
                    double numberInput = Double.parseDouble(inp);
                    double result = floor(numberInput);

                    System.out.println("The rounded down number result of " + numberInput + " is " + result);
                }
                break;

                case "2":
                case "ceil":
                case "ceiling": {
                    String inp = getUserInput(scanner, "Enter a number: ");

                    // converting string to double.
                    double numberInput = Double.parseDouble(inp);
                    double result = ceiling(numberInput);

                    System.out.println("The rounded up number result of " + numberInput + " is " + result);
                }
                break;

                case "3":
                case "mod":
                case "modulo": {
                    String inp1 = getUserInput(scanner, "Enter a number for the dividend: ");
                    String inp2 = getUserInput(scanner, "Enter a number for the divisor: ");

                    // converting string to double.
                    double numberInput1 = Double.parseDouble(inp1);
                    double numberInput2 = Double.parseDouble(inp2);

                    double result = modulo(numberInput1, numberInput2);

                    if(result == 0.0){
                        System.out.println("The remainder of " + numberInput1 + " and " + numberInput2 + " is " + (int) result);
                    }
                    else{
                        System.out.println("The remainder of " + numberInput1 + " and " + numberInput2 + " is " + result);
                    }

                    
                }
                break;

                default: // defaults to this if it is not one of the options.
                    System.out.print("\033[H\033[2J"); // code the clear out the terminal.
                    System.out.println("That is not one of the options");
                    System.out.flush();
                    main(args);
                    break;
            }
        } catch (ArithmeticException e) {
            System.out.println(e);
        }
    }

    static String getUserInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Math.floor(), rounds down the double to the nearest integer.
     */
    static double floor(double number) {
        // checks if number is less than 1, if less than 1 it throws Arithmetic Error
        // if (number < 1.0) {
        //     throw new ArithmeticException("Number must be more than 0");
        // } else {
        return Math.floor(number); // Round down.

        //}
    }

    /**
     * Math.ceil(), rounds up the double to its nearest integer.
     */
    static double ceiling(double number) {
        // checks if number is less than 1, if less than 1 it throws Arithmetic Error
        // if (number < 1.0) {
        //     throw new ArithmeticException("Number must be more than 0");
        // } else {
            return Math.ceil(number); // Round up
        //}
    }

    /**
     * The modulo method gives the remainder of the dividend and the divisor.
     * eg: 3 % 1 = 0
     */
    static double modulo(double dividend, double divisor) {
        if (dividend == 0 || divisor == 0) {
            return 0.0;
        } else {
            return dividend % divisor;
        }
    }
}
