package com.harambase.hackathon.common.support.charts.example;

import it.uniroma1.dis.wsngroup.gexf4j.core.*;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.Attribute;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeClass;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeList;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeType;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.GexfImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.StaxGraphWriter;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.data.AttributeListImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.viz.PositionImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.NodeShape;
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.Position;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;


public class StaticGexfGraphExample {

    public static Position generatePosition(int base, int index) {
        PositionImpl position = new PositionImpl();
        double x = Math.pow(-1, index) * (base);
        double y = x * (-1) + index * base / index;

        position.setX(Float.parseFloat(String.valueOf(x)));
        position.setY(Float.parseFloat(String.valueOf(y)));

        return position;
    }

    public static void main(String[] args) {
        Gexf gexf = new GexfImpl();
        Calendar date = Calendar.getInstance();

        gexf.getMetadata()
                .setLastModified(date.getTime())
                .setCreator("Gephi.org")
                .setDescription("A Web network");
        gexf.setVisualization(true);

        Graph graph = gexf.getGraph();
        graph.setDefaultEdgeType(EdgeType.UNDIRECTED).setMode(Mode.STATIC);

        AttributeList attrList = new AttributeListImpl(AttributeClass.NODE);
        graph.getAttributeLists().add(attrList);

        Attribute attUrl = attrList.createAttribute("0", AttributeType.STRING, "url");
        Attribute attIndegree = attrList.createAttribute("cate", AttributeType.FLOAT, "indegree");
        Attribute attFrog = attrList.createAttribute("2", AttributeType.BOOLEAN, "frog")
                .setDefaultValue("true");


        Node gephi = graph.createNode("0");
        gephi
                .setLabel("Gephi")
                .setSize(20)
                .getAttributeValues()
                .addValue(attUrl, "http://gephi.org")
                .addValue(attIndegree, "0");
        gephi.getShapeEntity().setNodeShape(NodeShape.DIAMOND).setUri("GephiURI");
        gephi.setPosition(generatePosition(300, 1));

        Node webatlas = graph.createNode("1");
        webatlas
                .setLabel("Webatlas")
                .setSize(20)
                .getAttributeValues()
                .addValue(attUrl, "http://webatlas.fr")
                .addValue(attIndegree, "2");
        webatlas.setPosition(generatePosition(300, 2));

        Node rtgi = graph.createNode("2");
        rtgi
                .setLabel("RTGI")
                .setSize(20)
                .getAttributeValues()
                .addValue(attUrl, "http://rtgi.fr")
                .addValue(attIndegree, "1");
        rtgi.setPosition(generatePosition(300, 3));

        Node blab = graph.createNode("3");
        blab
                .setLabel("BarabasiLab")
                .setSize(20)
                .getAttributeValues()
                .addValue(attUrl, "http://barabasilab.com")
                .addValue(attIndegree, "1")
                .addValue(attFrog, "false");
        blab.setPosition(generatePosition(300, 4));

        gephi.connectTo("0", webatlas);
        gephi.connectTo("1", rtgi);
        webatlas.connectTo("2", gephi);
        rtgi.connectTo("3", webatlas);
        gephi.connectTo("4", blab);

        StaxGraphWriter graphWriter = new StaxGraphWriter();
        File f = new File("static_graph_sample.gexf");
        Writer out;
        try {
            out = new FileWriter(f, false);
            graphWriter.writeToStream(gexf, out, "UTF-8");
            System.out.println(f.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
