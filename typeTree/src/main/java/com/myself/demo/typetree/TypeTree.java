package com.myself.demo.typetree;

import com.myself.demo.typetree.api.AbstractNode;
import com.myself.demo.typetree.api.AbstractTree;
import com.myself.demo.typetree.api.NodeType;

public class TypeTree extends AbstractTree {

    public TypeTree(){
        TypeNode rootNode = new TypeNode("","","",NodeType.Foot,0,null);
        this.rootNode = rootNode;
    }

    @Override
    public void addNode(String host, String type, String lb) {

    }

    @Override
    public void changeNode(String host, String type, String lb) {

    }

    @Override
    public void remove(String host) {

    }

    @Override
    public AbstractNode getNode(String host) {
        return null;
    }

    @Override
    public AbstractNode matchingNode(String host) {
        return null;
    }
}
