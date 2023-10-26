package hw3llpseq;

public class LlpScan {
	Integer A[];
	Integer S[];
	Integer G[];
	
	public LlpScan(Integer[] AInput, Integer[] SInput) {
		int n = AInput.length;
		A = AInput;
		S = SInput;
		G = new Integer[2*n];
		
		initialize (G.length);
	}
	
	public void initialize(int n) {
		for(int i = 0; i < n; i++) {
			G[i]=Integer.MIN_VALUE;
		}
		
	}
	
	public boolean ensure() {
		int n = A.length;
		int i = G.length;
		boolean ret = false;
		System.out.println("ensure");
		for (int j = 1; j < i; j++) {
			
			// 1
			if (j == 1) {
				if (G[j] < 0) {
					G[j] = 0;
					ret = true;
					//return true;
					System.out.println("1");
				}
			}
			
			// 2
			if (j % 2 == 0) {
				if (G[j] < G[j/2]) {
					G[j] = G[j/2];
					ret = true;
					//return true;
					System.out.println("2");
				}
			}
			
			// 3
			if ((j % 2 == 1) && (j < n)) {
				if (G[j] < S[j-1] + G[j/2]) {
					G[j] = S[j-1] + G[j/2];
					ret = true;
					//return true;
					System.out.println("3");
				}
			}
			
			// 4
			if ((j % 2 == 1) && (j > n)) {
				if (G[j] < A[j-n-1] + G[j/2]) {
					G[j] = A[j-n-1] + G[j/2];
					ret = true;
					//return true;
					System.out.println("4");
				}
			}
		}
		return ret;
	}
	
	public void printA() {
		System.out.println("A: ");
		for(int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
	}
	
	public void printS() {
		System.out.println("S: ");
		for(int i = 0; i < S.length; i++) {
			System.out.println(S[i]);
		}
	}
	
	public void printG() {
		System.out.println("G: ");
		for(int i = 0; i < G.length; i++) {
			System.out.println(G[i]);
		}
	}
	
	public void LlpRun() {
		
		ensure();
		
	}
}
