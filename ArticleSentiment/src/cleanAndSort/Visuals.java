package cleanAndSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
//CREATE PLACE TO TYPE IN SITE
public class Visuals {

    private HashMap<String, Long> emotions;
    
    public Visuals(HashMap<String, Long> emotions) {
    	this.emotions = emotions;
    }        
    
    public void run() {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout(1, false));

        // Create a label for the text region
        Label textLabel = new Label(shell, SWT.WRAP);
        GridData textLabelGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        textLabelGridData.verticalSpan = 2; // Increase the height by one row
        textLabel.setLayoutData(textLabelGridData);
        String unusedEmotionsString = getUnused(); 
        textLabel.setText("This text was summarized using eclipse. \n" + unusedEmotionsString);
        
        // Create a composite to hold the colored boxes
        final Composite composite = new Composite(shell, SWT.NONE);
        composite.setLayout(new FillLayout());
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        // Paint listener for drawing the colored boxes
        composite.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent event) {
                
            	GC gc = event.gc;
            	Rectangle compositeBounds = composite.getClientArea();
            	int x = compositeBounds.x + 10;
            	int y = compositeBounds.y + 10;
            	int i = 0;
            	for (Map.Entry<String, Long> entry : emotions.entrySet()) {
            	    String key = entry.getKey();
            	    Long value = entry.getValue();
            	    int width = compositeBounds.width - 20;
            	    int height = (int) (value * 6);
            	    //To fit in small values;
            	    if(height <= 12) {
            	    	height = 18;
            	    }

            	    //String boxColor = getBoxColor(key);
            	    //Color color = display.getSystemColor(getSWTColorConstant(boxColor));
                    Color color = getSWTColor(display, key);


            	    gc.setBackground(color);
            	    gc.fillRectangle(x, y, width, height);

            	    gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
            	    gc.drawRectangle(x, y, width, height);

            	    gc.drawText(value + "% " + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1), x + 5, y + 1);
            	    y += height;
            	    i++;
            	}
            }
        });
        shell.setSize(400, 725);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
    
    public String getUnused() {
    	//CHANGE NOT USED TO N/A if all words are used
    	String unusedOutput = "Not Used:"; 
    	// Remove unused emotions and add to notInText list to print at top of window.
        Iterator<Map.Entry<String, Long>> iterator = emotions.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Long> entry = iterator.next();
            if (entry.getValue() == 0) {
            	unusedOutput += " " + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
            	iterator.remove();
            }
        }
    	return unusedOutput;
    }
    
    public static Color getSWTColor(Display display, String emotionName) {
        switch (emotionName) {	
	        case "anger": return display.getSystemColor(SWT.COLOR_RED);              
	        	
	        case "anticipation": return display.getSystemColor(SWT.COLOR_CYAN);

	        case "disgust": return display.getSystemColor(SWT.COLOR_MAGENTA);
	        	
	        case "fear": return display.getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
	        	
	        case "joy": return display.getSystemColor(SWT.COLOR_YELLOW);
	        	
	        case "sadness": return display.getSystemColor(SWT.COLOR_BLUE);
	        	
	        case "suprise": return display.getSystemColor(SWT.COLOR_WHITE);
	        	
	        case "trust": return display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW);
	        	
	        case "negative": return display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW);
	        	
	        case "positive": return display.getSystemColor(SWT.COLOR_GREEN);
	        	
    	}
        return display.getSystemColor(SWT.COLOR_GREEN);
    }
    
}
