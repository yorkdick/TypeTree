package com.myself.demo.typetree.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.demo.typetree.TypeTree;
import com.myself.demo.typetree.api.AbstractNode;
import com.myself.demo.typetree.api.NodeType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class TypeTreeTest {

    public static final String lbFile = "C:\\tmp\\tree\\fenlei.json";
    public static final String findTxt = "C:\\tmp\\tree\\look.txt";

    @Test
    public void testInsert() throws IOException {
        TypeTree typeTree = crateTree(lbFile);
        printTree(typeTree.rootNode);
    }

    @Test
    public void testFind() throws IOException {
        TypeTree typeTree = crateTree(lbFile);
        List<String> hosts = Files.readAllLines(Paths.get(new File(findTxt).getPath()));
        long start = System.currentTimeMillis();
        for(String host : hosts){
            AbstractNode abstractNode = typeTree.matchingNode(host);
            if (abstractNode == null || StringUtils.isBlank(abstractNode.getType())) {
                System.out.println(host + "\t not found");
            } else {
                System.out.println(host + "\t" + abstractNode.getType());
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Find " + hosts.size() + " host cost:" + (end - start) + "ms");
    }


    public TypeTree crateTree(String filePath) throws IOException {
        TypeTree typeTree = new TypeTree();
        long start = System.currentTimeMillis();
        JSONObject jsonObject = JSON.parseObject(FileUtils.readFileToString(new File(filePath), "UTF-8"));
        long end = System.currentTimeMillis();
        System.out.println("Load file to json cost:" + (end - start) + "ms");

        start = System.currentTimeMillis();
        jsonObject.entrySet().forEach(entry -> {
            String type = entry.getKey();
            JSONArray hosts = (JSONArray) entry.getValue();
            hosts.forEach(host -> {
                String hostName = (String) host;
                typeTree.addNode(hostName, type, "");
            });
        });
        end = System.currentTimeMillis();
        System.out.println("Load json to tree cost:" + (end - start) + "ms");

        return typeTree;
    }

    public void printTree(AbstractNode parentNode) {
        if (parentNode.getNodeType() != NodeType.Root) {
            Map<String, AbstractNode> childs = parentNode.getChilds();
            if (StringUtils.isNotBlank(parentNode.getType()) || parentNode.getNodeType() == NodeType.Foot) {
                System.out.println(parentNode.getPath() + "\t" + parentNode.getType() + "\t" + parentNode.getLb());
            }
            if (!childs.isEmpty()) {
                childs.entrySet().forEach(entry -> printTree(entry.getValue()));
            }
        } else {
            parentNode.getChilds().entrySet().forEach(entry -> printTree(entry.getValue()));
        }
    }
}
