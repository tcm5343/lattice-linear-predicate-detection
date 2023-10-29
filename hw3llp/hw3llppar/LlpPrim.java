package hw3llppar;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class LlpPrim {
	Integer Fixed[];
	PriorityQueue<Integer> Ep = new PriorityQueue<Integer>();
	Integer W[][];
	Integer EdgeAddedtoEp[][];
	Integer G[];
	
	ArrayList<EnsureObj> ensureObjects;
	ArrayList<Thread> ensureThreads;
	
	int n;
	
	public LlpPrim(Integer[][] WInput, int nodes) {
		//Pre = PreInput;
		n = nodes;
		
		W = WInput;
		G = new Integer[n];
		EdgeAddedtoEp = new Integer[n][n];
		
		Fixed = new Integer[n];
		
		ensureObjects = new ArrayList<EnsureObj>();
		ensureThreads = new ArrayList<Thread>();
		
		initEp();
		
		initialize ();
		
		updateEpAndHeap();
		printEp();
	}
	
	public void initialize() {
		for(int i = 0; i < n; i++) {
			G[i]=getMinWeightEdgeFromEdgei(i);
			Fixed[i] = 0;
			ensureObjects.add(new EnsureObj(i));
			ensureThreads.add(new Thread(ensureObjects.get(i)));
		}
		Fixed[0] = 1;
		
		
	}
	
	void initEp() {
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				EdgeAddedtoEp[i][j] = 0;
			}
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
	
	boolean isEdgeInEPrime(int i, int j) {
		return (Fixed[i] == 1) && (Fixed[j] == 0);
	}
	
	void updateEpAndHeap() {
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (isEdgeInEPrime(i,j) && EdgeAddedtoEp[i][j] == 0){
					if (W[i][j]!= 0) {
						EdgeAddedtoEp[i][j] = 1;
						Ep.add(W[i][j]);
						if (W[i][j] == W[j][i]) {
							EdgeAddedtoEp[j][i] = 1;
							
						}
					}
				}
			}
		}
	}
	
	public boolean allNodesFixed() {
		for(int i = 0; i < n; i++) {
			if(Fixed[i] == 0) {
				return false;
			}
		}
		return true;
	}
	
	
	private boolean forbidden(int j) {
		// 1
		
		int minEdge = Ep.peek();
		
		for (int i = 0; i < n; i++) {
			if ((minEdge == W[i][j]) && (isEdgeInEPrime(i,j)) ){
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

	
	private void ensure(int j) {

		System.out.println("ensure");

		if (Fixed[j] == 1) {
			return;
		}
		
		for (int i = 0; i < n; i++) {
			
			// 1
			
			if (Fixed[i] == 0) {
				continue;
			}
			
			int minEdge = Ep.peek();
			
			if ((minEdge == W[i][j]) && (isEdgeInEPrime(i,j)) ){
				G[j] = W[i][j];
				Fixed[j] = 1;
				Ep.poll();
				
				updateEpAndHeap();
				printEp();
				
				//ret = true;
				//return true;
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
	
	public void printFixed() {
		System.out.println("Fixed: ");
		for(int i = 0; i < Fixed.length; i++) {
			System.out.println(Fixed[i]);
		}
	}
	
	void printEp() {
		System.out.println("E' : ");
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(EdgeAddedtoEp[i][j]);
				System.out.print(",");
			}
			System.out.println(" ");
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
