//ackage app;
 import java.util.Scanner;
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.InputStream;
 import java.io.PrintWriter;
 

public class App {
   Scanner stdIn;
   int width;
   int height;
   int neighbours;
   AdjacencyMatrix adjMatrix;
   public App (String fileName){
       try {
        stdIn = new Scanner(new FileReader(fileName));
        width = stdIn.nextInt();
        height = stdIn.nextInt();
        neighbours = stdIn.nextInt();
       } catch (FileNotFoundException e) {
          System.err.println("File not found!");
       }
   }
   public void populateMatrix(){
       adjMatrix = new AdjacencyMatrix(neighbours);
       while(stdIn.hasNextInt()){
            adjMatrix.addVertex(getMatrix());
       }
       adjMatrix.addEdge();
   }

    public int[][] getMatrix() {
        int[][] matrix = new int[width][height];
        int num = stdIn.nextInt();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                matrix[i][j] = stdIn.nextInt();
            }
        }
        return matrix;
    }
    
    public static void main(String[] args) throws Exception {
       if(args[0] != null){
           App myApp = new App(args[0]);
           myApp.populateMatrix();
           
       }
       else{
           System.out.println("Please enter a filename!");
       }
    }
}