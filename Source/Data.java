package naivebyesclassifierfortext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class Data {

    private List<String> topic;
    private Map<String, Integer> docTopic;
    private Map<String, Integer> vocabulary;
    private List<Document> docList;
    private List<Document> testDocList;
    private Map<String, Double> topicProbability;
    private Map<String, Double> wordTopicProbability;
    private int topicCount;
    private int vocabularyCount;
    private int docCount;
    private int testDocCount;
    
    private static Data instance = null;
    public static Data getInstance() {
      if(instance == null) {
         instance = new Data();
      }
      return instance;
    }

    public Data() {
        docCount = 0;
        testDocCount=0;
        vocabularyCount = 0;
        topicCount = 0;
        vocabulary = new HashMap<String, Integer>();
        docList = new ArrayList<Document>();
        docTopic = new HashMap<String, Integer>();
        testDocList = new ArrayList<Document>();
        topicProbability = new HashMap<String, Double>();
        wordTopicProbability = new HashMap<String, Double>();
    }
    
    
    public void setTopicCount(int topicCount) {
        this.topicCount = topicCount;
    }

    public void setTopic(List<String> topic) {
        this.topic = new ArrayList<String>();
        this.topic = topic;
    }

    public void setVocabulary(Map<String, Integer> vocabulary) {
        this.vocabulary = vocabulary;
    }
    
    public void setVocabularyCount(int vocabularyCount) {
        this.vocabularyCount = vocabularyCount;
    }
    
    public void setDocList(List<Document> docList) {
        this.docList = docList;
    }

    public void setDocCount(int docCount) {
        this.docCount = docCount;
    }
    
    public void setDocTopic(Map<String, Integer> docTopic) {
        this.docTopic = docTopic;
    }

    public void setTestDocList(List<Document> testDocList) {
        this.testDocList = testDocList;
    }

    public void setTestDocCount(int testDocCount) {
        this.testDocCount = testDocCount;
    }
    
    public void setTopicProbability(Map<String, Double> topicProbability) {
        this.topicProbability = topicProbability;
    }

    public void setWordTopicProbability(Map<String, Double> wordTopicProbability) {
        this.wordTopicProbability = wordTopicProbability;
    }
    
    public List<String> getTopic() {
        return topic;
    }
    
    public int getTopicCount() {
        return topicCount;
    }
    
    public Map<String, Integer> getVocabulary() {
        return vocabulary;
    }
    
    public int getVocabularyCount() {
        return vocabularyCount;
    }
    
    public List<Document> getDocList() {
        return docList;
    }

    public int getDocCount() {
        return docCount;
    }

    public Map<String, Integer> getDocTopic() {
        return docTopic;
    }

    public List<Document> getTestDocList() {
        return testDocList;
    }

    public int getTestDocCount() {
        return testDocCount;
    }
    
    public Map<String, Double> getTopicProbability() {
        return topicProbability;
    }

    public Map<String, Double> getWordTopicProbability() {
        return wordTopicProbability;
    }
    
}
