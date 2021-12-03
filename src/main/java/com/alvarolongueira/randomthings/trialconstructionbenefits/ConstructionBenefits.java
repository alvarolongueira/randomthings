package com.alvarolongueira.randomthings.trialconstructionbenefits;

import java.util.List;

public class ConstructionBenefits {

    /*
     * Complete the 'maxProfit' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER costPerCut
     * 2. INTEGER salePrice
     * 3. INTEGER_ARRAY lengths
     */
    public static int maxProfit(int costPerCut, int salePrice, List<Integer> lengths) {
        int maxProfit = 0;
        int maxLenght = lengths.stream().mapToInt(value -> value).max().orElse(0);

        for (int currentLenght = maxLenght; currentLenght > 0; currentLenght--) {
            int totalValue = 0;

            for (int currentRod : lengths) {
                int newRods = currentRod / currentLenght;
                int cuts = newRods;

                if ((currentRod % currentLenght) == 0) {
                    cuts--;
                }
                int value = (newRods * currentLenght * salePrice) - (cuts * costPerCut);
                if (value > 0) {
                    totalValue += value;
                }
            }
            if (totalValue > maxProfit) {
                maxProfit = totalValue;
            }
        }
        return maxProfit;
    }
}
