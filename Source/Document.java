package naivebyesclassifierfortext;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class Document {
    private int ID;
    private String topic;
    private Map<String, Integer> map;

    public Document() {
        this.ID = 0;
        this.topic=null;
        map = new HashMap<String, Integer>();
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public int getID() {
        return ID;
    }
    
    public String getTopic() {
        return topic;
    }

    public Map<String, Integer> getMap() {
        return map;
    }
    
}
