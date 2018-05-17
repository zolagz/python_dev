package guan.spider.src;

public class PriceObj {

    private String op;
    private String m;
    private String id;
    private String p;


    @Override
    public String toString() {
        return "PriceObj{" +
                "op='" + op + '\'' +
                ", m='" + m + '\'' +
                ", id='" + id + '\'' +
                ", p='" + p + '\'' +
                '}';
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }
}
