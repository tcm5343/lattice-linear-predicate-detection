package hw3llppar;

public class LlpOptimalBst {
	Integer Pre[];
	Integer W[][];
	Integer G[];
	int n;
	
	public LlpOptimalBst(Integer[][] WInput, int nodes, int start) {
		//Pre = PreInput;
		n = nodes;
		
		W = WInput;
		G = new Integer[n];
		
		initialize (G.length, start);
	}
	
	public void initialize(int n, int start) {
		for(int i = 0; i < n; i++) {
			G[i]=Integer.MAX_VALUE;
			if (i == start) {
				G[i] = 0;
			}
		}
		
	}
	
	int getMinWeight(int j) {
		int minWeight = Integer.MAX_VALUE;
		for(int i = 0; i < W[j].length; i++) {
			if (W[i][j] < minWeight){
				minWeight = W[i][j];
			}
		}
		return minWeight;
	}
	
	public boolean ensure() {

		boolean ret = false;

		return ret;
	}
	
	public void printPre() {
		System.out.println("Pre: ");
		for(int i = 0; i < Pre.length; i++) {
			System.out.println(Pre[i]);
		}
	}
	
	public void printW() {
		System.out.println("W: ");
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(W[i][j]);
				System.out.print(",");
			}
			System.out.println(" ");
		}
	}
	
	public void printG() {
		System.out.println("G: ");
		for(int i = 0; i < n; i++) {
			System.out.println(G[i]);
		}
	}
	
	public void LlpRun() {
		
		ensure();
		
	}
}
