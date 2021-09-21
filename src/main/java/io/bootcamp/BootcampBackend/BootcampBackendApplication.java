package io.bootcamp.BootcampBackend;

import com.github.javafaker.Faker;
import io.bootcamp.BootcampBackend.course.Category;
import io.bootcamp.BootcampBackend.course.Course;
import io.bootcamp.BootcampBackend.course.CourseRepository;
import io.bootcamp.BootcampBackend.course.Location;
import io.bootcamp.BootcampBackend.user.User;
import io.bootcamp.BootcampBackend.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.beans.BeanProperty;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BootcampBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcampBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			CourseRepository courseRepository
	){
		return args -> {
			Faker faker = new Faker();
			String[] name = faker.name().fullName().split(" ");
			String email = String.format("%s.%s@bnta.edu", name[0], name[1]);
			User user = new User(String.join(" ", name), email, faker.food().ingredient()
			);
//			user.setSession(new Session(user, LocalDateTime.now()));
//			userRepository.save(user);
			Course course = new Course("BRIGHT NETWORK ACADEMY", 4.6, "cool", Category.SOFTWARE_ENGINEERING, null, LocalDate.of(2021, 8,22), 0, Location.ONLINE, "Zoom", 25, "www.bnta.com");
//			courseRepository.save(course);
//			user.addFeedback(new Feedback(course, user, 4.5, "good"));

			user.addToWishlist(new Wishlist(
					user,
					course,
					LocalDateTime.now()
			));
			userRepository.save(user);

//			userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
//			PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("name"));
//			Page<User> page = userRepository.findAll(pageRequest);
//			System.out.println(page);
		};
	}

	private void generateRandomUsers(UserRepository userRepository) {
		Faker faker = new Faker();
		for (int i=0; i <= 10; i++){
			String[] name = faker.name().fullName().split(" ");
			String email = String.format("%s.%s@bnta.edu", name[0], name[1]);
			User user = new User(String.join(" ", name), email, faker.food().ingredient()
					);
			userRepository.save(user);
		}
	}

}
