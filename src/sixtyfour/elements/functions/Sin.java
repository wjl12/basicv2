package sixtyfour.elements.functions;

import sixtyfour.elements.Type;
import sixtyfour.system.Machine;

public class Sin extends AbstractFunction {

	public Sin() {
		super("SIN");
	}

	@Override
	public Type getType() {
		return Type.REAL;
	}

	@Override
	public Object eval(Machine memory) {
		if (!term.getType().equals(Type.STRING)) {
			return Float.valueOf((float) Math.sin(((Number) term.eval(memory)).floatValue()));
		}
		throw new RuntimeException("Type mismatch error: " + term.getType());
	}

}
