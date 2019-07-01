package com.hnb;

public class Monitor {

    public Monitor(FileParser fileParser) {
        final Menu main = new Menu(fileParser.getOptions());
        System.out.println("Welcome to the Zoo Monitoring System!");

        MenuSelection selection = null;
        Selection subselection = null;

        do {
            selection = main.displaySubMenu();
            if(selection.isValid()){
                do {
                    final Menu submenu = new Menu(selection.getSelection());
                    subselection = submenu.display();
                    if (subselection.isValid()) {
                        final Monitorable item = (Monitorable) subselection.getSelection();
                        System.out.println(item);
                        item.showAsterisk();
                    }
                } while (!subselection.isExit());
            }
        } while (!selection.isExit());
    }

    public static void main(String[] args) {
        new Monitor(new FileParser());
    }
}
