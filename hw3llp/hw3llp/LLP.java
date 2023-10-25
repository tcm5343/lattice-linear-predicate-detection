package hw3llp;

public class LLP {
	String test;
	Integer[][] G;

	public LLP() {
		test = "Hello";
	}
	
	public void test() {
		System.out.println(test);
		Integer[] A = new Integer[] {1,2,3,4,5,6,7,8};
		LlpReduce llpr = new LlpReduce(A);
		llpr.printG();
		llpr.LlpRun();
		llpr.printNewG();
		llpr.printA();
		llpr.printG();
		
		//
		// Test Scan
		//
		System.out.println("TestScan");
		//Integer[] STest = new Integer[] {36,10,26,3,7,11,15,0};
		//LlpScan llps = new LlpScan(A, STest);
		LlpScan llps = new LlpScan(A, llpr.G);
		llps.printG();
		llps.printA();
		llps.printS();
		llps.LlpRun();
		llps.printG();

		
	}
	
	
	
	public void ensure() {
		while(!forbidden()) {
			advance();
		}
	} // This is the same for each Algorithm
	
	public void advance() {
		return;
	} // Override for each Algorithm
	
	public Boolean forbidden() {
		return true;
	} // Override for each Algorithm
	
	
	public static void main(String args[]) {
		LLP l = new LLP();
		l.test();
	}
}
