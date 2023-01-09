package cup.example.enums;

/**
 * An entry for the symbol table
 * 
 * @author gabriel_nedianu
 *
 */
public class SymTableEntry {

	/**
	 * Numele variabilei/ functiei
	 */
	String symbolName;
	
	/**
	 * Tipul
	 * {@link SymbolType}
	 */
	SymbolType symbolType;
	
	/**
	 * Tipul de date
	 */
	String dataType;
	
	/**
	 * Scope-ul unde este folosita
	 * {@link IdentifierScope}
	 */
	IdentifierScope identifierScope;
	
	/**
	 * Contextul in care este declarata si folosita
	 */
	String contextName;

	/**
	 * Constructor 
	 * 
	 * @param symbolName	Numele variabilei/ functiei
	 * @param symbolType	Tipul(Functie sau Variabila)
	 * @param dataType		Tipul de date al variabilei
	 * @param identifierScope	Scope-ul unde este folosita
	 * @param contextName		Contextul in care este declarata si folosita (ex in test_function, main, etc)
	 */
	public SymTableEntry(String symbolName, SymbolType symbolType, String dataType, IdentifierScope identifierScope,
			String contextName) {
		this.symbolName = symbolName;
		this.symbolType = symbolType;
		this.dataType = dataType;
		this.identifierScope = identifierScope;
		this.contextName = contextName;
	}
	
	@Override
	public String toString() {
		return " Table entry: name:" + symbolName + " type:" + symbolType.name().toLowerCase() +
				" dataType:" + dataType + " scope:" + identifierScope.name().toLowerCase() + " context:" + contextName;
	}
	
}
