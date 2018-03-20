package pl.kubie.catalogue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleApp {
    private final GameRepository gameRepository;
    private final GameController gameController;
    private final UserInput userInput;


    public ConsoleApp(GameRepository gameRepository, GameController gameController, UserInput userInput) {
        this.gameRepository = gameRepository;
        this.gameController = gameController;
        this.userInput = userInput;

    }

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        GameRepository gameRepository = new GameRepository(entityManager);
        GameController gameController = new GameController(gameRepository,new GameView());
        UserInput userInput = new UserInput(new Scanner(System.in));



        ConsoleApp application = new ConsoleApp(gameRepository,gameController,userInput);

        application.run();

        entityManager.close();
        entityManagerFactory.close();

    }
    private void run(){
        insertSampleData();

        while(true){
            int choice = userInput.menuChoice();
            switch (choice){
                case Menu.LIST_ALL:
                    gameController.findAll();
                    break;

                case Menu.ADD:
                    gameController.create(userInput);
                    break;

                case Menu.DELETE:
                    gameController.delete(userInput);
                    break;

                case Menu.EDIT:
                    gameController.update(userInput);
                    break;

                case Menu.FIND:
                    switch (userInput.findChoice()){
                        case Menu.FIND_BY_NAME:
                            gameController.findByName(userInput);
                            break;

                        case Menu.FIND_BY_RATE:
                            gameController.findByRate(userInput);
                            break;

                        case Menu.FIND_BY_DATE:
                            gameController.findByDate(userInput);
                            break;
                    }
                    break;
            }if(choice == Menu.EXIT){
                break;
            }

        }
    }
    private void insertSampleData() {
        Arrays.asList(
                new Game("Wiedźmin", "Przygodowe", "Dobra gra", 10.0),
                new Game("Assassin's Creed", "Przygodowe ", "Dobra gra", 9.4),
                new Game("Gothic", "Przygodowe", "Dobra gra", 9.0),
                new Game("Need for speed", "Wyścigi", "Dobra gra", 8.0),
                new Game("GTA", "Przygodowe", "Dobra gra", 7.0)
        )
                .forEach(gameRepository::save);
    }
}

