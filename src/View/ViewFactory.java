package view;

import java.util.ResourceBundle;

/**
 * The ViewFactory class is responsible for creating instances of the view
 * interface based on configuration settings. It provides a method to obtain an
 * instance of a view implementation based on the specified view type.
 *
 * The view type is determined by the configuration settings, allowing the
 * application to use different types of views (terminal-based, graphical) based
 * on configuration.
 *
 * @author 2dam
 */
public class ViewFactory {

    /**
     * Creates and returns an instance of a view implementation based on the
     * specified view type specified in the configuration.
     *
     * @return An instance of a view implementation.
     */
    public static View getView() {
        View view = null;
        ResourceBundle config = ResourceBundle.getBundle("view.config");

        String viewType = config.getString("VIEW");
        if (viewType.equals("TERMINAL")) {
            view = new ViewTerminal();
        }
        return view;
    }
}
