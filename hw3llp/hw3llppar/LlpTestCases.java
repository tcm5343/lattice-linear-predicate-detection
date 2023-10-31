package hw3llppar;

import static org.junit.Assert.*;
import org.junit.Test;

public class LlpTestCases {

    //
	// LLP PPx Test
	//
    @Test public void testPpxLen0() {
		LLP l = new LLP();
		Integer[] A = new Integer[] {};
        Integer[] G = l.ppx(A);
        assertArrayEquals(new Integer[] {}, G);
    }
    
    @Test public void testPpxLen1() {
		LLP l = new LLP();
		Integer[] A = new Integer[] {1};
        Integer[] G = l.ppx(A);
        assertArrayEquals(new Integer[] {0}, G);
    }
    
    @Test public void testPpxLen2_1() {
		LLP l = new LLP();
		Integer[] A = new Integer[] {1,1};
        Integer[] G = l.ppx(A);
        assertArrayEquals(new Integer[] {0,1}, G);
    }
    
    @Test public void testPpxLen2_2() {
		LLP l = new LLP();
		Integer[] A = new Integer[] {0,1};
        Integer[] G = l.ppx(A);
        assertArrayEquals(new Integer[] {0,0}, G);
    }
    
    @Test public void testPpxLen4_1() {
		LLP l = new LLP();
		Integer[] A = new Integer[] {-1,-2,-3,-4};
        Integer[] G = l.ppx(A);
        assertArrayEquals(new Integer[] {0,-1,-3,-6}, G);
    }
    
    @Test public void testPpxLen8_1() {
		LLP l = new LLP();
		Integer[] A = new Integer[] {1,2,3,4,5,6,7,8};
        Integer[] G = l.ppx(A);
        assertArrayEquals(new Integer[] {0,1,3,6,10,15,21,28}, G);
    }
    
    @Test public void testPpxLen8_2() {
		LLP l = new LLP();
		Integer[] A = new Integer[] {0,0,1,0,0,0,0,0};
        Integer[] G = l.ppx(A);
        assertArrayEquals(new Integer[] {0,0,0,1,1,1,1,1}, G);
    }
    
    @Test public void testPpxLen8_3() {
		LLP l = new LLP();
		Integer[] A = new Integer[] {0,0,1,0,0,-1,0,0};
        Integer[] G = l.ppx(A);
        assertArrayEquals(new Integer[] {0,0,0,1,1,1,0,0}, G);
    }
    
    
    //
	// LLP BellmanFord Test
	//
    @Test public void testBellFord4_0() {
		LLP l = new LLP();
		
		Integer[][] WIn = new Integer[][] { 
				{0,1,2,0},
				{0,0,0,3},
				{0,0,0,4},
				{0,0,0,0}};
		int start = 0;
        Integer[] G = l.bellman(WIn, start);
        assertArrayEquals(new Integer[] {0,1,2,4}, G);
    }
    
    @Test public void testBellFord4_1() {
		LLP l = new LLP();
		
		Integer[][] WIn = new Integer[][] { 
				{0,1,2,0},
				{0,0,0,3},
				{0,0,0,4},
				{0,0,0,0}};
		int start = 1;
		int inf = Integer.MAX_VALUE;
        Integer[] G = l.bellman(WIn, start);
        assertArrayEquals(new Integer[] {inf,0,inf,3}, G);
    }
    
    @Test public void testBellFord4_2() {
		LLP l = new LLP();
		
		Integer[][] WIn = new Integer[][] { 
				{0,1,2,0},
				{0,0,0,3},
				{0,0,0,4},
				{0,0,0,0}};
		int start = 2;
		int inf = Integer.MAX_VALUE;
        Integer[] G = l.bellman(WIn, start);
        assertArrayEquals(new Integer[] {inf,inf,0,4}, G);
    }
    
    @Test public void testBellFord4_3() {
		LLP l = new LLP();
		
		Integer[][] WIn = new Integer[][] { 
				{0,1,2,0},
				{0,0,0,3},
				{0,0,0,4},
				{0,0,0,0}};
		int start = 3;
		int inf = Integer.MAX_VALUE;
        Integer[] G = l.bellman(WIn, start);
        assertArrayEquals(new Integer[] {inf,inf,inf,0}, G);
    }
    
    
    //
	// LLP Prim Test
	//
    @Test public void testPrim4_0() {
		LLP l = new LLP();
		
		Integer[][] WIn = new Integer[][] { 
				{0,1,2,0},
				{1,0,0,3},
				{2,0,0,4},
				{0,3,4,0}};
        Integer[] G = l.Prim(WIn);
        assertArrayEquals(new Integer[] {1,1,2,3}, G);
    }
    
    @Test public void testPrim4_1() {
		LLP l = new LLP();
		
		Integer[][] WIn = new Integer[][] { 
				{0,6,5,7},
				{6,0,0,4},
				{5,0,0,8},
				{7,4,8,0}};
        Integer[] G = l.Prim(WIn);
        assertArrayEquals(new Integer[] {5,6,5,4}, G);
    }
    
    //
	// Opt BST Test
	//
    @Test public void testObst1_0() {
		LLP l = new LLP();
		
		Integer[] P = new Integer[] {34,50};
		Integer Exp = 118;
        Integer[][] G = l.OptBst(P);
        assertEquals(Exp, G[0][P.length-1]);
    }
    
    @Test public void testObst1_1() {
		LLP l = new LLP();
		
		Integer[] P = new Integer[] {34,8,50};
		Integer Exp = 142;
        Integer[][] G = l.OptBst(P);
        assertEquals(Exp, G[0][P.length-1]);
    }
    
    @Test public void testObst1_2() {
		LLP l = new LLP();
		
		Integer[] P = new Integer[] {4,2,6,3};
		Integer Exp = 26;
        Integer[][] G = l.OptBst(P);
        assertEquals(Exp, G[0][P.length-1]);
    }
    
    @Test public void testObst1_3() {
		LLP l = new LLP();
		
		Integer[] P = new Integer[] {25,20,5,20,30};
		Integer Exp = 210;
        Integer[][] G = l.OptBst(P);
        assertEquals(Exp, G[0][P.length-1]);
    }
  
}
