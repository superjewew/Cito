package com.meyourours.cito;

import com.meyourours.cito.formula.Formulas;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by norman on 10/22/15.
 */
public class FormulaTest {

    @Test
    public void formulaValidator_EBM_ReturnCorrectValue() {
        assertThat(Formulas.getPostTestProb(0.4f, 0.83f, 0.96f, true), is(0.9325842f));
    }

    @Test
    public void formulaValidator_Dosage_ReturnCorrectValue() {
        assertThat(Formulas.getDosage(2f, 40f), is(80f));
    }
}
