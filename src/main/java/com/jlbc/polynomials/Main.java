package com.jlbc.polynomials;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("This program defines some polynomials and performs");
        System.out.println("operations between them.");
        System.out.println("");

        Polynomial[] polynomials = new Polynomial[4];
        // + 5 x^11 + 25 x^8 - 17 x^5
        polynomials[0] = new Polynomial();
        polynomials[0].Append(new Monomial(5, 11));
        polynomials[0].Append(new Monomial(25, 8));
        polynomials[0].Append(new Monomial(-17, 5));
        // + 2 x^4 - x^3 + 5 x - 5
        polynomials[1] = new Polynomial();
        polynomials[1].Append(new Monomial(2, 4));
        polynomials[1].Append(new Monomial(-1, 3));
        polynomials[1].Append(new Monomial(5, 1));
        polynomials[1].Append(new Monomial(-5, 0));
        // + 15 x^11
        polynomials[2] = new Polynomial();
        polynomials[2].Append(new Monomial(15, 11));
        // + 1
        polynomials[3] = new Polynomial();
        polynomials[3].Append(new Monomial(1, 0));

        System.out.println("POLYNOMIALS AND THEIR PROPERTIES");
        System.out.println();
        for (int i = 0; i < polynomials.length; i++)
        {
            System.out.println("P" + (i+1) + "(x) = " + polynomials[i].toString());
            System.out.println("* Coefficients: " + listDoubleToString(polynomials[i].getCoefficients()));
            System.out.println("* Exponents:" + listIntToString(polynomials[i].getExponents()));
            System.out.println("* Degree:" + polynomials[i].getDegree());
            System.out.println();
        }

        System.out.println("EVALUATION OF POLYNOMIALS");
        System.out.println();
        double[] values = new double[] {-2.4, -1.7, 0, 1, 3, 7.7};
        for (double value : values)
        {
            System.out.println("P1(" + value + ") = " + polynomials[0].Evaluate(value));
            System.out.println("P2(" + value + ") = " + polynomials[1].Evaluate(value));
            System.out.println("P3(" + value + ") = " + polynomials[2].Evaluate(value));
            System.out.println("P4(" + value + ") = " + polynomials[3].Evaluate(value));
            System.out.println();
        }

        System.out.println("OPERATION BETWEEN POLYNOMIALS");
        System.out.println();
        for (int i = 0; i < polynomials.length; i++)
        {
            for (int j = i+1; j < polynomials.length; j++)
            {
                System.out.println("P" +(i+1) + "(x) and P" + (j+1) + "(x)");
                System.out.println("* SUM: " + polynomials[i].Add(polynomials[j]).toString());
                System.out.println("* DIFFERENCE: " + polynomials[i].Subtract(polynomials[j]).toString());
                System.out.println("* PRODUCT: " + polynomials[i].Multiply(polynomials[j]).toString());
                System.out.println();
            }
        }

        System.out.println("");
        System.out.println("THANK YOU FOR USING THIS PROGRAM!");
    }
    
    // This is an auxiliary method to print a list of doubles.
    static String listDoubleToString(ArrayList<Double> list)
    {
        String result = "[";
        for (double e : list)
        {
            result += (e + ",");
        }
        return result.substring(0, result.length() - 1) + "]";
    }

    // This is an auxiliary method to print a list of integers.
    static String listIntToString(ArrayList<Integer> list)
    {
        String result = "[";
        for (int e : list)
        {
            result += (e + ",");
        }
        return result.substring(0, result.length() - 1) + "]";
    }

}
