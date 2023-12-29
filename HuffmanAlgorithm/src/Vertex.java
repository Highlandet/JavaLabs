public class Vertex implements Comparable<Vertex>{
    int data;
    byte symbol;
    Vertex left, right;
    boolean status;
    public Vertex(int data, byte symbol) {
        this.data = data;
        this.symbol = symbol;
        this.left = this.right = null;
        this.status = false;
    }
    @Override
    public int compareTo(Vertex node){
        return this.data - node.data;
    }
}
