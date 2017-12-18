package com.sixtyfour.cbmnative.mos6502.generators;

import java.util.List;
import java.util.Map;

import com.sixtyfour.Logger;
import com.sixtyfour.elements.Type;


public class Mov
  implements Generator
{

  @Override
  public String getMnemonic()
  {
    return "MOV";
  }


  @Override
  public void generateCode(String line, List<String> nCode, List<String> subCode, Map<String, String> name2label)
  {
    Operands ops = new Operands(line, name2label);
    Logger.log(line + " -- " + ops.getTarget() + "  |||  " + ops.getSource());

    Operand source = ops.getSource();
    Operand target = ops.getTarget();

    if (!source.isIndexed() && !target.isIndexed())
    {
      if (source.getType() == Type.INTEGER)
      {
        // Source is INTEGER

        if (source.isRegister())
        {
          nCode.add("LDA " + source.getRegisterName());
          nCode.add("LDY " + source.getRegisterName()+"+1");
        }
        else
        {
          nCode.add("LDA " + source.getAddress());
          nCode.add("LDY " + source.getAddress()+"+1");
        }

        if (target.getType() == Type.INTEGER)
        {
          if (target.isRegister())
          {
            nCode.add("STA " + target.getRegisterName());
            nCode.add("STY " + target.getRegisterName()+"+1");
          }
          else
          {
            nCode.add("STA " + target.getAddress());
            nCode.add("STY " + target.getAddress()+"+1");
          }
        }
        else
        {
          nCode.add("; integer in A/Y to FAC");
          nCode.add("JSR $B391"); // integer in A/Y to FAC

          if (target.isRegister())
          {
            nCode.add("LDX #<" + target.getRegisterName());
            nCode.add("LDY #>" + target.getRegisterName());
          }
          else
          {
            nCode.add("LDX #<" + target.getAddress());
            nCode.add("LDY #>" + target.getAddress());
          }
          nCode.add("; FAC to (X/Y)");
          nCode.add("JSR $BBD7"); // FAC to (X/Y)
        }
      }
      else
      {
        // Source is REAL

        if (source.isRegister())
        {
          nCode.add("LDA #<" + source.getRegisterName());
          nCode.add("LDY #>" + source.getRegisterName());
        }
        else
        {
          nCode.add("LDA #<" + source.getAddress());
          nCode.add("LDY #>" + source.getAddress());
        }
        nCode.add("; Real in (A/Y) to FAC");
        nCode.add("JSR $BBA2"); // Real in (A/Y) to FAC

        if (target.getType() == Type.INTEGER)
        {
          nCode.add("; FAC to integer in A/Y");
          nCode.add("JSR $B1AA"); // FAC to integer in A/Y

          if (target.isRegister())
          {
            nCode.add("STA " + target.getRegisterName());
            nCode.add("STY " + target.getRegisterName()+"+1");
          }
          else
          {
            nCode.add("STA " + target.getAddress());
            nCode.add("STY " + target.getAddress()+"+1");
          }
        }
        else
        {
          if (target.isRegister())
          {
            nCode.add("LDX #<" + target.getRegisterName());
            nCode.add("LDY #>" + target.getRegisterName()+"+1");
          }
          else
          {
            nCode.add("LDX #<" + target.getAddress());
            nCode.add("LDY #>" + target.getAddress()+"+1");
          }

          nCode.add("; FAC to (X/Y)");
          nCode.add("JSR $BBD7"); // FAC to (X/Y)
        }
      }
    }
    else
    {
      // Not supported yet
    }

  }

}