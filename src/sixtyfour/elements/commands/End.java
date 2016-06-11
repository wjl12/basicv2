package sixtyfour.elements.commands;

import sixtyfour.parser.ProgramCounter;
import sixtyfour.system.Machine;

public class End extends AbstractCommand {

	public End() {
		super("END");
	}

	@Override
	public String parse(String linePart, int lineCnt, int lineNumber, int linePos, Machine memory) {
		super.parse(linePart, lineCnt, lineNumber, linePos, memory);
		if (linePart.trim().length() > 3) {
			throw new RuntimeException("Syntax error: " + this);
		}
		return null;
	}

	@Override
	public ProgramCounter execute(Machine memory) {
		ProgramCounter pc = new ProgramCounter(this.lineCnt, this.linePos);
		pc.setStop(true);
		return pc;
	}

}
