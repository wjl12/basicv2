package com.sixtyfour.cbmnative;

import com.sixtyfour.cbmnative.mos6502.generators.Operand;

/**
 * @author EgonOlsen
 * 
 */
public class GeneratorContext {

	private Operand lastMoveTarget;
	private Operand lastMoveSource;

	public Operand getLastMoveTarget() {
		return lastMoveTarget;
	}

	public void setLastMoveTarget(Operand lastMoveTarget) {
		this.lastMoveTarget = lastMoveTarget;
	}

	public Operand getLastMoveSource() {
		return lastMoveSource;
	}

	public void setLastMoveSource(Operand lastMoveSource) {
		this.lastMoveSource = lastMoveSource;
	}
}