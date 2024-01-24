package fr.dorian_ferreira.cap_entreprise.configuration;

import com.github.javafaker.Faker;
import fr.dorian_ferreira.cap_entreprise.entity.*;
import fr.dorian_ferreira.cap_entreprise.entity.interfaces.NomenclatureInterface;
import fr.dorian_ferreira.cap_entreprise.repository.*;
import fr.dorian_ferreira.cap_entreprise.service.*;
import fr.dorian_ferreira.cap_entreprise.utils.Slugger;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {
    private UserRepository userRepository;
    private UserService userService;

    private BusinessModelRepository businessModelRepository;
    private BusinessModelService businessModelService;

    private PlatformRepository platformRepository;
    private PlatformService platformService;

    private PublisherRepository publisherRepository;
    private PublisherService publisherService;

    private ClassificationRepository classificationRepository;
    private ClassificationService classificationService;

    private GenreRepository genreRepository;
    private GenreService genreService;

    private GameRepository gameRepository;
    private GameService gameService;

    private ReviewRepository reviewRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private Slugger slugger;

    @Override
    public void run(String... args) {
//        createUsers();
//        createBusinessModels();
//        createPlatforms();
//        createPublishers();
//        createClassifications();
//        createGenres();
//        userRepository.flush();
//        createGames();
//        gameRepository.flush();
//        createReview();
//        reviewRepository.flush();
    }

    private void createUsers() {
        Faker faker = new Faker(Locale.of("fr"));
        List<String> names = new ArrayList<>();
        for (long i = 1L; i <= 200; i++) {
            User user;
            if (i <= 5) {
                user = new Moderator();
                ((Moderator) user).setPhoneNumber(faker.phoneNumber().cellPhone());
            } else {
                user = new Gamer();
                LocalDate localDate = new java.sql.Date(faker.date().birthday(12, 60).getTime()).toLocalDate();
                ((Gamer) user).setBirthAt(localDate);
            }
            user.setId(i);
            String name;
            do {
                name = slugger.slugify(faker.funnyName().name().replace(" ", ""));
            } while (names.contains(name));
            names.add(name);
            user.setNickname(name);
            user.setEmail(faker.internet().safeEmailAddress());
            user.setPassword(passwordEncoder.encode("12345"));
            userRepository.save(user);
        }
    }

    private void createBusinessModels() {
        createNomenclatures(
                businessModelRepository,
                BusinessModel.class,
                List.of("Free-to-Play", "Pay-to-Play", "Pay-to-win")
        );
    }

    private void createPlatforms() {
        createNomenclatures(
                platformRepository,
                Platform.class,
                List.of("Switch", "PC", "PS5", "PS4", "PS3", "XBOX Series X", "XBOX One")
        );
    }

    private void createPublishers() {
        createNomenclatures(
                publisherRepository,
                Publisher.class,
                List.of("Blizzard Entertainment", "Valve", "Riot Games", "Mojang", "Rockstar", "CD Projekt Red", "EA", "2k Games", "Ubisoft", "From Software", "Game Freak", "Nintendo", "Capcom")
        );
    }

    private void createGames() {
        Faker faker = new Faker(Locale.of("fr"));
        List<String> games = List.of("World of Warcraft", "Overwatch", "Diablo IV", "StarCraft II", "Warcraft III : reforged", "DotA 2", "Counter Strike 2", "Portal 2", "La League des Légendes", "Valorant", "Minecraft", "GTA V", "The witcher III", "Cyberpunk 2077", "Battlefield V", "Anno 1800", "Elden Ring", "Pokémon : Violet", "Pokémon : Ecarlate", "Zelda : Tears of the Kingdom", "Monster Hunter : World");
        List<Long> businessModels = List.of(2L, 2L, 2L, 2L, 2L, 1L, 2L, 2L, 3L, 1L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L);
        List<Long> publishers = List.of(1L, 1L, 1L, 1L, 1L, 2L, 2L, 2L, 3L, 3L, 4L, 5L, 6L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L);
        List<Long> genres = List.of(16L, 1L, 7L, 15L, 15L, 2L, 1L, 6L, 2L, 1L, 14L, 14L, 4L, 6L, 1L, 15L, 4L, 4L, 4L, 4L, 10L);
        List<Long> platforms = List.of(2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 1L, 1L, 1L, 2L);
        List<String> images = List.of("https://cdn.thegamesdb.net/images/thumb/boxart/front/149-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/32185-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/115193-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/151-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/803-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/2474-1.png", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/CS2_Cover_Art.jpg/220px-CS2_Cover_Art.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/914-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/928-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/72904-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/50424-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/20952-1.jpg", "https://calimacil.com/cdn/shop/files/Geralt-calimacil-larp-replica-banner-mobile.jpg?v=1695734545&width=1500", "https://cdn.thegamesdb.net/images/thumb/boxart/front/14517-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/55756-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/64422-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/65101-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104566-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104565-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104362-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/60572-1.jpg");
        for (int i = 0; i < games.size(); i++) {
            Long id = (long) (i + 1);
            if (gameRepository.findById(id).isEmpty()) {
                Game game = new Game();
                game.setId(id);
                game.setName(games.get(i));
                game.setDescription("<h2>" + faker.yoda().quote() + "</h2></br>" + faker.lorem().paragraph(8));
                game.setImage(images.get(i));
                LocalDate localDate = new java.sql.Date(faker.date().birthday(2, 25).getTime()).toLocalDate();
                game.setPublishedAt(localDate);
                Random rand = new Random();
                game.setModerator((Moderator) userService.findById(rand.nextLong(5 - 1) + 1));
                game.setBusinessModel(businessModelService.findById(businessModels.get(i)));
                game.setClassification(classificationService.findById(rand.nextLong(3 - 1) + 1));
                game.setPublisher(publisherService.findById(publishers.get(i)));
                game.setGenre(genreService.findById(genres.get(i)));
                game.addPlatform(platformService.findById(platforms.get(i)));
                gameRepository.save(game);
            }
        }
    }

    private void createReview() {
        Faker faker = new Faker(Locale.of("fr"));
        for (long i = 1; i <= 500; i++) {
            Review review = new Review();
            review.setId(i);
            Random rand = new Random();
            LocalDateTime localDate = new java.sql.Date(faker.date().birthday(0, 2).getTime()).toLocalDate().atTime(0, 0);
            review.setCreatedAt(localDate);
            review.setRating((float) rand.nextLong(20));
            review.setWriter((Gamer) userService.findById(rand.nextLong(200 - 6) + 6));
            review.setGame(gameService.findById(rand.nextLong(21 - 1) + 1));
            review.setDescription(faker.chuckNorris().fact() + "</br>" + faker.lorem().paragraph(3));
            if (i%5 != 0) {
                review.setModerator((Moderator) userService.findById(rand.nextLong(5 - 1) + 1));
                review.setModeratedAt(LocalDateTime.now());
            }
            reviewRepository.save(review);
        }
    }

    private void createClassifications() {
        createNomenclatures(
                classificationRepository,
                Classification.class,
                List.of("PEGI 3", "PEGI 7", "PEGI 12", "PEGI 16", "PEGI 18")
        );
    }

    private void createGenres() {
        createNomenclatures(
                genreRepository,
                Genre.class,
                List.of("FPS", "MOBA", "MMO", "RPG", "Voiture", "Aventure", "Hack'n'Slash", "Simulation", "Sport", "Action", "Horreur", "Plateforme", "Cartes", "Monde ouvert", "Stratégie", "MMO RPG")
        );
    }

    private void createNomenclatures(
            JpaRepository repository,
            Class<?> objectClass,
            List<String> items
    ) {
        items.forEach((name) -> {
            try {
                Long id = (long) items.indexOf(name) + 1;
                if (repository.findById(id).isEmpty()) {
                    Object item = objectClass.getDeclaredConstructor().newInstance();
                    if (item instanceof NomenclatureInterface nameEntity) {
                        nameEntity.setId(id);
                        nameEntity.setName(name);
                        repository.save(nameEntity);
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
