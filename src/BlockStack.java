import java.util.Stack;
/**
 * The <code>BlockStack<code> class imports the standard Stack class in the 
 * java.util package to help find the complexity of CodeBlocks nested in the function.
*    e-mail: kevin.gabayan@stonybrook.edu
*    Stony Brook ID: 111504873
 */
public class BlockStack {
	Stack<CodeBlock> stack = new Stack<CodeBlock>();
	/**
	 * BlockStack variables
	 * @param totalSize
	 * Used for the size method for the BlockStack.
	 * @param stack
	 * Initializes a stack for usage in the BlockStack.
	 */
	/**
	 * This method pushes a CodeBlock onto the stack.
	 * @param block
	 * The CodeBlock to be pushed onto the stack.
	 */
	public void push(CodeBlock block) {
		stack.push(block);
	}
	/**
	 * This method removes a CodeBlock from the stack.
	 * @return
	 * The process that removes the CodeBlock from the stack.
	 */
	public CodeBlock pop() {
		return stack.pop();
	}
	/**
	 * This method takes a peek at the top of the stack without removing it from the stack.
	 * @return
	 * The top of the stack that is to be looked at.
	 */
	public CodeBlock peek() {
		return stack.peek();
	}
	/**
	 * This method returns the size of the stack.
	 * @return
	 * The total size of the stack.
	 */
	public int size() {
		return stack.size();
	}
	/**
	 * This method returns whether or not the stack is empty.
	 * @return
	 * Whether or not the stack is empty.
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
}
