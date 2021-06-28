import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;

public class Convert {
    public static String fromPostfixToPrefix(String expression) throws IOException, SyntaxError {
        if (!expression.equals("")){
            List<String> expressionToArray = tokenizeExpression(expression);
            Stack<String> operandStack = new Stack<>();
            for (String token:expressionToArray) {
                if (!isOperator(token)){
                operandStack.push(token + " ");
                }else {
                try {
                    String operandTwo = operandStack.pop();
                    String operandOne = operandStack.pop();
                    String innerExpression = token + " " + operandOne + operandTwo;
                    operandStack.push(innerExpression);
                }catch (EmptyStackException ex){
                throw new SyntaxError("Trying to call pop on an empty stack! Please check expression.");
                }
                }
                }
                String result = operandStack.pop();
                if (operandStack.empty()){
                    return result;
                    }else { // else throw new exception
                    throw new SyntaxError("Stack isn't empty! Please check expression.");
                    }
                    } else { //will add empty string to stack without this
                    throw new SyntaxError("Please enter something!");
                    }
                    }
                    public static String fromPrefixToPostfix(String expression) throws SyntaxError, IOException {
                        if(!expression.equals("")){
                        List<String> expressionToArray = tokenizeExpression(expression);
                        Stack<String> operandStack = new Stack<>();
                        //reversal of the list
                        Collections.reverse(expressionToArray);
                        //loop through the expression array and first check for operands
                        for (String token:expressionToArray) {
                        if (!isOperator(token)){
                        operandStack.push(token + " ");
                        }else {
                        try {//replaced many if/else statements with a try/catch which simply throws the custom exception
                        String operandOne = operandStack.pop();
                        String operandTwo = operandStack.pop();
                        String innerExpression = operandOne + operandTwo + token + " ";
                        operandStack.push(innerExpression);
                        }catch (EmptyStackException ex){
                        throw new SyntaxError("Trying to call pop on an empty stack! Please check expression.");
                        }
                        }
                        }
                        String result = operandStack.pop();
                        
                        //check if stack is empty. If it is, return result

                        if (operandStack.empty()){
                        return result;
                        }else { // else throw new exception
                        throw new SyntaxError("Stack isn't empty! Please check expression.");
                        }
                        
                        }else { //will add empty string to stack without this
                        throw new SyntaxError("Please enter something!");
                        }
                        }

private static List<String> tokenizeExpression(String expression) throws IOException {
StreamTokenizer tokenizeExpression = new StreamTokenizer(new StringReader(expression));

//treat the following as normal chars

tokenizeExpression.ordinaryChar('-');
tokenizeExpression.ordinaryChar('/');
List<String> tokenList = new ArrayList<>();

// match tokens until end of stream

while (tokenizeExpression.nextToken() != StreamTokenizer.TT_EOF){

if (tokenizeExpression.ttype == StreamTokenizer.TT_NUMBER){
tokenList.add(String.valueOf((int)tokenizeExpression.nval));


}else if(tokenizeExpression.ttype == StreamTokenizer.TT_WORD){
tokenList.add(tokenizeExpression.sval);
}else{ //operator
tokenList.add(String.valueOf((char) tokenizeExpression.ttype));
}
}
return tokenList;
}

