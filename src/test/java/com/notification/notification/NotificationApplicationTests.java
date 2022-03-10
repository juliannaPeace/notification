package com.notification.notification;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
	@DisplayName("When user is noob and hour is 12AM, Then send specific notification")
	void whenUserIsTypeNoobThenSendSpecificNotification12AM() {

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
	@DisplayName("When user is noob and hour is 18AM, Then send specific notification")
	void whenUserIsTypeNoobThenSendSpecificNotification18AM() {

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

	@Test
	@DisplayName("When user is intermediary and hour is 18AM, Then send specific notification")
	void whenUserIsTypeIntermediaryThenSendSpecificNotification18AM() {

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

}
