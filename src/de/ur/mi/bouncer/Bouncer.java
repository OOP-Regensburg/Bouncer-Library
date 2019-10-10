package de.ur.mi.bouncer;


import de.ur.mi.bouncer.world.BouncerChangedListener;
import de.ur.mi.bouncer.world.fields.FieldColor;
import de.ur.mi.bouncer.world.fields.Field;
import de.ur.mi.bouncer.world.fields.NullField;
import de.ur.mi.bouncer.world.World;

public class Bouncer {
	private Field currentField;
	private Direction currentOrientation;
	private BouncerChangedListener listener;

	public Bouncer() {
		currentField = new NullField();
		currentOrientation = Direction.EAST;
	}

	public void placeInWorld(World world) {
		this.placeAt(world.bouncerStartField());
		this.listener = listener;
	}

	public final void placeAt(Field field) {
		if (currentField == field) {
			return;
		}
		move(currentField, field);
	}

	public final void move() {
		Field nextField = currentField
				.tryToLeaveInDirection(currentOrientation);
		if (nextField == currentField) {
			return;
		}
		move(currentField, nextField);
	}
	
	private void move(Field from, Field to) {
		from.leftByBouncer();
		currentField = to;
		to.enteredByBouncer();
		notifyListener();
	}

	public final void turnLeft() {
		currentOrientation = currentOrientation.afterLeftTurn();
		notifyListener();
	}

	public final void paintField(FieldColor fieldColor) {
		currentField.paintWith(fieldColor);
		notifyListener();
	}

	public final boolean isOnFieldWithColor(FieldColor fieldColor) {
		return currentField.isPaintedWith(fieldColor);
	}

	public final void clearFieldColor() {
		currentField.clearColor();
		notifyListener();
	}

	public final boolean canMoveForward() {
		return currentField.isNeighbourInDirectionClear(currentOrientation);
	}

	public final boolean canMoveLeft() {
		return currentField.isNeighbourInDirectionClear(currentOrientation
				.afterLeftTurn());
	}

	public final boolean canMoveRight() {
		return currentField.isNeighbourInDirectionClear(currentOrientation
				.afterRightTurn());
	}

	public final boolean canNotMoveForward() {
		return !canMoveForward();
	}

	public final boolean canNotMoveLeft() {
		return !canMoveLeft();
	}

	public final boolean canNotMoveRight() {
		return !canMoveRight();
	}

	public final boolean isFacingWest() {
		return currentOrientation == Direction.WEST;
	}

	public final boolean isFacingEast() {
		return currentOrientation == Direction.EAST;
	}

	public final boolean isFacingNorth() {
		return currentOrientation == Direction.NORTH;
	}

	public final boolean isFacingSouth() {
		return currentOrientation == Direction.SOUTH;
	}

	public Direction currentOrientation() {
		return this.currentOrientation;
	}

	public void setBouncerListener(BouncerChangedListener listener) {
		this.listener = listener;
	}

	private void notifyListener() {
		if(listener != null) {
			listener.onBouncerChanged();
		}
	}


}
