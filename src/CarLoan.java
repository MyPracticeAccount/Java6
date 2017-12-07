/**
 * @author Chris Angell
 * @version 1.0, Java Assn 6
 */

public class CarLoan {

	private double carPurchasePrice = 0;
	private double downPaymentRate = 0;
	private double currentLoanBalance = 0;
	private double annualIntRate = 0;
	private double monthlyPayment = 0;
	
/**
 * CarLoan() constructor	
 * 
 * @param carPurchasePrice
 * @param downPaymentRate
 * @param annualIntRate
 * @param monthlyPayment
 */
	public CarLoan(
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
	public String applyMonthlyPayment() {
		String monthlyPaymentString = "";
		monthlyPaymentString +=  "    $%.2f";  //, currentLoanBalance);
		
		double intCharge = currentLoanBalance * (annualIntRate / 12);
		double balanceWithInterest = intCharge + currentLoanBalance;

		monthlyPaymentString +=  "      %.2f"; //, intCharge);
		monthlyPaymentString +=  "      $%.2f"; //, balanceWithInterest);
		
		if ( balanceWithInterest < monthlyPayment) {
			monthlyPayment = balanceWithInterest;
		}

		monthlyPaymentString +=  "      $%.2f"; //, monthlyPayment;
		
		currentLoanBalance = balanceWithInterest - monthlyPayment;

		monthlyPaymentString +=  "    $%.2f\n"; //, currentLoanBalance);
		return monthlyPaymentString;
	}
	
/**
 * getLoanPaymentSchedule()
 * 		Calculate and display info every month until loan is paid off.	
 * 
 * @param loanOptionNum
 */
	public String getLoanPaymentSchedule(int loanOptionNum ) {
		String loanPaymentSchedule = "";
		final String DASHES = "--------------------------------";
		final int HUND1 = 100;
		int monthNum = 1;
		
		loanPaymentSchedule += "\n\nLoan option " + (loanOptionNum) + " loan payment schedule:";
		
		loanPaymentSchedule +=  "$%.2f car purchased with %.1f%% down payment"; // , carPurchasePrice, (downPaymentRate * HUND1) );
		loanPaymentSchedule +=  "\nLoan amount is $%.2f at a %.1f%% annual interest rate\n"; //, currentLoanBalance, (annualIntRate * HUND1);
		loanPaymentSchedule +=   DASHES + DASHES;
		
		loanPaymentSchedule +=  "         Initial    Month's";
		loanPaymentSchedule +=  "     Balance                  End";
		loanPaymentSchedule +=  "\nMonth    Balance    Interest";
		loanPaymentSchedule +=  "    w/Interest    Payment    Balance";
		
		loanPaymentSchedule +=   DASHES + DASHES;
		
		do {
			loanPaymentSchedule +=  "    " + monthNum++;
			loanPaymentSchedule += applyMonthlyPayment();
		}
		while ( currentLoanBalance > 0 );	
		
		return loanPaymentSchedule;
	}
	
}
