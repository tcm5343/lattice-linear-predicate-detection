package hw3llppar;

public class LlpOptimalBst {
	Integer P[];
	Integer G[][];
	Integer mininit[][];
	EnsureObj ensureObjects[][];
	Thread ensureThreads[][];
	
	int n;
	
	public LlpOptimalBst(Integer[] PInput) {
		P = PInput;
		n = P.length;
		G = new Integer[n][n];
		mininit = new Integer[n][n];
		
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
				mininit[i][j] = 0;
				ensureObjects[i][j] = new EnsureObj(i,j);
				ensureThreads[i][j] = new Thread(ensureObjects[i][j]);
			}
		}
	}
	
	private int S(int i, int j) {
		//if (i > j) { return 0; }

		int sum = 0;
		
		for(int k = i; k <= j; k++) {
			sum+=P[k];
		}
		
		return sum;
	}
	
	private int getTreeValue(int i, int j, int k) {
		int kj = k-1;
		int ki = k+1;
		
		if (kj < 0) {
			//kj=0;
			return /*G[i][i] + */S(i,j) + G[ki][j];
		}
		
		if (ki >= n) {
			ki=n-1;
			return G[i][kj] + S(i,j) /*+ G[j][j]*/;
		}
		
		return G[i][kj] + S(i,j) + G[ki][j];
	}
	
	private int getMinIkJ (int i, int j) {
				
		int min;

		min = getTreeValue(i,j,i);
		mininit[i][j] = min;

		for (int k = i; k <= j; k++) {

			int m = getTreeValue(i,j,k);
			if (m < min) {
				min = m;
			}
		}
		
		return min;
	}
	
	
	private boolean forbidden(int i, int j) {
		// 1
		
		if (i >= j) { return false; }
		
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
		if (i >= j) { return; }
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
		//ensureThreads[0][1].start();
	}
	
	private void StopEnsureThreads() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ensureObjects[i][j].done = true;
			}
		}
		//ensureObjects[0][1].done = true;;
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
