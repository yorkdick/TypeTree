package com.myself.demo.typetree;

import com.myself.demo.typetree.api.AbstractNode;
import com.myself.demo.typetree.api.NodeType;

public class TypeNode extends AbstractNode {

    public TypeNode(String type, String lb, String path, NodeType nodeType, int depth, AbstractNode parent) {
        super(type, lb, path, nodeType, depth, parent);
    }

    @Override
    public void addChild(String host, String type, String lb) {

    }

    @Override
    public void changeChild(String host, String type, String lb) {

    }

    @Override
    public void removeChild(String host) {

    }
}
