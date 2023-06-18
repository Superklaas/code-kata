import java.util.HashMap;
import java.util.Map;

public enum RomanNumeral {

    I(0,0,"I",1),
    V(1,0,"V",5),
    X(2,1,"X",10),
    L(3,1,"L",50),
    C(4,2,"C",100),
    D(5,2,"D",500),
    M(6,3,"M",1000),
    VV(7,3,"V̅",5000),
    XX(8,4,"X̅",10000);

    private final int index;
    private final int rank;
    private final String romanValue;
    private final int arabicValue;

    private static final Map<Integer,RomanNumeral> BY_INDEX = new HashMap<>();

    static {
        for (RomanNumeral romanNumeral : values()) {
            BY_INDEX.put(romanNumeral.getIndex(), romanNumeral);
        }
    }

    RomanNumeral(int index, int rank, String romanValue, int arabicValue) {
        this.index = index;
        this.rank = rank;
        this.romanValue = romanValue;
        this.arabicValue = arabicValue;
    }

    public int getIndex() {
        return index;
    }

    public int getRank() {
        return rank;
    }

    public String getRomanValue() {
        return romanValue;
    }

    public int getArabicValue() {
        return arabicValue;
    }

    public static RomanNumeral valueOfIndex(int index) {
        return BY_INDEX.get(index);
    }

}
