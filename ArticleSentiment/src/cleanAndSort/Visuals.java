package cleanAndSort;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Visuals {
	public Visuals() {
		
	}
	
	public void run() {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("My Window");
        shell.setSize(400, 300);
        
        shell.open();
        
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        
        display.dispose();
    }	
}
