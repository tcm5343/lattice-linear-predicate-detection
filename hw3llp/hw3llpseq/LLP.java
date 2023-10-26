package hw3llpseq;

public class LLP {
	String test;
	Integer[][] G;

	public LLP() {
		test = "Hello";
	}
	
	public void ppx() {
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
	
	public void bellman() {
		System.out.println(test);
		int start = 0;
		int nodes = 4;
		Integer[][]WIn = new Integer[][] { {0,1,2,0},
										   {1,0,0,3},
										   {2,0,0,4},
										   {0,3,4,0}};
		LlpBellmanFord llpb = new LlpBellmanFord(WIn, nodes, start);
		llpb.printG();
		llpb.printW();
		llpb.LlpRun();
		llpb.printG();
		llpb.printW();

	}
	
	public void OptBst() {
		System.out.println(test);
		int start = 0;
		int nodes = 4;
		Integer[][]WIn = new Integer[][] { {0,1,2,0},
										   {1,0,0,3},
										   {2,0,0,4},
										   {0,3,4,0}};
		LlpOptimalBst llpbst = new LlpOptimalBst(WIn, nodes, start);
		llpbst.printG();
		llpbst.printW();

	}
	
	public void Prim() {
		System.out.println(test);

		int nodes = 4;
		Integer[][]WIn = new Integer[][] { {0,1,2,0},
										   {1,0,0,3},
										   {2,0,0,4},
										   {0,3,4,0}};
		LlpPrim llpP = new LlpPrim(WIn, nodes);
		llpP.printG();
		llpP.printW();
		llpP.printFixed();

	}
	
	

	
	
	public static void main(String args[]) {
		LLP l = new LLP();
		//l.ppx();
		//l.bellman();
		l.Prim();
		
	}
}
