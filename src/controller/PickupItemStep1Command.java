package controller;

import java.util.List;
import model.PlayerType;
import model.WorldModel;
import view.WorldView;


/**
 * Command for checking whether the current human player can pick up items.
 */
public class PickupItemStep1Command implements WorldCommand {

  /**
   * Execute command.
   * If game is over, or current player is AI, or current player has carried enough items,
   * the command execution request will be ignored.
   *
   * @param world model for executing
   * @param view  view for display
   * @param param is ignored here
   * @throws IllegalArgumentException model or view is null
   */
  @Override
  public void execute(WorldModel world, WorldView view, Object param)
          throws IllegalArgumentException, IllegalStateException {
    if (world == null) {
      throw new IllegalArgumentException("world is null!");
    }
    if (view == null) {
      throw new IllegalArgumentException("view is null");
    }
    if (world.isGameOver()) {
      return;
    }
    if (world.getCurPlayerType() == PlayerType.AI) {
      view.closePickupItemPanel();
      return;
    }
    if (world.getCurPlayerItemNum() == world.getMaxItemNumCarried()) {
      view.showWarning("The player's item number has reached limit");
      view.closePickupItemPanel();
      return;
    }
    List<Integer> items = world.getRoomItems(world.getCurPlayerRoomIdx());
    view.showPickupItemPanel(items);
  }

  @Override
  public String toString() {
    return "PickupItemStep1Command";
  }
}
