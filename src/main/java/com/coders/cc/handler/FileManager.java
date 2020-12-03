package com.coders.cc.handler;

import com.coders.cc.util.Loggerg;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

public class FileManager {
    private final static Loggerg LOGGER = new Loggerg();
    private List<Branch> tree;
    private String path;

    public FileManager(String path) {
        this.path = path;
        LOGGER.log(Level.INFO, path + " injected as path");
        this.tree = new LinkedList<>();
    }

    public void traverse() {
        traverse(-1, path, 0);
//        showTree();
    }

    public void cp(String to, String... extensions) {
//        System.out.println("to path " + to + extensions);
        mkdir(to);
        for (Branch branch : tree) {
            for (int i = 0; i < extensions.length; i++) {
                if (branch.getExt().equals(extensions[i]) || extensions[i].equals("*")) {
                    if (!branch.isDirectory()) {
                        String baseToCopy = to + branch.getParentPath().replace(this.path, "");
                        mkdir(baseToCopy);
                        cp(branch.getPath(), baseToCopy + branch.getFileName());
                    }
                }
            }
        }
    }

    public static void cp(String from, String to) {
        try {
//            System.out.println(from + " " + to);
            Files.copy(new File(from), new File(to));
            System.err.println(from);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mkdir(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void traverse(int id, String path, int level) {
        try {
            File dir = new File(path);
            File[] files = dir.listFiles();
            for (File f : files) {
                Branch branch = new Branch(level, id, path, f.getPath(), f.getName());
                tree.add(branch);
                if (f.isDirectory()) {
                    branch.setDirectory(true);
                    traverse(branch.getId(), f.getPath(), level + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showTree() {
        for (Branch branch : tree) {
            String tabSize = "";
            for (int i = 0; i < branch.getLevel(); i++) {
                tabSize += "\t";
            }
            System.out.println(tabSize + branch.getFileName());
        }
    }
}