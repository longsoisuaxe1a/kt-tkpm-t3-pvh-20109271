package vn.edu.iuh.fit.examples;

import java.io.File;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.javadoc.Javadoc;
import com.google.common.base.Strings;

public class CommonOperations {
	public static void listMethodCalls(File projectDir) {
		new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			System.out.println(path);
			System.out.println(Strings.repeat("=", path.length()));
			try {
				new VoidVisitorAdapter<Object>() {

					@Override
					public void visit(JavadocComment n, Object arg) {
						super.visit(n, arg);
						checkValidComment(n);
					}

					private void checkValidComment(JavadocComment n) {
						if (!n.isBlockComment()) {
							System.out.println("\t********** not valid comment");
						} else {
							System.out.println("[" + n.getBegin() + n.getContent() + "]" + n.getEnd());
						}
					}
					
//					@Override
//					public void visit(PackageDeclaration n, Object arg) {
//						super.visit(n, arg);
//						System.out.println(n.getNameAsString());
//					}

					@Override
					public void visit(FieldDeclaration n, Object arg) {
						super.visit(n, arg);
//						System.out.println(n.getChildNodes().get(0));
//						----
						List<Node> nodes = n.getChildNodes();
						nodes.forEach(t -> System.out.println(t));
//						---
//						NodeList<VariableDeclarator> vars = n.getVariables();
//						vars.forEach(f->{
//							char c = f.getNameAsString().charAt(0);
//							if(!(c >= 'a' && c <= 'z')) {
//								System.out.println("invalid field name" + f);
//							}
//						});
					}
					
//
//					@Override
//					public void visit(MethodDeclaration n, Object arg) {
//						super.visit(n, arg);
//						System.out.println(" [L " + n.getBegin() + "] " + n.getDeclarationAsString());
//					}
				}.visit(StaticJavaParser.parse(file), null);
			} catch (Exception e) {
				new RuntimeException(e);
			}
		}).explore(projectDir);
	}

	public static void main(String[] args) {
		File projectDir = new File("D:\\X");
		listMethodCalls(projectDir);
	}
}
