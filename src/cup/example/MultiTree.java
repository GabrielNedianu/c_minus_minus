package cup.example;

public class MultiTree {
	
	private MultiTreeNode root = null;
	private int nodesCount = 0;
	
	
	public MultiTreeNode create(String rootData) throws Exception
	{
		if (root != null)
		{
			throw new Exception("The three has already been created");			
		}
		
		root = new MultiTreeNode(rootData);
		nodesCount = 1;
		return root;
	}
	
	public MultiTreeNode create(String rootData, MultiTreeNode child) throws Exception
	{
		MultiTreeNode createdNode = create(rootData);
		root.addChild(child);
		nodesCount = root.getDescendentsCount();
		return createdNode;
	}
	
	
	public MultiTreeNode addChildNode(String childData)
	{		
		MultiTreeNode newNode =  root.addChild(childData);
		nodesCount = getNodesCount() + 1;
		return newNode;
	}
	
	public MultiTreeNode addChildNode(MultiTreeNode child)
	{		
		root.addChild(child);
		nodesCount = getNodesCount() + child.getDescendentsCount();
		return child;
	}
	
	public void printTree()
	{
		if (root == null)
		{
			System.out.println("The tree was not created yet.");
			return;
		}
		
		root.printNode(0);
		System.out.println("Total elements: " + nodesCount);
				
	}

	public int getNodesCount() {
		return nodesCount;
	}
	
}
