package vv.tp3;

import net.sourceforge.pmd.lang.java.ast.ASTForStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

/**
 * Created by yassine & quentin.
 */
public class TwoNestedForLoopsRule extends AbstractJavaRule {

    public Object visit(ASTForStatement node, Object data){
        if (node.jjtGetNumChildren() != 0 && node.hasDescendantOfType(ASTForStatement.class)) {
            addViolation(data, node);
        }
        return super.visit(node,data);
    }
}
