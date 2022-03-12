package com.notification.notification;

import com.notification.notification.domain.entity.Hour;
import com.notification.notification.domain.entity.TypeUser;
import com.notification.notification.domain.service.NotificationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class NotificationApplicationTests {

	@Autowired private NotificationService notificationService;
	@MockBean private LocalDateTimeFactory localDateTimeFactory;

	@Test
	@DisplayName("When user is noob and hour is 12hs, Then send specific notification")
	void whenUserIsTypeNoobAndTwelveHoursThenSendSpecificNotification() {

		var typeUser = TypeUser.NOOB;
		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(12,0));

		var response = notificationService.send();

		assertThat(response.isEmpty()).isFalse();
		assertThat(response.get(0).getHours()).contains(new Hour(12,0));
		assertThat(response.get(0).getNumberDaysOfWeek()).isEqualTo(7);
		assertThat(response.get(0).getMessage()).isEqualTo("Você precisa usar mais nosso APP para " +
				"obter cada vez mais promoções");
	}

	@Test
	@DisplayName("When user is noob and hour is 18hs, Then send specific notification")
	void whenUserIsTypeNoobAndEighteenHoursThenSendSpecificNotification() {

		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(18,0));

		var response = notificationService.send()
				.stream().filter(r -> r.isTypeUser(TypeUser.NOOB)).toList();

		assertThat(response.isEmpty()).isFalse();
		assertThat(response.get(0).getHours()).contains(new Hour(18,0));
		assertThat(response.get(0).getNumberDaysOfWeek()).isEqualTo(7);
		assertThat(response.get(0).getMessage()).isEqualTo("Você precisa usar mais nosso APP para " +
				"obter cada vez mais promoções");
	}

	@ParameterizedTest
	@ValueSource(ints = {1,10,5,15,2,3})
	@DisplayName("When user is noob and hour is 12hs but minute doesn't 0, Then doesn't send specific notification")
	void whenUserIsTypeNoobAndMinuteIsWrongThenDoesNotSendNotification(int minute) {

		var typeUser = TypeUser.NOOB;
		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(12,minute));

		var response = notificationService.send();

		assertThat(response.isEmpty()).isTrue();
	}

	@ParameterizedTest
	@ValueSource(ints = {19,9,8,11,15,20})
	@DisplayName("When user is noob and hour doesn't 12hs or 18hs, Then doesn't send specific notification")
	void whenUserIsTypeNoobAndTimeIsWrongThenDoesNotSendNotification(int hour) {

		var typeUser = TypeUser.NOOB;
		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(hour,0));

		var response = notificationService.send();

		assertThat(response.isEmpty()).isTrue();
	}

	@Test
	@DisplayName("When user is intermediary and hour is 18hs, Then send specific notification")
	void whenUserIsTypeIntermediaryAndEighteenHoursThenSendSpecificNotification() {

		var typeUser = TypeUser.INTERMEDIARY;
		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(18,0));

		var response = notificationService.send();

		assertThat(response.isEmpty()).isFalse();
		assertThat(response.get(0).getHours()).contains(new Hour(18,0));
		assertThat(response.get(0).getNumberDaysOfWeek()).isEqualTo(7);
		assertThat(response.get(0).getMessage()).isEqualTo("Você está no caminho certo, continue acessando " +
				"nosso APP para obter cada vez mais promoções");
	}

	@ParameterizedTest
	@ValueSource(ints = {1,10,5,15,2,3})
	@DisplayName("When user is intermediary and hour is 18hs but minute doesn't 0, Then doesn't send specific notification")
	void whenUserIsTypeIntermediaryAndMinuteIsWrongThenDoesNotSendNotification(int minute) {

		var typeUser = TypeUser.INTERMEDIARY;
		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(18,minute));

		var response = notificationService.send();

		assertThat(response.isEmpty()).isTrue();
	}

	@ParameterizedTest
	@ValueSource(ints = {12,11,19,17,15})
	@DisplayName("When user is intermediary and hour doesn't 18hs, Then doesn't send specific notification")
	void whenUserIsTypeIntermediaryAndTimeIsWrongThenDoesNotSendNotification(int hour) {
		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(hour,0));

		var response = notificationService.send().stream()
				.filter(r -> r.isTypeUser(TypeUser.INTERMEDIARY)).toList();

		assertThat(response.isEmpty()).isTrue();
	}

	@Test
	@DisplayName("When user is advanced and hour is 22hs and did not receive 2 notifications in the week, Then send specific notification")
	void whenUserIsTypeAdvancedAndTwentyTwoHoursThenSendSpecificNotification() {
		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(22,0));

		//TODO: Implementar lógica de envio de mensagens 2x por semana
		//TODO: Para isso preciso persistir esse dado em algum lugar, criando assim uma repositório de dados

		var response = notificationService.send();

		assertThat(response.isEmpty()).isFalse();
		assertThat(response.get(0).getHours()).contains(new Hour(22,0));
		assertThat(response.get(0).getNumberDaysOfWeek()).isEqualTo(2);
		assertThat(response.get(0).getMessage()).isEqualTo("Você é um dos nosso melhores usuários, continue " +
				"acessando nosso APP para obter cada vez mais promoções");
	}

}
