package gameworld;

/**
 * An item that can hold other items within it.
 * @author thomsodyla1
 *
 */
public interface Container extends Item {
  /**
   * Withdraw the given item from this container.
   * @param item item you wish to withdraw
   * @return it that has been withdrawn
   */
  Moveable withdraw(Moveable item);

  /**
   * Deposit an item within this container.
   * @param item the item you wish to deposit
   */
  void deposit(Moveable item);
}
