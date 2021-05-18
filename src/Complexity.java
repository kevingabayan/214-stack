/**
 * The <code>Complexity<code> class represents the Big-Oh complexity of some block of code.
 * @author Kevin Gabayan
*    e-mail: kevin.gabayan@stonybrook.edu
*    Stony Brook ID: 111504873
 */
public class Complexity {
	int n_power = 0;
	int log_power = 0;
	/**
	 * Complexity Variables
	 * @param n_power
	 * The n power member of a complexity object.
	 * @param log_power
	 * The log power member of a complexity object.
	 */
	/**
	 * This method retrieves the N power of the block of code.
	 * @return
	 * The N power of the block of code.
	 */
	public int getN_power() {
		return n_power;
	}
	/**
	 * This method sets the N power of the block of code.
	 * @param n_power
	 * THe N power of the block of the code to be set.
	 */
	public void setN_power(int n_power) {
		this.n_power = n_power;
	}
	/**
	 * This method retrieves the Log power of the block of code.
	 * @return
	 * The Log power of the block of code.
	 */
	public int getLog_power() {
		return log_power;
	}
	/**
	 * This method sets the Log power of the block of code.
	 * @param log_power
	 * The Log power of the block of code to be set/
	 */
	public void setLog_power(int log_power) {
		this.log_power = log_power;
	}
	/**
	 * This is an empty constructor for the creation of the Big-Oh complexity of a block of code.
	 * Note: This means that the Big O notation for both the N and Log Power will be O(1).
	 */
	public Complexity() {
	}
	/**
	 * This is a constructor for the creation of the Big-Oh complexity of a block of code.
	 */
	public Complexity(int n_power, int log_power) {
		super();
		this.n_power = n_power;
		this.log_power = log_power;
	}
	/**
	 * This method prints human-readable Big-Oh notation.
	 */
	public String toString() {
		if(n_power == 0 && log_power == 0)
			return ("O(1)");
		else if (n_power == 1 && log_power == 1)
			return ("O(n * log(n))");
		else if(n_power == 1 && log_power == 0)
			return ("O(n)");
		else if(n_power == 0 && log_power == 1)
			return ("O(log(n))");
		else if((n_power != 0 || n_power != 1) || log_power == 0)
			return("O(n^" + n_power + ")");
		else if((n_power == 0 && (log_power != 0 || log_power != 1)))
			return("O(log(n)^" + log_power + ")"); 
		else if(n_power == 1 && (log_power != 0 || log_power != 1))
			return("O(n * " + "log(n)^" + log_power + ")");
		else if((n_power != 0 || n_power != 1) && log_power == 1)
			return("O(n^" + n_power + " * log(n)" + ")");
		else
			return ("O(n^" + n_power + " * log(n)^" + log_power + ")");
	}
	
}
