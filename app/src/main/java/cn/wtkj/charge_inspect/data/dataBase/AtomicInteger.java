package cn.wtkj.charge_inspect.data.dataBase;

public class AtomicInteger {
	private int i;

	public int incrementAndGet() {
		return ++i;
	}

	public int decrementAndGet() {
		return --i;
	}
}
