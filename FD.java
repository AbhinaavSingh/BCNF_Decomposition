import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FD {

    Set<String> lhs;
    Set<String> rhs;

    public FD() {
    }

    public FD(Set<String> lhs, Set<String> rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Set<String> getLhs() {
        return lhs;
    }

    public Set<String> getRhs() {
        return rhs;
    }

    public void setLhs(Set<String> lhs) {
        this.lhs = lhs;
    }

    public void setRhs(Set<String> rhs) {
        this.rhs = rhs;
    }

    public static Set<String> changeStringToList(String value) {
        Set<String> list = new HashSet<>();
        for (int i = 0;i < value.length(); i++){
            list.add(Character.toString(value.charAt(i)));
        }
        return list;
    }

    public static FD create(String lhs, String rhs) {
        Set<String> lhsList = changeStringToList(lhs);
        Set<String> rhsList = changeStringToList(rhs);
        return new FD(lhsList, rhsList);
    }

    @Override
    public String toString() {
        return String.join("", lhs) + "->" + String.join("", rhs);
    }
}
