public class HelloWorld{

     public static void main(String []args){
        System.out.println("----- SETTING UP PLAYERS -----");

        System.out.println("PLAYER 1");
        Player player1 = new Player(5);
        player1.initClearWaters();
        player1.addShip(1, 1);
        player1.addShip(1, 2);
        player1.addShip(1, 3);
        player1.printBoard();

        System.out.println("PLAYER 2");
        Player player2 = new Player(5);
        player2.initClearWaters();
        player2.addShip(1, 1);
        player2.addShip(2, 1);
        player2.addShip(3, 1);
        player2.printBoard();

        System.out.println("----- Let's Play - Round 1 - ------");

        // player 1
        player2.sendMissile(1,1);
        System.out.println("PLAYER 2");
        player2.printBoard();

        // player 2
        player1.sendMissile(1,4);
        System.out.println("PLAYER 1");
        player1.printBoard();
     }
}

class Player {
    Tile[][] tiles;

    Player(int size) {
        this.tiles = new Tile[size][size];
    }

    void printBoard() {
        for (int x  = 0; x < tiles.length; x++) {
            for (int y  = 0; y < tiles[x].length; y++) {
                System.out.print(tiles[x][y]);
            }
            System.out.println();
        }
    }

    void initClearWaters() {
        for (int x  = 0; x < tiles.length; x++) {
            for (int y  = 0; y < tiles[x].length; y++) {
                tiles[x][y] = Tile.WATER;
            }
        }
    }

    // TODO support complex forms
    void addShip(int row, int col) {
        for (int y  = 0; y < tiles.length; y++) {
            for (int x  = 0; x < tiles[y].length; x++) {
                if (x == col && y == row) {
                    this.tiles[x][y] = Tile.SHIP;
                }
            }
        }
    }

    void sendMissile(int y, int x) {
        if (this.tiles[x][y] == Tile.SHIP) {
            this.tiles[x][y] = Tile.HIT;
            System.out.println("Hit");
            return;
        }
        this.tiles[x][y] = Tile.MISS;
        System.out.println("Miss");
    }

    boolean hasNoMoreShips() {
        boolean hasShip = false;
        for (int x  = 0; x < tiles.length; x++) {
            for (int y  = 0; y < tiles[y].length; y++) {
                if (this.tiles[x][y] == Tile.SHIP) {
                    hasShip = true;
                }
            }
        }
        return !hasShip;
    }
}

enum Tile {
    WATER,
    SHIP,
    HIT,
    MISS;

    @Override
    public String toString() {
        switch(this) {
            case WATER:
                return "-";
            case SHIP:
                return "o";
            case HIT:
                return "u";
            case MISS:
                return "x";
        }
        return "";
    }
}