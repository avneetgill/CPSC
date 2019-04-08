//package app;

import java.util.ArrayList;
import java.io.PrintWriter;

public class AdjacencyMatrix {
    int[][] theMatrix;
    int size;
    ArrayList<Matrix> vertices;

    public AdjacencyMatrix(int size){
        this.size = size;
        theMatrix = new int[size][size];
        vertices = new ArrayList<Matrix>();
    }

    public void addVertex(int[][]mat){ 
        Matrix matrix = new Matrix(mat);
        vertices.add(matrix);
    }

    public void addEdge(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j<= i; j++){
                int diff = vertices.get(i).difference(vertices.get(j));
                theMatrix[i][j] = diff;
                theMatrix[j][i] = diff;
            }
        } 
    }

}
