import java.util.Scanner;
import java.util.ArrayList;


public class MyHashtable<K, V> {
	public static void main(String args[]) throws Exception {
		new TestHarness(new MyHashtable<String, String>()).run();
	}

	ArrayList<ArrayList<HashEntries<K, V>>> hashMap;
	int size;

	class HashEntries<K, V> {
		K key;
		V value;
		
		public HashEntries(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public String toString() {
			return key.toString() + "=" + value.toString();
		}
	}
	
	public MyHashtable() {
		this(100);
	}
	
	public MyHashtable(int sizeOf) {
		this.size = sizeOf;
		hashMap = new ArrayList<>(sizeOf);
		for(int i=0; i<sizeOf; i++)
			hashMap.add(i, new ArrayList<HashEntries<K, V>>());
	}
	public int getIndex(K keys) {
		return keys.hashCode() % size;
	}
	public V get(K keys) {
		int index = getIndex(keys);
		System.out.println("get "+ index);
		ArrayList<HashEntries<K, V>> entriesList = hashMap.get(index);
		System.out.println("got list: " + entriesList);
		for(HashEntries<K, V> entries : entriesList) {
			if(entries.key.equals(keys)) {
				return entries.value;
			}
		}
		return null;
	}
	
	public void put(K keys, V values) {
		int index = getIndex(keys);
		System.out.println("put "+ index);	
		ArrayList<HashEntries<K, V>> entriesLists = hashMap.get(index);
		HashEntries<K, V> newEntries = new HashEntries<K, V>(keys, values);
		boolean Founded = false;
		for(HashEntries<K, V> entries : entriesLists) {
			if(entries.key.equals(newEntries.key)) {
				Founded = true;
				entries.value = newEntries.value;
			}
		}
		if(!Founded) {
			entriesLists.add(newEntries);
		}
		System.out.println(hashMap);
	}
	public static class TestHarness implements Runnable {
		final MyHashtable<String, String> hashtable;

		public TestHarness(MyHashtable<String, String> hshtbl) {
			this.hashtable = hshtbl;
		}

		public void run() {

			Scanner example = new Scanner(System.in);
			while (example.hasNext()) {
				String k = example.next();
				String v = null;
				if (k.contains("=")) {
					String[] toSplit = k.split("\\=");
					k = toSplit[0];
					v = toSplit[1];
				}
				if (v == null) {
					System.out.println(hashtable.get(k));
				} else {
					hashtable.put(k, v);
				}
			}
		}
	}
}