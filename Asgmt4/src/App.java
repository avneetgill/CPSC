//ackage app;
 import java.util.Scanner;
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.FileReader;
 import java.io.InputStream;
 import java.io.PrintWriter;
 

public class App {
   Scanner stdIn;
   PrintWriter dstOutputStream;
   PrintWriter mstOutputStream;
   int width;
   int height;
   int neighbours;
   Graph myGraph;

   public App (String fileName){
       try {
        stdIn = new Scanner(new FileReader(fileName));
        width = stdIn.nextInt();
        height = stdIn.nextInt();
        neighbours = stdIn.nextInt();

        String[] slicedName = fileName.split("\\.");
        String dstOutFilename = slicedName[0] + "-DFSGRAPH_out." + slicedName[1];
        String mstOutFilename = slicedName[0] + "-MST_out." + slicedName[1];
        dstOutputStream = new PrintWriter(new FileOutputStream(dstOutFilename), true);
        mstOutputStream = new PrintWriter(new FileOutputStream(mstOutFilename), true);
       } catch (FileNotFoundException e) {
          System.err.println("File not found!");
       }
   }

   public void printDFS() {
    myGraph.printDFS(dstOutputStream);
  }

  public void printMST() {
    myGraph.prim().printMST(mstOutputStream);
  }

  /**
   * @return the myGraph
   */
  public Graph getMyGraph() {
      return myGraph;
  }
   public void populateMatrix(){
       myGraph = new Graph(neighbours);
       while(stdIn.hasNextInt()){
            myGraph.addVertex(getMatrix());
       }
       myGraph.addEdge();
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
    
    public static void main(String[] args) {
       if(args[0] != null){
            App myApp = new App(args[0]);
            myApp.populateMatrix();
            myApp.printDFS();
            myApp.printMST();
            System.out.println(myApp.getMyGraph());
          } 
       else{
           System.out.println("Please enter a filename!");
       }
    }
}