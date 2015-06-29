package com.csoft.wong.feedmenow;

import android.util.Log;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLHandler {

    private static final String TAG = "XMLHandler";

    //public JSONHandler(){};

    public HashMap<String, HashMap<String, String>> parseXml(String xmlstring) {

        //xmlDocument.createElement("myxml");
        try {
            Document xmlDocument = loadXMLFromString(xmlstring);

            int counter = 0;

            NodeList nodes = xmlDocument.getElementsByTagName("Ingredient");

            String ingredients = "";

            // iterate the employees
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);

                NodeList name = element.getElementsByTagName("Name");
                Element line = (Element) name.item(0);
                if(i== 0){
                    ingredients = ingredients +getCharacterDataFromElement(line);
                }else{
                    ingredients = ingredients + ", " +getCharacterDataFromElement(line);
                }


            }


            HashMap resultMap = new HashMap<String, HashMap<String, String>>();

            HashMap header = new HashMap<String, String>();
            header.put("title", "title");
            header.put("version", "version");
            header.put("href", "href");
            resultMap.put("header", header);

            HashMap subResultMap = new HashMap<String, String>();
            subResultMap.put("title", "title");
            subResultMap.put("resultHref", "resultHref");
            subResultMap.put("ingredients", ingredients);
            subResultMap.put("thumbnail", "thumbnail");
                resultMap.put(Integer.toString(counter++), subResultMap);

            return resultMap;


        } catch (SAXParseException e){
            Log.v(TAG, Integer.toString(e.getColumnNumber()));
            Log.v(TAG, Integer.toString(e.getLineNumber()));

        } catch (Exception e){
            Log.v(TAG, e.toString());
        }

        return new HashMap<String, HashMap<String, String>>();
    }


    public Document loadXMLFromString(String xml) throws Exception
    {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xml));
        inputSource.setCharacterStream(new StringReader(xml));
        Document document = builder.parse(inputSource);
        return document;

    }
    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }
}
