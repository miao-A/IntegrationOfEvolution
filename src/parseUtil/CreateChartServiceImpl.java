package parseUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.DataUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;  
import java.awt.Font;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.text.DecimalFormat;  
import java.text.NumberFormat;  
  
import java.util.Vector;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartUtilities;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.CategoryAxis;  
import org.jfree.chart.axis.CategoryLabelPositions;  
import org.jfree.chart.axis.NumberAxis;  
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;  
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PiePlot3D;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.renderer.category.BarRenderer;  
import org.jfree.chart.renderer.category.LineAndShapeRenderer;  
import org.jfree.chart.renderer.category.StackedBarRenderer;  
import org.jfree.chart.title.TextTitle;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.general.DatasetUtilities;  
import org.jfree.data.general.DefaultPieDataset;  
import org.jfree.data.general.PieDataset; 


public class CreateChartServiceImpl {
	
	private static final String CHART_PATH = "D:/test/"; 
	private String[] rowKeys;
	private String[] columnKeys;
	

	public CreateChartServiceImpl(String[] rowkeys,String[] columKeys,Vector<InfoOfExtensibility> vec){

        String[] rowKeys = {"����", "����"};  
        String[] columnKeys = {"D:/ProjectEOfHW/heritrix0.2.0","D:/ProjectEOfHW/heritrix0.4.0",
				"D:/ProjectEOfHW/heritrix0.6.0","D:/ProjectEOfHW/heritrix0.8.0",
				"D:/ProjectEOfHW/heritrix1.0.0","D:/ProjectEOfHW/heritrix1.10.0",
				"D:/ProjectEOfHW/heritrix1.12.0","D:/ProjectEOfHW/heritrix1.14.4",
				"D:/ProjectEOfHW/heritrix-3.0.0","D:/ProjectEOfHW/heritrix-3.1.0"};  
        
		double[][] data = new double[2][vec.size()];
		for (int i = 0; i < vec.size(); i++) {
			data[0][i]=vec.get(i).getThird();
		}
		
		for (int i = 0; i < vec.size(); i++) {
			data[1][i]=8;
		}
		
        CategoryDataset dataset = getBarData(data, rowKeys, columnKeys);  
        createTimeXYChar("����ͼ", "x��", "y��", dataset, "lineAndShap.png");  
		
	}
	 /** 
     * ��������ͼ 
     */  
 /*   public void makeLineAndShapeChart() {  
        double[][] data = new double[][]{  
            {672, 766, 223, 540, 126},  
            {325, 521, 210, 340, 106},  
            {332, 256, 523, 240, 526}  
        };  
        String[] rowKeys = {"abc", "����", "����"};  
        String[] columnKeys = {"����", "�Ϻ�", "����", "�ɶ�", "����"};  
        CategoryDataset dataset = getBarData(data, rowKeys, columnKeys);  
        createTimeXYChar("����ͼ", "x��", "y��", dataset, "lineAndShap.png");  
    }  
  */
	
	 public CategoryDataset getBarData(double[][] data, String[] rowKeys,  
	            String[] columnKeys) { 

	        return DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);  
	  
	    }  
	 
	 /** 
	     * �ж��ļ����Ƿ���ڣ�������������½� 
	     * @param chartPath 
	     */  
	    private void isChartPathExist(String chartPath) {  
	        File file = new File(chartPath);  
	        if (!file.exists()) {  
	            file.mkdirs();  
	        // log.info("CHART_PATH="+CHART_PATH+"create.");  
	        }  
	    }  
	
	 /** 
     * ����ͼ 
     *  
     * @param chartTitle 
     * @param x 
     * @param y 
     * @param xyDataset 
     * @param charName 
     * @return 
     */  
    public String createTimeXYChar(String chartTitle, String x, String y,  
            CategoryDataset xyDataset, String charName) {  
  
        JFreeChart chart = ChartFactory.createLineChart(chartTitle, x, y,  
                xyDataset, PlotOrientation.VERTICAL, true, true, false);  
  
        chart.setTextAntiAlias(false);  
        chart.setBackgroundPaint(Color.WHITE);  
        // ����ͼ�����������������title  
        Font font = new Font("����", Font.BOLD, 25);  
        TextTitle title = new TextTitle(chartTitle);  
        title.setFont(font);  
        chart.setTitle(title);  
        // �����������  
        Font labelFont = new Font("����", Font.TRUETYPE_FONT, 12);  
  
        
        chart.setBackgroundPaint(Color.WHITE);  
  
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();  
        // x�� // �����������Ƿ�ɼ�  
        categoryplot.setDomainGridlinesVisible(true);  
        // y�� //�����������Ƿ�ɼ�  
        categoryplot.setRangeGridlinesVisible(true);  
  
        categoryplot.setRangeGridlinePaint(Color.WHITE);// ����ɫ��  
  
        categoryplot.setDomainGridlinePaint(Color.WHITE);// ����ɫ��  
  
        categoryplot.setBackgroundPaint(Color.lightGray);  
  
        // ����������֮��ľ���  
        // categoryplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));  
  
        CategoryAxis domainAxis = categoryplot.getDomainAxis();  
  
        domainAxis.setLabelFont(labelFont);// �����  
  
        domainAxis.setTickLabelFont(labelFont);// ����ֵ  
        
        
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // �����ϵ�  
        // Lable  
        // 45����б  
        // ���þ���ͼƬ��˾���  

        domainAxis.setLowerMargin(0.0);  
        // ���þ���ͼƬ�Ҷ˾���  
        domainAxis.setUpperMargin(0.0);  
  
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
  //      numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());  //������ʾ

        numberaxis.setAutoRangeIncludesZero(false);
  
     
        // ���renderer ע���������������͵�lineandshaperenderer����  
        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();  
  
        lineandshaperenderer.setBaseShapesVisible(true); // series �㣨�����ݵ㣩�ɼ�  
  
        lineandshaperenderer.setBaseLinesVisible(true); // series �㣨�����ݵ㣩�������߿ɼ�  
  
        // ��ʾ�۵�����  
        // lineandshaperenderer.setBaseItemLabelGenerator(new  
        // StandardCategoryItemLabelGenerator());  
        // lineandshaperenderer.setBaseItemLabelsVisible(true);  
  
        FileOutputStream fos_jpg = null;  
        try {  
            isChartPathExist(CHART_PATH);  
            String chartName = CHART_PATH + charName;  
            fos_jpg = new FileOutputStream(chartName);  
  
            // ��������Ϊpng�ļ�  
            ChartUtilities.writeChartAsPNG(fos_jpg, chart, 500, 510);  
  
            return chartName;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        } finally {  
            try {  
                fos_jpg.close();  
                System.out.println("create time-createTimeXYChar.");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	
}
