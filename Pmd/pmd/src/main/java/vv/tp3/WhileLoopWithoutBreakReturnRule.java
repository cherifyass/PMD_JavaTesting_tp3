package vv.tp3;

import net.sourceforge.pmd.lang.java.ast.*;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * Created by yassine & quentin.
 */
public class WhileLoopWithoutBreakReturnRule extends AbstractJavaRule {

    public Object visit(ASTWhileStatement node, Object data) {
        if(node.jjtGetNumChildren()>0) {
            // while > Expression > Primary >un seul PremaryPrefix
            int count = node.jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren();
            boolean cond = false;
            boolean hasntReturnOrBreak = true;
            // while > Expression > Primary > PrimaryPrefix > Literal > BooleanLiteral
            if (node.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).hasDescendantOfType(ASTBooleanLiteral.class))
                cond = true;

            // Expression has descendant of type return or break ?
            if (node.hasDescendantOfType(ASTReturnStatement.class) ||
                    node.hasDescendantOfType(ASTBreakStatement.class) )
                hasntReturnOrBreak = false;

            // checking conditions
            if(cond && count == 1 && hasntReturnOrBreak) {
                addViolation(data, node);
            }
        }
        return super.visit(node,data);
    }

    /**
     * THIS SHOULD NEVER BE CALLED !! its just for test purposes !
     */
    public void infiteWhileLoopTest() {
        int i=0;
        while(true) {
            i++;
        }
    }
}