package io.bootcamp.BootcampBackend;

import io.bootcamp.BootcampBackend.user.User;
import io.bootcamp.BootcampBackend.user.UserDAO;
import io.bootcamp.BootcampBackend.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
class BootcampBackendApplicationTests {

	@Mock
	private UserDAO UserDAO;
	private UserService underTest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		underTest = new UserService(UserDAO);
	}

	@Test
	void itCanAddUser() {
		User fatma = new User(1, "Fatma", "fatma@gmail.com", "yolo");

		Mockito.when(UserDAO.selectAllUserSortBy("id")).thenReturn(
				List.of(new User(2, "Silvia", "silvia@gmail.com", "carpediem"))
		);

		Mockito.when(UserDAO.insertUser(eq(fatma))).thenReturn(1);

		int result = underTest.addNewUser(fatma);

		ArgumentCaptor<User> UserArgumentCaptor = ArgumentCaptor.forClass(User.class);
		Mockito.verify(UserDAO).insertUser(UserArgumentCaptor.capture());

		User capturedFatma = UserArgumentCaptor.getValue();
		assertThat(capturedFatma).isEqualTo(fatma);

		assertThat(result).isEqualTo(1);
	}

	@Test
	void canGetPeopleFromDB() {
		//When
		List<User> Users = List.of(
				new User(2, "Fatma", "fatma@gmail.com", "yolo"));
				new User(5, "Silvia","silvia@gmail.com", "carpediem");

		Mockito.when(UserDAO.selectAllUserSortBy("id")).thenReturn(Users);

		//Given
		List<User> UserDAOList = underTest.selectAllUser("id");

		// Then
		assertThat(Users).isEqualTo(UserDAOList);
	}
}
