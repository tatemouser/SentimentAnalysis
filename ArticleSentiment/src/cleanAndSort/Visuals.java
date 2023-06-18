package cleanAndSort;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Visuals {
    private HashMap<String, Long> emotions;
    private Display display;
    private Shell shell;

    public String findLink() {
        display = new Display();
        shell = new Shell(display);
        shell.setLayout(new GridLayout(1, false));

        Composite inputComposite = new Composite(shell, SWT.NONE);
        inputComposite.setLayout(new GridLayout(3, false));
        inputComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        inputComposite.setBackground(backgroundColor());

        Rectangle screenSize = display.getPrimaryMonitor().getBounds();
        shell.setLocation(screenSize.x, screenSize.y);
        shell.setBackground(new Color(67, 70, 75));

        Label enterSiteLabel = new Label(inputComposite, SWT.NONE);
        enterSiteLabel.setText("Enter site:");
        enterSiteLabel.setBackground(backgroundColor());
        enterSiteLabel.setForeground(textColor());

        Text linkText = new Text(inputComposite, SWT.NONE);
        linkText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        linkText.setForeground(textColor());
        linkText.setBackground(new Color(67, 70, 75));

        Button enterButton = new Button(inputComposite, SWT.PUSH);
        enterButton.setText("Enter");

        final String[] link = new String[1];
        enterButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                link[0] = linkText.getText();
                shell.dispose();
            }
        });
        
        shell.pack();
        shell.setSize(400, 725);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();

        return link[0];
    }

    public void createVisuals(HashMap<String, Long> emotions) {
        display = new Display();
        shell = new Shell(display);
        shell.setLayout(new GridLayout(1, false));
        Rectangle screenSize = display.getPrimaryMonitor().getBounds();
        shell.setLocation(screenSize.x, screenSize.y);
        shell.setBackground(backgroundColor());
        this.emotions = emotions;

        Label textLabel = new Label(shell, SWT.WRAP);
        GridData textLabelGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        textLabelGridData.verticalSpan = 2;
        textLabel.setLayoutData(textLabelGridData);
        String unusedEmotionsString = getUnused();
        textLabel.setText("This text was summarized using Version 1.0.0 \n" + unusedEmotionsString);
        textLabel.setBackground(backgroundColor());
        textLabel.setForeground(textColor());

        Composite composite = new Composite(shell, SWT.NONE);
        composite.setLayout(new FillLayout());
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        composite.setBackground(backgroundColor());
        composite.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent event) {
                GC gc = event.gc;
                Rectangle compositeBounds = composite.getClientArea();
                int x = compositeBounds.x;
                int y = compositeBounds.y;

                int i = 0;
                for (Map.Entry<String, Long> entry : emotions.entrySet()) {
                    String key = entry.getKey();
                    Long value = entry.getValue();
                    int width = compositeBounds.width;
                    int height = (int) (value * 6);
                    if (height <= 12) {
                        height = 18;
                    }

                    Color color = getSWTColor(display, key);

                    gc.setBackground(color);
                    gc.fillRectangle(x, y, width, height);

                    gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
                    gc.drawRectangle(x, y, width, height);

                    gc.drawText(value + "% " + Character.toUpperCase(key.charAt(0)) + key.substring(1), x + 5, y + 1);
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
        String unusedOutput = "Not Used:";
        Iterator<Map.Entry<String, Long>> iterator = emotions.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Long> entry = iterator.next();
            if (entry.getValue() == 0) {
                unusedOutput += " " + Character.toUpperCase(entry.getKey().charAt(0)) + entry.getKey().substring(1);
                iterator.remove();
            }
        }
        if(unusedOutput.length() == 9) unusedOutput = "All emotions were used in this text.";
        return unusedOutput;
    }

    public static Color getSWTColor(Display display, String emotionName) {
        switch (emotionName) {
            case "anger":
                return new Color(191, 82, 80);

            case "anticipation":
                return new Color(233, 236, 107);

            case "disgust":
                return new Color(113, 169, 44);

            case "fear":
                return new Color(207, 207, 196);

            case "joy":
                return new Color(248, 250, 112);

            case "sadness":
                return new Color(139, 211, 230);

            case "surprise":
                return new Color(250, 248, 246);

            case "trust":
                return new Color(239, 190, 125);

            case "negative":
                return new Color(255, 109, 106);

            case "positive":
                return new Color(119, 221, 119);
        }
        return display.getSystemColor(SWT.COLOR_GREEN);
    }

    public Color backgroundColor() {
        return new Color(45, 48, 51);
    }

    public Color textColor() {
        return new Color(250, 249, 246);
    }
}
