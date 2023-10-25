package hw3llp;

public class LlpReduce {
	Integer A[];
	Integer G[];
	Integer NewG[];
	
	public LlpReduce(Integer[] Input) {
		A = Input;
		G = new Integer[A.length];
		NewG = new Integer[G.length-1];
		
		initialize (A.length);
	}
	
	public void initialize(int j) {
		for(int i = 0; i < j; i++) {
			G[i]=Integer.MIN_VALUE;
		}
	}
	
	public boolean ensure() {
		int n = G.length;
		boolean ret = false;
		for (int j = 0; j < n; j++) {

			// 1
			if ((0 <= j) && (j < n/2)) {
				if (G[j] < G[2*j] + G[2*j+1]){
					G[j] = G[2*j] + G[2*j+1];
					ret = true;
					return true;
				}
			}
			
			// 2
			if ((n/2 <= j) && (j < n)) {
				if (G[j] < A[2*j-n] + A[2*j-n+1]) {
					G[j] = A[2*j-n] + A[2*j-n+1];
					ret = true;
					return true;
				}
			}
		}
		return ret;
	}
	
	public void makeNewG() {
		System.out.println("NewG: ");
		for(int i = 0; i < NewG.length; i++) {
			System.out.println(NewG[i] = G[i+1]);
		}
	}
	
	
	public void printNewG() {
		System.out.println("NewG: ");
		for(int i = 0; i < NewG.length; i++) {
			System.out.println(NewG[i]);
		}
	}
	
	public void printA() {
		System.out.println("A: ");
		for(int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
	}
	
	public void printG() {
		System.out.println("G: ");
		for(int i = 0; i < G.length; i++) {
			System.out.println(G[i]);
		}
	}
	
	public void LlpRun() {
		while (ensure());
		//ensure();
		makeNewG();
	}
}
