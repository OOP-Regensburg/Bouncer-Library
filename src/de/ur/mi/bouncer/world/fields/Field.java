package de.ur.mi.bouncer.world.fields;
import de.ur.mi.bouncer.Direction;

public class Field {
	private final Surroundings surroundings;
	private FieldContent content;
	private FieldColor fieldColor;
	
	public Field(FieldContent content, FieldColor fieldColor,
			Surroundings surroundings) {
		this.content = content;
		this.fieldColor = fieldColor;
		this.surroundings = surroundings;
	}
	
	public void setNeighbourInDirection(Field field, Direction direction) {
		surroundings.setNeighbourInDirection(field, direction);
	}

	public boolean isNeighbourInDirectionClear(Direction direction) {
		Field fieldInDirection = surroundings.neighbourInDirection(direction);
		if (fieldInDirection == null) {
			return false;
		}
		return fieldInDirection.isClear();
	}

	public Field tryToLeaveInDirection(Direction direction) {
		if (!canBeLeftInDirection(direction)) {
			return this;
		}
		Field nextField = surroundings.neighbourInDirection(direction);
		return nextField;
	}

	private boolean canBeLeftInDirection(Direction direction) {
		Field neighbour = surroundings.neighbourInDirection(direction);
		return (neighbour != null && neighbour.isClear());
	}
	
	public void leftByBouncer() {
		content = FieldContent.FREE;
	}

	public void enteredByBouncer() {
		content = FieldContent.BOUNCER;
	}

	public void paintWith(FieldColor fieldColor) {
		this.fieldColor = fieldColor;
	}

	public boolean isPaintedWith(FieldColor fieldColor) {
		return this.fieldColor.equals(fieldColor);
	}

	public boolean isClear() {
		return content != FieldContent.OBSTACLE;
	}

	public boolean isBlocked() {
		return !isClear();
	}

	public void clearColor() {
		this.fieldColor = FieldColor.WHITE;
	}
	
	public void setContent(FieldContent content) {
		this.content = content;
	}

	public FieldColor color() {
		return this.fieldColor;
	}

	public boolean hasBouncer() {
		return content == FieldContent.BOUNCER;
	}
	
	public boolean isNeighbourTo(Field field) {
		return surroundings.haveField(field);
	}

}
