public enum RomanNumeral {

    I(0,"I",1),
    V(0,"V",5),
    X(1,"X",10),
    L(1,"L",50),
    C(2,"C",100),
    D(2,"D",500),
    M(3,"M",1000),
    VV(3,"V̅",5000),
    XX(4,"X̅",10000);

    private final int placeValue; // index digit in decimal number: units 0, tens 1, hundreds 2, thousands 3, ...
    private final String romanValue;
    private final int arabicValue;

    RomanNumeral(int placeValue, String romanValue, int arabicValue) {
        this.placeValue = placeValue;
        this.romanValue = romanValue;
        this.arabicValue = arabicValue;
    }

    public int getPlaceValue() {
        return placeValue;
    }

    public String getRomanValue() {
        return romanValue;
    }

    public int getArabicValue() {
        return arabicValue;
    }

}
