package pl.kubie.catalogue;

import java.util.List;

public class GameView {
    private static final String GAME_FORMAT = "%20s || %-15s || %5.2f || %-15s || %-15s%n";

    public void print(List<Game> games){
        if(games.isEmpty()){
            System.out.println("No games found");
        }else {
            games.forEach(this::print);
        }
    }

    public void print(Game game){
        System.out.printf(GAME_FORMAT,
                game.getName(),
                game.getType(),
                game.getRate(),
                game.getComment(),
                game.getDate());
    }
}
