/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package naivebyesclassifierfortext;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class FileInputOutput {
    public static final String PATH_TO_DATA_FILE = "training.data";
    public static final String PATH_TO_DATA_FILE1 = "topics.data";
    public static final String PATH_TO_DATA_FILE2 = "test.data";
    
    private static FileInputOutput instance = null;
    public static FileInputOutput getInstance() {
      if(instance == null) {
         instance = new FileInputOutput();
      }
      return instance;
    }
    public void getData()
    {
        Data data = Data.getInstance();
        int docCount=0, topicCount=0, vocabularyCount=0, testDocCount=0;
        Map<String, Integer> vocabulary = new HashMap<String, Integer>();
        List<String> topic = new ArrayList<String>();
        List<Document> docList = new ArrayList<Document>();
        List<Document> testDocList = new ArrayList<Document>();
        Map<String, Integer> docTopic = new HashMap<String, Integer>();
        BufferedReader reader = null;
	DataInputStream dis = null; 
        
        try {
            File f = new File(PATH_TO_DATA_FILE);
            FileInputStream fis;
            fis = new FileInputStream(f);
            reader = new BufferedReader(new InputStreamReader(fis));
            String line=null;
            while ((line = reader.readLine()) != null) { 
                Document doc = new Document();
                doc.setID(docCount);
                doc.setTopic(line);
                if(docTopic.containsKey(line))
                {
                    int t = docTopic.get(line);
                    docTopic.put(line, t+1);
                }
                else if(!docTopic.containsKey(line))
                {
                    docTopic.put(line, 1);
                }
                Map<String, Integer> map = new HashMap<String, Integer>();
                //System.out.println(line);
                String s1 = reader.readLine(); //blank line
                while(!(s1=reader.readLine()).isEmpty()){} //story title
                int dateCounter=0;
                while(!(s1=reader.readLine()).isEmpty()){dateCounter++;} //story location, date
                if(dateCounter==0)
                {
                    String s5 = reader.readLine(); //blank line
                    String s6 = reader.readLine(); //blank line
                //    System.out.println("--------------------------------------------------");
                }
                else if(dateCounter>0)
                {
                    String s6 = reader.readLine();
                    String delims = "[ \\-*+/(){}\\[\\]?!$\\^:\"]+";
                    while(!s6.isEmpty())
                    {
                        s6 = s6.toLowerCase();
                        String[] tokens = s6.split(delims);
                        for(int i=0; i<tokens.length; ++i)
                        {
                            if(tokens[i].contains("&lt;"))
                                tokens[i]=tokens[i].replace("&lt;", "");
                            if(tokens[i].contains("&gt;"))
                                tokens[i]=tokens[i].replace("&gt;", "");
                            if(tokens[i].endsWith(","))
                                tokens[i] = tokens[i].substring(0, tokens[i].length()-1);
                            if(tokens[i].endsWith("..."))
                                tokens[i] = tokens[i].substring(0, tokens[i].length()-3);
                            if(tokens[i].endsWith("."))
                                tokens[i] = tokens[i].substring(0, tokens[i].length()-1);
                            if(!tokens[i].isEmpty())
                            {
                                if(!vocabulary.containsKey(tokens[i]))
                                {
                                    vocabulary.put(tokens[i], 1);
                                    vocabularyCount++;
                                }
                                if(map.containsKey(tokens[i]))
                                {
                                    int t = map.get(tokens[i]);
                                    map.put(tokens[i], t+1);
                                }
                                else if(!map.containsKey(tokens[i]))
                                {
                                    map.put(tokens[i], 1);
                                }
                            }
                           // System.out.print("$"+tokens[i]+"$ ");
                        }
                        //System.out.println();
                        s6 = reader.readLine();
                        
                        //s6.concat(s7);
                    }
                    s6 = reader.readLine();
                //    System.out.println("--------------------------------------------------");
                }
                docCount++;
                doc.setMap(map);
                docList.add(doc);
                //System.out.println();
            }
            //System.out.println(docCount);
            data.setVocabulary(vocabulary);
            data.setVocabularyCount(vocabularyCount);
            data.setDocCount(docCount);
            data.setDocList(docList);
            data.setDocTopic(docTopic);
            f = new File(PATH_TO_DATA_FILE1);
            fis = new FileInputStream(f);
            reader = new BufferedReader(new InputStreamReader(fis));
            line=null;
            while((line=reader.readLine()) != null)
            {
                if(!line.isEmpty())
                {
                    topic.add(line);
                    topicCount++;
                }
                //System.out.println("#"+line+"#");
            }
            //System.out.println(topicCount);
            data.setTopicCount(topicCount);
            data.setTopic(topic);
            
            f = new File(PATH_TO_DATA_FILE2);
            fis = new FileInputStream(f);
            reader = new BufferedReader(new InputStreamReader(fis));
            line=null;
            while ((line = reader.readLine()) != null) { 
                Document doc = new Document();
                doc.setID(testDocCount);
                doc.setTopic(line);
                
                Map<String, Integer> map = new HashMap<String, Integer>();
                //System.out.println(line);
                String s1 = reader.readLine(); //blank line
                while(!(s1=reader.readLine()).isEmpty()){} //story title
                int dateCounter=0;
                while(!(s1=reader.readLine()).isEmpty()){dateCounter++;} //story location, date
                if(dateCounter==0)
                {
                    String s5 = reader.readLine(); //blank line
                    String s6 = reader.readLine(); //blank line
                //    System.out.println("--------------------------------------------------");
                }
                else if(dateCounter>0)
                {
                    String s6 = reader.readLine();
                    String delims = "[ \\-*+/(){}\\[\\]?!$\\^:\"]+";
                    while(!s6.isEmpty())
                    {
                        s6 = s6.toLowerCase();
                        String[] tokens = s6.split(delims);
                        for(int i=0; i<tokens.length; ++i)
                        {
                            if(tokens[i].contains("&lt;"))
                                tokens[i]=tokens[i].replace("&lt;", "");
                            if(tokens[i].contains("&gt;"))
                                tokens[i]=tokens[i].replace("&gt;", "");
                            if(tokens[i].endsWith(","))
                                tokens[i] = tokens[i].substring(0, tokens[i].length()-1);
                            if(tokens[i].endsWith("..."))
                                tokens[i] = tokens[i].substring(0, tokens[i].length()-3);
                            if(tokens[i].endsWith("."))
                                tokens[i] = tokens[i].substring(0, tokens[i].length()-1);
                            if(!tokens[i].isEmpty())
                            {
                                if(map.containsKey(tokens[i]))
                                {
                                    int t = map.get(tokens[i]);
                                    map.put(tokens[i], t+1);
                                }
                                else if(!map.containsKey(tokens[i]))
                                {
                                    map.put(tokens[i], 1);
                                }
                            }
                           // System.out.print("$"+tokens[i]+"$ ");
                        }
                        //System.out.println();
                        s6 = reader.readLine();
                        
                        //s6.concat(s7);
                    }
                    s6 = reader.readLine();
                //    System.out.println("--------------------------------------------------");
                }
                testDocCount++;
                doc.setMap(map);
                testDocList.add(doc);
                //System.out.println();
            }
            //System.out.println(docCount);
            data.setTestDocCount(testDocCount);
            data.setTestDocList(testDocList);
            
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(FileInputOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException e) { 
           System.out.println("Uh oh, got an IOException error: " + e.getMessage()); 
        }
    }
}
