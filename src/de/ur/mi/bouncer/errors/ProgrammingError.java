package de.ur.mi.bouncer.errors;
public class ProgrammingError extends RuntimeException {
	private static final long serialVersionUID = -7957978874872752226L;

	public ProgrammingError(String message) {
		super(message);
	}
}
