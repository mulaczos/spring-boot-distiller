package pl.jcommerce.moonshine.model;

import java.util.List;

public interface TwiFacade {

	public List<TwiAddress> lookUp();

	public void attach(Thermometer thermometer);

}