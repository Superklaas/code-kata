public class SavingsCalculator {

    public static void main(String[] args) {

        double yearlySavings = 1000;
        double growthFactor = 1.021;
        int years = 10;

        double totalGrowthFactor = 0;
        for (int i = 1; i <= years; i++) {
            totalGrowthFactor += Math.pow(growthFactor, i);
        }
        double totalSavings = yearlySavings * totalGrowthFactor;
        System.out.printf("Total savings amount: € %,.2f%n", totalSavings);

        double totalProfit = totalSavings - yearlySavings * years;
        System.out.printf("Total profit: € %,.2f%n", totalProfit);

        double totalInterestRate = totalProfit / (yearlySavings * years) * 100;
        System.out.printf("Total interest rate: %.2f %%%n", totalInterestRate);

    }

}
