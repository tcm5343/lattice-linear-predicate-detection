package hw3llppar;

import java.util.ArrayList;

public class LlpBellmanFord {
	Integer Pre[][];
	Integer W[][];
	Integer G[];
	ArrayList<EnsureObj> ensureObjects;
	ArrayList<Thread> ensureThreads;
	int n;
	
	public LlpBellmanFord(Integer[][] WInput, int start) {
		
		n = WInput[0].length;
		//System.out.println("nodes: "+n);
		
		W = WInput;
		Pre = new Integer[n][n];
		G = new Integer[n];
		
		ensureObjects = new ArrayList<EnsureObj>();
		ensureThreads = new ArrayList<Thread>();
		
		initialize (G.length, start);
		initPre();
	}
	
	
	//
	// Pre(j) - all nodes i leading to j in DAG
	//
	public void initPre() {
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				Pre[i][j] = 0;
				if (W[i][j] != 0) {
					Pre[i][j] = 1;
				}
			}
			
		}
	}
	
	public void initialize(int n, int start) {
		for(int i = 0; i < n; i++) {
			G[i]=Integer.MAX_VALUE;
			if (i == start) {
				G[i] = 0;
			}
			ensureObjects.add(new EnsureObj(i));
			ensureThreads.add(new Thread(ensureObjects.get(i)));
		}
	}
	
	private boolean forbidden(int j) {
		// 1
		
		for(int i = 0; i < W[j].length; i++) {
			if ((G[j] > G[i] + W[i][j]) && (Pre[i][j] == 1) && G[i] != Integer.MAX_VALUE) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isAnyForbidden() {
		for(int j = 0; j < n; j++) {
			if (forbidden(j)) {
				//System.out.println("Forbidden: " + i);
				return true;
			}
		}
		return false;
	}
	
	private synchronized void printDebug(int i,int j,int Gi,int Gj,int Wij) {
		System.out.println("Forbidden: " + j +": ");
		System.out.println("Forbidden: " + j +": i: " +i);
		System.out.println("Forbidden: " + j +": G[j]: "+Gi);
		System.out.println("Forbidden: " + j +": G[i]: "+Gj);
		System.out.println("Forbidden: " + j +": W[i][j]"+Wij);
	}
	
	private void ensure(int j) {

		//System.out.println("ensure");
			
		for (int i = 0; i < n; i++) {
			
			// 1
			if ((G[j] > G[i] + W[i][j]) && (Pre[i][j] == 1) && G[i] != Integer.MAX_VALUE) {
				//printDebug(i,j,G[i],G[j],W[i][j]);
				G[j] = G[i] + W[i][j];
				//System.out.println("1");
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
			if (j == 0) 
				return;
			while(!done) {
				ensure(j);
			}
			return;
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
