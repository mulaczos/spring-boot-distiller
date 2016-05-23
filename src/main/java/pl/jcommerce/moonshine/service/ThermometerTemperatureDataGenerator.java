package pl.jcommerce.moonshine.service;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.jcommerce.moonshine.model.Thermometer;

@Component
public class ThermometerTemperatureDataGenerator implements ApplicationListener<BrokerAvailabilityEvent> {

	private final MessageSendingOperations<String> messagingTemplate;

	@Autowired
	private ThermometerService service;

	@Autowired
	public ThermometerTemperatureDataGenerator(final MessageSendingOperations<String> messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@Override
	public void onApplicationEvent(final BrokerAvailabilityEvent event) {
	}

	@Scheduled(fixedDelay = 1000)
	public void sendDataUpdates() {

		Thermometer thermometer = service.getChoosenTermometer();

		String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US));
		String temp = new DecimalFormat("##.##").format(thermometer.getTemperature());

		String message = Json.createObjectBuilder().add("time", time).add("value", temp).build().toString();

		this.messagingTemplate.convertAndSend("/hello", message);
	}
}