package mapeditor;


/**
 * Locations to store the map.
 * @author dunninnath
 *
 */
public class Location {

  TilePiece[][] room; // 2D array of the positions the objects of the room are held
  /**
   * 0 - North; 1 - East; 2 - South; 3 - West true - locked false - unlocked null
   * - no exit
   */
  Boolean[] exits;

  /**
   * Creates a new location specifying only the height and width.
   *
   * @param height
   *          of the location
   * @param width
   *          of the location
   * @param room
   *          2D Array of TilePiece
   */
  public Location(int height, int width, TilePiece[][] room) {
    this.room = room;
    exits = new Boolean[4];
    this.room = room;
  }

  /**
   * Check if there is an empty tile in this location.
   *
   * @return if the locations room is full
   */
  public boolean isFull() {
    for (int x = 0; x < room.length; x++) {
      for (int y = 0; y < room[0].length; y++) {
        if (room[x][y] == null) {
          return false;
        }
      }
    }
    return true;
  }

}
