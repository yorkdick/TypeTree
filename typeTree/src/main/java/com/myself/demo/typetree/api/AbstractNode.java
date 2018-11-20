package com.myself.demo.typetree.api;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractNode {
    private String type;
    private String lb;
    private String path;
    private NodeType nodeType;
    private int depth;

    public AbstractNode(String type, String lb, String path, NodeType nodeType, int depth, AbstractNode parent) {
        this.type = type;
        this.lb = lb;
        this.path = path;
        this.nodeType = nodeType;
        this.depth = depth;
        this.parent = parent;
        childs = new HashMap<>();
    }

    public AbstractNode parent;
    public Map<String,AbstractNode> childs;

    public String getType() {
        return type;
    }

    public String getLb() {
        return lb;
    }

    public String getPath() {
        return path;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public int getDepth() {
        return depth;
    }

    public AbstractNode getParent() {
        return parent;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public Map<String, AbstractNode> getChilds() {
        return childs;
    }
}
