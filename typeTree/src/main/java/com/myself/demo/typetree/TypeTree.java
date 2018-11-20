package com.myself.demo.typetree;

import com.myself.demo.typetree.api.AbstractNode;
import com.myself.demo.typetree.api.AbstractTree;
import com.myself.demo.typetree.api.NodeType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TypeTree extends AbstractTree {

    public TypeTree(){
        TypeNode rootNode = new TypeNode("","","",NodeType.Root,0,null);
        this.rootNode = rootNode;
    }

    @Override
    public synchronized void addNode(String host, String type, String lb) {
        List<String> hostList = revertHost(host);
        addNode(rootNode,hostList,type,lb);
    }

    private synchronized void addNode(AbstractNode parentNode, List<String> hostList, String type, String lb) {
        String hostName = hostList.remove(0);

        if(!parentNode.childs.containsKey(hostName)){
            String path = hostName+"."+parentNode.getPath();
            if(StringUtils.isBlank(parentNode.getPath())){
                path = hostName;
            }
            TypeNode childNode = new TypeNode("","",path,NodeType.Foot,parentNode.getDepth()+1,parentNode);
            parentNode.getChilds().put(hostName,childNode);
            if(parentNode.getNodeType()!=NodeType.Root){
                parentNode.setNodeType(NodeType.Normal);
            }
        }

        AbstractNode rootNode = parentNode.getChilds().get(hostName);
        if(hostList.isEmpty()){
            rootNode.setLb(lb);
            rootNode.setType(type);
        }else{
            addNode(rootNode,hostList,type,lb);
        }
    }

    @Override
    public synchronized void changeNode(String host, String type, String lb) {
        AbstractNode node = getNode(host);
        if(node!=null){
            node.setType(type);
            node.setLb(lb);
        }
    }

    @Override
    public synchronized void remove(String host) {
        List<String> hostList = revertHost(host);

        AbstractNode parentNode = rootNode;
        while(!hostList.isEmpty()){
            String hostName = hostList.remove(0);
            if(parentNode.getChilds().containsKey(hostName)){
                AbstractNode currentNode = parentNode.getChilds().get(hostName);
                if(hostList.isEmpty()){
                    parentNode.getChilds().remove(hostName);
                    if(parentNode.getChilds().isEmpty()){
                        parentNode.setNodeType(NodeType.Foot);
                    }
                }else{
                    parentNode = currentNode;
                }
            }
        }
    }

    @Override
    public AbstractNode getNode(String host) {
        List<String> hostList = revertHost(host);

        AbstractNode parentNode = rootNode;
        while(!hostList.isEmpty()){
            String hostName = hostList.remove(0);
            if(parentNode.getChilds().containsKey(hostName)){
                AbstractNode currentNode = parentNode.getChilds().get(hostName);
                if(hostList.isEmpty()){
                    return currentNode;
                }else{
                    parentNode = currentNode;
                }
            }else{
                return null;
            }
        }
        return null;
    }

    @Override
    public AbstractNode matchingNode(String host) {
        List<String> hostList = revertHost(host);

        AbstractNode parentNode = rootNode;
        while(!hostList.isEmpty()){
            String hostName = hostList.remove(0);
            if(parentNode.getChilds().containsKey(hostName)){
                AbstractNode currentNode = parentNode.getChilds().get(hostName);
                if(hostList.isEmpty()){
                    return currentNode;
                }else{
                    parentNode = currentNode;
                }
            }else{
                if(parentNode.getDepth()>1){
                    return parentNode;
                }
                return null;
            }
        }
        return null;
    }

    private List<String> revertHost(String host){
        List<String> hostList = new ArrayList<>(Arrays.asList(host.split("\\.")));
        Collections.reverse(hostList);
        return hostList;
    }

}
