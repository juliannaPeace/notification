package com.notification.notification;

import com.notification.notification.domain.entity.Hour;
import com.notification.notification.domain.entity.TypeUser;
import com.notification.notification.domain.service.NotificationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class NotificationApplicationTests {

	@ParameterizedTest
	@EnumSource(DayOfWeek.class)
	@DisplayName("When user is noob and hour is 12hs, Then send specific notification")
	void whenUserIsTypeNoobAndTwelveHoursThenSendSpecificNotification(DayOfWeek dayOfWeek) {

		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(12,0));
		when(localDateTimeFactory.dayOfWeek()).thenReturn(dayOfWeek);

		var response = notificationService.send();

		assertThat(response.isEmpty()).isFalse();
		assertThat(response.get(0).getHours()).contains(new Hour(12,0));
		assertThat(response.get(0).getNumberDaysOfWeek()).isEqualTo(7);
		assertThat(response.get(0).isTypeUser(TypeUser.NOOB)).isTrue();
		assertThat(response.get(0).getMessage()).isEqualTo("Você precisa usar mais nosso APP para " +
				"obter cada vez mais promoções");
	}
	@Autowired private NotificationService notificationService;

	@MockBean private LocalDateTimeFactory localDateTimeFactory;

	@ParameterizedTest
	@EnumSource(DayOfWeek.class)
	@DisplayName("When user is noob and hour is 18hs, Then send specific notification")
	void whenUserIsTypeNoobAndEighteenHoursThenSendSpecificNotification(DayOfWeek dayOfWeek) {

		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(18,0));
		when(localDateTimeFactory.dayOfWeek()).thenReturn(dayOfWeek);

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

		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(12,minute));
		when(localDateTimeFactory.dayOfWeek()).thenReturn(DayOfWeek.MONDAY);

		var response = notificationService.send();

		assertThat(response.isEmpty()).isTrue();
	}

	@ParameterizedTest
	@ValueSource(ints = {19,9,8,11,15,20})
	@DisplayName("When user is noob and hour doesn't 12hs or 18hs, Then doesn't send specific notification")
	void whenUserIsTypeNoobAndTimeIsWrongThenDoesNotSendNotification(int hour) {

		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(hour,0));
		when(localDateTimeFactory.dayOfWeek()).thenReturn(DayOfWeek.SATURDAY);

		var response = notificationService.send();

		assertThat(response.isEmpty()).isTrue();
	}

	@ParameterizedTest
	@EnumSource(DayOfWeek.class)
	@DisplayName("When user is intermediary and hour is 18hs, Then send specific notification")
	void whenUserIsTypeIntermediaryAndEighteenHoursThenSendSpecificNotification(DayOfWeek dayOfWeek) {

		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(18,0));
		when(localDateTimeFactory.dayOfWeek()).thenReturn(dayOfWeek);

		var response = notificationService.send();

		assertThat(response.isEmpty()).isFalse();
		assertThat(response.get(0).getHours()).contains(new Hour(18,0));
		assertThat(response.get(0).getNumberDaysOfWeek()).isEqualTo(7);
		assertThat(response.get(0).isTypeUser(TypeUser.INTERMEDIARY)).isTrue();
		assertThat(response.get(0).getMessage()).isEqualTo("Você está no caminho certo, continue acessando " +
				"nosso APP para obter cada vez mais promoções");
	}

	@ParameterizedTest
	@ValueSource(ints = {1,10,5,15,2,3})
	@DisplayName("When user is intermediary and hour is 18hs but minute doesn't 0, Then doesn't send specific notification")
	void whenUserIsTypeIntermediaryAndMinuteIsWrongThenDoesNotSendNotification(int minute) {

		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(18,minute));
		when(localDateTimeFactory.dayOfWeek()).thenReturn(DayOfWeek.SUNDAY);

		var response = notificationService.send();

		assertThat(response.isEmpty()).isTrue();
	}

	@ParameterizedTest
	@ValueSource(ints = {12,11,19,17,15})
	@DisplayName("When user is intermediary and hour doesn't 18hs, Then doesn't send specific notification")
	void whenUserIsTypeIntermediaryAndTimeIsWrongThenDoesNotSendNotification(int hour) {
		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(hour,0));
		when(localDateTimeFactory.dayOfWeek()).thenReturn(DayOfWeek.WEDNESDAY);

		var response = notificationService.send().stream()
				.filter(r -> r.isTypeUser(TypeUser.INTERMEDIARY)).toList();

		assertThat(response.isEmpty()).isTrue();
	}

	@ParameterizedTest
	@EnumSource(value = DayOfWeek.class, names = {"MONDAY","FRIDAY"})
	@DisplayName("When user is advanced and hour is 22hs and day of week is Monday or Friday, Then send specific notification")
	void whenUserIsTypeAdvancedAndTwentyTwoHoursAndWeekOkThenSendSpecificNotification(DayOfWeek dayOfWeek) {
		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(22,0));
		when(localDateTimeFactory.dayOfWeek()).thenReturn(dayOfWeek);

		var response = notificationService.send();

		assertThat(response.isEmpty()).isFalse();
		assertThat(response.get(0).getHours()).contains(new Hour(22,0));
		assertThat(response.get(0).getNumberDaysOfWeek()).isEqualTo(2);
		assertThat(response.get(0).getMessage()).isEqualTo("Você é um dos nosso melhores usuários, continue " +
				"acessando nosso APP para obter cada vez mais promoções");
	}

	@ParameterizedTest
	@EnumSource(value = DayOfWeek.class, names = {"MONDAY","FRIDAY"}, mode = EnumSource.Mode.EXCLUDE)
	@DisplayName("When user is advanced and hour is 22hs and day of week is not Monday or Friday, Then doesn't send specific notification")
	void whenUserIsTypeAdvancedAndTwentyTwoHoursAndWeekNotOkThenDoesNotSendSpecificNotification(DayOfWeek dayOfWeek) {
		when(localDateTimeFactory.now()).thenReturn(LocalDate.now().atTime(22,0));
		when(localDateTimeFactory.dayOfWeek()).thenReturn(dayOfWeek);

		var response = notificationService.send();

		assertThat(response.isEmpty()).isTrue();
	}
}
