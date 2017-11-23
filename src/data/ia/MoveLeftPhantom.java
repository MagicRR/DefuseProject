/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: data/ia/MoveLeftPhantom.java 2015-03-11 buixuan.
 * ******************************************************/
package data.ia;

import tools.Position;

import specifications.PhantomService;

public class MoveLeftPhantom implements PhantomService{
  private Position position;
  private PhantomService.MOVE move;

  public MoveLeftPhantom(Position p){ position=p; }

  @Override
  public Position getPosition() { return position; }

  @Override
  public PhantomService.MOVE getAction() { 
	if(move == null ) {
		return PhantomService.MOVE.LEFT;
	}
	  return move;

  }
  
  public void setAction(PhantomService.MOVE m) {
	  move = m;
  }


  @Override
  public void setPosition(Position p) { position=p; }
}
