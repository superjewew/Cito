package com.meyourours.cito.formula;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.meyourours.cito.R;
import com.meyourours.cito.activity.CountActivity;

/**
 * Created by norman on 7/18/15.
 */
public class Formulas {

    public static final String[] CATEGORY = {"Anak", "Fluid", "Geriatri", "Internal Medicine", "Kebidanan", "Pulmonologi", "Rehabilitasi"};
    public static final int[] FORMULA_LIST = {R.array.anak, R.array.fluid, R.array.geriatri,
                R.array.internal_medicine, R.array.kebidanan, R.array.pulmonologi, R.array.rehabilitasi};
    public static final int[] FORMULA_DESC = {R.array.anak_desc, R.array.fluid_desc, R.array.geriatri_desc,
            R.array.internal_medicine_desc, R.array.kebidanan_desc, R.array.pulmonologi_desc, R.array.rehabilitasi_desc};

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
            LR = (1 - sensitivity)/specificity;
        }
        float preTestOdds = preTestProb / (1 - preTestProb);
        float postTestOdds = preTestOdds * LR;
        result = postTestOdds / (postTestOdds + 1);

        return result;
    }

    public static float getDosage(float mlkg, float weight) {
        return mlkg * weight;
    }

    public static String getDosage(int medicine, int age, int weight) {
        switch(medicine) {
            case 0:
                if(0 < age && age <= 1) {
                    return "1/20 - 1/8 tablet";
                } else if(1 < age && age <= 5) {
                    return "1/8 - 1/4 tablet";
                } else if(5 < age && age <= 12) {
                    return "1/4 - 1/2 tablet";
                } else {
                    return "2 x 1 tablet (awal), max 2 x 2 tablet";
                }
            case 1:
                if(age <= 12) {
                    return "Tidak direkomendasikan";
                } else {
                    return "rawat jalan: (awal) 1x2 tab, maintenance 1 x 1-4\n" +
                            "rawat inap: 3-6 x 1 tab";
                }
            case 2:
                if(5 <= age && age <= 12) {
                    return "2 - 3 x 1 tablet";
                } else {
                    return "3 x 2 tablet, turunkan dosis setelah 2-3 hari 3 x 1 tablet";
                }
            case 3:
                String res;
                if(2 <= age && age <= 5) {
                    res = "3 x 1/2 sdt";
                } else {
                    res = "3 x 2 sdt";
                }
                return res + ", segera setelah makan";
            case 4:
                if(age <= 12) {
                    return "3 x " + 20 * weight / 3 + " mg";
                } else {
                    return "3 x 1/2 - 1 tablet";
                }
            case 5:
                if(age <= 12) {
                    return "3 x " + 20 * weight / 3 + " mg";
                } else {
                    return "3 x 1 - 2 tablet";
                }
            case 6:
                return "1 sdt = 125 mg/5 ml\n" +
                        "forte = 250 mg/5 ml\n" +
                        "drop = 100 mg/ml";
            case 7:
                if(age <= 12) {
                    return "Tidak dianjurkan untuk anak-anak";
                } else {
                    return "3 x 1-2 tab, max 6 tab/hari";
                }
            case 8:
                if(age <= 12) {
                    return "Tidak dianjurkan untuk anak-anak";
                } else {
                    return "2 x 1 , dikunyah 30 menit sebelum makan";
                }
            case 9:
                return "";
            case 10:
                if(age <= 12) {
                    return "Tidak dianjurkan untuk anak-anak";
                } else {
                    return "1-2 x 1 (tiap sebelum tidur)";
                }
            case 11:
                if(age <= 12) {
                    return "Tidak dianjurkan untuk anak-anak";
                } else {
                    return "1 x 1 tablet";
                }
            case 12:
                if(age <= 12) {
                    return "Tidak dianjurkan untuk anak-anak";
                } else {
                    return "3 x 1 (awal), lanjutan 3 x 1/2, max 7 hari";
                }
            case 13:
                if(age <= 12) {
                    return "Tidak dianjurkan untuk anak-anak";
                } else {
                    return "dosis awal 1/4 - 1 mg/hari\n" +
                            "rumatan 1/4-1/2 mg/hari\n" +
                            "ibu hamil dan anemia 1/2-1 mg/hari";
                }
            case 14:
                if(age <= 12) {
                    return "Tidak dianjurkan untuk anak-anak";
                } else {
                    return "3 x 1/4-1/2 mg, ditingkatkan tiap 3-4 hari max 4 mg/hari";
                }
            case 15:
                if(age <= 12) {
                    return "Tidak dianjurkan untuk anak-anak";
                } else {
                    return "ketokonazol cr 2%\n" +
                            "1 x/hari, kecuali seboroik 2 x\n" +
                            "mikonazol cr 2%\n" +
                            "2 x/hari, kecuali versikolor 1 x";
                }
            case 16:
                if(age <= 12) {
                    return "Tidak dianjurkan untuk anak-anak";
                } else {
                    return "";
                }
            case 17:
                if(age <= 12) {
                    return "2 x 1/2 tablet";
                } else {
                    return "1 x 1 tablet";
                }
            case 18:
                if(age <= 12) {
                    return 5 * weight + " - " + 10 * weight + " mg/kali (100 mg/5 cc)";
                } else {
                    return "3 - 4 x 400 mg";
                }
            case 19:
                if(age <= 12) {
                    if(3 <= weight && weight < 6) {
                        return "2 x 1/4 (2 ml)";
                    } else if (6 < weight && weight < 10){
                        return "2 x 1/2 (3.5 ml)";
                    } else if (10 < weight && weight <= 15) {
                        return "2 x 1 (6 ml)";
                    } else if (15 < weight && weight <= 20) {
                        return "2 x 1 (8.5 ml)";
                    }
                } else {
                    return "2 x 2 tablet";
                }
            case 20:
                return "";
            case 21:
                if(age <= 12) {
                    if(weight < 40) {
                        return "2 x " + 25 * weight / 2 + " mg/hari";
                    }
                } else {
                    return "2 x 1 - 2 tablet";
                }
            case 22:
                if(age <= 12) {
                    return "125 mg/5 cc";
                } else {
                    return "";
                }
            case 23:
                if(age <= 12) {
                    return "2 x " + 1.5f * weight/2 + " - " + 3 * weight/2 + " mg/hari , max " + 6 * weight + " mg/hari";
                } else {
                    return "1-2 x 1, max 2x2 tab";
                }
            case 24:
                if(age <= 12) {
                    return 0.03f * weight + " - " + 0.09f * weight + " mg/hari";
                } else {
                    return "";
                }
            case 25:
                if(age <= 12) {
                    return 0.2f * weight + " - " + 0.4f * weight + " mg/hari (bagi 3-6 kali)";
                } else {
                    return "3-6 x 1-2 tab";
                }
            case 26:
                if(age <= 12) {
                    return 2 * weight + " - " + 3 * weight + " mg/hari (dibagi 4-6 dosis)";
                } else {
                    return "3-4 x 1-2 tab";
                }
            case 27:
                if(2 < age && age <= 6) {
                    return "6 x 1/2-1 (max 6 tab)";
                } else if(6 < age && age <= 12) {
                    return "6 x 1-2 (max 12 tab)";
                } else {
                    return "6 x 2-4 tab (max 24 tab)";
                }
            case 28:
                return "";
            case 29:
                if(age <= 12) {
                    return "5 mg/5 cc\n" +
                            "2 x " + 0.5f *  weight/2 + " mg/hari";
                } else {
                    return "3x 5-10 mg/hari (sediaan 10 mg)";
                }
            case 30:
                if(age <= 12) {
                    return weight + " - " + 2 * weight + " mg/hari (dibagi 2 atau satu kali)";
                } else {
                    return "40-60 mg (dibagi 2 atau satu aja)";
                }
            case 31:
                if(age <= 12) {
                    return "zinkid syr 20 mg/5 cc\n" +
                            "< 6 bln: 10 mg\n" +
                            "> 6 bln: 20 mg";
                } else {
                    return "";
                }
            case 32:
                if(age <= 12) {
                    return "";
                } else {
                    return "3x 1-2 tab";
                }
            case 33:
                if(6 < age && age <= 12) {
                    return "1 tab tiap setelah diare, max 6 tab/hari";
                } else {
                    return "2 tab tiap setelah diare, max 12 tab/hari";
                }
            case 34:
                if(age <= 12) {
                    return "Tidak direkomendasikan";
                } else {
                    return "tiap 12 jam (potensi rendah atau sedang\n" +
                            "digunakan kurang dari 2 minggu";
                }
            case 35:
                if(age <= 12) {
                    if(10 <= weight && weight < 20) {
                        return "1 x 1/2 tablet selama 14 hari";
                    } else if(20 <= weight && weight <= 29) {
                        return "1 x 1 tablet selama 14 hari";
                    }
                } else {
                    return "";
                }
            case 36:
                if(1 <= age && age <= 6) {
                    return "3 - 4x 1/4 tablet/hari";
                } else if(6 < age && age <= 12) {
                    return "3 - 4x 1/2 tablet/hari";
                } else {
                    return "3 - 4x 1/2 - 1 tablet/hari";
                }
            case 37:
                if(age <= 12) {
                    return "2x " + 0.5 * weight/2 + " - " + 1.7 * weight/2 + " mg/hari";
                } else {
                    return "";
                }
            case 38:
                if(age <= 12) {
                    if(weight < 10) {
                        return "2x 100mg tablet selama 3 hari";
                    } else {
                        return "1x 500mg tablet";
                    }
                } else {
                    return "";
                }
            case 39:
                if(age <= 2) {
                    return "Tidak dianjurkan";
                } else if(2 < age && age <= 6) {
                    return "1 x 1/2 tablet";
                } else {
                    return "1 x 1 tablet";
                }
            default:
                return "";
        }
    }

    public static String getBishopString(int result) {
        if(result < 5) {
            return "Unlikely to start without induction";
        } else if(result >= 9) {
            return "Most likely commence spontaneously";
        } else {
            return "Probably need induction";
        }
    }

    public static int getBishopScore(int position, int consistency, int effacement, int dilation, int fetal) {
        return position + consistency + effacement + dilation + fetal;
    }

    public static int getDaldiyonoScore(int... points) {
        int res = 0;
        for(int point : points) {
            res += point;
        }
        return res;
    }

    public static float getDaldiyonoString(int score, int weight) {
        return score/15.00f * weight * 100;
    }

    public static int getMaintenanceFluid(int weight) {
        int res = 0;
        int b = weight - 10;
        if(b < 0) {
            return weight * 100;
        } else {
            res += 10 * 100;
            int c = b - 10;
            if(c < 0) {
                return res + b * 50;
            } else {
                res += ((10 * 50) + (c * 20));
                return res;
            }
        }
    }

    public static double getFluidDropRate(int volume) {
        return Math.ceil(volume * 20.00f / 24 / 60);
    }

    public static float getMaxVolumeO2(float distance, int age, int height, int weight, int sex) {
        return (0.053f * distance) + (0.022f * age) + (0.032f * height) + (0.164f * weight - 2.228f * sex) - 2.287f;
    }

}








