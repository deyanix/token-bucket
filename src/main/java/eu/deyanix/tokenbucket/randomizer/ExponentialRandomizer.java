package eu.deyanix.tokenbucket.randomizer;

import java.util.Random;

public class ExponentialRandomizer {
	private final Random random = new Random();
	private final double lambda;

	public ExponentialRandomizer(double lambda) {
		this.lambda = lambda;
	}

	public double next() {
		return Math.log(1-random.nextDouble()) / (-lambda);
	}
}
