package sixtyfour.elements.functions;

import sixtyfour.elements.Type;
import sixtyfour.system.Machine;

public class Int extends AbstractFunction {

	public Int() {
		super("INT");
	}

	@Override
	public Type getType() {
		return Type.INTEGER;
	}

	@Override
	public Object eval(Machine memory) {
		if (term.getType().equals(Type.STRING)) {
			throw new RuntimeException("Type mismatch error: " + term.getType());
		}
		return ((Number) term.eval(memory)).intValue();
	}

}
