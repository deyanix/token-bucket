package eu.deyanix.tokenbucket;

public class TokenBucket {
	private long capacity;
	private long kept = 0;

	public TokenBucket(long capacity) {
		this.capacity = capacity;
	}

	public long getCapacity() {
		return capacity;
	}

	public long getKept() {
		return kept;
	}

	public boolean consume(long tokens) {
		if (kept >= tokens) {
			kept -= tokens;
			return true;
		}
		return false;
	}

	public boolean consume() {
		return consume(1);
	}

	public void refill(long tokens) {
		kept = Math.min(kept + tokens, capacity);
	}

	public void refill() {
		refill(1);
	}
}
