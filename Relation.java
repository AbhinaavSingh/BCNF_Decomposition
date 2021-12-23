import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Relation {

    Set<String> completeRelation;


    public Relation() {
        completeRelation = new HashSet<>();
    }

    public Relation(Set<String> completeRelation) {
        this.completeRelation = new HashSet<>();
        this.completeRelation = completeRelation;
    }

    public Set<String> getCompleteRelation() {
        return completeRelation;
    }

    public void setCompleteRelation(Set<String> completeRelation) {
        this.completeRelation = completeRelation;
    }

    public static Set<String> changeStringToList(String value) {
        Set<String> list = new HashSet<>();
        for (int i = 0;i < value.length(); i++){
            list.add(Character.toString(value.charAt(i)));
        }
        return list;
    }

    public static Relation create(String value) {
        Set<String> list = changeStringToList(value);
        return new Relation(list);
    }

    @Override
    public String toString() {
        String s = "Relation(";
        for (String attribute:completeRelation) {
            s +=attribute + ",";
        }
        s = s.substring(0, s.length() -1);
        return s +")";
    }
}
