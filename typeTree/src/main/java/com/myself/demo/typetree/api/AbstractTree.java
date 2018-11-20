package com.myself.demo.typetree.api;

import com.myself.demo.typetree.TypeNode;

public abstract class AbstractTree {
    private TypeNode rootNode;

    public SimpleNode getRootNode(){
        return rootNode;
    }

    protected void setRootNode(TypeNode rootNode){
        this.rootNode = rootNode;
    }

    public abstract void addNode(String host,String type, String lb);

    public abstract void changeNode(String host,String type, String lb);

    public abstract void remove(String host);

    public abstract SimpleNode getNode(String host);

    public abstract SimpleNode matchingNode(String host);
}
