package com.myself.demo.typetree.api;

import com.myself.demo.typetree.TypeNode;

import java.util.Map;

public abstract class AbstractTree {
    public TypeNode rootNode;

    public abstract void addNode(String host,String type, String lb);

    public abstract void changeNode(String host,String type, String lb);

    public abstract void remove(String host);

    public abstract AbstractNode getNode(String host);

    public abstract AbstractNode matchingNode(String host);
}
