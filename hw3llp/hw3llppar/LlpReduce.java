package hw3llppar;

import java.util.ArrayList;

public class LlpReduce {
	Integer A[];
	Integer G[];
	ArrayList<EnsureObj> ensureObjects;
	ArrayList<Thread> ensureThreads;
	int n;
	
	public LlpReduce(Integer[] Input) {
		A = Input;
		n = A.length;
		G = new Integer[n];
		ensureObjects = new ArrayList<EnsureObj>();
		ensureThreads = new ArrayList<Thread>();
		initialize ();
	}
	
	private void initialize() {
		for(int i = 0; i < n; i++) {
			G[i]=Integer.MIN_VALUE;
			ensureObjects.add(new EnsureObj(i));
			ensureThreads.add(new Thread(ensureObjects.get(i)));
		}
	}
	
	private boolean forbidden(int j) {
		if (j == 0) {
			return false;
		}
		// 1
		if ((1 <= j) && (j < n/2)) {
			if (G[j] < G[2*j] + G[2*j+1]){
				return true;
			}
		}
		
		// 2
		if ((n/2 <= j) && (j < n)) {
			if (G[j] < A[2*j-n] + A[2*j-n+1]) {
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
		if ((1 <= j) && (j < n/2)) {
			if (G[j] < G[2*j] + G[2*j+1]){
				G[j] = G[2*j] + G[2*j+1];
			}
		}
		
		// 2
		if ((n/2 <= j) && (j < n)) {
			if (G[j] < A[2*j-n] + A[2*j-n+1]) {
				G[j] = A[2*j-n] + A[2*j-n+1];
			}
		}
		//System.out.println("jid:"+j);
		//printG();
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
		
		//System.out.println("done");
		try {
			Thread.sleep(200);
			StopEnsureThreads();
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
