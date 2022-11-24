package cup.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;

class Driver {

	public static void main(String[] args) throws Exception {
		ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
		Parser parser = new Parser();
		parser.debug_parse();
		File file = new File("input.txt");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		Lexer lexer = new Lexer(symbolFactory,fis);

		Symbol currentSymbolNr;
		while ((currentSymbolNr=lexer.next_token()).sym!= sym.EOF )
		{
			System.out.println(currentSymbolNr);
		}
	}
}