package pl.kubie.catalogue;

import java.time.LocalDate;
import java.util.List;

public class GameController {
    private final GameRepository gameRepository;
    private final GameView gameView;

    public GameController(GameRepository gameRepository, GameView gameView) {
        this.gameRepository = gameRepository;
        this.gameView = gameView;
    }

    void findAll(){
        List<Game> games = gameRepository.findAll();
        gameView.print(games);
    }
    void findByName(UserInput userInput){
        String name = userInput.gameId();
        gameView.print(gameRepository.findByName(name));
    }
    void findByDate(UserInput userInput){
        LocalDate localDate = userInput.dateToFind();
        gameView.print(gameRepository.findByDate(localDate));
    }
    void findByRate(UserInput userInput){
        Double rate = userInput.rateToFind();
        gameView.print(gameRepository.findByRateGreaterThan(rate));
    }
    void create(UserInput userInput){
        Game game = userInput.game();
        gameRepository.save(game);
    }
    void delete(UserInput userInput){
        String name = userInput.gameId();
        gameRepository.deleteByName(name);
    }
    void update(UserInput userInput){
        Game old = gameRepository.findByName(userInput.gameId());
        Game newGame = userInput.update(old);
        gameRepository.deleteByName(old.getName());
        gameRepository.save(newGame);
    }

}
