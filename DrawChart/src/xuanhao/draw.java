package xuanhao;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class draw extends ApplicationFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436297668253931745L;
	/**
	 * @param args
	 */
	public draw(String title) {
		super(title);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.addValue(10, "Negative", "22/11/2012");
		dataset.addValue(50, "Negative", "Column 2");
		dataset.addValue(30, "Negative", "Column 3");
		dataset.addValue(20, "Positive", "22/11/2012");
		dataset.addValue(30, "Positive", "Column 2");
		dataset.addValue(20, "Positive", "Column 3");
		dataset.addValue(12, "Negative", "Column 4");
		dataset.addValue(50, "Negative", "Column 5");
		dataset.addValue(30, "Negative", "Column 6");
		dataset.addValue(20, "Positive", "Column 4");
		dataset.addValue(30, "Positive", "Column 5");
		dataset.addValue(20, "Positive", "Column 6");
		dataset.addValue(10, "Negative", "Column 7");
		dataset.addValue(50, "Negative", "Column 8");
		dataset.addValue(30, "Negative", "Column 9");
		dataset.addValue(20, "Positive", "Column 7");
		dataset.addValue(30, "Positive", "Column 8");
		dataset.addValue(20, "Positive", "Column 9");
		
		
		JFreeChart chart = ChartFactory.createBarChart(
		"Bar Chart Demo", // chart title
		"Thời gian", // domain axis label
		"Chỉ số", // range axis label
		dataset, // data
		PlotOrientation.VERTICAL, // orientation
		true, // include legend
		true, // tooltips?
		false // URLs?
		);
		
		chart.setBackgroundPaint(Color.white);
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.blue);
		
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, Color.CYAN);
		renderer.setSeriesPaint(1, Color.green);
		renderer.setDrawBarOutline(false);
		
		renderer.setItemMargin(0.0);//khoang cach 2 cot
		
		ChartPanel chartPanel = new ChartPanel(chart, false);
		chartPanel.setPreferredSize(new Dimension(1000, 600));//man hinh
		setContentPane(chartPanel);
		
		chart.setBorderVisible(true);// duong vien
		chart.setBorderPaint(Color.magenta);
		BasicStroke s = new BasicStroke(2.0f);
		chart.setBorderStroke(s);
		
		chart.setTitle("A Chart Title"); // tieu de
		//chart.getTitle().setPosition(RectangleEdge.LEFT);// noi dat tieu de
		
		TextTitle subtitle1 = new TextTitle("A Subtitle");
		chart.addSubtitle(subtitle1);
		
		// turn on antialiasing...
		chart.setAntiAlias(true);
		
		StandardCategoryItemLabelGenerator labelGen = new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("0"));
	    renderer.setBaseItemLabelGenerator(labelGen);
	    renderer.setBaseItemLabelsVisible(true);
		
		try {
			ChartUtilities.saveChartAsJPEG(new File("src/chart.jpg"), chart, 1000, 600);
			} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
			}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create a dataset...

		draw demo = new draw(" ");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
		
	}
}
