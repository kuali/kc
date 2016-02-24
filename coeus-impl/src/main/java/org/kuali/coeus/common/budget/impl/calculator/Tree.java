/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.budget.impl.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Tree of Objects of generic type T. The Tree is represented as
 * a single rootElement which points to a List&lt;Node&lt;T&gt;&gt; of children. There is
 * no restriction on the number of children that a particular node may have.
 * This Tree provides a method to serialize the Tree into a List by doing a
 * pre-order traversal. It has several methods to allow easy updation of Nodes
 * in the Tree.
 */
public class Tree<T> {
 
    private Node<T> rootElement;

    public Tree() {
        super();
    }
 
    /**
     * Return the root Node of the tree.
     * @return the root element.
     */
    public Node<T> getRootElement() {
        return this.rootElement;
    }
 
    /**
     * Set the root Element for the tree.
     * @param rootElement the root element to set.
     */
    public void setRootElement(Node<T> rootElement) {
        this.rootElement = rootElement;
    }
     
    /**
     * Returns the Tree&lt;T&gt; as a List of Node&lt;T&gt; objects. The elements of the
     * List are generated from a pre-order traversal of the tree.
     * @return a List&lt;Node&lt;T&gt;&gt;.
     */
    public List<Node<T>> toList() {
        List<Node<T>> list = new ArrayList<Node<T>>();
        walk(rootElement, list);
        return list;
    }
     
    /**
     * Returns a String representation of the Tree. The elements are generated
     * from a pre-order traversal of the Tree.
     * @return the String representation of the Tree.
     */
    public String toString() {
        return toList().toString();
    }
     
    /**
     * Walks the Tree in pre-order style. This is a recursive method, and is
     * called from the toList() method with the root element as the first
     * argument. It appends to the second argument, which is passed by reference     * as it recurses down the tree.
     * @param element the starting element.
     * @param list the output of the walk.
     */
    private void walk(Node<T> element, List<Node<T>> list) {
        list.add(element);
        for (Node<T> data : element.getChildren()) {
            walk(data, list);
        }
    }
}

/**
 * Represents a node of the Tree<T> class. The Node<T> is also a container, and
 * can be thought of as instrumentation to determine the location of the type T
 * in the Tree<T>.
 */
class Node<T> {
 
    public T data;
    public List<Node<T>> children;

    public Node() {
        super();
    }
 
    /**
     * Convenience constructor to create a Node<T> with an instance of T.
     * @param data an instance of T.
     */
    public Node(T data) {
        this();
        setData(data);
    }
     
    /**
     * Return the children of Node&lt;T&gt;. The Tree&lt;T&gt; is represented by a single
     * root Node&lt;T&gt; whose children are represented by a List&lt;Node&lt;T&gt;&gt;. Each of
     * these Node&lt;T&gt; elements in the List can have children. The getChildren()
     * method will return the children of a Node&lt;T&gt;.
     * @return the children of Node&lt;T&gt;
     */
    public List<Node<T>> getChildren() {
        if (this.children == null) {
            return new ArrayList<Node<T>>();
        }
        return this.children;
    }
 
    /**
     * Sets the children of a Node&lt;T&gt; object. See docs for getChildren() for
     * more information.
     * @param children the List&lt;Node&lt;T&gt;&gt; to set.
     */
    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }
 
    /**
     * Returns the number of immediate children of this Node<T>.
     * @return the number of immediate children.
     */
    public int getNumberOfChildren() {
        if (children == null) {
            return 0;
        }
        return children.size();
    }
     
    /**
     * Adds a child to the list of children for this Node&lt;T&gt;. The addition of
     * the first child will create a new List&lt;Node&lt;T&gt;&gt;.
     * @param child a Node&lt;T&gt; object to set.
     */
    public void addChild(Node<T> child) {
        if (children == null) {
            children = new ArrayList<Node<T>>();
        }
        children.add(child);
    }
     
    /**
     * Inserts a Node&lt;T&gt; at the specified position in the child list. Will
     * @throws ArrayIndexOutOfBoundsException if the index does not exist.
     * @param index the position to insert at.
     * @param child the Node&lt;T&gt; object to insert.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void insertChildAt(int index, Node<T> child) throws IndexOutOfBoundsException {
        if (index == getNumberOfChildren()) {
            // this is really an append
            addChild(child);
            return;
        } else {
            children.get(index); //just to throw the exception, and stop here
            children.add(index, child);
        }
    }
     
    /**
     * Remove the Node&lt;T&gt; element at index index of the List&lt;Node&lt;T&gt;&gt;.
     * @param index the index of the element to delete.
     * @throws IndexOutOfBoundsException if thrown.
     */
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }
 
    public T getData() {
        return this.data;
    }
 
    public void setData(T data) {
        this.data = data;
    }
     
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(getData().toString()).append(",[");
        int i = 0;
        for (Node<T> e : getChildren()) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(e.getData().toString());
            i++;
        }
        sb.append("]").append("}");
        return sb.toString();
    }
}
