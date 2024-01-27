package vn.edu.iuh.fit.examples;

import java.io.File;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

public class Demo_Tool {
	public static void main(String[] args) throws Throwable {
		File file = new File("D:\\SpringBoot\\PhamVanHau_Demo_Tool_JavaParser\\src\\main\\java\\vn\\edu\\iuh\\fit\\examples\\DirExplorer.java");
//		JavaParser parser = new JavaParser();
//		parser.parse(file);
		CompilationUnit unit = StaticJavaParser.parse(file);
		List<MethodDeclaration> methodDeclarations = unit.findAll(MethodDeclaration.class);
		for (MethodDeclaration m : methodDeclarations) {
			System.out.println(m.getNameAsString());
		}
	}
//	void explore(File root) {
//		
//	}
}
