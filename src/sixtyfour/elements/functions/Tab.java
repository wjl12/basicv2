package sixtyfour.elements.functions;

import sixtyfour.parser.Type;
import sixtyfour.system.Machine;


public class Tab
  extends Spc
{

  public Tab()
  {
    super("TAB");
  }


  @Override
  public Object eval(Machine memory)
  {
    ensureContext(memory);
    if (term.getType().equals(Type.STRING))
    {
      throw new RuntimeException("Type mismatch error: " + this);
    }
    int num = ((Number) term.eval(memory)).intValue() - memory.getOutputChannel().getCursor();
    if (num < 0 || num > 255)
    {
      throw new RuntimeException("Illegal quantity error: " + this);
    }
    return SPACES.substring(0, num);
  }

}
