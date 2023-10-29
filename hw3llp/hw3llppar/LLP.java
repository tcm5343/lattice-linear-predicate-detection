package hw3llppar;

public class LLP {
	String test;

	public LLP() {
		test = "LLP";
	}
	public void test() {
		System.out.println(test);
	}
	
	public void printArr(Integer[] G, String str) {
		System.out.println(str + ": ");
		for(int i = 0; i < G.length; i++) {
			System.out.println(G[i]);
		}
	}
	public void printArr2d(Integer[][] G, String str) {
		System.out.println(str + ": ");
		for(int i = 0; i < G.length; i++) {
			for (int j = 0; j < G.length; j++) {
				System.out.print(G[i][j]);
				System.out.print(",");
			}
			System.out.println(" ");
		}
	}
	
	
	public Integer[] ppx(Integer[] AInput) {
		System.out.println("Reduce and PPx");

		Integer[] A = AInput;
		LlpReduce llpr = new LlpReduce(A);

		//printArr(llpr.G, "G");
		llpr.LlpRun();
		//printArr(llpr.A, "A");
		//printArr(llpr.G, "G");
		

		
		LlpScan llps = new LlpScan(A, llpr.G);
		//printArr(llps.A, "A");
		//printArr(llps.G, "G");
		//printArr(llps.S, "S");
		llps.LlpRun();
		//printArr(llps.G, "G");

		return llps.getExclusiveG();
	}
	
	public Integer[] bellman(Integer[][]WIn, int start) {
		System.out.println("Bellman");
		//int start = 3;
		//int nodes = 4;
		//Integer[][]WIn = new Integer[][] { {0,1,2,0},
		//								   {0,0,0,3},
		//								   {0,0,0,4},
		//								   {0,0,0,0}};
		LlpBellmanFord llpb = new LlpBellmanFord(WIn, start);
		//printArr(llpb.G, "G");
		//printArr2d(llpb.W, "W");
		//printArr2d(llpb.Pre, "Pre");

		llpb.LlpRun();

		//printArr(llpb.G, "G");
		//printArr2d(llpb.W, "W");
		//printArr2d(llpb.Pre, "Pre");

		return llpb.G;

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
	
	public Integer[] Prim(Integer[][]WIn) {
		System.out.println("Prim");

/*		Integer[][]WIn = new Integer[][] { {0,1,2,0},
										   {1,0,0,3},
										   {2,0,0,4},
										   {0,3,4,0}};
		Integer[][]WIn = new Integer[][] { {0,6,5,7},
			   							   {6,0,0,4},
										   {5,0,0,8},
										   {7,4,8,0}};*/
		LlpPrim llpP = new LlpPrim(WIn);

//		printArr(llpP.G, "G");
//		printArr2d(llpP.W, "W");
//		printArr(llpP.Fixed, "Fixed");
		llpP.LlpRun();
//		printArr(llpP.G, "G");
//		printArr2d(llpP.W, "W");
//		printArr(llpP.Fixed, "Fixed");
		
		return llpP.G;
		
	}

	
	public static void main(String args[]) {
		LLP l = new LLP();
		l.test();
		
		//
		//PPx
		//
		//l.printArr(l.ppx(new Integer[] {1,2,3,4,5,6,7,8}),"PPx G");

		
		
		//
		//Bellman Ford
		//
		/*
		l.printArr(l.bellman(new Integer[][] { {0,1,2,0},
			   									{0,0,0,3},
			   									{0,0,0,4},
			   									{0,0,0,0}},
												0),"Bellman G");
		 */
		
		//
		// Prim
		//
		/*
		l.printArr(l.Prim(new Integer[][] { 
			{0,6,5,7},
			{6,0,0,4},
			{5,0,0,8},
			{7,4,8,0}}),"Prim G");*/
		
		
		System.out.println("main done:");
		//System.exit(0);
	}
}
