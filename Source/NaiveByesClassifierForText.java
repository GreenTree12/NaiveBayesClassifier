package naivebyesclassifierfortext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class NaiveByesClassifierForText {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileInputOutput f = FileInputOutput.getInstance();
        f.getData();
        Data data = Data.getInstance();
        
        List<String> topic = data.getTopic(); //Total topic list
        Map<String, Integer> docTopic = data.getDocTopic(); //Existing document topic list
        Map<String, Integer> vocabulary = data.getVocabulary();//vocabulary list
        List<Document> docList = data.getDocList(); //documents list
        List<Document> testDocList = data.getTestDocList(); //test documents list
        int vocabularyCount = data.getVocabularyCount(); //vocabulary count
        int docCount = data.getDocCount(); //total doocument count
        Map<String, Double> topicProbability = new HashMap<String, Double>();
        Map<String, Double> wordTopicProbability = new HashMap<String, Double>();
        
        for(int j=0; j<topic.size(); ++j)
        {
            String topicName = topic.get(j);
            double prob1=0;
            if(docTopic.containsKey(topicName))
            {
                int docsj = docTopic.get(topicName);
                prob1 = (docsj*1.0)/docCount;
                topicProbability.put(topicName, Double.valueOf(prob1));
            }
            else if(!docTopic.containsKey(topicName))
            {
                prob1 = 0.0;
                topicProbability.put(topicName, Double.valueOf(prob1));
            }
            //System.out.println("Probability For "+ topicName+":   "+prob1);
            int n=0,nk;
            List<Document> subDocList =  new ArrayList<Document>();
            for(int i=0; i<docList.size(); ++i)
            {
                Document doc = new Document();
                doc = docList.get(i);
                if(doc.getTopic().equals(topicName))
                {
                    subDocList.add(doc);
                    n += doc.getMap().size();
                }
            }
            //System.out.println("Class Doc Words Count:  "+n);
            //System.out.println("Probability For Word VS Class:");
            for(String word: vocabulary.keySet())
            {
                nk=0;
                for(int i=0; i<subDocList.size(); ++i)
                {
                    Document doc = subDocList.get(i);
                    if(doc.getTopic().equals(topicName))
                    {
                        if(doc.getMap().containsKey(word))
                            nk+=doc.getMap().get(word);
                    }
                }
                double prob2 = ((nk*1.0)+1.5)/(n+(vocabularyCount));
                wordTopicProbability.put(word+"-"+topicName, Double.valueOf(prob2));
                //System.out.print(prob2+"   ");
            }
            //System.out.println();
            //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
        data.setTopicProbability(topicProbability);
        data.setWordTopicProbability(wordTopicProbability);
        Map<String, Double> topicProbability1 = data.getTopicProbability();
        Map<String, Double> wordTopicProbability1 = data.getWordTopicProbability();
        /*for(String key: topicProbability1.keySet())
        {
            double d = topicProbability1.get(key);
            System.out.println(key+":   "+d);
        }
        
        for(String key: wordTopicProbability1.keySet())
        {
            double d = wordTopicProbability1.get(key);
            System.out.println(key+":   "+d);
        }
        */ 
        int counter = 0;
        for(int i=0; i<testDocList.size(); ++i)
        {
            Document doc = testDocList.get(i);
            String testTopicName = doc.getTopic();
            Map<String, Integer> map = doc.getMap();
            String topicName=null;
            String maxTopicName=null;
            double maxProb = 0;
            for(int j=0; j<topic.size(); ++j)
            {
                topicName = topic.get(j);
                double prob1 = topicProbability1.get(topicName);
                double prob2=1.0;
                for(String key: map.keySet())
                {
                    if(wordTopicProbability1.containsKey(key+"-"+topicName))
                    {
                        prob2 *= wordTopicProbability1.get(key+"-"+topicName);
                    }
                }
                prob2 *= prob1;
                if(prob2>maxProb)
                {
                    maxProb = prob2;
                    maxTopicName = topicName;
                }
            }
            System.out.println("Testing Topic: "+testTopicName+"  Output Topic:  "+maxTopicName+"  Max probability: "+maxProb);
            if(maxTopicName!=null && testTopicName.equals(maxTopicName))
            {
                counter++;
            }
        }
        System.out.println("Accuracy: "+((counter*100.0)/testDocList.size()));
    }

}
