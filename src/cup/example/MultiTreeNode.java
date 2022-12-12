package cup.example;

import java.util.ArrayList;

public class MultiTreeNode {
	
	private String data;
	private String extraData;
	private ArrayList<MultiTreeNode> children;	
	private int descendentsCount = 0; 
	
	public String getData() {
		return data;
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

}
