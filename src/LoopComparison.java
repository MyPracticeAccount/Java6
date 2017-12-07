import java.util.Scanner;

/**
 * @author Chris Angell
 * @version 1.0, Java Assn 6
 */

public class LoopComparison {

	public static void main(String[] args) {
		final int OPTION_NUM = 2; // number of option comparisons
		int customerNum; // number of customers
		double localCarPurchasePrice = 0;

		Scanner input0 = new Scanner(System.in);

		System.out.print("\nThis program implements a Car Loan " + "comparison. \nIt will compute and display "
				+ "the payoff schedule for two loan options");

		do {
			System.out.print("\n\nHow many customers want to compare loans? ");
			customerNum = input0.nextInt();

			if (customerNum <= 0) {
				System.out.print("\nInvalid input.");
			}
		} while (customerNum <= 0);

		for (int i = 0; i < customerNum; i++) {

			System.out.print("\nFOR CUSTOMER " + (i + 1) + ":");

			localCarPurchasePrice = readMoney("purchase price of the car", 1000, 99000);

			for (int j = 1; j <= OPTION_NUM; j++) {
				// call loan option twice for the comparison
				System.out.print("\nFor loan option " + j + ":");

				loanOption(j, localCarPurchasePrice);
			}

			System.out.print("\n\n");
			System.out.print("\nLOAN COMPARISONS FOR CUSTOMER " + (i + 1) + ":");

			// if (loanOptionNum == 1) {
			for (int k = 1; k <= OPTION_NUM; k++) {
				LoanComparison.generateLoanPaymentSchedule(k);
			}
		}
	}

	/**
	 * readMoney() Prompt, read, and validate a dollars and cents amount.
	 * 
	 * @param moneyAmountType
	 * @param minAmount
	 * @param maxAmount
	 * 
	 * @return - A valid amount of money.
	 */
	public static double readMoney(String moneyAmountType, double minAmount, double maxAmount) {
		double amount = 0;

		Scanner input1 = new Scanner(System.in);

		do {
			System.out.printf("\nEnter the %s (between %.0f and %.0f): ", moneyAmountType, minAmount, maxAmount);
			amount = input1.nextDouble();

			if ((amount < minAmount) || (amount > maxAmount)) {
				System.out.print("\nInvalid input.");
			}
		} while ((amount < minAmount) || (amount > maxAmount));

		return amount;
	}

	/**
	 * readPercent() Prompt, read, validate a percentage.
	 * 
	 * @param minAmount
	 * 
	 * @return percent - A valid percentage.
	 */
	public static double readPercent(double minPercent) {
		final double maxPercent = 1.0;
		final double DIV_HUND = 100;
		double percent = 0;

		Scanner input2 = new Scanner(System.in);

		do {
			System.out.print("\nEnter the down payment percentage " + "(greater than 10%): ");
			percent = input2.nextDouble();

			percent /= DIV_HUND;

			if ((percent < minPercent) || (percent > maxPercent)) {
				System.out.print("\nInvalid input.");
			}
		} while ((percent < minPercent) || (percent > maxPercent));

		return percent;
	}

	/**
	 * loanOption() The option chosen determines the down payment % and the annual
	 * interest rate.
	 * 
	 * @param loanOptionNum
	 * @param carPurchasePrice
	 * 
	 * @return
	 */
	public static LoanComparison loanOption(int loanOptionNum, double carPurchasePrice) {
		final double FIVE_PERCENT_DOWN = .05; // 5% down payment
		final double TEN_PERCENT_DOWN = .1; // 10% down payment
		final double ZERO_DOWN_RATE = .048; // 4.8% annual int
		final double FIVE_DOWN_RATE = .042; // 4.2% annual int
		final double TEN_DOWN_RATE = .037; // 3.7% annual int
		final double GREATER_TEN_DOWN_RATE = .033; // 3.3% annual int
		final double DIV_72 = 72; // use to find min monthly payment
		final double percent1 = .1;

		int choice = 0;
		double downPaymentPercent = 0; // user custom down payment %
		double localLoanBalance = 0;
		double localAnnualIntRate = 0;
		double carPurchaseMonthlyMin = 0;
		double localMonthlyPayment = 0;
		boolean switchChoice = false; // boolean for the switch

		Scanner input3 = new Scanner(System.in);

		do {

			System.out.println("\nWhat will the down payment be? " + "\n    1 - no down payment"
					+ "\n    2 - 5% down payment" + "\n    3 - 10% down payment" + "\n    4 - Over 10% down payment"
					+ "\nEnter choice from menu above: ");
			choice = input3.nextInt();

			switch (choice) {
			case 1:
				localLoanBalance = localLoanBalance + carPurchasePrice;
				localAnnualIntRate = ZERO_DOWN_RATE;
				switchChoice = true;
				break;
			case 2:
				downPaymentPercent = FIVE_PERCENT_DOWN;
				localLoanBalance = carPurchasePrice - (carPurchasePrice * FIVE_PERCENT_DOWN);
				localAnnualIntRate = FIVE_DOWN_RATE;
				switchChoice = true;
				break;
			case 3:
				downPaymentPercent = TEN_PERCENT_DOWN;
				localLoanBalance = carPurchasePrice - (carPurchasePrice * TEN_PERCENT_DOWN);
				localAnnualIntRate = TEN_DOWN_RATE;
				switchChoice = true;
				break;
			case 4:
				downPaymentPercent = readPercent(percent1);
				localLoanBalance = carPurchasePrice - (carPurchasePrice * downPaymentPercent);
				localAnnualIntRate = GREATER_TEN_DOWN_RATE;
				switchChoice = true;
				break;
			}
			if (switchChoice == false) {
				System.out.print("\nInvalid input.");
			}
		} while ((switchChoice == false));

		carPurchaseMonthlyMin = carPurchasePrice * (1 / DIV_72);

		localMonthlyPayment = readMoney("monthly loan payment", carPurchaseMonthlyMin, 5000);

		// Not sure about this... only one object specified in requirements...
		// System.out.print("\n test 1 loanOption number: " + loanOptionNum + "\n");

		if (loanOptionNum == 1) {
			// System.out.print("\n test 2 loanOption number: " + loanOptionNum + "\n");

			LoanComparison newCarLoan1 = new LoanComparison(carPurchasePrice, downPaymentPercent, localAnnualIntRate,
					localMonthlyPayment);

			return newCarLoan1;
		} else {
			// System.out.print("\n test 3 loanOption number: " + loanOptionNum + "\n");
			LoanComparison newCarLoan2 = new LoanComparison(carPurchasePrice, downPaymentPercent, localAnnualIntRate,
					localMonthlyPayment);

			return newCarLoan2;
		}

	}
}
