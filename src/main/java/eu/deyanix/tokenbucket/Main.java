package eu.deyanix.tokenbucket;

import eu.deyanix.tokenbucket.trafficgenerator.ExponentialTrafficGenerator;
import eu.deyanix.tokenbucket.trafficgenerator.UniformTrafficGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	public static void histogram() {
		int length = 1_000_000;
		List<Double> values = new ArrayList<>(length);
		ExponentialTrafficGenerator randomizer = new ExponentialTrafficGenerator(0.25);
		for (int i = 0; i < length; i++) {
			values.add(randomizer.next());
		}

		double min = Math.floor(Collections.min(values));
		double step = 0.5;

		values.stream()
				.map((val) -> (int) Math.floor((val - min) / step))
				.map((val) -> ((2*val + 1) / 2d) * step)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.forEach((bar, occurs) -> System.out.printf("%f;%d\n\r", bar, occurs));
	}

	public static void main(String[] args) {
		double mean = 500;
		System.out.printf("λ = %d", (int) mean);
		System.out.println();

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 1000; j++) {
				TokenBucketSimulationConfiguration configuration = new TokenBucketSimulationConfiguration()
						.setSimulationEndTime(500)
						.setBucketCapacity(100 + j*10)
						.setBucketRefillRate(45500 + i*500)
						.setPacketSize(100)
						.setTrafficGenerator(new UniformTrafficGenerator(mean));
				TokenBucketSimulation simulation = new TokenBucketSimulation(configuration);
				simulation.run();
				if (simulation.getTransmittedPacketsRatio() >= 0.9) {
					System.out.printf("%d;%f", configuration.getBucketCapacity(), configuration.getBucketRefillRate());
					System.out.println();
					break;
				}
			}
		}
	}
}
