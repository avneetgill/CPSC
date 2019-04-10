//package app;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.*;

public class Graph{
    int[][] theMatrix;
    int size;
    ArrayList<Matrix> vertices;

    public Graph(int size){
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

    public void printDFS(PrintWriter outputStream) {
        outputStream.println("Edge\tWeight");
        boolean[] visited = new boolean[size];
        printDFS(0, visited, outputStream);
      }
    
      private void printDFS(int n, boolean[] visited, PrintWriter outputStream) {
        visited[n] = true;
    
        for (int i = 0; i < size; i++) {
          outputStream.println(n + " - " + i + "\t" +theMatrix[n][i]);
          if (!visited[i] &&theMatrix[n][i] != Integer.MAX_VALUE) {
            printDFS(i, visited, outputStream);
          }
        }
      }   

      public PrimSpan prim() {
        int[] cost = new int[size];
        int[] par = new int[size];
        boolean[] vis = new boolean[size];
        PriorityQueue<CostVertex> pq = new PriorityQueue<>(size);
    
        for (int i = 0; i < size; i++) {
          cost[i] = (i == 0) ? 0 : Integer.MAX_VALUE;
          par[i] = i;
          pq.add(new CostVertex(i, cost[i]));
        }
    
        while (!pq.isEmpty()) {
          CostVertex u = pq.poll();
          vis[u.name] = true;
          if (u.cost > cost[u.name])
            continue;
          for (int v = 0; v < size; v++) {
            if (v == u.name)
              continue;
    
            if (!vis[v] && theMatrix[u.name][v] < cost[v]) {
              cost[v] = theMatrix[u.name][v];
              par[v] = u.name;
              pq.add(new CostVertex(v, cost[v]));
            }
          }
        }
    
        return new PrimSpan(cost, par);
      }
    
      public String toString() {
        String str = "";
    
        for (int[] row : theMatrix) {
          for (int edge : row) {
            str += edge + " ";
          }
          str += "\n";
        }
        return str;
      }
    
      class PrimSpan {
        int[] cost;
        int[] par;
        int mstCost;
    
        public PrimSpan(int[] _cost, int[] _par) {
          cost = _cost;
          par = _par;
          mstCost = 0;
          for (int i = 0; i < cost.length; i++)
            mstCost += cost[i];
        }
    
        public void printMST(PrintWriter outputStream) {
          outputStream.println("Edge\tWeight");
          for (int i = 1; i < par.length; i++) {
            outputStream.println(par[i] + " - " + i + "\t" + theMatrix[i][par[i]]);
          }
        }
      }
    
      class CostVertex implements Comparable<CostVertex> {
        int name;
        int cost;
    
        public CostVertex(int _name, int _cost) {
          name = _name;
          cost = _cost;
        }
    
        public int compareTo(CostVertex other) {
          return cost < other.cost ? -1 : cost > other.cost ? 1 : 0;
        }
      }
    }



