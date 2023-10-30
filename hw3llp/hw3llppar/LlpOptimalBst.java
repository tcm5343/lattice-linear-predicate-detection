package hw3llppar;

public class LlpOptimalBst {
	Integer P[];
	Integer G[][];
	EnsureObj ensureObjects[][];
	Thread ensureThreads[][];
	
	int n;
	
	public LlpOptimalBst(Integer[] PInput) {
		P = PInput;
		n = P.length;
		G = new Integer[n][n];
		
		ensureObjects = new EnsureObj[n][n];
		ensureThreads = new Thread[n][n];
		
		initialize ();
	}
	
	public void initialize() {
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				G[i][j]=0;
				if (i == j) {
					G[i][j] = P[i];
				}
				ensureObjects[i][j] = new EnsureObj(i,j);
				ensureThreads[i][j] = new Thread(ensureObjects[i][j]);
			}
		}
	}
	
	private int S(int i, int j) {
		if (i > j) { return 0; }
		int sum = 0;
		for(; i < j; i++) {
			sum+=P[i];
		}
		return sum;
	}
	
	private int getMinIkJ (int i, int j) {
		if (i >= j) {return 0;}

		if (i >= n) {
			return 0;
		}
		
		int min;

		min = G[i][i] + S(i,j) + G[i][j];

		for (int k = i; k < j; k++) {
			int m = G[i][k] + S(i,j) + G[k+1][j];
			if (m < min) {
				min = m;
			}
		}
		
		return min;
	}
	
	
	private boolean forbidden(int i, int j) {
		// 1
		
		int minIkJ = getMinIkJ(i,j);
		if (G[i][j] < minIkJ) {
			return true;
			//System.out.println("1");
		}
		return false;
	}
	
	private boolean isAnyForbidden() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if (forbidden(i,j)) {
					//System.out.println("Forbidden: " + i + "," + j);
					return true;
				}
			}
		}
		return false;
	}

	private synchronized void printDebug(int i,int j,int Gij) {
		System.out.println("Forbidden: " + i + "," + j);
		System.out.println("Forbidden: " + i + "," + j + ": G[i][j]: "+Gij);
	}
	
	private void ensure(int i, int j) {

		//System.out.println("ensure");
		//printDebug(i,j,G[i][j]);
		int minIkJ = getMinIkJ(i,j);
//		System.out.println(minIkJ);
		if (G[i][j] < minIkJ) {
			G[i][j] = minIkJ;
			//System.out.println("1");
		}
	}
	
	private class EnsureObj implements Runnable {
		
		int i;
		int j;
		boolean done;
		
		public EnsureObj(int i_id, int j_id) {
			i = i_id;
			j = j_id;
			done = false;
		}

		@Override
		public void run() {

			while(!done) {
				ensure(i,j);
			}
			return;
		}
	}
	
	private void StartEnsureThreads() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ensureThreads[i][j].start();
			}
		}
	}
	
	private void StopEnsureThreads() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ensureObjects[i][j].done = true;
			}
		}
	}
	
	public void LlpRun() {
		StartEnsureThreads();
		while(isAnyForbidden()) {}
		
		System.out.println("done");
		try {
			Thread.sleep(200);
			StopEnsureThreads();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
