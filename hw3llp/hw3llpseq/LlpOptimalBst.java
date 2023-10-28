package hw3llpseq;

public class LlpOptimalBst {
//	Integer Pre[];
	Integer[] p;
	Integer[] symbols;
	Integer[][] G;
	int n;
	
	public LlpOptimalBst(Integer[] symbols, Integer[] p) {
		this.n = symbols.length;
		this.p = p;
		this.symbols = symbols;
		this.G = new Integer[n][n];

		initializeG();
	}
	
	private void initializeG() {
		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				G[i][j] = 0;
				if (i == j) {
					G[i][j] = i;
				}
			}
		}
	}
	
//	public boolean ensure() {
//		boolean ret = false;
//		return ret;
//	}
	
//	public void printG() {
//		System.out.println("G: ");
//		for(int i = 0; i < n; i++) {
//			System.out.println(G[i]);
//		}
//	}

	public int sum(int i, int j) {
		if (i > j) { return 0; }

		int sum = 0;
		for (; i <= j; i ++) {
			sum += p[i];
		}
		return sum;
	}
	
	public void LlpRun() {
//		ensure();
		int ex = sum(0, 1);
		System.out.println(ex);
	}
}
