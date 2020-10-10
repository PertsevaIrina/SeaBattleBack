package com.company;

public class Cell {
    private int x;
    private int y;
    private Status status;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        status = Status.EMPTY;
    }

    public void cellStatus(Status status){
        this.status = status;
    }

    public Status getStatus (){
        return status;
    }
}
