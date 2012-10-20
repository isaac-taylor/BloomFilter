import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.sizeof.SizeOf;
import bloomfilter.BloomSet;

/**
 * main.java
 * Copyright AT&T 2012
 * Written by it153a
 */

/**
 * @author it153a
 *
 */
public class main {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		
		int setSize = 334;
		int k = 100;
		
		int objectsToAdd = 50;
		
		BloomSet set = new BloomSet(setSize,k);
		Object obj = new Object();
		Object obj2 = new Object();
		set.add(obj);
		
		List<Object> list = new ArrayList<Object>(objectsToAdd);
		
		list.add(obj);
		
		double avg = 0.0;
		
		for(int i = 0; i < objectsToAdd; i++){
			Object o = new Object();
			double start = System.nanoTime();
			set.add(o);
			double end = System.nanoTime();
			if(avg <= 0.0)
				avg += (end - start);
			else
				avg = (avg + (end - start)) / 2;
			//list.add(o);
		}
		
		double base = 0.6185;
		
		System.out.println("k: " + avg);
		System.out.println("set Size: " + setSize);
		System.out.println("Size of one object: " + SizeOf.deepSizeOf(obj));
		System.out.println("Size of the set: " + SizeOf.deepSizeOf(set));
		
		
		
		System.out.println("False positive probability: " + Math.ceil((1 - Math.pow(base, (setSize/objectsToAdd))) * 100) + "%");
		System.out.println("Set size versus objects added " +((float) objectsToAdd/(float)setSize) * 100 + "%");
		
		
		
		System.out.println("Size of the list: " + SizeOf.deepSizeOf(list));
		
		System.out.println(set.contains(obj2));
	}

}
