package com.company;

public class Main {


    public static void main(String[] args) {
	Game game = new Game();
	Interface.draw(game);
        System.out.println(Service.process(game));
        Interface.draw(game);
    }
}
