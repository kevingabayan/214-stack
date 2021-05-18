/**
 * The <code>CodeBlock<code> class describes a nested block of code.
*    e-mail: kevin.gabayan@stonybrook.edu
*    Stony Brook ID: 111504873
 */
public class CodeBlock {
	public static final String[] BLOCK_TYPES = 
		{"def", "for", "while", "if", "elif", "else"};
	public static final int DEF = 0;
	public static final int FOR = 1;
	public static final int WHILE = 2;
	public static final int IF = 3;
	public static final int ELIF = 4;
	public static final int ELSE = 5;
	private Complexity blockComplexity;
	private Complexity highestSubComplexity;
	private String name;
	private String loopVariable = null;
	private int nestedBlocks = 0;
	/**
	 * CodeBlock variables 
	 * @param BLOCK_TYPES
	 * The block types that are available for nesting.
	 * @param DEF
	 * The index of the BLOCK_TYPE array for "def".
	 * @param FOR
	 * The index of the BLOCK_TYPE array for "for".
	 * @param WHILE
	 * The index of the BLOCK_TYPE array for "while".
	 * @param IF
	 * The index of the BLOCK_TYPE array for "if".
	 * @param ELIF
	 * The index of the BLOCK_TYPE array for "elif".
	 * @param ELSE
	 * The index of the BLOCK_TYPE array for "else".
	 */
	
	/**
	 * This method returns the complexity of the block.
	 * @return
	 * The complexity of the block.
	 */
	public Complexity getBlockComplexity() {
		return blockComplexity;
	}
	/**
	 * This method sets the complexity of the block.
	 * @param blockComplexity
	 * The complexity to be set to the block.
	 */
	public void setBlockComplexity(Complexity blockComplexity) {
		this.blockComplexity = blockComplexity;
	}
	/**
	 * This method returns the highest sub complexity of hte block.
	 * @return
	 * The highest sub complexity of the block.
	 */
	public Complexity getHighestSubComplexity() {
		return highestSubComplexity;
	}
	/**
	 * This method sets the highest sub complexity of the block.
	 * @param highestSubComplexity
	 * The sub complexity to be set to the block.
	 */
	public void setHighestSubComplexity(Complexity highestSubComplexity) {
		this.highestSubComplexity = highestSubComplexity;
	}
	/**
	 * This method returns the name of the String.
	 * @return
	 * The name that keeps track of the nested structure of the blocks.
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method sets the name of the string.
	 * @param name
	 * The new name that is used to keep track of the nested structure of the blocks.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This method retrieves the loop variable for while blocks in the assignment.
	 * @return
	 * The loop variable of the block of code.
	 */
	public String getLoopVariable() {
		return loopVariable;
	}
	/**
	 * This method sets the loop variable for while blocks in the assignment.
	 * @param loopvariable
	 * The new loop variable of the block of code.
	 */
	public void setLoopVariable(String loopVariable) {
		this.loopVariable = loopVariable;
	}
	/**
	 * This method increments the nested block variable, telling you the number 
	 * of nested blocks found under this CodeBlock.
	 */
	public void incrementNestedBlocks() {
		nestedBlocks++;
	}
	/**
	 * This method retrieves the number of nested code blocks from the CodeBlock.
	 * @return
	 */
	public int getNestedBlocks() {
		return nestedBlocks;
	}
	/**
	 * This is a constructor for the code block.
	 */
	public CodeBlock(String name, String loopVariable) {
		this.name = name;
		this.loopVariable = loopVariable;
	}
	/**
	 * This is an empty constructor for the creation of a code block.
	 */
	public CodeBlock() {
	}
}
