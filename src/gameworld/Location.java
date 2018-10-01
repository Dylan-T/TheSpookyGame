package gameworld;

/**
 * This is a single location in the game.
 * @author thomsodyla1
 *
 */
public class Location {
  Item[][] grid = new Item[4][4]; //2D array of the positions the objects of the room are held
  Location[] exits = new Location[4]; //0 - North; 1 - East; 2 - South; 3 - West

}
