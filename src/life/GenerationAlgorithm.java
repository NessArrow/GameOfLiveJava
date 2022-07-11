package life;

public class GenerationAlgorithm {


    public static void neighbours(String[][] first, String[][] next, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int counter = 0;
                ex exception = searchExceptions(n, i, j);
                switch (exception) {
                    case upLeftCorner:
                    case upRightCorner:
                    case downRightCorner:
                    case downLeftCorner:
                        counter = cornerNeighbors(n, i, j, first, exception);
                        break;
                    case right:
                    case left:
                    case down:
                    case up:
                        counter = sideNeighbors(n, i, j, first, exception);
                        break;
                    case center:
                        counter = centerNeighbors(i, j, first);
                        break;
                }
                if (first[i][j].equals("O")) {
                    if (counter > 3 || counter < 2) {
                        next[i][j] = " ";
                    } else {
                        next[i][j] = first[i][j];
                    }
                } else if (first[i][j].equals(" ")) {
                    if (counter == 3) {
                        next[i][j] = "O";
                    } else {
                        next[i][j] = first[i][j];
                    }
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                first[k][l] = next[k][l];
            }
        }
    }

    public enum ex {
        center, upLeftCorner, upRightCorner, downLeftCorner, downRightCorner, up, left, down, right,
    }

    public static ex searchExceptions(int n, int i, int j) {
        if (i == 0 && j == 0) {
            return ex.upLeftCorner;
        } else if (i == 0 && j == n - 1) {
            return ex.upRightCorner;
        } else if (i == n - 1 && j == 0) {
            return ex.downLeftCorner;
        } else if (i == n - 1 && j == n - 1) {
            return ex.downRightCorner;
        } else if (i == 0) {
            return ex.up;
        } else if (j == 0) {
            return ex.left;
        } else if (i == n - 1) {
            return ex.down;
        } else if (j == n - 1) {
            return ex.right;
        } else {
            return ex.center;
        }
    }

    public static int sideNeighbors(int n, int i, int j, String[][] first, ex exs) {
        int counter = 0;
        String[] coo = new String[8];
        if (exs == ex.up) {
            //0 1 2
            coo[0] = first[n - 1][j - 1];//nw
            coo[1] = first[n - 1][j];//n
            coo[2] = first[n - 1][j + 1];//ne
            coo[3] = first[i][j + 1];//e
            coo[4] = first[i + 1][j + 1];//se
            coo[5] = first[i + 1][j];//s
            coo[6] = first[i + 1][j - 1];//sw
            coo[7] = first[i][j - 1];//w
        } else if (exs == ex.right) {
            //2 3 4
            coo[0] = first[i - 1][j - 1];//nw
            coo[1] = first[i - 1][j];//n
            coo[2] = first[i - 1][0];//ne
            coo[3] = first[i][0];//e
            coo[4] = first[i + 1][0];//se
            coo[5] = first[i + 1][j];//s
            coo[6] = first[i + 1][j - 1];//sw
            coo[7] = first[i][j - 1];//w
        } else if (exs == ex.down) {
            // 4 5 6
            coo[0] = first[i - 1][j - 1];//nw
            coo[1] = first[i - 1][j];//n
            coo[2] = first[i - 1][j + 1];//ne
            coo[3] = first[i][j + 1];//e
            coo[4] = first[0][j + 1];//se
            coo[5] = first[0][j];//s
            coo[6] = first[0][j - 1];//sw
            coo[7] = first[i][j - 1];//w
        } else if (exs == ex.left) {
            // 6 7 0
            coo[1] = first[i - 1][j];//n
            coo[2] = first[i - 1][j + 1];//ne
            coo[3] = first[i][j + 1];//e
            coo[4] = first[i + 1][j + 1];//se
            coo[5] = first[i + 1][j];//s
            coo[6] = first[i + 1][n - 1];//sw
            coo[7] = first[i][n - 1];//w
            coo[0] = first[i - 1][n - 1];//nw
        }

        for (int k = 0; k < coo.length; k++) {
            if (coo[k].equals("O")) {
                counter++;
            }
        }
        return counter;
    }

    public static int cornerNeighbors(int n, int i, int j, String[][] first, ex exs) {
        int counter = 0;
        String[] coo = new String[8];
        if (exs == ex.upLeftCorner) {
            coo[0] = first[n - 1][n - 1];//nw
            coo[1] = first[n - 1][0];//n
            coo[2] = first[n - 1][1];//ne
            coo[3] = first[i][j + 1];//e
            coo[4] = first[i + 1][j + 1];//se
            coo[5] = first[i + 1][j];//s
            coo[6] = first[1][n - 1];//sw
            coo[7] = first[0][n - 1];//w
            //0 1 2 6 7
        } else if (exs == ex.upRightCorner) {
            coo[0] = first[n - 1][n - 2];//nw
            coo[1] = first[n - 1][n - 1];//n
            coo[2] = first[n - 1][0];//ne
            coo[3] = first[0][0];//e
            coo[4] = first[1][0];//se
            coo[5] = first[i + 1][j];//s
            coo[6] = first[i + 1][j - 1];//sw
            coo[7] = first[i][j - 1];//w
            //0 1 2 3 4
        } else if (exs == ex.downRightCorner) {
            coo[0] = first[i - 1][j - 1];//nw
            coo[1] = first[i - 1][j];//n
            coo[2] = first[n - 2][0];//ne
            coo[3] = first[n - 1][0];//e
            coo[4] = first[0][0];//se
            coo[5] = first[0][n - 1];//s
            coo[6] = first[0][n - 2];//sw
            coo[7] = first[i][j - 1];//w
            //2 3 4 5 6
        } else if (exs == ex.downLeftCorner) {
            coo[0] = first[n - 2][n - 1];//nw
            coo[1] = first[i - 1][j];//n
            coo[2] = first[i - 1][j + 1];//ne
            coo[3] = first[i][j + 1];//e
            coo[4] = first[0][1];//se
            coo[5] = first[0][0];//s
            coo[6] = first[0][n - 1];//sw
            coo[7] = first[n - 1][n - 1];//w
            //0 4 5 6 7
        }
        for (int k = 0; k < coo.length; k++) {
            if (coo[k].equals("O")) {
                counter++;
            }
        }
        return counter;
    }

    public static int centerNeighbors(int i, int j, String[][] first) {
        int counter = 0;
        String[] coo = new String[8];
        coo[0] = first[i - 1][j - 1];//nw
        coo[1] = first[i - 1][j];//n
        coo[2] = first[i - 1][j + 1];//ne
        coo[3] = first[i][j + 1];//e
        coo[4] = first[i + 1][j + 1];//se
        coo[5] = first[i + 1][j];//s
        coo[6] = first[i + 1][j - 1];//sw
        coo[7] = first[i][j - 1];//w
        for (int k = 0; k < coo.length; k++) {
            if (coo[k].equals("O")) {
                counter++;
            }
        }
        return counter;
    }

}
