package fr.dorian_ferreira.cap_entreprise.configuration;

import fr.dorian_ferreira.cap_entreprise.entity.*;
import fr.dorian_ferreira.cap_entreprise.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private ReviewRepository reviewRepository;
    private GameRepository gameRepository;

    private BusinessModelRepository businessModelRepository;
    private PlatformRepository platformRepository;
    private GenreRepository genreRepository;
    private PublisherRepository publisherRepository;
    private ClassificationRepository classificationRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        createUsers();

        createBusinessModels();
        createPlatforms();
        createClassifications();
        createGenres();
        createPublishers();

        createGames();
    }

    private void createUsers() {
        Moderator modo = new Moderator();
        modo.setId(1L);
        modo.setEmail("toto@gmail.com");
        modo.setNickname("toto");
        modo.setPhoneNumber("0698325417");
        modo.setPassword(passwordEncoder.encode("toto"));
        userRepository.save(modo);

        Gamer gamer = new Gamer();
        gamer.setId(2L);
        gamer.setEmail("toto2@gmail.com");
        gamer.setNickname("toto2");
        gamer.setBirthAt(LocalDate.now());
        gamer.setPassword(passwordEncoder.encode("toto"));
        userRepository.saveAndFlush(gamer);

        Gamer gamer2 = new Gamer();
        gamer2.setId(3L);
        gamer2.setEmail("toto3@gmail.com");
        gamer2.setNickname("toto3");
        gamer2.setBirthAt(LocalDate.now());
        gamer2.setPassword(passwordEncoder.encode("toto"));
        userRepository.saveAndFlush(gamer2);
    }

    private void createBusinessModels(){
        List<String> businessModels = List.of("Free-to-Play", "Pay-to-Play", "Pay-to-win");
        businessModels.forEach((string) -> {
            BusinessModel businessModel = new BusinessModel();
            businessModel.setId((long) businessModels.indexOf(string) + 1);
            businessModel.setName(string);
            businessModelRepository.save(businessModel);
        });
        businessModelRepository.flush();
    }

    private void createPlatforms(){
        List<String> platforms = List.of("Windows", "Playstation 5", "Nintendo Switch");
        platforms.forEach((string) -> {
            Platform entity = new Platform();
            entity.setId((long) platforms.indexOf(string) + 1);
            entity.setName(string);
            platformRepository.save(entity);
        });
        platformRepository.flush();
    }

    private void createGenres(){
        List<String> list = List.of("FPS", "MOBA", "RPG");
        list.forEach((string) -> {
            Genre entity = new Genre();
            entity.setId((long) list.indexOf(string) + 1);
            entity.setName(string);
            genreRepository.save(entity);
        });
        genreRepository.flush();
    }

    private void createPublishers(){
        List<String> list = List.of("Riot Games", "Blizzard Entertainment", "Ubisoft");
        list.forEach((string) -> {
            Publisher entity = new Publisher();
            entity.setId((long) list.indexOf(string) + 1);
            entity.setName(string);
            publisherRepository.save(entity);
        });
        publisherRepository.flush();
    }

    private void createClassifications(){
        List<String> list = List.of("PEGI7", "PEGI12", "PEGI8");
        list.forEach((string) -> {
            Classification entity = new Classification();
            entity.setId((long) list.indexOf(string) + 1);
            entity.setName(string);
            classificationRepository.save(entity);
        });
        classificationRepository.flush();
    }

    private void createGames(){
        Game game = new Game();

        game.setId(1L);
        game.setName("League Of Legends");
        game.setDescription("Jeu de merde");
        game.setPublishedAt(LocalDate.of(2009, 10, 27));
        game.setClassification(classificationRepository.findById(2L).get());
        game.setGenre(genreRepository.findById(2L).get());
        game.setPublisher(publisherRepository.findById(1L).get());
        game.setBusinessModel(businessModelRepository.findById(1L).get());
        game.addPlatform(platformRepository.findById(1L).get());

        Review review = new Review();

        review.setId(1L);
        review.setCreatedAt(LocalDateTime.now());
        review.setWriter((Gamer)userRepository.findById(2L).get());
        review.setRating(0F);
        review.setDescription("Pas fou");
        review.setGame(game);

        Review review2 = new Review();

        review2.setId(2L);
        review2.setCreatedAt(LocalDateTime.now());
        review2.setWriter((Gamer)userRepository.findById(3L).get());
        review2.setRating(1F);
        review2.setDescription("Bof");
        review2.setGame(game);
        review2.setModerator((Moderator) userRepository.findById(1L).get());

        gameRepository.save(game);

        reviewRepository.save(review);
        reviewRepository.save(review2);

        Game game2 = new Game();

        game2.setId(2L);
        game2.setName("Overwatch");
        game2.setDescription("Yes");
        game2.setPublishedAt(LocalDate.of(2016, 5, 3));
        game2.setClassification(classificationRepository.findById(1L).get());
        game2.setGenre(genreRepository.findById(1L).get());
        game2.setPublisher(publisherRepository.findById(2L).get());
        game2.setBusinessModel(businessModelRepository.findById(1L).get());
        game2.addPlatform(platformRepository.findById(1L).get());
        game2.addPlatform(platformRepository.findById(3L).get());

        Review review3 = new Review();

        review3.setId(3L);
        review3.setCreatedAt(LocalDateTime.now());
        review3.setWriter((Gamer)userRepository.findById(2L).get());
        review3.setRating(10F);
        review3.setDescription("Peux mieux faire");
        review3.setGame(game2);
        review3.setModerator((Moderator) userRepository.findById(1L).get());

        Review review4 = new Review();

        review4.setId(4L);
        review4.setCreatedAt(LocalDateTime.now());
        review4.setWriter((Gamer)userRepository.findById(3L).get());
        review4.setRating(5F);
        review4.setDescription("Projet Titan aurait été mieux");
        review4.setGame(game2);

        gameRepository.saveAndFlush(game2);

        reviewRepository.save(review3);
        reviewRepository.saveAndFlush(review4);

    }
}
