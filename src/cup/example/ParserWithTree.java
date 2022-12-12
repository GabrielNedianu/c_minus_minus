package cup.example;

public class ParserWithTree extends Parser{

	public ParserWithTree() 
	{
		super();
	}
	protected MultiTreeNode createDeclarationNode(MultiTreeNode declaration) 
	{ 
		MultiTreeNode newNode = new MultiTreeNode("Declaration");
		newNode.addChild(declaration);
		return newNode;
	}
 	protected MultiTreeNode createFunctionDeclarationNode(MultiTreeNode typeSpecifier, String identifierName, MultiTreeNode paramsList, MultiTreeNode block) 	
 	{ 
 		MultiTreeNode newNode = new MultiTreeNode("FunctionDeclaration", identifierName);
 		newNode.addChild(typeSpecifier);
 		if (paramsList != null)
 			newNode.addChild(paramsList);
 		newNode.addChild(block);
 		return newNode; 
 	}
 	
 	protected MultiTreeNode createParameters(MultiTreeNode params)
  	{ 
  		return params;
  	}
 	
  	protected MultiTreeNode createTypeSpecifier(String typeName)
  	{ 
  		return new MultiTreeNode("TypeSpecifier", typeName);
  	}
	protected MultiTreeNode createListNode(String listName, MultiTreeNode firstChild)
	{
		MultiTreeNode newNode = new MultiTreeNode(listName);
		newNode.addChild(firstChild);
		return newNode;
	}
	protected MultiTreeNode createBlock(MultiTreeNode declList, MultiTreeNode stmtList) {
		MultiTreeNode blockNode = new MultiTreeNode("Block");
		blockNode.addChild(declList);
		blockNode.addChild(stmtList);
		return blockNode;
	}
	protected MultiTreeNode createVarDeclaration(MultiTreeNode typeSpecifier, String identifierName, Integer value )
	{
		MultiTreeNode newNode = new MultiTreeNode("Var Declaration", identifierName);
		newNode.addChild(typeSpecifier);
		if(value != null) {
			newNode.addChild(new MultiTreeNode("IntValue", "" + value));
		}
		return newNode;
	}
	protected MultiTreeNode createVarArrDeclaration(MultiTreeNode typeSpecifier, String identifierName, Integer value )
	{
		MultiTreeNode newNode = new MultiTreeNode("Array Var Declaration", identifierName);
		newNode.addChild(typeSpecifier);		
		newNode.addChild(new MultiTreeNode("Array Dim", "" + value));
		return newNode;
	}
	protected MultiTreeNode createReturnStatement(MultiTreeNode exp) {
		MultiTreeNode rtrn = new MultiTreeNode("Return");
		if (exp != null) {
			rtrn.addChild(exp);
		}
		return rtrn;
	}
	protected MultiTreeNode createCompoundStatement(MultiTreeNode declarations, MultiTreeNode instructions)
	{
		MultiTreeNode newNode = new MultiTreeNode("CompoundStatement");
		if (declarations != null)
			newNode.addChild(declarations);
		if (instructions != null)
			newNode.addChild(instructions);
		return newNode;
	}
	protected MultiTreeNode createIfStatement(String identifier, MultiTreeNode ifInstructions, MultiTreeNode elseInstructions)
	{
		MultiTreeNode newNode = new MultiTreeNode("IfStatement", identifier);
		newNode.addChild(ifInstructions);
		if (elseInstructions != null)
			newNode.addChild(elseInstructions);
		return newNode;
	}
}
