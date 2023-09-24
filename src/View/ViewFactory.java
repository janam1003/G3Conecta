package view;

import java.util.ResourceBundle;


public class ViewFactory {

	public static View getView() {
		View view = null;
		ResourceBundle config = ResourceBundle.getBundle("view.config");

		String viewType = config.getString("VIEW");
		if (viewType.equals("TERMINAL"))
			view = new ViewTerminal();
        return view;
    }
}
