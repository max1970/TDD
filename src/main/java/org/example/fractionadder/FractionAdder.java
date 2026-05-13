package org.example.fractionadder;

public class FractionAdder {
    private final Fraction fraction1;
    private final Fraction fraction2;

    public FractionAdder(Fraction fraction1, Fraction fraction2) {
        this.fraction1 = fraction1;
        this.fraction2 = fraction2;
    }

    public String addFractions(Fraction fraction1, Fraction fraction2) {
        int wholeNumber = 0;
        int denominator1 = fraction1.getDenominator();
        int denominator2 = fraction2.getDenominator();
        int numerator1 = fraction1.getNumerator();
        int numerator2 = fraction2.getNumerator();
        int aggregatedNumerators = numerator2 + numerator1;
        int commonDenominator = denominator1;

        if(denominator2 != denominator1) {
            commonDenominator = denominator2 * denominator1;
            aggregatedNumerators = numerator1 * denominator2 + numerator2 * denominator1;
        }

        if(aggregatedNumerators >= denominator1) {
            wholeNumber = aggregatedNumerators / commonDenominator;
            aggregatedNumerators = aggregatedNumerators % commonDenominator;
        }

        for(int i = aggregatedNumerators; i > 1; i--) {
            if(aggregatedNumerators % i == 0 && commonDenominator % i == 0) {
                aggregatedNumerators = aggregatedNumerators / i;
                commonDenominator = commonDenominator / i;
                break;
            }
        }

        return ((wholeNumber > 0 ? wholeNumber + "" : "") + " " + (aggregatedNumerators > 0 ? aggregatedNumerators + "/" + commonDenominator : "")).trim();
    }
}
