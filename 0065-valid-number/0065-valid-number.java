class Solution {
    public boolean isNumber(String s) {

        boolean digitSeen = false;
        boolean dotSeen = false;
        boolean exponentSeen = false;
        boolean exponentDigitSeen = true;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            // Digit
            if (Character.isDigit(c)) {

                digitSeen = true;

                // If we are after e/E, exponent must contain a digit
                if (exponentSeen) {
                    exponentDigitSeen = true;
                }
            }

            // Decimal point
            else if (c == '.') {

                // Only one dot and dot cannot appear after exponent
                if (dotSeen || exponentSeen) {
                    return false;
                }

                dotSeen = true;
            }

            // Exponent
            else if (c == 'e' || c == 'E') {

                // Exponent requires a number before it
                // and there can only be one exponent
                if (exponentSeen || !digitSeen) {
                    return false;
                }

                exponentSeen = true;
                exponentDigitSeen = false;
            }

            // Sign
            else if (c == '+' || c == '-') {

                // Sign is allowed only at beginning
                // or immediately after e/E
                if (i != 0 &&
                    s.charAt(i - 1) != 'e' &&
                    s.charAt(i - 1) != 'E') {

                    return false;
                }
            }

            // Anything else
            else {
                return false;
            }
        }

        return digitSeen && exponentDigitSeen;
    }
}