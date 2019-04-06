package app;

public class Matrix{
    int [][] myMatrix;
    
    public Matrix(int[][]matrix){
        myMatrix = matrix;
    }

    public int difference(Matrix other){
        int difference = 0;

        for(int i = 0; i < other.myMatrix.length; i++){
            for(int j = 0; j < other.myMatrix.length; j++){
                if(myMatrix[i][j] != other.myMatrix[i][j])
                    difference++;
            }
        }
        return difference;
    }
}