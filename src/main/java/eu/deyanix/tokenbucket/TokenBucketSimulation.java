package eu.deyanix.tokenbucket;

import eu.deyanix.tokenbucket.randomizer.PoissonRandomizer;

public class TokenBucketSimulation {
	private final TokenBucket bucket;
	private final TokenBucketSimulationConfiguration configuration;
	private final PoissonRandomizer randomizer;
	private double now = 0;
	private double nextRefill = 0;
	private double nextArrival = 0;

	private long arrivalPackets = 0, droppedPackets = 0, transmittedPackets = 0;

	public TokenBucketSimulation(TokenBucketSimulationConfiguration configuration) {
		this.bucket = new TokenBucket(configuration.getBucketCapacity());
		this.configuration = configuration;
		this.randomizer = new PoissonRandomizer(configuration.getPacketArrivalRate());
	}

	public void run() {
		while (now < configuration.getEndTime()) {
			if (nextRefill < nextArrival) {
				now = nextRefill;
				nextRefill += 1/configuration.getBucketRefillRate();

				bucket.refill(configuration.getBucketRefillAmount());
//				System.out.print("Refill bucket ");
//				System.out.printf("(tokens=%d, nextRefill=%f)", bucket.getKept(), nextRefill);
//				System.out.println();
			} else {
				now = nextArrival;
				nextArrival += 1d;

				if (bucket.consume(randomizer.next())) {
					transmittedPackets++;
				} else {
					droppedPackets++;
				}
				arrivalPackets++;

//				System.out.print("Departure packet ");
//				System.out.printf("(tokens=%d)", bucket.getKept());
//				System.out.println();
			}
		}
	}

	public long getDroppedPackets() {
		return droppedPackets;
	}

	public long getTransmittedPackets() {
		return transmittedPackets;
	}

	public long getArrivalPackets() {
		return arrivalPackets;
	}

	public double getTransmittedPacketsRatio() {
		return ((double) getTransmittedPackets()) / ((double) getArrivalPackets());
	}
}
