package hw3llp;

public class LlpBellmanFord {
	Integer Pre[];
	Integer W[][];
	Integer G[];
	
	public LlpBellmanFord(Integer[] PreInput, Integer[][] WInput, int n) {
		Pre = PreInput;
		W = WInput;
		G = new Integer[n];
		
		initialize (G.length);
	}
	
	public void initialize(int n) {
		G[0] = 0;
		for(int i = 1; i < n; i++) {
			G[i]=Integer.MAX_VALUE;
		}
		
	}
	
	public boolean ensure() {

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
		}
		return ret;
	}
	
	public void printPre() {
		System.out.println("Pre: ");
		for(int i = 0; i < Pre.length; i++) {
			System.out.println(Pre[i]);
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
