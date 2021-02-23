package com.jlbc.polynomials;

import java.util.ArrayList;

public class Polynomial {

    // <editor-fold desc="INTERNAL FIELDS">

    private final ArrayList<Monomial> terms;

    // </editor-fold>

    // <editor-fold desc="CONSTRUCTORS">

    public Polynomial()
    {
        // Creates a new empty list
        this.terms = new ArrayList<>();
        // Add the monomial equal to zero
        this.terms.add(new Monomial());
    }

    // </editor-fold>

    // <editor-fold desc="PROPERTIES">

    public ArrayList<Double> getCoefficients()
    {
        ArrayList<Double> coefficients = new ArrayList<>();
        // Traverse the list of monomials extracting
        // their coefficients.
        this.terms.forEach(monomial -> {
            coefficients.add(monomial.getCoefficient());
        });
        return coefficients;
    }
    
    public ArrayList<Integer> getExponents()
    {
        ArrayList<Integer> exponents = new ArrayList<>();
        // Traverse the list of monomials extracting
        // their exponents.
        this.terms.forEach(monomial -> {
            exponents.add(monomial.getExponent());
        });
        return exponents;
    }
    
    public int getDegree()
    {
        // It is assumed that list of terms is sorted from 
        // largest to smallest, so the monomial with largest 
        // degree is the first one.
        return this.terms.get(0).getExponent();
    }

    // </editor-fold>

    // <editor-fold desc="METHODS">

    // This method assures that the list of terms is
    // always simplified (cannot exist multiple terms
    // with same degree) and sorted from largest to 
    // smaller degree.
    public void Append(Monomial monomial) throws Exception
    {
        // The monomial to add must be different than zero.
        if (monomial.getCoefficient() != 0)
        {
            // If the actual polynomial is zero, internal list
            // only contains one monomial and this monomial will
            // be replaced with the one received.
            if (this.terms.size() == 1 && this.terms.get(0).getCoefficient() == 0)
            {
                this.terms.set(0, monomial);
            }
            else
            {
                boolean appended = false;
                int i = 0;
                while (!appended && i < this.terms.size())
                {
                    // If the exponent of the monomial to append is greater
                    // than the exponent of the monomial [i] analyzed, it 
                    // means that monomial to append does not exists in the
                    // polynomial, so it is inserted in the currente position
                    // to maintain the order of the list.
                    if (monomial.getExponent() > this.terms.get(i).getExponent())
                    {
                        this.terms.add(i, monomial);
                        appended = true;
                    }
                    // If the exponent of the monomial to append is equal to
                    // the exponent of the monomial [i] analyzed, perform
                    // addition between monomials and replaces the current
                    // monomial; in the case the sum of the monomials is zero,
                    // removes the current monomial from the list.
                    else if (monomial.getExponent() == this.terms.get(i).getExponent())
                    {
                        if (this.terms.get(i).getCoefficient() + monomial.getCoefficient() != 0)
                        {
                            this.terms.set(i, this.terms.get(i).add(monomial));
                        }
                        else
                        {
                            this.terms.remove(i);
                        }
                        appended = true;
                    }
                    // Go to the next monomial in the internal list.
                    else
                    {
                        i++;
                    }
                }
                // If monomial has not been appended at this point, append it
                // at the end of the list.
                if (!appended)
                {
                    this.terms.add(monomial);
                }
                // If the list becomes empty, it means that the polynomial is
                // zero, so append a monomial equal to zero.
                if (this.terms.isEmpty())
                {
                    this.terms.add(new Monomial());
                }
                // If the list has only one monomial and its coefficient is 
                // zero, it means the that polynomial is zero, so replace 
                // with a monomial equal to zero (this is only for consistency
                // of monomial representation).
                else if (this.terms.size() == 1 && this.terms.get(0).getCoefficient() == 0)
                {
                    this.terms.set(0, new Monomial());
                }
            }
        }
    }

    public Polynomial Add(Polynomial polynomial) throws Exception
    {
        Polynomial sum = new Polynomial();
        // Traverse to add the terms of this polynomial
        for(Monomial monomial : this.terms)
        {
            sum.Append(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
        }
        // Traverse to add the terms of received polyomial
        for (Monomial monomial : polynomial.terms)
        {
            sum.Append(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
        }
        return sum;
    }

    public Polynomial Subtract(Polynomial polynomial) throws Exception
    {
        Polynomial difference = new Polynomial();
        // Traverse to add the terms of this polynomial
        for (Monomial monomial : this.terms)
        {
            difference.Append(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
        }
        // Traverse to add the inverse terms of received polyomial
        for (Monomial monomial : polynomial.terms)
        {
            difference.Append(new Monomial(-monomial.getCoefficient(), monomial.getExponent()));
        }
        return difference;
    }

    public Polynomial Multiply(Polynomial polynomial) throws Exception
    {
        Polynomial product = new Polynomial();
        // Traverse the terms of this polynomial
        for (Monomial m1 : this.terms)
        {
            // Traverse to terms of received polyomial
            for (Monomial m2 : polynomial.terms)
            {
                // Multiply each pair of monomials and
                // add to product
                product.Append(m1.multiply(m2));
            }
        }
        return product;
    }

    // To evaluate the polynomial, sum all values of
    // its internal monomials.
    public double Evaluate(double value)
    {
        double result = 0;
        for (Monomial monomial : this.terms)
        {
            result += monomial.evaluate(value);
        }
        return result;
    }

    // To convert the polynomial to string, concatenate
    // all string representations of its internal monomials.
    @Override
    public String toString()
    {
        String result = "";
        for (Monomial monomial : this.terms)
        {
            result += (" " + monomial.toString());
        }
        return result.trim();
    }

    // </editor-fold>
}
