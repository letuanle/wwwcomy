package com.iteye.wwwcomy.compiler;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class TestCompiler {

	public static void main(String[] args) throws Exception {
		String fileString = "package test.compiler;"
				+ "\n"
				+ "public class HelloWorld{"
				+ "\n"
				+ "\tpublic static void main(String[] args) {"
				+ "\n"
				+ "\t\tSystem.out.println(\"HelloWorld\");"
				+ "\n"
				+ "}"
				+ "public void test(){System.out.println(\"HelloWorld1Test\");}}";
		String fileName = System.getProperty("user.dir");
		fileName += "/src/test/compiler/HelloWorld.java";
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write(fileString);
		fw.flush();
		fw.close();

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null,
				null, null);
		Iterable i = fileMgr.getJavaFileObjects(fileName);
		CompilationTask task = compiler.getTask(null, fileMgr, null, null,
				null, i);
		task.call();

		String u = ("file:/" + System.getProperty("user.dir") + "/src/")
				.replaceAll("\\\\", "/");
		URL[] url = new URL[] { new URL(u) };
		URLClassLoader uc = new URLClassLoader(url);
		Class c = uc.loadClass("test.compiler.HelloWorld");
		System.out.println(c);
		Method m = c.getDeclaredMethod("test", null);
		m.invoke(c.newInstance(), null);
	}
}
