/**
 * @author Chris Angell
 * @version 1.0, Java Assn 6
 */

public class LoanComparison {

	private static double carPurchasePrice = 0;
	private static double downPaymentRate = 0;
	private static double currentLoanBalance = 0;
	private static double annualIntRate = 0;
	private static double monthlyPayment = 0;
	
/**
 * LoanComparison() constructor	
 * 
 * @param carPurchasePrice
 * @param downPaymentRate
 * @param annualIntRate
 * @param monthlyPayment
 */
	public LoanComparison(
		double carPurchasePrice,
		double downPaymentRate,
		double annualIntRate,
		double monthlyPayment) {
		
		//this.carPurchasePrice = ;
		this.carPurchasePrice = carPurchasePrice;
		this.downPaymentRate = downPaymentRate;
		this.annualIntRate = annualIntRate;
		this.monthlyPayment = monthlyPayment;
		
		currentLoanBalance = carPurchasePrice - (carPurchasePrice * downPaymentRate);
		//System.out.print( carPurchasePrice1 );
		
		
		//System.out.print("\n test loanComparison: " + monthlyPayment + "\n");
		
	}
	
/**
 * applyMonthlyPayment() 
 * 		Contains the data for one months payment.	
 */
	public static void applyMonthlyPayment() {
		System.out.printf("    $%.2f", currentLoanBalance);
		
		double intCharge = currentLoanBalance * (annualIntRate / 12);
		double balanceWithInterest = intCharge + currentLoanBalance;
		
		System.out.printf("      %.2f", intCharge);
		System.out.printf("      $%.2f", balanceWithInterest);
		
		if ( balanceWithInterest < monthlyPayment) {
			monthlyPayment = balanceWithInterest;
		}
		
		System.out.printf("      $%.2f", monthlyPayment);
		
		currentLoanBalance = balanceWithInterest - monthlyPayment;
		
		System.out.printf("    $%.2f\n", currentLoanBalance);		
	}
	
/**
 * generateLoanPaymentSchedule()
 * 		Calculate and display info every month until loan is paid off.	
 * 
 * @param loanOptionNum
 */
	public static void generateLoanPaymentSchedule( int loanOptionNum ) {
		final String DASHES = "--------------------------------";
		final int HUND1 = 100;
		int monthNum = 1;
		
		System.out.println("\n\nLoan option " + (loanOptionNum)
			+ " loan payment schedule:");
		
		System.out.printf("$%.2f car purchased with %.1f%% down payment", 
			carPurchasePrice, (downPaymentRate * HUND1) );
		System.out.printf("\nLoan amount is $%.2f at a %.1f%% annual interest rate\n", 
				 currentLoanBalance, (annualIntRate * HUND1) );
		
		System.out.println( DASHES + DASHES );
		
		System.out.println("         Initial    Month's"
			+ "     Balance                  End" 
			+ "\nMonth    Balance    Interest"
			+ "    w/Interest    Payment    Balance");
		
		System.out.println( DASHES + DASHES );
		
		do {
			System.out.print("    " + monthNum++ );
			applyMonthlyPayment();
		}
		while ( currentLoanBalance > 0 );	
	}
}
