package com.myself.demo.typetree;

import com.myself.demo.typetree.api.SimpleNode;
import com.myself.demo.typetree.api.NodeType;

public class TypeNode extends SimpleNode {
    public TypeNode(String type, String lb, String path, NodeType nodeType, int depth, SimpleNode parent) {
        super(type, lb, path, nodeType, depth, parent);
    }
}
