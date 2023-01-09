package cup.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cup.example.enums.SymTableEntry;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;

class Driver {

	public static void main(String[] args) throws Exception {
		ParserWithTree parser = new ParserWithTree();
		parser.parse();
		
		MultiTree tree = parser.getSyntaxTree();
		tree.printTree();
		
		HashMap<String, SymTableEntry> symMap = new HashMap<>();
		tree.parseTree(symMap);
		
		System.out.println();
		for (Entry<String, SymTableEntry> entry : symMap.entrySet()) {
			int tabVal = 20 - entry.getKey().length();
		    System.out.print(entry.getKey() + ":");
		    for (int i = 0; i < tabVal; i++) {
				System.out.print(" ");
			}
		    System.out.println(entry.getValue());
		}
		
	}
}