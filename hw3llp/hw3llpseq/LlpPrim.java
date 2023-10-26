package hw3llpseq;

public class LlpPrim {
	Integer Fixed[];
	Integer W[][];
	Integer G[];
	int n;
	
	public LlpPrim(Integer[][] WInput, int nodes) {
		//Pre = PreInput;
		n = nodes;
		
		W = WInput;
		G = new Integer[n];
		
		Fixed = new Integer[n];
		
		initialize ();
	}
	
	public void initialize() {
		for(int i = 0; i < n; i++) {
			G[i]=getMinWeightEdgeFromEdgei(i);
			Fixed[i] = 0;
		}
	}
	
	int getMinWeightEdgeFromEdgei(int i) {
		int minWeight = Integer.MAX_VALUE;
		for(int j = 0; j < n; j++) {
			if (W[i][j] < minWeight && W[i][j]!= 0){
				minWeight = W[i][j];
			}
		}
		return minWeight;
	}
	
	public boolean ensure() {

		boolean ret = false;

		return ret;
	}
	
	public void printFixed() {
		System.out.println("Fixed: ");
		for(int i = 0; i < Fixed.length; i++) {
			System.out.println(Fixed[i]);
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
