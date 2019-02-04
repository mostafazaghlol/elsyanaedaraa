package androiddeveloper.hazzaa.yasser.elsyanaedaraa;

public class staticManger {
    public static final staticManger ourInstance = new staticManger();



    String type;
    String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    String color;

    public static staticManger getInstance() {
        return ourInstance;
    }

    private staticManger() {
    }
}
