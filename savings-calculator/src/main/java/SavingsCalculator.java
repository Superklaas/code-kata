public class SavingsCalculator {

    public static void main(String[] args) {

        double yearlySavings = 20000;
        double growthFactor = 1.021;
        int years = 10;

        double totalGrowthFactor2 = 0;
        for (int i2 = 1; i2 <= years; i2++) {
            totalGrowthFactor2 += Math.pow(growthFactor, i2);
        }
        double totalSavings = yearlySavings * totalGrowthFactor2;
        System.out.printf("Total savings amount: € %,.2f%n", totalSavings);

        double totalProfit = totalSavings - yearlySavings * years;
        System.out.printf("Total profit: € %,.2f%n", totalProfit);

        double totalInterestRate = totalProfit / (yearlySavings * years) * 100;
        System.out.printf("Total interest rate: %.2f %%%n", totalInterestRate);

    }


    //        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter yearly savings: ");
//        double yearlySavings = scanner.nextDouble();
//
//        System.out.println("Enter interest rate: ");
//        double growthFactor = 1 + scanner.nextDouble()/100;
//
//        System.out.println("Enter amount of years: ");
//        int years = scanner.nextInt();

}
