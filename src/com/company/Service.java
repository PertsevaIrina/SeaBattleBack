package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Service {

    public static void setShips(Field field, List<Ship> listOfShip) {
        List<Cell> listOfRandom = new ArrayList<>(field.getListOfCells());
        for (int i = 0; i < 4; i++) {//ответает за тип корабля
            int countOfDeck = 4 - i;
            int n = 5;
            for (int j = 0; j < i + 1; j++) {//отвечает за количество
                List<Cell> listOfCell = getListOfShipCells(field, listOfRandom, countOfDeck);
//                for (Cell c : listOfCell) {
//                    System.out.println(c.getX() + " " + c.getY());
//                }

                if (listOfCell != null) {
//                    listOfCell = copy(field, listOfCell);
                    listOfShip.add(new Ship(countOfDeck, listOfCell));
                    n = 5;
                    for (Cell c : listOfCell) {
                        c.setStatus(Status.SHIP);
                    }
                    removeCells(field, listOfRandom, listOfCell);
                } else if (n > 0) {
                    j--;
                    n--;
                }
            }

        }
    }

    public static List<Cell> copy(Field field, List<Cell> list) {
        List<Cell> copy = new ArrayList<>();
        for (Cell c : list) {
            copy.add(field.getCell(c.getX(), c.getY()));
        }
        return copy;
    }

    private static List<Cell> getListOfShipCells(Field field, List<Cell> listOfRandom, int countOfDeck) {
        List<Cell> listOfCellX = new ArrayList<>();
        List<Cell> listOfCellY = new ArrayList<>();
        Cell cell;
        if (listOfRandom.size() > 0) {
            cell = listOfRandom.get(random(0, listOfRandom.size()));
        } else {
            return null;
        }
        listOfCellX.add(cell);
        listOfCellY.add(cell);
        if (countOfDeck == 1) {
            return listOfCellX;
        }
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;

        for (int k = 1; k < countOfDeck; k++) {
            Cell cellTemp = field.getCell(cell.getX() + k, cell.getY());
            if (listOfRandom.contains(cellTemp) && flag1) {
                listOfCellX.add(cellTemp);
                if (listOfCellX.size() == countOfDeck) {
                    return listOfCellX;
                }
            } else {
                flag1 = false;
            }

            cellTemp = field.getCell(cell.getX() - k, cell.getY());
            if (listOfRandom.contains(cellTemp) && flag2) {
                listOfCellX.add(cellTemp);
                if (listOfCellX.size() == countOfDeck) {
                    return listOfCellX;
                }
            } else {
                flag2 = false;
            }
            cellTemp = field.getCell(cell.getX(), cell.getY() + k);
            if (listOfRandom.contains(cellTemp) && flag3) {
                listOfCellY.add(cellTemp);
                if (listOfCellY.size() == countOfDeck) {
                    return listOfCellY;
                }
            } else {
                flag3 = false;
            }

            cellTemp = field.getCell(cell.getX(), cell.getY() - k);
            if (listOfRandom.contains(cellTemp) && flag4) {
                listOfCellY.add(cellTemp);
                if (listOfCellY.size() == countOfDeck) {
                    return listOfCellY;
                }
            } else {
                flag4 = false;
            }
        }
        return null;
    }

    private static void removeCells(Field field, List<Cell> listOfRandom, List<Cell> listOfCell) {
        for (Cell c : listOfCell) {
            int x = c.getX();
            int y = c.getY();
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    Cell cell = field.getCell(x + i, y + j);
                    if (listOfRandom.contains(cell)) {
                        listOfRandom.remove(cell);
                    }
                }
            }
        }
    }

    public static boolean process(Game game) {


        while (alive(game.getPlayer1()) && alive(game.getPlayer2())) {
            if (game.getStep()) {
                if (!shot(game.getPlayer2())) {
                    game.setStep(false);

                }
            } else {
                if (!shot(game.getPlayer1())) {
                    game.setStep(true);

                }

            }

        }

        return game.getStep();
    }

    private static boolean alive(Player player) {
        for (Ship ship : player.getListOfShips()) {
            if (ship.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private static void killAround(Player player, Ship ship) {
        for (Cell c : ship.getCellList()) {
            int x = c.getX();
            int y = c.getY();
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    Cell cell = player.getField().getCell(x + i, y + j);
                    if (player.getField().getListOfCells().contains(cell)) {
                        player.getField().removeCell(cell);

                        if (cell.getStatus() == Status.EMPTY) {
                            cell.setStatus(Status.MISS);
                        }
                    }
                }
            }
        }
    }

    private static boolean shot(Player player) {

        Cell cell = player.getField().getListOfCells().remove(random(0, player.getField().getListOfCells().size()));
        if (cell.getStatus() == Status.SHIP) {
            cell.setStatus(Status.INJURED);
            for (Ship s : player.getListOfShips()) {
                if (s.getCellList().contains(cell)) {
                    if (!s.isAlive()) {
                        killAround(player, s);
                    }
                }
            }
            return true;
        } else if (cell.getStatus() == Status.EMPTY) {
//            cell.setStatus(Status.MISS);
        }
        return false;

    }

    private static int random(int a, int b) {
        return (int) (Math.random() * (b - a)) + a;
    }

}
