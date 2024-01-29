package fr.dorian_ferreira.cap_entreprise.configuration;

import com.github.javafaker.Faker;
import fr.dorian_ferreira.cap_entreprise.entity.*;
import fr.dorian_ferreira.cap_entreprise.entity.interfaces.ImageInterface;
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
            if (userRepository.findById(i).isEmpty()) {
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
                user.setEmail(name + "@gmail.com");
                user.setPassword(passwordEncoder.encode("12345"));
                userRepository.save(user);
            }
        }
    }

    private void createBusinessModels() {
        createNomenclatures(
                businessModelRepository,
                BusinessModel.class,
                List.of("Free-to-Play", "Pay-to-Play", "Pay-to-win"),
                null
        );
    }

    private void createPlatforms() {
        createNomenclatures(
                platformRepository,
                Platform.class,
                List.of("Switch", "PC", "PS5", "PS4", "PS3", "XBOX Series X", "XBOX One"),
                List.of("<?xml version=\"1.0\" encoding=\"UTF-8\"?><svg width=\"24px\" height=\"24px\" viewBox=\"0 0 24 24\" stroke-width=\"1.5\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\" color=\"#ffffff\"><path d=\"M2 17V7C2 4.79086 3.79086 3 6 3H9.9C10.2314 3 10.5 3.26863 10.5 3.6V20.4C10.5 20.7314 10.2314 21 9.9 21H6C3.79086 21 2 19.2091 2 17Z\" stroke=\"#ffffff\" stroke-width=\"1.5\"></path><path d=\"M6.5 8C7.05228 8 7.5 7.55228 7.5 7C7.5 6.44772 7.05228 6 6.5 6C5.94772 6 5.5 6.44772 5.5 7C5.5 7.55228 5.94772 8 6.5 8Z\" fill=\"#ffffff\" stroke=\"#ffffff\" stroke-width=\"1.5\" stroke-linecap=\"round\" stroke-linejoin=\"round\"></path><path d=\"M17.5 14C18.0523 14 18.5 13.5523 18.5 13C18.5 12.4477 18.0523 12 17.5 12C16.9477 12 16.5 12.4477 16.5 13C16.5 13.5523 16.9477 14 17.5 14Z\" fill=\"#ffffff\" stroke=\"#ffffff\" stroke-width=\"1.5\" stroke-linecap=\"round\" stroke-linejoin=\"round\"></path><path d=\"M22 17V7C22 4.79086 20.2091 3 18 3H14.1C13.7686 3 13.5 3.26863 13.5 3.6V20.4C13.5 20.7314 13.7686 21 14.1 21H18C20.2091 21 22 19.2091 22 17Z\" stroke=\"#ffffff\" stroke-width=\"1.5\"></path></svg>",
                        "<i class=\"fa-solid fa-computer\"></i>",
                        "<i class=\"fa-brands fa-playstation\"></i>",
                        "<i class=\"fa-brands fa-playstation\"></i>",
                        "<i class=\"fa-brands fa-playstation\"></i>",
                        "<i class=\"fa-brands fa-xbox\"></i>",
                        "<i class=\"fa-brands fa-xbox\"></i>")
        );

    }

    private void createPublishers() {
        createNomenclatures(
                publisherRepository,
                Publisher.class,
                List.of("Blizzard Entertainment", "Valve", "Riot Games", "Mojang", "Rockstar", "CD Projekt Red", "EA", "2k Games", "Ubisoft", "From Software", "Game Freak", "Nintendo", "Capcom"),
                null);
    }

    private void createGames() {
        Faker faker = new Faker(Locale.of("fr"));
        List<String> games = List.of("World of Warcraft", "Overwatch", "Diablo IV", "StarCraft II", "Warcraft III : reforged",
                "DotA 2", "Counter Strike 2", "Portal 2", "La League des Légendes", "Valorant", "Minecraft", "GTA V", "The witcher III",
                "Cyberpunk 2077", "Battlefield V", "Civilization VI", "Anno 1800", "Elden Ring", "Pokémon : Violet", "Pokémon : Ecarlate",
                "Zelda : Tears of the Kingdom", "Monster Hunter : World");
        List<Long> businessModels = List.of(2L, 2L, 2L, 2L, 2L, 1L, 2L, 2L, 3L, 1L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L);
        List<Long> publishers = List.of(1L, 1L, 1L, 1L, 1L, 2L, 2L, 2L, 3L, 3L, 4L, 5L, 6L, 6L, 7L, 8L, 9L, 10L, 11L, 11L, 12L, 13L);
        List<Long> genres = List.of(16L, 1L, 7L, 15L, 15L, 2L, 1L, 6L, 2L, 1L, 14L, 14L, 4L, 6L, 1L, 15L, 15L, 4L, 4L, 4L, 4L, 10L);
        List<List<Long>> platforms = List.of(List.of(2L), List.of(2L, 1L), List.of(2L), List.of(2L),
                List.of(2L), List.of(2L), List.of(2L), List.of(2L, 1L, 6L, 7L), List.of(2L), List.of(2L),
                List.of(2L, 1L, 3L, 4L, 5L, 6L, 7L), List.of(2L, 3L, 4L, 5L, 6L, 7L), List.of(2L, 1L, 3L, 4L, 5L, 6L, 7L), List.of(2L, 3L),
                List.of(2L), List.of(1L, 2L), List.of(2L, 6L), List.of(2L, 6L, 3L), List.of(1L), List.of(1L), List.of(1L), List.of(2L, 4L, 7L));
        List<String> images = List.of("https://cdn.thegamesdb.net/images/thumb/boxart/front/149-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/32185-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/115193-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/151-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/803-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/2474-1.png",
                "https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/CS2_Cover_Art.jpg/220px-CS2_Cover_Art.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/914-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/928-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/72904-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/50424-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/20952-1.jpg",
                "https://calimacil.com/cdn/shop/files/Geralt-calimacil-larp-replica-banner-mobile.jpg?v=1695734545&width=1500",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/14517-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/55756-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/36612-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/64422-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/65101-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/104566-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/104565-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/104362-1.jpg",
                "https://cdn.thegamesdb.net/images/thumb/boxart/front/60572-1.jpg");
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
                platforms.get(i).forEach(platform -> {
                    game.addPlatform(platformService.findById(platform));
                });
                gameRepository.save(game);
            }
        }
    }

    private void createReview() {
        Faker faker = new Faker(Locale.of("fr"));
        for (long i = 1; i <= 500; i++) {
            if (reviewRepository.findById(i).isEmpty()) {
                Review review = new Review();
                review.setId(i);
                Random rand = new Random();
                LocalDateTime localDate = new java.sql.Date(faker.date().birthday(0, 2).getTime()).toLocalDate().atTime(0, 0);
                review.setCreatedAt(localDate);
                review.setWriter((Gamer) userService.findById(rand.nextLong(201 - 6) + 6));
                review.setGame(gameService.findById(rand.nextLong(22 - 1) + 1));

                float rating = (float) rand.nextLong(21 - 2) + 2;
                if (review.getGame().getId().equals(9L)) {
                    rating = (float) rand.nextLong(3);
                } else if (review.getGame().getId().equals(6L) || review.getGame().getId().equals(1L)) {
                    rating = (float) rand.nextLong(21 - 10) + 10;
                }
                review.setRating(rating);

                review.setDescription("<strong>" + faker.chuckNorris().fact() + "</strong></br></br>" + faker.lorem().paragraph(3));
                if (i%5 != 0) {
                    review.setModerator((Moderator) userService.findById(rand.nextLong(5 - 1) + 1));
                    review.setModeratedAt(LocalDateTime.now());
                }
                reviewRepository.save(review);
            }
        }
    }

    private void createClassifications() {
        createNomenclatures(
                classificationRepository,
                Classification.class,
                List.of("PEGI 3", "PEGI 7", "PEGI 12", "PEGI 16", "PEGI 18"),
                null
        );
    }

    private void createGenres() {
        createNomenclatures(
                genreRepository,
                Genre.class,
                List.of("FPS", "MOBA", "MMO", "RPG", "Voiture", "Aventure", "Hack'n'Slash", "Simulation", "Sport", "Action", "Horreur", "Plateforme", "Cartes", "Monde ouvert", "Stratégie", "MMO RPG"),
                null
        );
    }

    private void createNomenclatures(
            JpaRepository repository,
            Class<?> objectClass,
            List<String> items,
            List<String> images
    ) {


        for (int i = 0; i < items.size(); i++) {
            String name = items.get(i);
            try {
                Long id = (long) items.indexOf(name) + 1;
                if (repository.findById(id).isEmpty()) {
                    Object item = objectClass.getDeclaredConstructor().newInstance();
                    if (item instanceof NomenclatureInterface nameEntity) {
                        nameEntity.setId(id);
                        nameEntity.setName(name);
                        if (nameEntity instanceof ImageInterface imageEntity) {
                            imageEntity.setImage(images.get(i));
                        }

                        repository.save(nameEntity);
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
