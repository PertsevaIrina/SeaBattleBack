package com.company;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        Game game = new Game();
        Interface.draw(game);
        boolean b = service.process((game));
        Interface.draw(game);
        if (b) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}
