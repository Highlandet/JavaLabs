import java.util.*;
import java.io.*;

class Node {
    private int parent;
    private int left;
    private int right;
    private int probability;
    private char symbol;
    private String code;
    private boolean merged;

    public Node(int probability, char symbol) {
        this.parent = -1;
        this.right = -1;
        this.left = -1;
        this.probability = probability;
        this.symbol = symbol;
        this.code = "";
        this.merged = false;
    }

    public void setChildren(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void merging() {
        this.merged = true;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public int getRight() {
        return this.right;
    }

    public int getLeft() {
        return this.left;
    }

    public int getProbability() {
        return this.probability;
    }

    public int getParent() {
        return this.parent;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isMerged() {
        return this.merged;
    }
}