package vv.tp3;

import net.sourceforge.pmd.lang.java.ast.*;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import java.util.List;

/**
 * Created by yassine & quentin.
 */
public class WhileTrueFalseLoopsRule extends AbstractJavaRule {

    public Object visit(ASTWhileStatement node, Object data) {
        if(node.jjtGetNumChildren()>0) {
            // while > Expression > Primary >un seul PremaryPrefix
            int cmpt = node.jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren();
            boolean cond = false;
            // while > Expression > Primary > PrimaryPrefix > Literal > BooleanLiteral
            if (node.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).hasDescendantOfType(ASTBooleanLiteral.class))
                 cond = true;

            if(cond && cmpt ==1) {
                addViolation(data, node);
            }
        }
        return super.visit(node,data);
    }
}
