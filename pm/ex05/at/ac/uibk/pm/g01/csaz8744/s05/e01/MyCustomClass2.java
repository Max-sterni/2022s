package at.ac.uibk.pm.g01.csaz8744.s05.e01;

import java.util.Objects;

public class MyCustomClass2 extends Object {

    private Integer attribute1;
    private String attribute2;
    private Double attribute3;
    private int attribute4;

    public MyCustomClass2(Integer attribute1, String attrubute2, Double attrubute3, int attribute4) {
        this.attribute1 = attribute1;
        this.attribute2 = attrubute2;
        this.attribute3 = attrubute3;
        this.attribute4 = attribute4;
    }

    public Integer getAttribute1() {
        return attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public Double getAttribute3() {
        return attribute3;
    }

    public int getAttribute4() {
        return attribute4;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return false;
        } else if (obj instanceof MyCustomClass2) {
            return attribute1 == ((MyCustomClass2) obj).getAttribute1()
                    && attribute2 == ((MyCustomClass2) obj).getAttribute2()
                    && attribute3 == ((MyCustomClass2) obj).getAttribute3()
                    && attribute4 == ((MyCustomClass2) obj).getAttribute4();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute1, attribute2, attribute3, attribute4);
    }

    @Override
    public String toString() {
        return "Attribute1 = " + attribute1 + " Attribute2 = " + attribute2 + " Attribute3 = " + attribute3 +" Attribute4 = " + attribute4 + "\n";
    }
}