import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                String result = calc(input);
                System.out.println("Output: " + result);
            } catch (Exception e) {
                System.out.println("Output: throws Exception");
                System.exit(1);
            }
        }
    }

    public static String calc(String input) throws Exception {
        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            throw new Exception("Invalid input format");
        }

        String operand1 = tokens[0];
        String operator = tokens[1];
        String operand2 = tokens[2];

        int num1, num2;

        try {
            num1 = RomanConverter.convertToArabic(operand1);
            num2 = RomanConverter.convertToArabic(operand2);
        } catch (NumberFormatException e) {
            try {
                num1 = Integer.parseInt(operand1);
                num2 = Integer.parseInt(operand2);
            } catch (NumberFormatException ex) {
                throw new Exception("Invalid input: both operands should be either Arabic or Roman numerals");
            }
        }

        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
            throw new Exception("Invalid input: numbers should be between 1 and 10 inclusive");
        }

        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new Exception("Invalid input: division by zero");
                }
                result = num1 / num2;
                break;
            default:
                throw new Exception("Invalid input: unsupported operator");
        }

        if (result < 1 || result > 10) {
            throw new Exception("Invalid output: result should be between 1 and 10 inclusive");
        }

        if (input.contains("I") || input.contains("V") || input.contains("X")) {
            return RomanConverter.convertToRoman(result);
        } else {
            return Integer.toString(result);
        }
    }
}

class RomanConverter {
    private static final String[] ROMAN_NUMERALS = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static int convertToArabic(String roman) {
        for (int i = 0; i < ROMAN_NUMERALS.length; i++) {
            if (ROMAN_NUMERALS[i].equals(roman)) {
                return i + 1;
            }
        }
        throw new NumberFormatException("Invalid Roman numeral");
    }

    public static String convertToRoman(int arabic) {
        if (arabic < 1 || arabic > 10) {
            throw new IllegalArgumentException("Number out of Roman numeral range");
        }
        return ROMAN_NUMERALS[arabic - 1];
    }
}
