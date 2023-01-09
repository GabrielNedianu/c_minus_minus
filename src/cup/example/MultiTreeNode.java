package cup.example;

import java.util.ArrayList;
import java.util.HashMap;

import cup.example.enums.IdentifierScope;
import cup.example.enums.SymTableEntry;
import cup.example.enums.SymbolType;

public class MultiTreeNode {
	
	private String data;
	private String extraData;
	private ArrayList<MultiTreeNode> children;	
	private int descendentsCount = 0; 
	
	public String getData() {
		return data;
	}
	
	public String geteExtraData() {
		return extraData;
	}
	
	public void setData(String data) {
		this.data = data;	
		
	}
	
	public int getDescendentsCount()
	{		
		return descendentsCount;
	}

	public MultiTreeNode[] getChildren() {
		MultiTreeNode[] childrenArray = new MultiTreeNode[children.size()];		
		return children.toArray(childrenArray);
	}	
	
	public MultiTreeNode(String data, String extraData)
	{
		this.data = data;
		this.extraData = extraData;
		children = new ArrayList<MultiTreeNode>();
	}
	
	public MultiTreeNode(String data) 
	{
		this(data, "");
	}
	
	public MultiTreeNode addChild(String childData)
	{
		MultiTreeNode addedNode = new MultiTreeNode(childData);
		children.add(addedNode);
		return addedNode;
	}
	
	public void addChild(MultiTreeNode node)
	{
		children.add(node);
		descendentsCount += node.descendentsCount + 1;
	}
	
	public void printNode(int level)
	{
		for (int i = 0; i < level; i++)
		{
			System.out.print(" ");
		}
		System.out.print(data);
		if (extraData != null && extraData.length() > 0)
		{
			System.out.print(" - " + extraData + " - ");
		}
		System.out.println("");
		
		for (MultiTreeNode multiTreeNode : children) {
			multiTreeNode.printNode(level + 1);
		}
	}
	
	public void parseNode(int level, HashMap<String, SymTableEntry> tableMap) {
		
		if (level == 1) {
			if ("Var Declaration".equalsIgnoreCase(data)) {
				tableMap.put(extraData, new SymTableEntry(extraData, 
												SymbolType.VARIABLE, 
												children.get(0).geteExtraData(), 
												IdentifierScope.GLOBAL,
												"global"));
			}
			if ("FunctionDeclaration".equalsIgnoreCase(data)) {
				tableMap.put(extraData, new SymTableEntry(extraData, 
						SymbolType.FUNCTION, 
						children.get(0).geteExtraData(), 
						IdentifierScope.GLOBAL,
						"global"));
				parseNode(level + 1, tableMap, extraData);
			} 
		}
		
		// Continue parsing
		for (MultiTreeNode multiTreeNode : children) {
			multiTreeNode.parseNode(level +1, tableMap);
		}
		
	}
	
	public void parseNode(int level, HashMap<String, SymTableEntry> tableMap, String contextName) {
		if ("Var Declaration".equalsIgnoreCase(data)) {
			tableMap.put(extraData, new SymTableEntry(extraData, 
											SymbolType.VARIABLE, 
											children.get(0).geteExtraData(), 
											IdentifierScope.LOCAL,
											contextName));
		}
		for (MultiTreeNode multiTreeNode : children) {
			multiTreeNode.parseNode(level +1, tableMap, contextName);
		}
	}
	
}
