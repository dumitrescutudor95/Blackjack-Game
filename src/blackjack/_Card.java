
package blackjack;

public class _Card {

    private int number;
    private String type;
    private String icon;
    private int value;

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getIcon() {
        return icon;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public _Card(int numar, String tip, int value, String i) {
        this.number = numar;
        this.type = tip;
        this.value = value;
        this.icon = i;
    }

}
