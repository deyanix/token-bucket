package eu.deyanix.tokenbucket;

import java.util.Random;

public class Main {
	private static final Random RANDOM = new Random(12213542552L);

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			for (int j = i; j < 100; j++) {
				TokenBucketSimulationConfiguration configuration = new TokenBucketSimulationConfiguration()
						.setSimulationEndTime(1000)
						.setBucketCapacity(750 + j * 5)
						.setBucketRefillRate(600 + i * 5)
						.setBucketRefillAmount(1)
						.setPacketArrivalRate(750)
						.setServiceRate(1000);
				TokenBucketSimulation simulation = new TokenBucketSimulation(configuration);
				simulation.run();
				if (simulation.getTransmittedPacketsRatio() >= 0.9) {
					System.out.printf("%f;%f;%d", configuration.getPacketArrivalRate(), configuration.getBucketRefillRate(), configuration.getBucketCapacity());
					System.out.println();
					break;
				}
			}
		}



//		System.out.printf("All packets: %d\n", simulation.getArrivalPackets());
//		System.out.printf("Transmitted packets: %d\n", simulation.getTransmittedPackets());
//		System.out.printf("Dropped packets: %d\n", simulation.getDroppedPackets());
//		System.out.printf("Radio: %f\n", simulation.getTransmittedPacketsRatio());

//		for (int i = 1; i < 400; i++) {
//			eu.deyanix.tokenbucket.TokenBucketSimulationConfiguration configuration = new eu.deyanix.tokenbucket.TokenBucketSimulationConfiguration()
//					.setBucketCapacity(i)
//					.setPacketArrivalRate(500);
//			eu.deyanix.tokenbucket.TokenBucketSimulation simulation = new eu.deyanix.tokenbucket.TokenBucketSimulation(configuration);
//			simulation.run();
//			if (simulation.getTransmittedPacketsRatio() >= 0.9) {
//				System.out.printf("A %d %f", i, simulation.getTransmittedPacketsRatio());
//				System.out.println();
//				break;
//			}
//		}
//
//		for (int i = 1; i < 400; i++) {
//			eu.deyanix.tokenbucket.TokenBucketSimulationConfiguration configuration = new eu.deyanix.tokenbucket.TokenBucketSimulationConfiguration()
//					.setBucketCapacity(i)
//					.setPacketArrivalRate(400);
//			eu.deyanix.tokenbucket.TokenBucketSimulation simulation = new eu.deyanix.tokenbucket.TokenBucketSimulation(configuration);
//			simulation.run();
//			if (simulation.getTransmittedPacketsRatio() >= 0.9) {
//				System.out.printf("B %d %f", i, simulation.getTransmittedPacketsRatio());
//				System.out.println();
//				break;
//			}
//		}

	}
}
