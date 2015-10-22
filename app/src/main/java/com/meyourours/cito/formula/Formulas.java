package com.meyourours.cito.formula;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.meyourours.cito.R;
import com.meyourours.cito.activity.CountActivity;

/**
 * Created by norman on 7/18/15.
 */
public class Formulas {

    public static final String[] CATEGORY = {"Anak", "Geriatri", "Pulmonologi"};
    public static final int[] FORMULA_LIST = {R.array.anak, R.array.geriatri, R.array.pulmonologi};
    public static final int[] FORMULA_DESC = {R.array.anak_desc, R.array.geriatri_desc, R.array.pulmonologi_desc};

    public static double countBMI(double height, double weight) {
        double res;
        res = weight / Math.pow(height/100, 2);
        return res;
    }

    public static double countCURB(int score) {
        switch(score) {
            case 0:
                return 0.60;
            case 1:
                return 2.70;
            case 2:
                return 6.80;
            case 3:
                return 14.00;
            case 4:
                return 27.80;
            case 5:
                return 27.80;
            default:
                return 0.00;
        }
    }

    public static void startCountActivity(FragmentActivity activity, String formulaName) {
        Intent intent = new Intent(activity, CountActivity.class);
        intent.putExtra("formula", formulaName);
        activity.startActivity(intent);
    }

    /**
     * Return post test probability for EBM
     * @param preTestProb Probability percentage pre test (e.g 0.4)
     * @param sensitivity Sensitivity of test
     * @param specificity Specificity of test
     * @return Probability percentage post test
     */
    public static float getPostTestProb(float preTestProb, float sensitivity, float specificity, boolean testResult) {
        float result;
        float LR;
        if(testResult) {
            LR = sensitivity / (1 - specificity);
        } else {
            LR = 1 - sensitivity/specificity;
        }
        float preTestOdds = preTestProb / (1 - preTestProb);
        float postTestOdds = preTestOdds * LR;
        result = postTestOdds / (postTestOdds + 1);

        return result;
    }

    public static float getDosage(float mlkg, float weight) {
        return mlkg * weight;
    }
}
