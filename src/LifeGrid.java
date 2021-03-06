public class LifeGrid
{
    private boolean[][] grid;

    LifeGrid(int w, int h) {
        grid = new boolean[h][w];
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (boolean[] booleans : grid) {
            for (int c = 0; c < grid.length; c++) {
                out.append("[").append(booleans[c] ? "X" : " ").append("]");
            }
            out.append("\n");
        }
        return out.toString();
    }

    int rows(){
        return grid.length;
    }

    int cols(){
        return grid[0].length;
    }

    void change(int row, int col) {
        grid[row][col] = !grid[row][col];
    }

    void next() {
        boolean[][] tempGrid = new boolean[rows()][cols()];

        for(int row = 0; row < rows(); row++){
            for(int col = 0; col < cols(); col++){
                // 2 cases
                // count neighbors
                int neighbors = numNeighbors(row,col);

                if(grid[row][col]){
                    // 1 - alive
                    tempGrid[row][col] = (neighbors == 2 || neighbors == 3);
                } else {
                    // 2 - dead
                    tempGrid[row][col] = (neighbors == 3);
                }
            }
        }
        grid = tempGrid;
    }

    private int numNeighbors(int row, int col){
        int count = 0;
        count += get(row-1, col-1)?1:0;
        count += get(row-1, col)?1:0;
        count += get(row-1, col+1)?1:0;
        count += get(row, col-1)?1:0;
        count += get(row, col+1)?1:0;
        count += get(row+1, col-1)?1:0;
        count += get(row+1, col)?1:0;
        count += get(row+1, col+1)?1:0;

        return count;
    }

    boolean get(int row, int col){
        return grid[(row + rows()) % rows()][(col + cols()) % cols()];
    }

    void clearBoard() {
        for(int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                if(get(i,j))
                    change(i,j);
            }
        }
    }
    
    void undo(){
    }
}
