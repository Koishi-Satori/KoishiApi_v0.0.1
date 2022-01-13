/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package top.kkoishi.parser;

import top.kkoishi.io.Files;
import top.kkoishi.parser.util.SyntaxTree;
import top.kkoishi.parser.util.SyntaxType;
import top.kkoishi.util.LinkedList;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * The parser static class.
 *
 * @author KKoishi_
 */
public class Parser extends SyntaxTree {
    /**
     * Prevent others invoke the constructor method and
     * get its instances for this is a static class.
     */
    private Parser () {
        super();
    }

    public static List<SyntaxTree> build (File srcFile) throws IOException {
        return build(Files.DefaultFiles.build().read(srcFile));
    }

    public static List<SyntaxTree> build (String commands) {
        return build(pre(commands));
    }

    /**
     * Actually handler of the build method.
     *
     * @param token token
     * @return syntax tree for higher syntax analysing.
     */
    public static SyntaxTree build (Token token) {
        SyntaxTree tree = SyntaxTree.getInstance();
        // variable : int x1 = 100;
        // an example of token : Token{type:PRE, content:'draw -line x1 y1 x2 y2;'}

    }

    public static List<SyntaxTree> build (List<Token> tokens) {
        List<SyntaxTree> syntaxTrees = new LinkedList<>();
        for (Token token : tokens) {
            syntaxTrees.add(build(token));
        }
        return syntaxTrees;
    }

    /**
     * Pre-handle the commands(remove all the annotates
     *
     * @param commands commands
     * @return a list of tokens
     */
    private static List<Token> pre (String commands) {
        Objects.requireNonNull(commands);
        // make scanner work normally.
        commands += " |";
        Scanner scanner = new Scanner(commands);
        if (!scanner.hasNext()) {
            return new LinkedList<>();
        }
        boolean startAnnotate = false;
        Token sToken = null;
        List<Token> tokens = new LinkedList<>();
        for (String token = scanner.next(); scanner.hasNext(); token = scanner.next()) {
            if (startAnnotate) {
                if (token.matches("[^#]+#$")) {
                    startAnnotate = false;
                    sToken.append(token.replaceFirst("#$", ""));
                    tokens.add(sToken);
                    sToken = null;
                    continue;
                } else if (token.matches("^[^#]#.+$")) {
                    sToken.append(token.split("#")[0]);
                    startAnnotate = false;
                    tokens.add(sToken);
                    sToken = null;
                    tokens.addAll(pre(token.replaceFirst("^[^#]#", "")));
                    continue;
                } else {
                    sToken.append(token);
                }
                tokens.add(sToken);
            }
            // if read annotates, invoke this part.
            // annotate format : #test annotate#
            // use regex to match, and when there exists other symbols after '#',
            // invoke this method itself again
            else if (token.matches("^#[^#]*#?$")) {
                if (token.matches("^#[^#]*#$")) {
                    // directly add to the linkedlist
                    tokens.add(new Token(SyntaxType.ANNOTATE,
                            token.replaceFirst("^#", "").replaceFirst("#$", "")));
                } else {
                    startAnnotate = true;
                    sToken = new Token(SyntaxType.ANNOTATE, token.replaceFirst("^#", ""));
                }
            }
            // invoke itself
            else if (token.matches("^#[^#]*#.+$")) {
                tokens.addAll(pre(token.split("#")[1] +
                        ' ' + token.replaceFirst("^#[^#]*#", "")));
            }
            // other situation : add token which its type is 'PRE'
            else {
                tokens.addAll(advancedHandle(token));
            }
        }
        return tokens;
    }

    /**
     * Separating different sentences.
     * Still consider how to implement.
     *
     * @param commands commands
     * @return a list of tokens
     * @apiNote piss of shit.
     * @see Parser#pre(String)
     */
    private static List<Token> advancedHandle (String commands) {


    }

    /**
     * The token of a sentence.
     */
    public static class Token {
        SyntaxType type;
        String content;

        public Token (SyntaxType type, String content) {
            this.type = type;
            this.content = content;
        }

        public SyntaxType getType () {
            return type;
        }

        public String getContent () {
            return content;
        }

        public void append (String content) {
            this.content += Objects.toString(content, " ");
        }

        @Override
        public String toString () {
            return "Token{" +
                    "type=" + type +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
