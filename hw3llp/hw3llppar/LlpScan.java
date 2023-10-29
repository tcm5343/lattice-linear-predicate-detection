package hw3llppar;

import java.util.ArrayList;

public class LlpScan {
	Integer A[];
	Integer S[];
	Integer G[];
	ArrayList<EnsureObj> ensureObjects;
	ArrayList<Thread> ensureThreads;
	int n;
	
	public LlpScan(Integer[] AInput, Integer[] SInput) {
		n = AInput.length;
		A = AInput;
		S = SInput;
		G = new Integer[2*n];
		
		ensureObjects = new ArrayList<EnsureObj>();
		ensureThreads = new ArrayList<Thread>();
		
		initialize (G.length);
	}
	
	private void initialize(int n) {
		for(int i = 0; i < n; i++) {
			G[i]=Integer.MIN_VALUE;
			ensureObjects.add(new EnsureObj(i));
			ensureThreads.add(new Thread(ensureObjects.get(i)));
		}
	}
	
	private boolean forbidden(int j) {
		
		// 1
		if (j == 1) {
			if (G[j] < 0) {
				return true;
			}
		}
		
		// 2
		if (j % 2 == 0) {
			if (G[j] < G[j/2]) {
				return true;
			}
		}
		
		// 3
		if ((j % 2 == 1) && (j < n)) {
			if (G[j] < S[j-1] + G[j/2]) {
				return true;
			}
		}
		
		// 4
		if ((j % 2 == 1) && (j > n)) {
			if (G[j] < A[j-n-1] + G[j/2]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isAnyForbidden() {
		for(int i = 0; i < n; i++) {
			if (forbidden(i)) {
				//System.out.println("Forbidden: " + i);
				return true;
			}
		}
		return false;
	}
	
	private void ensure(int j) {

		// 1
		if (j == 1) {
			if (G[j] < 0) {
				G[j] = 0;
				//System.out.println("1");
			}
		}
		
		// 2
		else if (j % 2 == 0) {
			if (G[j] < G[j/2]) {
				G[j] = G[j/2];
				//System.out.println("2");
			}
		}
		
		// 3
		else if ((j % 2 == 1) && (j < n)) {
			if (G[j] < S[j-1] + G[j/2]) {
				G[j] = S[j-1] + G[j/2];
				//System.out.println("3");
			}
		}
		
		// 4
		else if ((j % 2 == 1) && (j > n)) {
			if (G[j] < A[j-n-1] + G[j/2]) {
				G[j] = A[j-n-1] + G[j/2];
				//System.out.println("4");
			}
		}
	}
	
	
	private class EnsureObj implements Runnable {
		
		int j;
		boolean done;
		
		public EnsureObj(int id) {
			j = id;
			done = false;
		}

		@Override
		public void run() {
			while(!done) {
				ensure(j);
			}
		}
	}
	
	private void StartEnsureThreads() {
		for (int i = 0; i < ensureObjects.size(); i++) {
			ensureThreads.get(i).start();
		}
	}
	
	private void StopEnsureThreads() {
		for (int i = 0; i < ensureObjects.size(); i++) {
			ensureObjects.get(i).done = true;
		}
	}
	
	public Integer[] getExclusiveG() {
		Integer[] ExclusiveG = new Integer[n];
		for(int i = n; i < G.length; i++) {
			ExclusiveG[i-n] = G[i];
		}
		return ExclusiveG;
	}
	
	public void LlpRun() {
		StartEnsureThreads();
		while(isAnyForbidden()) {}
		
		System.out.println("done");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		StopEnsureThreads();
	}
}
