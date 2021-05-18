import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

/**
 * The <code>PythonTracerk<code> class takes a text file and evaluates the 
 * order of complexity of each block of code and the details of the trace.
*    e-mail: kevin.gabayan@stonybrook.edu
*    Stony Brook ID: 111504873
 */
public class PythonTracer {
	public static final int SPACE_COUNT = 4;
	/**
	 * PythonTracer variables
	 * @param SPACE_COUNT
	 * Tells you that every instance of "def, for, while if, elif, and else" contains
	 * all following statements that are indented exactly 4 spaces further than the block
	 * declaration.
	 */
	
	/**
	 * This method opens the indicated file and traces through the Python code contained 
	 * within the file, returning the Big-Oh complexity of the function. THe stack trace
	 * should be printed to the console as code blocks are pushed to/popped from the stack.
	 * @param filename
	 * <dt><b>Preconditions:</b><dd>
	 * Filename is not null and the file it names contains a single Python function with valid syntax.
	 * @return
	 * A Complexity object representing the total order of complexity of the Python code contained
	 * within the file.
	 * @throws IOException 
	 */
	public static Complexity traceFile(String filename) throws IOException {
 		BlockStack stack = new BlockStack();
		FileInputStream fis = null;
		InputStreamReader inStream = null;
		BufferedReader reader = null;
		try {
			fis = new FileInputStream(filename);
			inStream = new InputStreamReader(fis);
			reader = new BufferedReader(inStream);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		String data = reader.readLine();
		while(data != null) {
			if((data != null && !data.isEmpty()) && !data.contains("#") && data.trim().length() > 0) {
				int indents = data.indexOf(data.trim()) / SPACE_COUNT;
				while(indents < stack.size()) {
					if(indents == 0) {
						reader.close();
						return stack.peek().getBlockComplexity();
					}
					else {
						CodeBlock oldTop = stack.pop();
						int oldTopComplexityNPower = oldTop.getBlockComplexity().getN_power()
						  + oldTop.getHighestSubComplexity().getN_power();
						int oldTopComplexityLPower = oldTop.getBlockComplexity().getLog_power()
						  + oldTop.getHighestSubComplexity().getLog_power();
						Complexity oldTopComplexity = new Complexity(oldTopComplexityNPower,
						  oldTopComplexityLPower);
						if(complexityComparison(oldTopComplexity, stack.peek().getHighestSubComplexity())
						  == oldTopComplexity) {
							stack.peek().setHighestSubComplexity(oldTopComplexity);
							
							// String format spaced out to make copy and pasting easier
							String neaterFormatBlock = stack.peek().getName() + ":";
							System.out.println("Leaving block " + oldTop.getName() + ", updating block " +
							  stack.peek().getName() + ":");
							System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
									  neaterFormatBlock,stack.peek().getBlockComplexity(), stack.peek().getHighestSubComplexity());
						}
						else {	
							// String format spaced out to make copy and pasting easier
							String neaterFormatBlock = stack.peek().getName() + ":";
							System.out.println("Leaving block " + oldTop.getName() + ", nothing to update.");
							System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
								  neaterFormatBlock,stack.peek().getBlockComplexity(), stack.peek().getHighestSubComplexity());
						}
					}
				}
				if(keywordSearch(data) != null) {
					String keyword = keywordSearch(data);
					if(keyword.equals("for")) {
						if(data.contains("log_N")) {
							CodeBlock forblock = new CodeBlock();
							if(indents >= stack.size()) {
								Complexity log_n = new Complexity(0,1);
								forblock.setBlockComplexity(log_n);
								Complexity subzero = new Complexity(0,0);
								forblock.setHighestSubComplexity(subzero);
								stack.peek().incrementNestedBlocks();
								forblock.setName(stack.peek().getName() + "." + stack.peek().getNestedBlocks());
								
								// String format spaced out to make copy and pasting easier
								String neaterFormatBlock = forblock.getName() + ":";
								System.out.println("Entering block " + forblock.getName() + " 'for':");
								System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
								  neaterFormatBlock,forblock.getBlockComplexity(), forblock.getHighestSubComplexity());
								
								stack.push(forblock);
							}
						}
						else {
							CodeBlock forblock = new CodeBlock();
							if(indents >= stack.size()) {
								Complexity n = new Complexity(1,0);
								forblock.setBlockComplexity(n);
								Complexity subzero = new Complexity(0,0);
								forblock.setHighestSubComplexity(subzero);
								stack.peek().incrementNestedBlocks();
								forblock.setName(stack.peek().getName() + "." + stack.peek().getNestedBlocks());
								
								// String format spaced out to make copy and pasting easier
								String neaterFormatBlock = forblock.getName() + ":";
								System.out.println("Entering block " + forblock.getName() + " 'for':");
								System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
								  neaterFormatBlock,forblock.getBlockComplexity(), forblock.getHighestSubComplexity());
								
								stack.push(forblock);
							}
						}
					}
					else if(keyword.equals("while")) {
						CodeBlock whileblock = new CodeBlock();
						Complexity whilecomp = new Complexity(0,0);
						whileblock.setBlockComplexity(whilecomp);
						whileblock.setHighestSubComplexity(whilecomp);
						stack.peek().incrementNestedBlocks();
						whileblock.setName(stack.peek().getName() + "." + stack.peek().getNestedBlocks());
						
						//Update loop variable
						whileblock.setLoopVariable("ESKETIT");
						
						// String format spaced out to make copy and pasting easier
						String neaterFormatBlock = whileblock.getName() + ":";
						System.out.println("Entering block " + whileblock.getName() + " 'while':");
						System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
						  neaterFormatBlock, whileblock.getBlockComplexity(), whileblock.getHighestSubComplexity());
						
						stack.push(whileblock);
					}
					else if(keyword.equals("def")) {
						CodeBlock defblock = new CodeBlock();
						Complexity def = new Complexity(0,0);
						defblock.setBlockComplexity(def);
						defblock.setHighestSubComplexity(def);
						defblock.setName("1");
						
						// String format spaced out to make copy and pasting easier
						String neaterFormatBlock = defblock.getName() + ":";
						System.out.println("Entering block " + defblock.getName() + " 'def':");
						System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
						  neaterFormatBlock,defblock.getBlockComplexity(), defblock.getHighestSubComplexity());
						
						stack.push(defblock);
					}
					else if(keyword.equals("elif")) {
						CodeBlock elifblock = new CodeBlock();
						Complexity elif = new Complexity(0,0);
						elifblock.setBlockComplexity(elif);
						elifblock.setHighestSubComplexity(elif);
						elifblock.setName(stack.peek().getName() + "." + stack.peek().getNestedBlocks());
						
						// String format spaced out to make copy and pasting easier
						String neaterFormatBlock = elifblock.getName() + ":";
						System.out.println("Entering block " + elifblock.getName() + " 'for':");
						System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
						  neaterFormatBlock, elifblock.getBlockComplexity(), elifblock.getHighestSubComplexity());
						
						stack.push(elifblock);
					}
					else if(keyword.equals("if")) {
						CodeBlock ifblock = new CodeBlock();
						Complexity ifcomp = new Complexity(0,0);
						ifblock.setBlockComplexity(ifcomp);
						ifblock.setHighestSubComplexity(ifcomp);
						ifblock.setName(stack.peek().getName() + "." + stack.peek().getNestedBlocks());
						
						// String format spaced out to make copy and pasting easier
						String neaterFormatBlock = ifblock.getName() + ":";
						System.out.println("Entering block " + ifblock.getName() + " 'for':");
						System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
						  neaterFormatBlock, ifblock.getBlockComplexity(), ifblock.getHighestSubComplexity());
						
						stack.push(ifblock);
						
					}
					else if(keyword.equals("else")) {
						CodeBlock elseblock = new CodeBlock();
						Complexity elsecomp = new Complexity(0,0);
						elseblock.setBlockComplexity(elsecomp);
						elseblock.setHighestSubComplexity(elsecomp);
						elseblock.setName(stack.peek().getName() + "." + stack.peek().getNestedBlocks());
						
						// String format spaced out to make copy and pasting easier
						String neaterFormatBlock = elseblock.getName() + ":";
						System.out.println("Entering block " + elseblock.getName() + " 'for':");
						System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
						  neaterFormatBlock, elseblock.getBlockComplexity(), elseblock.getHighestSubComplexity());
						
						stack.push(elseblock);
					}
				}
				else if(stack.isEmpty()) {
					
				}
				else if(stack.peek().getLoopVariable() != null && (data.contains("-= 1") || data.contains("/= 2")) ) {
					if(data.contains("-= 1")) {
						Complexity whileplexity = new Complexity(1,0);
						stack.peek().setBlockComplexity(whileplexity);
						
						// String format spaced out to make copy and pasting easier
						String neaterFormatBlock = stack.peek().getName() + ":";
						System.out.println("Found update statement, updating block " + neaterFormatBlock);
						System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
						  neaterFormatBlock, stack.peek().getBlockComplexity(), stack.peek().getHighestSubComplexity());
						
					}
					else if (data.contains("/= 2") ) {
						Complexity whileplexity = new Complexity(0,1);
						stack.peek().setBlockComplexity(whileplexity);
						
						// String format spaced out to make copy and pasting easier
						String neaterFormatBlock = stack.peek().getName() + ":";
						System.out.println("Found update statement, updating block " + neaterFormatBlock);
						System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
						  neaterFormatBlock, stack.peek().getBlockComplexity(), stack.peek().getHighestSubComplexity());
					
					}
					
				}
			}
			data = reader.readLine();
		}
		while(stack.size() > 1) {
			CodeBlock oldTop = stack.pop();
			int oldTopComplexityNPower = oldTop.getBlockComplexity().getN_power()
			  + oldTop.getHighestSubComplexity().getN_power();
			int oldTopComplexityLPower = oldTop.getBlockComplexity().getLog_power()
			  + oldTop.getHighestSubComplexity().getLog_power();
			Complexity oldTopComplexity = new Complexity(oldTopComplexityNPower,
			  oldTopComplexityLPower);
			if(complexityComparison(oldTopComplexity, stack.peek().getHighestSubComplexity())
			  == oldTopComplexity) {
				stack.peek().setHighestSubComplexity(oldTopComplexity);
				// String format spaced out to make copy and pasting easier
				String neaterFormatBlock = stack.peek().getName() + ":";
				System.out.println("Leaving block " + oldTop.getName() + ", updating block " +
				  stack.peek().getName() + ":");
				System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
				  neaterFormatBlock,stack.peek().getBlockComplexity(), stack.peek().getHighestSubComplexity());
			}
			else {
				// String format spaced out to make copy and pasting easier
				String neaterFormatBlock = stack.peek().getName() + ":";
				System.out.println("Leaving block " + oldTop.getName() + ", nothing to update.");
				System.out.printf("	BLOCK %-10s block complexity = %-11s highest sub-complexity = %s\n", 
					  neaterFormatBlock,stack.peek().getBlockComplexity(), stack.peek().getHighestSubComplexity());
			}
			
		}
		if(stack.isEmpty()) {
			return null;
		}
		System.out.println("Leaving block 1.");
		return stack.pop().getHighestSubComplexity();
	}
	
	/**
	 * This method compares two complexities and returns the one with the highest
	 * order. If the complexities are the same, c2 is returned.
	 * @param c1
	 * The first complexity to be compared.
	 * @param c2
	 * The second complexity to be compared.
	 * @return
	 * The complexity with the highest order.
	 */
	public static Complexity complexityComparison(Complexity c1, Complexity c2) {
		if(c1.getN_power() > c2.getN_power())
			return c1;
		else if(c1.getN_power() < c2.getN_power())
			return c2;
		else {
			if(c1.getLog_power() > c2.getLog_power())
				return c1;
			else if(c1.getLog_power() < c2.getLog_power())
				return c2;
			else {
				return c2;
			}
		}
	}
	
	/**
	 * This method tells you if there is a keyword and returns the proper keyword present in the line.
	 * @param search
	 * The string to be searched through.
	 * @return
	 * The keyword, if there is one.
	 */
	public static String keywordSearch(String search) {
		if(search.contains("def")) {
			int firstpos = search.indexOf("def");
			int lastpos = firstpos + 3;
			if(search.charAt(lastpos) == ' ') {
				return "def";
			}
		}
		if(search.contains("for")) {
			int firstpos = search.indexOf("for")-1;
			int lastpos = firstpos + 4;
			if(search.charAt(firstpos) == ' ' && search.charAt(lastpos) == ' ') {
				return "for";
			}
		}
		if(search.contains("while")) {
			int firstpos = search.indexOf("while")-1;
			int lastpos = firstpos + 6;
			if(search.charAt(firstpos) == ' ' && search.charAt(lastpos) == ' ') {
				return "while";
			}
		}
		if(search.contains("if")) {
			int firstpos = search.indexOf("if")-1;
			int lastpos = firstpos + 3;
			if(search.charAt(firstpos) == ' ' && search.charAt(lastpos) == ' ') {
				return "if";
			}
		}
		if(search.contains("elif")) {
			int firstpos = search.indexOf("elif")-1;
			int lastpos = firstpos + 5;
			if(search.charAt(firstpos) == ' ' && search.charAt(lastpos) == ' ') {
				return "elif";
			}
		}
		if(search.contains("else")) {
			int firstpos = search.indexOf("else")-1;
			int lastpos = firstpos + 4;
			if(search.charAt(firstpos) == ' ' && search.charAt(lastpos) == ' ') {
				return "else";
			}
		}
		return null;
	}
	
	/**
	 * This method prompts the user for the name of a file containing a 
	 * Python function, determines its order of complexity, and prints
	 * the result to the console.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		boolean end = false;
		while(end == false) {
			System.out.print("Please enter a file name (or 'quit' to quit): ");
			String fileName = input.nextLine();
			if(fileName.equals("quit")) {
				end = true;
				System.out.println("Program terminating successfully...");
			}
			else {
				if(fileName != null) {
					Complexity overall = traceFile(fileName);
					if(overall != null) 
						System.out.println("Overall complexity of " + fileName + ": " + overall);
					else {
						System.out.println("File does not exist/the file is not a proper python function!");
					}
				}
			}
		}
	}
}

