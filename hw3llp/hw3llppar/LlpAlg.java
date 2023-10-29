package hw3llppar;
//
// WIP
//
// ToDo: Don't duplicate so much code
//

public abstract class LlpAlg {

	abstract protected void initialize(int n);
	
	abstract protected boolean forbidden(int j);
		
	abstract protected boolean isAnyForbidden();
	
	abstract protected void ensure(int j);
	
	protected class EnsureObj implements Runnable {
		
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
}
