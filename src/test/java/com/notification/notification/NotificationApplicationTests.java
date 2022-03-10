package com.notification.notification;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class NotificationApplicationTests {

	@InjectMocks private NotificationService notificationService;
	@Mock private LocalDateTimeFactory localDateTimeFactory;

	@Test
	@DisplayName("When user is noob and hour is 12hs, Then send specific notification")
	void whenUserIsTypeNoobAndTwelveHoursThenSendSpecificNotification() {

		var typeUser = TypeUser.NOOB;
		Mockito.when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(12,0));

		var response = notificationService.send(typeUser);

		Assertions.assertThat(response.isPresent()).isTrue();
		Assertions.assertThat(response.get().hour()).isEqualTo(12);
		Assertions.assertThat(response.get().minute()).isEqualTo(0);
		Assertions.assertThat(response.get().numberDaysOfWeek()).isEqualTo(7);
		Assertions.assertThat(response.get().message()).isEqualTo("Você precisa usar mais nosso APP para " +
				"obter cada vez mais promoções");
	}

	@Test
	@DisplayName("When user is noob and hour is 18hs, Then send specific notification")
	void whenUserIsTypeNoobAndEighteenHoursThenSendSpecificNotification() {

		var typeUser = TypeUser.NOOB;
		Mockito.when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(18,0));

		var response = notificationService.send(typeUser);

		Assertions.assertThat(response.isPresent()).isTrue();
		Assertions.assertThat(response.get().hour()).isEqualTo(18);
		Assertions.assertThat(response.get().minute()).isEqualTo(0);
		Assertions.assertThat(response.get().numberDaysOfWeek()).isEqualTo(7);
		Assertions.assertThat(response.get().message()).isEqualTo("Você precisa usar mais nosso APP para " +
				"obter cada vez mais promoções");
	}

	@ParameterizedTest
	@ValueSource(ints = {1,10,5,15,2,3})
	@DisplayName("When user is noob and hour is 12hs but minute doesn't 0, Then doesn't send specific notification")
	void whenUserIsTypeNoobAndMinuteIsWrongThenDoesNotSendNotification(int minute) {

		var typeUser = TypeUser.NOOB;
		Mockito.when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(12,minute));

		var response = notificationService.send(typeUser);

		Assertions.assertThat(response.isPresent()).isFalse();
	}

	@ParameterizedTest
	@ValueSource(ints = {19,9,8,11,15,20})
	@DisplayName("When user is noob and hour doesn't 12hs or 18hs, Then doesn't send specific notification")
	void whenUserIsTypeNoobAndTimeIsWrongThenDoesNotSendNotification(int hour) {

		var typeUser = TypeUser.NOOB;
		Mockito.when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(hour,0));

		var response = notificationService.send(typeUser);

		Assertions.assertThat(response.isPresent()).isFalse();
	}

	@Test
	@DisplayName("When user is intermediary and hour is 18hs, Then send specific notification")
	void whenUserIsTypeIntermediaryAndEighteenHoursThenSendSpecificNotification() {

		var typeUser = TypeUser.INTERMEDIARY;
		Mockito.when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(18,0));

		var response = notificationService.send(typeUser);

		Assertions.assertThat(response.isPresent()).isTrue();
		Assertions.assertThat(response.get().hour()).isEqualTo(18);
		Assertions.assertThat(response.get().minute()).isEqualTo(0);
		Assertions.assertThat(response.get().numberDaysOfWeek()).isEqualTo(7);
		Assertions.assertThat(response.get().message()).isEqualTo("Você está no caminho certo, continue acessando " +
				"nosso APP para obter cada vez mais promoções");
	}

	@ParameterizedTest
	@ValueSource(ints = {1,10,5,15,2,3})
	@DisplayName("When user is intermediary and hour is 12hs but minute doesn't 0, Then doesn't send specific notification")
	void whenUserIsTypeIntermediaryAndMinuteIsWrongThenDoesNotSendNotification(int minute) {

		var typeUser = TypeUser.INTERMEDIARY;
		Mockito.when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(12,minute));

		var response = notificationService.send(typeUser);

		Assertions.assertThat(response.isPresent()).isFalse();
	}

	@ParameterizedTest
	@ValueSource(ints = {12,11,19,17,15})
	@DisplayName("When user is intermediary and hour doesn't 18hs, Then doesn't send specific notification")
	void whenUserIsTypeIntermediaryAndTimeIsWrongThenDoesNotSendNotification(int hour) {

		var typeUser = TypeUser.INTERMEDIARY;
		Mockito.when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(hour,0));

		var response = notificationService.send(typeUser);

		Assertions.assertThat(response.isPresent()).isFalse();
	}

}
