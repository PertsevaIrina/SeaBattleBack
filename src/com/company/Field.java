package com.company;

public class Field {
    private Cell[][] field;
    private int n,m;

    public Field() {
        n = m = 10;
        field = new Cell[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                field [i][j] = new Cell (j, i);
            }
        }

    }


}
