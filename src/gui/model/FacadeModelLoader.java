package gui.model;

import java.sql.SQLException;

public class FacadeModelLoader {

    private static FacadeModelLoader instance = null;
    private static FacadeModel facadeModel = null;

    private FacadeModelLoader() {
        // private constructor to prevent external instantiation
    }

    public static synchronized FacadeModelLoader getInstance() {
        if (instance == null) {
            instance = new FacadeModelLoader();
        }
        return instance;
    }

    public synchronized FacadeModel getFacadeModel() {
        if (facadeModel == null) {
            Thread thread = new Thread(() -> {
                try {
                    facadeModel = new FacadeModel();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            try {
                thread.join(); // wait for the thread to finish
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return facadeModel;
    }
}