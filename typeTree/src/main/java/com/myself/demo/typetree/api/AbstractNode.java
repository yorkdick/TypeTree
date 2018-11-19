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

    public abstract void addChild(String host,String type,String lb);
    public abstract void changeChild(String host,String type,String lb);
    public abstract void removeChild(String host);

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

    public Map<String, AbstractNode> getChilds() {
        return childs;
    }
}
