import java.io.*;

import java.util.*;



class BinarySearchTree {
	

		 // Driver Code
	public static void main (String[] args) {
		// Let us create following BST
		BST binTree=new BST();
		binTree.insert(310);
		binTree.insert(560);
		binTree.insert(165);
		binTree.insert(290);
		binTree.insert(100);
		binTree.insert(420);
		binTree.insert(670);
		// print inorder traversal of the BST
		binTree.inorder();
	}
}

class Node{
	Node left;
	int value;
	Node right;
	Node(int value){
		this.value=value;
	}
}
//Root of BST
class BST{
Node root;

public void insert(int keys){
		Node nodes=new Node(keys);
		if(root==null) {
			root = nodes;
			return;
		}
		Node previous=null;
		Node temporary=root;
		while (temporary!=null){
			if(temporary.value>keys){
				previous=temporary;
				temporary=temporary.left;
			}
			else if (temporary.value<keys){
				previous=temporary;
				temporary=temporary.right;
			}
		}
		if(previous.value>keys)
			previous.left=nodes;
		else previous.right=nodes;
	}

	
public void inorder(){
	Node temporary=root;
	Stack<Node> stck=new Stack<>();
	while (temporary!=null||!stck.isEmpty()){
		if(temporary!=null){
			stck.add(temporary);
			temporary=temporary.left;
		}
		else {
			temporary=stck.pop();
			System.out.print(temporary.value + " ");
			temporary=temporary.right;
		}
	}
}
}
