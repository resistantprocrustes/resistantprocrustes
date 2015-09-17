package com.rps.app;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.LookAndFeel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

import com.procrustes.Utils.Commons;
import com.procrustes.dataContainer.ProcrustesResult;
import com.rps.app.analysis.AnalysisConfiguration;
import com.rps.app.analysis.DatasetConfiguration;
import com.rps.app.analysis.DialogConfiguration;
import com.rps.app.analysis.DistanceCalculatorAdapter;
import com.rps.app.analysis.DistanceConfiguration;
import com.rps.app.analysis.ProcrustesCalculatorAdapter;
import com.rps.app.analysis.ProjectionCalculatorAdapter;
import com.rps.app.analysis.ProjectionConfiguration;
import com.rps.app.analysis.thread.ProcrustesAnalisysWorker;
import com.rps.app.dialogs.AboutDialog;
import com.rps.app.dialogs.AddObjectDialog;
import com.rps.app.dialogs.EditElement3DDialog;
import com.rps.app.dialogs.ExportObjectDialog;
import com.rps.app.dialogs.HelpDialog;
import com.rps.app.elements.Element3D;
import com.rps.app.elements.Element3DCollection;
import com.rps.app.elements.Element3DDataSet;
import com.rps.app.elements.Element3DEntity;
import com.rps.app.elements.Element3DFactory;
import com.rps.app.elements.Element3DProjection;
import com.rps.app.elements.Element3DWireframe;
import com.rps.app.elements.actions.ShowSimpleElementAction;
import com.rps.app.elements.actions.SimpleElementAction;
import com.rps.app.elements.simpleelements.ComposeSimpleElement;
import com.rps.app.elements.simpleelements.ProjectSimpleElement;
import com.rps.app.elements.simpleelements.SampleSimpleElement;
import com.rps.app.elements.simpleelements.SimpleElement;
import com.rps.app.export.ExportConfiguration;
import com.rps.app.export.ExporterFactory;
import com.rps.app.export.IExporter;
import com.rps.app.fileload.FileLoader;
import com.rps.app.fileload.LoaderFactory;
import com.rps.app.panels.ColorIcon;
import com.rps.app.panels.GraphicsPane;
import com.rps.app.panels.ReportPnl;
import com.rps.app.panels.StatusBarPanel;
import com.rps.app.reports.DatasetDetails;
import com.rps.app.reports.DistancesDetailer;
import com.rps.app.reports.ProcrustesFitDetalier;
import com.rps.app.reports.ProjectionDetailer;
import com.rps.app.reports.ReportGenerator;
import com.rps.app.resources.Icons;
import com.rps.app.resources.Messages;
import com.rps.engine3d.Camera3D;
import com.rps.engine3d.Scene3D;
import com.rps.geometry3d.Clip;
//
//import com.rps.log.ReportWindowHandler;
import com.rps.renderer.Canvas3D;
import com.rps.renderer.InteractionHandler;
import com.rps.renderer.Renderer;
import com.rps.utils.ColorUtils;
//import com.example.loaders.PCEntity;
//import com.example.utils.CommonUtils;
//import com.procrustes.Utils.Commons;
//import com.procrustes.dataContainer.ProcrustesResult;

public class CopyOfGui extends JFrame implements ActionListener,  MouseListener, ChangeListener{
	
	/** The app version */
	public static final String VERSION = "1.0.0";
	
	/** this tabsPanel is the main tabs manager */
	private JTabbedPane centerTabPnl;
	
	/** the report panl*/
	private JPanel reportPanel;
	
	private ProcrustesResult result;
	
	/** The main menu bar */
	private JMenuBar barMenu;
	
	/** The file menu */
	private JMenu mnuFile;
	
	/** The File_New menu option */
	private JMenuItem mnuNew;
	
	/** The File_Open menu option */
	private JMenuItem mnuOpen;
	
	/** The File_Save As... menu option */
	private JMenuItem mnuSave;
	
	/** The File_Save As... menu option */
	private JMenuItem mnuSaveAs;
	
	/** The File_Export to Image.. menu option */
	private JMenuItem mnuExport;
	
	/** The File_Print menu option */
	private JMenuItem mnuPrint;
	
	/** The File_Exit menu option */
	private JMenuItem mnuExit;
		
	/** The window menu */
	private JMenu mnuWindow;
	
	/** The window_Preferneces menu */
	private JMenuItem mnuPreferences;
	
	/** The Window_look and feel menu */
	private JMenu mnuLookAndFeel;
	
	/** The Insert menu */
	private JMenu mnuInsert;
	
	/** The Evaluate menu */
	private JMenu mnuEvaluate;
	
	/** The Settings menu */
	private JMenu mnuSettings;
	
	/** The help menu */
	private JMenu mnuHelp;
	
	/** The help_Content menu */
	private JMenuItem mnuContent;
	
	/** The help_GoToHomePage menu */
	private JMenuItem mnuGotoHomePage;
	
	/** The help_Check Updates menu */
	private JMenuItem mnuCheckUpdate;

	/** The help_About menu */
	private JMenuItem mnuAbout;
	
	
	/** The File_New toolbar button  */
	private JButton btnNew;
	
	/** The File_Open toolbar button */
	private JButton btnOpen;
	
	/** The File_Save toolbar button */
	private JButton btnSave;
	

	/** The File_Print toolbar button */
	private JButton btnPrint;
	
	/** The File_Export toolbar button */
	private JButton btnExport;
	
	/** The Preferences toolbar button */
	private JButton btnPreferences;
	
	/** The zoom in button */
	private JButton btnZoomIn;
	
	/** The zoom out button */
	private JButton btnZoomOut;
	
	/** The Reset Camera button */
	private JButton btnReset;
	
	/** The Axis Show/hide toggle button */
	private JToggleButton tglAxis;
	
	/** The Box Show/hide toggle button */
	private JToggleButton tglBox;
	
	/** The GridXY Show/hide toggle button */
	private JToggleButton tglGridXY;
	
	/** The Light Enable/disable toggle button */
	private JToggleButton tglLight;
	
	/** The Perspective on/off toggle button */
	private JToggleButton tglPerspective;
	
	/** The 3D Steteoscope on/off  toggle button */
	private JToggleButton tgl3D;
	
	/** The Antialiasing on/off  toggle button */
	private JToggleButton tglAntialias;
	
	/** The ComboBox for Drawing Modes*/
	private JComboBox comboDrawMode;
	
	/** The ComboBox for Hidden Surface Removal Modes*/
	private JComboBox comboHSR;
	
	private ReportGenerator reporter;
	
	/** Upper toolbar*/
	JToolBar fileToolbar ;
	
	/** Lower toolbar*/
	JToolBar editToolbar ;
	
	/** toolbars containers*/
	JPanel pnlToolBar;
	/** The Edit_addPoint toolbar button */
	private JButton btnAddPoint;
	
	/** The Edit_addLine toolbar button */
	private JButton btnAddLine;
	
	/** The Edit_addVector toolbar button */
	private JButton btnAddVector;

	/** The Edit_addPolygon toolbar button */
	private JButton btnAddPolygon;
	
	/** The Edit_addPlane toolbar button */
	private JButton btnAddPlane;
	
	/** The Edit_addCurve toolbar button */
	private JButton btnAddCurve3d;
	
	private JXTreeTable treeTable;
	
	private JButton btnLoad;
	
	private JButton btnCMAnalisys;
	
	private JButton btnRobAnalisys;
	
	//TODO Cambiar para que sea mas dinamico
//	private ArrayList<PCEntity> entities;
	
	/** The Edit_addCurve2d_cartesian toolbar button */
	private JButton btnAddCurve2d_cartesian;
	
	/** The Edit_addCurve2d_implicit toolbar button */
	private JButton btnAddCurve2d_implicit;

	/** The Edit_addSurface toolbar button */
	private JButton btnAddSurface_Explicit;
	
	/** The Edit_addSurface toolbar button */
	private JButton btnAddSurface_Parametric;
	
	/** The Edit_addSurface toolbar button */
	private JButton btnAddSurface_Implicit;
	
	/** The Edit_addPrimitive toolbar button */
	private JButton btnAddPrimitive;
	
	/** The Edit_removeElement toolbar button */
	private JButton btnRemoveElement;
	
	/** The panel to show the element eqn/info */
	private JPanel pnlInfo;
	
	/** The panel to show transform */
	private JPanel pnlTransform;
	
	/** The panel to edit element*/
	private JPanel pnlEdit;
	
	/**table to display elements*/
	private JTable table ;

	/**Editorpane to display objectInfo*/
	private JLabel editorPane ;
	
	/**Drawing Canvas*/
	private Canvas3D canvas3D;
	
	/**Renderer asssociated with canvas*/
	private Renderer renderer = new Renderer();
	
	/**Scenemanager keeps and manages Elements of Canvas*/
	private SceneManager sceneManager;
	
	/**if new Element added has Random Color*/
	private boolean bodyColorRandom=true;
	
	/**true means file has been modified and needs to be saved*/
	private boolean dirty=false;
	
	/**Status bar*/
	private StatusBarPanel statusBar = new StatusBarPanel();
	
	/**Tab Canvas*/
	private JTabbedPane tabsCanvas;
	/**
	 * Default constructor.
	 */
	private ColorIcon bgColorIcon;

	private TabsManager tabsManager;

	private ProjectSimpleElement project;

	private JMenuItem mnuNewDataset;

	private JMenuItem mnuExportDataset;
	public CopyOfGui(){
	    super();
		this.setTitle("RESISTIRE-A 3D calculus Visualizer");	
		// make sure tooltips and menus show up on top of the heavy weight canvas
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		
		this.barMenu = new JMenuBar();
		
		// file menu
		this.mnuFile = new JMenu(Messages.getString("menu.file"));
		
		//newdataset file menu item
		this.mnuNewDataset = new JMenuItem(Messages.getString("menu.file.newdataset"));
//		this.mnuNewDataset.setIcon(Icons.DATASET);
		this.mnuNewDataset.setActionCommand("load");
		this.mnuNewDataset.addActionListener(this);
		this.mnuNewDataset.setEnabled(true);
		
		//exportdataset file menu item
		this.mnuExportDataset = new JMenuItem(Messages.getString("menu.file.exportdataset"));
		this.mnuExportDataset.setIcon(Icons.EXPORTDATASET);
		this.mnuExportDataset.setActionCommand("exportDataset");
		this.mnuExportDataset.addActionListener(this);
		this.mnuExportDataset.setEnabled(true);
		
		this.mnuNew = new JMenuItem(Messages.getString("menu.file.new"));
		this.mnuNew.setIcon(Icons.NEW);
		this.mnuNew.setActionCommand("new");
		this.mnuNew.addActionListener(this);
//		this.mnuNew.setEnabled(false);
		
		this.mnuSave = new JMenuItem(Messages.getString("menu.file.save"));
		this.mnuSave.setIcon(Icons.SAVE);
		this.mnuSave.setActionCommand("save");
		this.mnuSave.addActionListener(this);
		this.mnuSave.setEnabled(true);
		
		this.mnuSaveAs = new JMenuItem(Messages.getString("menu.file.saveas"));
		this.mnuSaveAs.setIcon(Icons.SAVEAS);
		this.mnuSaveAs.setActionCommand("saveas");
		this.mnuSaveAs.addActionListener(this);
//		this.mnuSaveAs.setEnabled(true);
		
		this.mnuOpen = new JMenuItem(Messages.getString("menu.file.open"));
		this.mnuOpen.setIcon(Icons.OPEN);
		this.mnuOpen.setActionCommand("openproject");
		this.mnuOpen.addActionListener(this);
//		this.mnuOpen.setEnabled(false);
		
		this.mnuExport = new JMenuItem(Messages.getString("menu.file.export"));
		this.mnuExport.setIcon(Icons.EXPORT);
		this.mnuExport.setActionCommand("export");
		this.mnuExport.addActionListener(this);
//		this.mnuExport.setEnabled(false);
		
		this.mnuPrint = new JMenuItem(Messages.getString("menu.file.print"));
		this.mnuPrint.setIcon(Icons.PRINT);
		this.mnuPrint.setActionCommand("print");
		this.mnuPrint.addActionListener(this);
//		this.mnuPrint.setEnabled(false);
		
		this.mnuExit = new JMenuItem(Messages.getString("menu.file.exit"));
		this.mnuExit.setActionCommand("exit");
		this.mnuExit.addActionListener(this);
		
		
		this.mnuFile.add(this.mnuNewDataset);
		this.mnuFile.add(this.mnuNew);
		this.mnuFile.add(this.mnuOpen);
		this.mnuFile.addSeparator();
		this.mnuFile.add(this.mnuSave);
		this.mnuFile.add(this.mnuSaveAs);
		this.mnuFile.add(this.mnuExport);
		this.mnuFile.add(this.mnuExportDataset);
		this.mnuFile.addSeparator();
		this.mnuFile.add(this.mnuPrint);
		this.mnuFile.addSeparator();
		this.mnuFile.add(mnuExit);
		
		// window menu
		this.mnuWindow = new JMenu(Messages.getString("menu.window"));

		this.mnuPreferences = new JMenuItem(Messages.getString("menu.window.preferences"));
		this.mnuPreferences.setIcon(Icons.PREFERENCES);
		this.mnuPreferences.setActionCommand("preferences");
		this.mnuPreferences.addActionListener(this);

		this.mnuLookAndFeel = new JMenu(Messages.getString("menu.window.laf"));
		this.mnuLookAndFeel.setIcon(Icons.LOOKANDFEEL);
		//this.createLookAndFeelMenuItems(this.mnuLookAndFeel);
			
		this.mnuWindow.add(mnuPreferences);
		this.mnuWindow.addSeparator();
		
		JMenuItem menuItem = new JMenuItem(Messages.getString("menu.window.toolbar1"));
		menuItem.setIcon(Icons.TOOLBAR);
		menuItem.setActionCommand("filetoolbar");
		menuItem.addActionListener(this);
		this.mnuWindow.add(menuItem);
	
		
		menuItem = new JMenuItem(Messages.getString("menu.window.toolbar2"));
		menuItem.setIcon(Icons.TOOLBAR);
		menuItem.setActionCommand("edittoolbar");
		menuItem.addActionListener(this);
		this.mnuWindow.add(menuItem);
		
		menuItem = new JMenuItem(Messages.getString("menu.window.statusbar"));
		menuItem.setIcon(Icons.STATUSBAR);
		menuItem.setActionCommand("statusbar");
		menuItem.addActionListener(this);
		this.mnuWindow.add(menuItem);
		
		this.mnuInsert = new JMenu(Messages.getString("menu.insert"));	
		this.mnuWindow.addSeparator();
		this.mnuWindow.add(this.mnuLookAndFeel);
		
//		mnuInsert.addSeparator();
		menuItem = new JMenuItem(Messages.getString("menu.insert.pcanalysis"));
		menuItem.setIcon(Icons.CM);
		menuItem.setActionCommand("CMAnalysis");
		menuItem.addActionListener(this);
		mnuInsert.add(menuItem);		
		menuItem = new JMenuItem(Messages.getString("menu.insert.distance"));
		menuItem.setIcon(Icons.DISTANCE);
		menuItem.setActionCommand("distance");
		menuItem.addActionListener(this);
		mnuInsert.add(menuItem);	
//		mnuInsert.addSeparator();		
		
		menuItem = new JMenuItem(Messages.getString("menu.insert.projection"));
		menuItem.setIcon(Icons.PROJECTION);
		menuItem.setActionCommand("addProjection");
		menuItem.addActionListener(this);
		mnuInsert.add(menuItem);	

		this.mnuSettings = new JMenu(Messages.getString("menu.settings"));
		JMenu menu = new JMenu(Messages.getString("menu.settings.scenesettings"));
		menu.setIcon(Icons.SCENESETTINGS);
		menu.setActionCommand("scenesettings");
		menu.addActionListener(this);
		
		menuItem = new JMenuItem(Messages.getString("menu.settings.scenesettings.enableantialias"));
		menuItem.setActionCommand("antialias");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem(Messages.getString("menu.settings.scenesettings.enablelights"));
		menuItem.setActionCommand("light");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem(Messages.getString("menu.settings.scenesettings.enableperspective"));
		menuItem.setActionCommand("perspective");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Edit Scene Settings..");
		menuItem.setActionCommand("scenesettings");
		menuItem.addActionListener(this);
		menu.add(menuItem);
	
		mnuSettings.add(menu);
		mnuSettings.addSeparator();
		menu = new JMenu(Messages.getString("menu.settings.steroscopysettings"));
		menu.setIcon(Icons.STEREOSCOPE);
		menu.setActionCommand("steroscopysettings");
		menu.addActionListener(this);
		menuItem = new JMenuItem(Messages.getString("menu.settings.steroscopysettings.onoff"));
		menuItem.setActionCommand("stereoscope");
		menuItem.addActionListener(this);
		menu.add(menuItem);	
		menu.addSeparator();
	
		menuItem = new JMenuItem(Messages.getString("menu.settings.steroscopysettings.mode1"));
		menuItem.setActionCommand("mode1 ");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem(Messages.getString("menu.settings.steroscopysettings.mode2"));
		menuItem.setActionCommand("mode2 ");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem(Messages.getString("menu.settings.steroscopysettings.mode3"));
		menuItem.setActionCommand("mode3 ");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem(Messages.getString("menu.settings.steroscopysettings.mode4"));
		menuItem.setActionCommand("mode4 ");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem(Messages.getString("menu.settings.steroscopysettings.mode5"));
		menuItem.setActionCommand("mode5 ");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		mnuSettings.add(menu);
		
		menu = new JMenu(Messages.getString("menu.settings.hsrsettings"));
		menu.setIcon(Icons.HSR);
		menu.setActionCommand("hsrsettings");
		menu.addActionListener(this);
		mnuSettings.addSeparator();
		
		/*
		menuItem = new JMenuItem(Messages.getString("menu.settings.hsrsettings.zsort"));
		//menuItem.setIcon(Icons.ADDSURFACE);
		menuItem.setActionCommand("zsort");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem(Messages.getString("menu.settings.hsrsettings.zbuffer"));
		//menuItem.setIcon(Icons.ADDSURFACE);
		menuItem.setActionCommand("zbuffer");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem(Messages.getString("menu.settings.hsrsettings.bsptree"));
		//menuItem.setIcon(Icons.ADDSURFACE);
		menuItem.setActionCommand("bsptree");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		mnuSettings.add(menu);
		*/
		menu = new JMenu(Messages.getString("menu.settings.axissettings"));
		menu.setIcon(Icons.AXIS);
		menu.setActionCommand("axissettings");
		menu.addActionListener(this);
		
		menuItem = new JMenuItem(Messages.getString("menu.settings.axissettings.showaxis"));
		//menuItem.setIcon(Icons.ADDSURFACE);
		menuItem.setActionCommand("axis");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem(Messages.getString("menu.settings.axissettings.showbox"));
	
		//menuItem.setIcon(Icons.ADDSURFACE);
		menuItem.setActionCommand("box");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem(Messages.getString("menu.settings.axissettings.editaxis"));
		//menuItem.setIcon(Icons.ADDSURFACE);
		menuItem.setActionCommand("axissettings");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		mnuSettings.add(menu);

		this.mnuHelp = new JMenu(Messages.getString("menu.help"));

		this.mnuContent = new JMenuItem(	Messages.getString("menu.help.content"));
		mnuContent.setIcon(Icons.CONTENT);
		mnuContent.setActionCommand("content");
		mnuContent.addActionListener(this);

			
		this.mnuGotoHomePage = new JMenuItem(	Messages.getString("menu.help.gotoHomePage"));
		mnuGotoHomePage.setIcon(Icons.GOTOHOMEPAGE);
		mnuGotoHomePage.setActionCommand("homepage");
		mnuGotoHomePage.addActionListener(this);

		this.mnuCheckUpdate = new JMenuItem(	Messages.getString("menu.help.checkUpdates"));
		mnuCheckUpdate.setIcon(Icons.CHECKUPDATE);
		mnuCheckUpdate.setActionCommand("checkupdates");
		mnuCheckUpdate.addActionListener(this);

		this.mnuAbout = new JMenuItem(	Messages.getString("menu.help.about"));
		mnuAbout.setIcon(Icons.ABOUT);
		mnuAbout.setActionCommand("about");
		mnuAbout.addActionListener(this);

		this.mnuHelp.add(mnuContent);
		this.mnuHelp.addSeparator();
		this.mnuHelp.add(mnuGotoHomePage);
		this.mnuHelp.add(mnuCheckUpdate);
		this.mnuHelp.addSeparator();
		this.mnuHelp.add(mnuAbout);
		
		//Add main menu to menu bar
		this.barMenu.add(this.mnuFile);
//		this.barMenu.add(this.mnuWindow);	
		this.barMenu.add(this.mnuInsert);	
		//this.barMenu.add(this.mnuEvaluate);	
		this.barMenu.add(this.mnuSettings);	
		this.barMenu.add(this.mnuHelp);	
		
		this.setJMenuBar(this.barMenu);
				
		this.fileToolbar = new JToolBar(Messages.getString("toolbar.file"), SwingConstants.HORIZONTAL);
		//this.fileToolbar.setFloatable(false);
		
		this.btnNew = new JButton(Icons.NEW);
		this.btnNew.addActionListener(this);
		this.btnNew.setActionCommand("new");
		this.btnNew.setToolTipText(Messages.getString("toolbar.file.new"));
		this.btnNew.setEnabled(true);
		
		this.btnLoad = new JButton(Icons.ADDDATASET);
		this.btnLoad.addActionListener(this);
		this.btnLoad.setActionCommand("load");
		this.btnLoad.setToolTipText("Add Dataset");
		
		this.btnCMAnalisys = new JButton(Icons.CM);
		btnCMAnalisys.setToolTipText(Messages.getString("toolbar.file.procrustesAnalysis"));
		this.btnCMAnalisys.addActionListener(this);
		this.btnCMAnalisys.setActionCommand("CMAnalysis");
		
		
		
		this.btnRobAnalisys = new JButton(Icons.DISTANCE);
		this.btnRobAnalisys.addActionListener(this);
		this.btnRobAnalisys.setActionCommand("distance");
		this.btnRobAnalisys.setToolTipText("Calculate Distance");
		
		this.btnOpen = new JButton(Icons.OPEN);
		this.btnOpen.addActionListener(this);
		this.btnOpen.setActionCommand("openproject");
		this.btnOpen.setToolTipText(Messages.getString("toolbar.file.open"));
//		this.btnOpen.setEnabled(false);
		
		this.btnSave = new JButton(Icons.SAVE);
		this.btnSave.addActionListener(this);
		this.btnSave.setActionCommand("saveas");
		this.btnSave.setToolTipText(Messages.getString("toolbar.file.save"));
		this.btnSave.setEnabled(true);
		
		this.btnPrint = new JButton(Icons.PRINT);
		this.btnPrint.addActionListener(this);
		this.btnPrint.setActionCommand("print");
		this.btnPrint.setToolTipText(Messages.getString("toolbar.file.print"));
		this.btnPrint.setEnabled(false);
		
		this.btnExport = new JButton(Icons.EXPORT);
		this.btnExport.addActionListener(this);
		this.btnExport.setActionCommand("export");
		this.btnExport.setToolTipText(Messages.getString("toolbar.file.export"));
		this.btnExport.setEnabled(false);
		
		this.btnZoomOut = new JButton(Icons.ZOOM_OUT);
		this.btnZoomOut.setToolTipText(Messages.getString("toolbar.preferences.zoomOut"));
		this.btnZoomOut.setActionCommand("zoomout");
		this.btnZoomOut.addActionListener(this);
		
		this.btnZoomIn = new JButton(Icons.ZOOM_IN);
		this.btnZoomIn.setToolTipText(Messages.getString("toolbar.preferences.zoomIn"));
		this.btnZoomIn.setActionCommand("zoomin");
		this.btnZoomIn.addActionListener(this);
		
		this.btnReset = new JButton(Icons.RESET);
		this.btnReset.setToolTipText(Messages.getString("toolbar.preferences.reset"));
		this.btnReset.setActionCommand("reset");
		this.btnReset.addActionListener(this);
		
		this.tglAxis = new JToggleButton(Icons.AXIS);
		this.tglAxis.setToolTipText(Messages.getString("toolbar.preferences.axis"));
		this.tglAxis.setActionCommand("axis");
		this.tglAxis.addActionListener(this);
		this.tglAxis.setSelected(true);//Preferences.isAntiAliasingEnabled());
		
		this.tglBox = new JToggleButton(Icons.BOX);
		this.tglBox.setToolTipText(Messages.getString("toolbar.preferences.box"));
		this.tglBox.setActionCommand("box");
		this.tglBox.addActionListener(this);
		this.tglBox.setSelected(true);//Preferences.isAntiAliasingEnabled());
		
		this.tglGridXY = new JToggleButton(Icons.GRIDXY);
		this.tglGridXY.setToolTipText(Messages.getString("toolbar.preferences.gridxy"));
		this.tglGridXY.setActionCommand("gridxy");
		this.tglGridXY.addActionListener(this);
		this.tglGridXY.setSelected(false);//Preferences.isAntiAliasingEnabled());
		
		
		fileToolbar.add(this.btnNew);
		fileToolbar.add(this.btnOpen);
		fileToolbar.add(this.btnSave);
//		fileToolbar.add(this.btnPrint);
//		fileToolbar.add(this.btnExport);
		fileToolbar.add(this.btnLoad);
		fileToolbar.add(this.btnCMAnalisys);
		fileToolbar.add(this.btnRobAnalisys);
		
		btnP = new JButton(Icons.PROJECTION);
		btnP.setToolTipText("Calculate Projection");
		btnP.addActionListener(this);
		btnP.setActionCommand("addProjection");
		btnP.setToolTipText("Calculate Projection");
		
		
		fileToolbar.add(btnP);
		
		
		this.editToolbar = new JToolBar(Messages.getString("toolbar.edit"), SwingConstants.HORIZONTAL);
		this.editToolbar.setFloatable(true);
		
//		editToolbar.addSeparator();
		editToolbar.add(this.btnZoomOut);
		editToolbar.add(this.btnZoomIn);
		
		editToolbar.add(this.btnReset);
		editToolbar.addSeparator();
		editToolbar.add(this.tglAxis);
		editToolbar.add(this.tglBox);
		editToolbar.add(this.tglGridXY);
//		
//		this.btnAddPoint = new JButton(Icons.ADDPOINT);
//		this.btnAddPoint.addActionListener(this);
//		this.btnAddPoint.setActionCommand("addpoint");
//		this.btnAddPoint.setToolTipText(Messages.getString("toolbar.edit.addpoint"));
//		
//		this.btnAddVector = new JButton(Icons.ADDVECTOR);
//		this.btnAddVector.addActionListener(this);
//		this.btnAddVector.setActionCommand("addvector");
//		this.btnAddVector.setToolTipText(Messages.getString("toolbar.edit.addvector"));
//		
//		this.btnAddLine = new JButton(Icons.ADDLINE);
//		this.btnAddLine.addActionListener(this);
//		this.btnAddLine.setActionCommand("addline");
//		this.btnAddLine.setToolTipText(Messages.getString("toolbar.edit.addline"));
//				
//		this.btnAddPolygon = new JButton(Icons.ADDPOLYGON);
//		this.btnAddPolygon.addActionListener(this);
//		this.btnAddPolygon.setActionCommand("addpolygon");
//		this.btnAddPolygon.setToolTipText(Messages.getString("toolbar.edit.addpolygon"));
//		
//		this.btnAddPlane = new JButton(Icons.ADDPLANE);
//		this.btnAddPlane.addActionListener(this);
//		this.btnAddPlane.setActionCommand("addplane");
//		this.btnAddPlane.setToolTipText(Messages.getString("toolbar.edit.addplane"));
//		
//		this.btnAddCurve3d = new JButton(Icons.ADDCURVE3D);
//		this.btnAddCurve3d.addActionListener(this);
//		this.btnAddCurve3d.setActionCommand("addcurve3d");
//		this.btnAddCurve3d.setToolTipText(Messages.getString("toolbar.edit.addcurve3d"));
//		
//		this.btnAddCurve2d_cartesian = new JButton(Icons.ADDCURVE2DCARTESIAN);
//		this.btnAddCurve2d_cartesian.addActionListener(this);
//		this.btnAddCurve2d_cartesian.setActionCommand("addcurve2dcartesian");
//		this.btnAddCurve2d_cartesian.setToolTipText(Messages.getString("toolbar.edit.addcurve2dcartesian"));
//		
//		this.btnAddCurve2d_implicit = new JButton(Icons.ADDCURVE2DIMPLICIT);
//		this.btnAddCurve2d_implicit.addActionListener(this);
//		this.btnAddCurve2d_implicit.setActionCommand("addcurve2dimplicit");
//		this.btnAddCurve2d_implicit.setToolTipText(Messages.getString("toolbar.edit.addcurve2dimplicit"));
//		
//		this.btnAddSurface_Explicit = new JButton(Icons.ADDSURFACE);
//		this.btnAddSurface_Explicit.addActionListener(this);
//		this.btnAddSurface_Explicit.setActionCommand("addsurface");
//		this.btnAddSurface_Explicit.setToolTipText(Messages.getString("toolbar.edit.addsurface"));
//		
//		this.btnAddPrimitive = new JButton(Icons.ADDPREMITIVE);
//		this.btnAddPrimitive.addActionListener(this);
//		this.btnAddPrimitive.setActionCommand("addprimitive");
//		this.btnAddPrimitive.setToolTipText(Messages.getString("toolbar.edit.addprimitive"));
//			
//		this.btnAddSurface_Parametric = new JButton(Icons.ADDSURFACEPARAMETRIC);
//		this.btnAddSurface_Parametric.addActionListener(this);
//		this.btnAddSurface_Parametric.setActionCommand("addsurfaceparametric");
//		this.btnAddSurface_Parametric.setToolTipText(Messages.getString("toolbar.edit.addsurfaceparametric"));
//	
//		this.btnAddSurface_Implicit = new JButton(Icons.ADDSURFACEIMPLICIT);
//		this.btnAddSurface_Implicit.addActionListener(this);
//		this.btnAddSurface_Implicit.setActionCommand("addsurfaceimplicit");
//		this.btnAddSurface_Implicit.setToolTipText(Messages.getString("toolbar.edit.addsurfaceimplicit"));
//			
		this.btnRemoveElement = new JButton(Icons.REMOVE);
		this.btnRemoveElement.addActionListener(this);
		this.btnRemoveElement.setActionCommand("remove");
		this.btnRemoveElement.setToolTipText(Messages.getString("toolbar.edit.removeelement"));
	
		this.tglPerspective = new JToggleButton(Icons.PERSPECTIVE);
		this.tglPerspective.setToolTipText(Messages.getString("toolbar.preferences.perspective"));
		this.tglPerspective.setActionCommand("perspective");
		this.tglPerspective.addActionListener(this);
		this.tglPerspective.setSelected(true);//Preferences.isAntiAliasingEnabled());
		
		this.tgl3D = new JToggleButton(Icons.STEREOSCOPE);
		this.tgl3D.setToolTipText(Messages.getString("toolbar.preferences.stereoscope"));
		this.tgl3D.setActionCommand("stereoscope");
		this.tgl3D.addActionListener(this);
		this.tgl3D.setSelected(false);//Preferences.isAntiAliasingEnabled());
		
		this.tglAntialias = new JToggleButton(Icons.ANTIALIAS);
		this.tglAntialias.setToolTipText(Messages.getString("toolbar.preferences.antialias"));
		this.tglAntialias.setActionCommand("antialias");
		this.tglAntialias.addActionListener(this);
		this.tglAntialias.setSelected(false);//Preferences.isAntiAliasingEnabled());
		
		this.tglLight = new JToggleButton(Icons.LIGHT);
		this.tglLight.setToolTipText(Messages.getString("toolbar.preferences.light"));
		this.tglLight.setActionCommand("light");
		this.tglLight.addActionListener(this);
		this.tglLight.setSelected(true);//Preferences.isAntiAliasingEnabled());
//		editToolbar.add(btnAddPoint);
//		editToolbar.add(btnAddVector);
//		editToolbar.add(btnAddLine);
//		editToolbar.addSeparator();
//		
//		editToolbar.add(btnAddCurve3d);
//		editToolbar.add(btnAddCurve2d_cartesian);
//		editToolbar.add(btnAddCurve2d_implicit);
//		editToolbar.addSeparator();
//		
//		editToolbar.add(btnAddPolygon);
//		editToolbar.add(btnAddPlane);
//		editToolbar.add(btnAddSurface_Explicit);
//		
//		editToolbar.addSeparator();
//		editToolbar.add(btnAddSurface_Parametric);
//		editToolbar.add(btnAddSurface_Implicit);
//		
//		editToolbar.add(btnAddPrimitive);
//		editToolbar.add(btnRemoveElement);
//		editToolbar.addSeparator();
//		editToolbar.add(this.tglAntialias);
//		editToolbar.add(this.tglLight);
//		editToolbar.add(this.tgl3D);
//		editToolbar.add(this.tglPerspective);
		
		editToolbar.addSeparator();
		
		
		JLabel lblDrawMode=new JLabel(Icons.DRAWMODE);
		lblDrawMode.setText("");
		lblDrawMode.setToolTipText(Messages.getString("toolbar.preferences.drawmode"));
		/*
		JLabel lblHSR=new JLabel(Icons.HSR);
		lblHSR.setToolTipText(Messages.getString("toolbar.preferences.hsrmode"));
		lblHSR.setText("HSR Mode:");
		String[] str ="WireFrame","Solid","SolidFlat","GrayScale"};
		comboDrawMode=new JComboBox(str);
		comboDrawMode.setSelectedIndex(1);
		comboDrawMode.setMaximumSize(new Dimension(80,40));
	    
		str =new String[]"Z sort","Z Buffer","BSP Tree"};
		comboHSR=new JComboBox(str);
		comboHSR.setSelectedIndex(1);
		comboHSR.setMaximumSize(new Dimension(80,40));
		
		editToolbar.add(comboDrawMode);
		//editToolbar.addSeparator();
		editToolbar.add(lblHSR);
		editToolbar.add(comboHSR);
		*/
		//editToolbar.addSeparator();
		editToolbar.add(lblDrawMode);
		bgColorIcon=new ColorIcon(Globalsettings.backgroundColor);
		editToolbar.add(bgColorIcon);
		
		//Add toolbars
		pnlToolBar = new JPanel();
		pnlToolBar.setLayout(new GridLayout(2, 1));
		pnlToolBar.add(fileToolbar);
		pnlToolBar.add(editToolbar);
		getContentPane().add(pnlToolBar,BorderLayout.NORTH);

		
		pnlInfo=new JPanel();
		pnlTransform = new JPanel();
		pnlEdit = new JPanel();
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 0));
		tabs.addTab(Messages.getString("tab.info"), this.pnlInfo);
		tabs.addTab(Messages.getString("tab.transform"), this.pnlTransform);
		tabs.addTab(Messages.getString("tab.edit"), this.pnlEdit);
		
		JPanel pnlLeft = new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
	
		 //Create an editor pane.
        editorPane = new JLabel();//createEditorPane();
       // editorPane.setBackground(Color.white);
        editorPane.setOpaque(true);
        editorPane.setBorder(BorderFactory.createEmptyBorder(3, 5, 5, 0));
         JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        editorScrollPane.setEnabled(false);
        editorScrollPane.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        
        //Create a table pane.
        this.project = new ProjectSimpleElement("New project");
        project.setIcon(Icons.PROJECT);
        treeTable = new JXTreeTable(new TreeTableModel(project));
        TableColumnModel cModel = treeTable.getColumnModel();
        treeTable.setPreferredScrollableViewportSize(new Dimension(120, 120));
        treeTable.setTreeCellRenderer(new TreeCellRenderer());
        treeTable.setAutoscrolls(true);
        treeTable.setRootVisible(true);
        treeTable.setVisible(true);
        LeftTableSelectionListener treeListener = new LeftTableSelectionListener(editorPane, treeTable, this);
        treeTable.addTreeSelectionListener(treeListener);
        treeTable.addMouseListener(treeListener);
        //treeTable.addTreeSelectionListener(new customSelectionListener(this.btnP));
        
        this.btnPreferences = new JButton(Icons.PREFERENCES);
        this.btnPreferences.addActionListener(this);
        this.btnPreferences.setActionCommand("preferences");
        this.btnPreferences.setToolTipText(Messages.getString("toolbar.file.preferences"));
//        fileToolbar.add(btnPreferences);
        JScrollPane paneScrollPane = new JScrollPane(treeTable);
        paneScrollPane.setVerticalScrollBarPolicy(
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        paneScrollPane.setPreferredSize(new Dimension(250, 155));
        paneScrollPane.setMinimumSize(new Dimension(10, 10));
        
        
 
        //Put the editor pane and the text pane in a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,paneScrollPane,
                                              editorScrollPane
                                              );
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.5);
        JPanel leftPane = new JPanel(new GridLayout(1,0));
        splitPane.setRequestFocusEnabled(false);
        leftPane.add(splitPane);
	    getContentPane().add(leftPane,BorderLayout.WEST);
		
	    tabsCanvas = new JTabbedPane();
	    //tabsCanvas.add(canvas3D);
	    this.tabsManager = new TabsManager(tabsCanvas);

	    this.tabsManager.addChangeListener(this);
		reportPanel = new ReportPnl();
		centerTabPnl = new JTabbedPane();
		centerTabPnl.add("Graphics", tabsCanvas);
		centerTabPnl.add("Reports", reportPanel);
		
		
//		Logger.setReportWindowHandler(new ReportWindowHandler( ( (ReportPnl)reportPanel).getTextArea() ));
//		
//		com.rps.log.Logger.getLogger("info").info("entro");
	    reporter = new ReportGenerator((ReportPnl)reportPanel);
		JSplitPane pneSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, centerTabPnl);
		// setup the layout
		pneSplit.setOneTouchExpandable(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pnlToolBar,BorderLayout.NORTH);
		getContentPane().add(pneSplit,BorderLayout.CENTER);
		
//		getContentPane().add(statusBar, BorderLayout.SOUTH);

		//size everything
		this.pack();
		sceneManager=new SceneManager();
		sceneManager.createDemo();
		//canvas3D.setBackgroundColor(Color.lightGray.brighter());
//		canvas3D.setScene(sceneManager.createScene(true));//ObjectFactory.createDemo());
//		canvas3D.setRequestFocusEnabled(false);
//		canvas3D.setFocusable(true);
		
		scrollPane = new JScrollPane();
//		canvas3D.add(scrollPane);
//		canvas3D.requestFocus();
//		
//		canvas3D.getRenderer().setBackgroundColor(Globalsettings.backgroundColor);
//		bgColorIcon.setBackground(canvas3D.getRenderer().getBackgroundColor());
		bgColorIcon.setActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Canvas3D canvas3D = tabsManager.getCurrentCanvas();
				SceneManager sceneManager = canvas3D.getSceneManager();
				
				canvas3D.getRenderer().setBackgroundColor(bgColorIcon.getBackground());
				sceneManager.getSettings().backgroundColor = bgColorIcon.getBackground();
				
//				Globalsettings.backgroundColor=bgColorIcon.getBackground();
			}
			
		});
		updateTable();
		setLastFileName(null);
		this.setSize(800, 400);
		this.setIconImage(Icons.APP.getImage());
		// let the methods in this class handle closing the window
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
	            
				public void windowClosing(WindowEvent e){ 
	                close();
	            }
	    });
		
		this.setVisible(true);
		
	}

	/**
	 * Create a new Canvas with their attributes
	 * @param localSettings 
	 * @param sceneManager 
	 * @return new Canvas3D
	 */
	
	private Canvas3D createCanvas(SceneManager sceneManager, LocalSettings localSettings){
		Canvas3D canvas =new Canvas3D(sceneManager, localSettings);
		canvas.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		Camera3D cam = new Camera3D();
		cam.setOrthographic(!Globalsettings.perspectiveEnabled);
		canvas.setCamera(cam);
		canvas.setInteractionHandler(new InteractionHandler());
		return canvas;
	}
	
	/**
	 * Adds menu items to the given menu for each look and feel
	 * installed in the running vm.
	 * @param menu the menu to add the items to
	 */
	private void createLookAndFeelMenuItems(JMenu menu) {
		LookAndFeel current = UIManager.getLookAndFeel();
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){ 
			JMenuItem mnuLaF = new JMenuItem(info.getName());
			if (current.getClass().getName().equals(info.getClassName())) {
				mnuLaF.setIcon(Icons.CHECK);
			}
			mnuLaF.setActionCommand("laf+" + info.getClassName());
			mnuLaF.addActionListener(this);
			menu.add(mnuLaF);
		}
	}
	


	
	public void actionPerformed(ActionEvent arg0) {
		String command=arg0.getActionCommand();
		if (command.startsWith("laf+")) {
			// make sure they are sure
			int choice = JOptionPane.showConfirmDialog(this, 
					Messages.getString("dialog.laf.warning.text"), 
					Messages.getString("dialog.laf.warning.title"), 
					JOptionPane.YES_NO_CANCEL_OPTION);
			// check the user's choice
			if (choice == JOptionPane.YES_OPTION){ 
				// parse out the LAF class name
				String className = command.replace("laf+", "");
				try {
					// attempt to set the look and feel
					UIManager.setLookAndFeel(className);
					Globalsettings.lookandFeel=UIManager.getLookAndFeel().getName();
					// get the current windows open by this application
					Window windows[] = Window.getWindows();
					// update the ui
			        for(Window window : windows){ 
			            SwingUtilities.updateComponentTreeUI(window);
			        }
			        // we need to pack since certain look and feels may have different component
			        // gaps which can cause stuff not to be shown
			        this.pack();
			        // find the item in the menu to set the current one
			        for (Component component : this.mnuLookAndFeel.getPopupMenu().getComponents()){ 
			        	JMenuItem item = (JMenuItem)component;
			        	// set the newly selected LAF to have a checked icon
			        	// and the rest to have no icon
			        	if (item.getActionCommand().equalsIgnoreCase(command)) {
			        		item.setIcon(Icons.CHECK);
			        	} else {
			        		item.setIcon(null);
			        	}
			        }
				} catch (Exception e){ 
					System.out.println(e);
				}
			
			}
		}
		if (command=="perspective"){
			  Globalsettings.perspectiveEnabled=!canvas3D.getRenderer().isPerspectiveEnabled();
		      canvas3D.getRenderer().setPerspectiveEnabled(!canvas3D.getRenderer().isPerspectiveEnabled());
		      this.tglPerspective.setSelected(canvas3D.getRenderer().isPerspectiveEnabled());
		}else if(command=="CMAnalysis"){
			
			int i=this.treeTable.getSelectedRow();
			TreePath path = treeTable.getPathForRow(i);
			ComposeSimpleElement selected = (ComposeSimpleElement) path.getLastPathComponent();
			AnalysisConfiguration configuration = (AnalysisConfiguration) AddObjectDialog.show(this,selected, Element3DFactory.PROCRUSTES_ELEMENT);
			if(configuration == null)return;
			
			ProcrustesCalculatorAdapter calculator = new ProcrustesCalculatorAdapter();
			calculator.setConfiguration(configuration);
			
			ArrayList<SampleSimpleElement> specimens = (ArrayList<SampleSimpleElement>) ((ComposeSimpleElement)selected.getElementByKey("specimens")).getAllElements();
			
			int tabIndex = this.createEmptyTab(configuration);
			GraphicsPane gp = (GraphicsPane) tabsManager.getTabAt(tabIndex);
			ProcrustesAnalisysWorker worker = new ProcrustesAnalisysWorker(this, calculator, specimens, tabIndex, gp.getProgressBar());
//			ProcrustesAnalisysThread t1 = new ProcrustesAnalisysThread(this, calculator, specimens, tabIndex);
			worker.execute();


		}else if(command=="distance"){
			int i=this.treeTable.getSelectedRow();
			TreePath path = treeTable.getPathForRow(i);
			ComposeSimpleElement selected = (ComposeSimpleElement) path.getLastPathComponent();
			DistanceConfiguration configuration = (DistanceConfiguration) AddObjectDialog.show(this,null, Element3DFactory.DISTANCE_ELEMENT); 			
			DistanceCalculatorAdapter calc = new DistanceCalculatorAdapter(configuration);
			ArrayList<SampleSimpleElement> specimens = (ArrayList<SampleSimpleElement>) ((ComposeSimpleElement)selected.getElementByKey("specimens")).getAllElements();
			ComposeSimpleElement distances  = (ComposeSimpleElement) selected.getElementByKey("distances");
			ComposeSimpleElement newDistance = calc.calculate(specimens); 
			if(distances == null){
				distances = new ComposeSimpleElement("distances");
				distances.addAction(new SimpleElementAction(
						"Remove",
						"remove",
						this,
						distances
						));
			}
			distances.addElement(newDistance);
			
			selected.addElement(distances);
			newDistance.addAction(new SimpleElementAction(
					"Remove",
					"remove",
					this,
					newDistance
					));
			newDistance.addAction(new SimpleElementAction(
					"Export Distance",
					"exportDistance",
					this,
					newDistance
					));
			
//			newDistance.addAction("delete");
			
			treeTable.updateUI();
			
			DistancesDetailer detailer = new DistancesDetailer();
			reporter.writeReport("Matrix of distances generated \n");
			reporter.writeReport("Data source: " + selected.getName() + '\n');
			reporter.writeReport("Type of analysis: "+ (configuration.getType() == DistanceConfiguration.MIN_SQR_DISTANCE ? "Least squares distance" : "Robusts distance")+'\n');
			reporter.writeReport(detailer.getDetails(newDistance));
			
		}else if(command=="addProjection"){
			int i=this.treeTable.getSelectedRow();
			TreePath path = treeTable.getPathForRow(i);
			ComposeSimpleElement selected = (ComposeSimpleElement) path.getLastPathComponent();
			ProjectionConfiguration configuration = (ProjectionConfiguration) AddObjectDialog.show(this,selected, Element3DFactory.PROJECTION_ELEMENT);
			ProjectionCalculatorAdapter calculator = new ProjectionCalculatorAdapter(configuration);
			ComposeSimpleElement projection = calculator.calculate((ComposeSimpleElement) selected);
			ComposeSimpleElement projections = (ComposeSimpleElement) selected.getElementByKey("ordinations");
			if(projections==null){
				projections = new ComposeSimpleElement("ordinations");
			}

			projection.addAction(new ShowSimpleElementAction(
					"Show Element",
					"showpanel",
					this,
					projection
					));
			projection.addAction(new ShowSimpleElementAction(
					"Export Dataset",
					"exportDataset",
					this,
					projection
					));
			projection.addAction(new SimpleElementAction(
					"Remove",
					"remove",
					this,
					projection
					));
			projections.addElement(projection);
			selected.addElement(projections);

			this.treeTable.updateUI();
			Object[] paths =  path.getPath();
			String names = "";
			int z=0;
			for(z=0 ; z<paths.length-1; z++){
				names += ((SimpleElement)paths[z]).getName() + " > ";  
			}
			names += ((SimpleElement)paths[z]).getName();						
			this.addElement3D(new Element3DProjection(projection), configuration);
			ProjectionDetailer detailer = new ProjectionDetailer();
			reporter.writeReport("New Projection Generated \n");
			reporter.writeReport("Data Source Path: " + names + '\n');
			reporter.writeReport("Type of Projection: "+ (configuration.getType() == ProjectionConfiguration.LEAST_SQR_PROJETION ? "Least squares projection" : "Robusts projection")+'\n');
			reporter.writeReport(detailer.getDetails(projection));
		}else if(command=="addWireframe"){
			int i=this.treeTable.getSelectedRow();
			TreePath path = treeTable.getPathForRow(i);
			ComposeSimpleElement selected = (ComposeSimpleElement) path.getLastPathComponent();
			ComposeSimpleElement wireframe = (ComposeSimpleElement) this.loadFromFile(getFileName(false, ".nts", "Add wireframe"));
			int[][] links = commonUtils.getLinksMatrix(wireframe);
			if(selected.getElementByKey("specimens") != null){
				for(SampleSimpleElement elem : (ArrayList<SampleSimpleElement>)((ComposeSimpleElement)selected.getElementByKey("specimens")).getAllElements() ){
					Element3DWireframe wireframe3D = new Element3DWireframe(elem, links);
					
				}
			}
			
		}else if(command=="box"){
			  Canvas3D canvas3D = tabsManager.getCurrentCanvas();
			  SceneManager sceneManager = canvas3D.getSceneManager();
			  sceneManager.getSettings().boxVisible= !sceneManager.getSettings().boxVisible;
			  canvas3D.setScene(sceneManager.createScene(false));
//			  Globalsettings.boxVisible=!Globalsettings.boxVisible;
			  this.tglBox.setSelected(Globalsettings.boxVisible);
			  canvas3D.refresh();
		}else if(command=="axis"){
			double g = Globalsettings.maxX;
			Canvas3D canvas3D = tabsManager.getCurrentCanvas();
			SceneManager sceneManager = canvas3D.getSceneManager();
			sceneManager.setAxisVisible(!sceneManager.isAxisVisible());
      	  canvas3D.setScene(sceneManager.createScene(false));
      	  this.tglAxis.setSelected(sceneManager.isAxisVisible());
      	  canvas3D.refresh();
		}else if(command=="gridxy"){
			Canvas3D canvas3D = tabsManager.getCurrentCanvas();
			SceneManager sceneManager = canvas3D.getSceneManager();
			sceneManager.setGridXYVisible(!sceneManager.isGridXYVisible());
    	    canvas3D.setScene(sceneManager.createScene(false));
    	    this.tglGridXY.setSelected(sceneManager.isGridXYVisible());
    	    canvas3D.refresh();
		
		}else if(command=="exportDataset"){
			TreeTableModel model = (TreeTableModel) treeTable.getTreeTableModel();
			int i=treeTable.getSelectedRow();
			TreePath path = treeTable.getPathForRow(i);
			SimpleElement selected = (SimpleElement) path.getLastPathComponent();
			ExportConfiguration configuration = ExportObjectDialog.show(this, selected);
			IExporter exporter = ExporterFactory.getExporter(configuration.getType());
			exporter.export(selected, configuration.getSource());
			
		}else if(command=="exportDistance"){
			TreeTableModel model = (TreeTableModel) treeTable.getTreeTableModel();
			int i=treeTable.getSelectedRow();
			TreePath path = treeTable.getPathForRow(i);
			SimpleElement selected = (SimpleElement) path.getLastPathComponent();
			IExporter exporter = ExporterFactory.getExporter(ExportConfiguration.NTS_DISTANCE_TYPE);
			String destination = this.getFileName(true, ".nts", "Export Distance");
			exporter.export(selected, destination);
		}else if(command=="remove"){
			
			
			if (treeTable.getSelectedRowCount()>0){ 
				if (JOptionPane.showConfirmDialog(this,"Are you sure you wan to delete the selected elements from list")== 0 ){	
					TreeTableModel model = (TreeTableModel) treeTable.getTreeTableModel();
					int i=treeTable.getSelectedRow();
					TreePath path = treeTable.getPathForRow(i);
					ComposeSimpleElement node = (ComposeSimpleElement) path.getLastPathComponent();
					ComposeSimpleElement parent = (ComposeSimpleElement) path.getParentPath().getLastPathComponent();
					parent.removeElement(node);
					SimpleElement[] subItems = node.getSubElements();
					for(SimpleElement element : subItems){
						int tabIndex = tabsManager.getTabIndex(element.getName());
						if(tabIndex != -1) tabsManager.removeTab(tabIndex);
					}
					treeTable.updateUI();
			
				}
			  }
		}else if(command=="zoomin"){
			 Canvas3D canvas3D = tabsManager.getCurrentCanvas();
			 SceneManager sceneManager = canvas3D.getSceneManager();
			 canvas3D.iCamera.setFov((canvas3D.iCamera.getFov() + 359) % 360);
			 if (Globalsettings.steroscopyEnabled){
				  canvas3D.iCameraL.setFov(canvas3D.iCamera.getFov());
				  canvas3D.iCameraR.setFov(canvas3D.iCamera.getFov());
		      }
			  Globalsettings.fov=canvas3D.iCamera.getFov();
			  canvas3D.refresh();
		}else if(command=="zoomout"){
			Canvas3D canvas3D = tabsManager.getCurrentCanvas();
			 SceneManager sceneManager = canvas3D.getSceneManager();  
			canvas3D.iCamera.setFov((canvas3D.iCamera.getFov() + 1) % 360);
			  if (Globalsettings.steroscopyEnabled){
				  canvas3D.iCameraL.setFov(canvas3D.iCamera.getFov());
				  canvas3D.iCameraR.setFov(canvas3D.iCamera.getFov());
			  }
			  Globalsettings.fov=canvas3D.iCamera.getFov();
			  canvas3D.refresh();
		}else if(command=="reset"){
			  Canvas3D canvas3D = tabsManager.getCurrentCanvas();
			  canvas3D.iCamera.reset();
	 	      canvas3D.refresh();
		}else if (command=="export"){
			  exportPNG();
		}else if (command=="new"){ 
			  newFile();
			  initProject();
		}
		else if(command=="load"){
			DatasetConfiguration configuration = (DatasetConfiguration) AddObjectDialog.show(this,null, Element3DFactory.DATASET_ELEMENT);
			ComposeSimpleElement dataset = (ComposeSimpleElement) loadFromFile(configuration.getSrc());
			if(dataset==null)return;
			dataset.addAction(new ShowSimpleElementAction(
					"Show Element",
					"showpanel",
					this,
					dataset
					));
			dataset.addAction(new SimpleElementAction(
					"Export Dataset",
					"exportDataset",
					this,
					dataset
					));	
			dataset.addAction(new SimpleElementAction(
					"Remove",
					"remove",
					this,
					dataset
					));		
			dataset.setIcon(Icons.DATASET);
			this.addElement(dataset);
			this.addElement3D(new Element3DDataSet(dataset), configuration);
			this.tabsManager.setCurrentTitle(dataset.getName());
			DatasetDetails detailer = new DatasetDetails();
			reporter.writeReport(detailer.getDetails(dataset));
		}else if(command=="openproject"){
			initProject();
			loadProjectFromFile();
		}
		else if(command=="save"){
			  saveToFile(false);
	    }else if(command=="saveas"){
			  saveToFile(true);
	    }else if(command=="print"){
			  printGraphics();
	    }else if (command=="exit"){
	    	  close();
	    }else if (command=="preferences"){
		      Preferences preferences;
		      preferences=SettingsDialog.show(this,Globalsettings.getSettings(),0);
			  if (null==preferences)return;
			  applySettings(preferences,false,!preferences.getClipBox().equals(Globalsettings.getClipBox()));
	    }else if(command=="filetoolbar"){
			  Preferences preferences=Globalsettings.getSettings();
			  preferences.setFileToolbarVisible(!Globalsettings.fileToolbarVisible);
			  applySettings(preferences,false,false);
	    }else if(command=="edittoolbar"){
	    	  Preferences preferences=Globalsettings.getSettings();
			  preferences.setObjectToolbarVisible(!Globalsettings.objectToolbarVisible);
			  applySettings(preferences,false,false);
	    }else if(command=="statusbar"){
	    	  Preferences preferences=Globalsettings.getSettings();
			  preferences.setStatusbarVisible(!preferences.isStatusbarVisible());
			  applySettings(preferences,false,false);
	    }else if(command.startsWith("mode")){
	  	      Globalsettings.steroscopyEnabled=true;
		      canvas3D.getRenderer().setStereoscopyEnabled(true);
		      this.tgl3D.setSelected(true);
		      Globalsettings.steroscopicMode=Integer.valueOf(command.substring(4,5))-1;
		      canvas3D.stereoMode= Globalsettings.steroscopicMode;
		      canvas3D.refresh();
	    }else if(command=="axissettings"){
	    	Canvas3D canvas3d = tabsManager.getCurrentCanvas();
	    	if(canvas3d != null){
	    	  Preferences localSettings = canvas3d.getSceneManager().getSettings().getSettings();
	    	  localSettings=SettingsDialog.show(this,localSettings,3);
			  if (null==localSettings)return;
			  applySettings(canvas3d, localSettings,false,localSettings.getClipBox().equals(Globalsettings.getClipBox()));
	    	}
	    }else if(command=="scenesettings"){
	    	Canvas3D canvas3d = tabsManager.getCurrentCanvas();
	    	if(canvas3d != null){
	    		Preferences preferences;
		      preferences=SettingsDialog.show(this,canvas3d.getSceneManager().getSettings().getSettings(),2);
			  if (null==preferences)return;
			  applySettings(canvas3d,preferences,false,preferences.getClipBox().equals(Globalsettings.getClipBox()));
	    	}
	    }else if(command=="content" ){
	    	  HelpDialog helpDialog=new HelpDialog(this,"About RESISTIRE","about.html");
	    	  helpDialog.show();
	    }else if(command=="about" ){
	    	  AboutDialog aboutDialog=new AboutDialog(this);
	    	  aboutDialog.show();
	    }else if(command=="homepage"){
	    	 try {
	             //Set your page url in this string. For eg, I m using URL for Google Search engine
	             String url = "http://lucasasecas.github.io/Procrustes3D/";
	             java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
	           }
	           catch (java.io.IOException e){ 
	               System.out.println(e.getMessage());
	           }
	    }else if(command=="showpanel"){
	    	int row = treeTable.getSelectedRow();
	    	TreePath path = treeTable.getPathForRow(row);
	    	SimpleElement node = (SimpleElement) path.getLastPathComponent();
	    	this.tabsManager.showTab(node.getName());    	
	    }
	    else if(command=="checkupdates"){
	    	 try {
	             //Set your page url in this string. For eg, I m using URL for Google Search engine
	             String url = "https://github.com/lucasasecas/Procrustes3D";
	             java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
	           }
	           catch (java.io.IOException e){ 
	               System.out.println(e.getMessage());
	           }
	    }
	  				
		if (command.startsWith("add"))updateTable();
	}
	
	/**
	 * Initializate a new project
	 * 
	 */
	private void initProject() {
		this.tabsManager.reset();
		TreeTableModel treeTableModel = (TreeTableModel) this.treeTable.getTreeTableModel();
		treeTableModel.reset();
		
	}

	/**
	 * Create and
	 * @param configuration
	 */
	public int createEmptyTab(AnalysisConfiguration configuration){
		return tabsManager.newTab(new GraphicsPane(this), configuration.getTabTitle());
	}
	
	public void addElement(SimpleElement elem){
		ArrayList<SimpleElement> list = new ArrayList<SimpleElement>();
		list.add(elem);
		((TreeTableModel) treeTable.getTreeTableModel()).addData((list));
		treeTable.updateUI();
	
	}
	
	private void addElement3D(Element3D element) {
		ArrayList<Element3D> list = new ArrayList<Element3D>();
		list.add(element);
    	Preferences preferences;
		preferences = Commons.setPreferences(list);
		preferences.setLookandFeel(Globalsettings.lookandFeel);
		preferences.setBackColor(Color.WHITE);
	    Canvas3D newCanvas = this.createCanvas(new SceneManager(), new LocalSettings(preferences));
		newCanvas.getSceneManager().addElement(element);
		applySettings(newCanvas,preferences,true,true);
        updateTable();
        dirty=false;

        JXTreeTable treeTableElem3d = new JXTreeTable(new Element3DTreeTableModel(list, newCanvas));
        treeTableElem3d.setSize(120, 120);
        treeTableElem3d.setPreferredScrollableViewportSize(new Dimension(120, 120));
        treeTableElem3d.setAutoscrolls(true);
        treeTableElem3d.addMouseListener(this);
        treeTableElem3d.setRootVisible(false);
        treeTableElem3d.setVisible(true);
        
        treeTableElem3d.addTreeSelectionListener(new customSelectionListener(newCanvas));
        
        JScrollPane paneScrollPane = new JScrollPane(treeTableElem3d);
        paneScrollPane.setVerticalScrollBarPolicy(
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        paneScrollPane.setPreferredSize(new Dimension(250, 155));
        paneScrollPane.setMinimumSize(new Dimension(10, 10));
        
        
		JSplitPane pneSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newCanvas, paneScrollPane);
		pneSplit.setResizeWeight(1);
		// setup the layout

        tabsManager.newTab(pneSplit,"new tab");		
	}
//	
	public void addElement3D(Element3D element3D, DialogConfiguration configuration){
		ArrayList<Element3D> list = new ArrayList<Element3D>();
		list.add(element3D);
    	Preferences preferences = configuration.getGraphPreferences();
	    Canvas3D newCanvas = this.createCanvas(new SceneManager(), new LocalSettings(preferences));
		newCanvas.getSceneManager().addElement(element3D);
		newCanvas.getSceneManager().setAxisVisible(true);
		if(configuration!=null){
			configuration.setGraphPreferences(Commons.setPreferences(list));
			preferences = configuration.getGraphPreferences();
		}
		
		preferences.setLookandFeel(Globalsettings.lookandFeel);
		preferences.setBackColor(Color.WHITE);
		newCanvas.setSettings(new LocalSettings(preferences)); 
		applySettings(newCanvas,preferences,true,true);
        updateTable();
        dirty=false;
              

        GraphicsPane gPane = new GraphicsPane(list, newCanvas, this);
        
       		
        tabsManager.newTab(gPane, configuration==null?"new tab" : configuration.getTabTitle());		
	}
	
	public void insertElement3D(Element3D element3D, DialogConfiguration configuration, int tabIndex){
		ArrayList<Element3D> list = new ArrayList<Element3D>();
		list.add(element3D);
    	Preferences preferences;
		configuration.setGraphPreferences(Commons.setPreferences(list));
		preferences = configuration.getGraphPreferences();
		preferences.setLookandFeel(Globalsettings.lookandFeel);
		preferences.setBackColor(Color.WHITE);
	    Canvas3D newCanvas = this.createCanvas(new SceneManager(), new LocalSettings(preferences));
		newCanvas.getSceneManager().addElement(element3D);
		newCanvas.getSceneManager().setAxisVisible(true);
		newCanvas.getSceneManager().setSettings(new LocalSettings(preferences));
		applySettings(newCanvas,preferences,true,true);
        updateTable();
        dirty=false;
        newCanvas.setSettings(new LocalSettings(configuration.getGraphPreferences()));       
        JXTreeTable treeTableElem3d = new JXTreeTable(new Element3DTreeTableModel(list, newCanvas));
        treeTableElem3d.setSize(120, 120);
        treeTableElem3d.setPreferredScrollableViewportSize(new Dimension(120, 120));
        treeTableElem3d.setAutoscrolls(true);
        treeTableElem3d.addMouseListener(this);
        treeTableElem3d.setRootVisible(false);
        treeTableElem3d.setVisible(true);
        
        treeTableElem3d.addTreeSelectionListener(new customSelectionListener(newCanvas));
        
        JScrollPane paneScrollPane = new JScrollPane(treeTableElem3d);
        paneScrollPane.setVerticalScrollBarPolicy(
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        paneScrollPane.setPreferredSize(new Dimension(250, 155));
        paneScrollPane.setMinimumSize(new Dimension(10, 10));
        
        
		JSplitPane pneSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, newCanvas, paneScrollPane);
		pneSplit.setResizeWeight(1);
		// setup the layout
		pneSplit.setOneTouchExpandable(true);
		
		tabsManager.setTab(tabIndex, pneSplit);
	}


	private void initialiseElement3D(Element3D element3D){
	// check if we need to randomize colors
			sceneManager.setValidName(element3D);
			if (bodyColorRandom) {
				Color fc = ColorUtils.getRandomColor(0.5f, 1.0f);
				Color oc = fc.darker();
				element3D.setLineColor(oc);
				element3D.setFillColor(fc);
			}
	}
	
	/**
	 * Prints the graphics on Canvas
	 */
	private void printGraphics() {
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPrintable(canvas3D);
		if (pj.printDialog()) {
			try {
				pj.print();
			} catch (PrinterException ex){ 
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * returns true if dirty (file is changed and need to be saved) and if necessary shows a dialog box
	 * @param saveMessage
	 * @return
	 */
    public boolean isDirty(String saveMessage){ 
        if (!dirty) return false;
        int result=JOptionPane.showConfirmDialog(this,saveMessage,"File Modified",
            JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        switch (result) {
            case JOptionPane.YES_OPTION:
                if (saveToFile(false)) return false;
                break;
            case JOptionPane.NO_OPTION:
                return false;
        }
        return true;
    }
	
	
	/**
	 * Last Working directories/files
	 */
	private String lastDirectory=null;
	private String lastFileName=null;
	private JScrollPane scrollPane;
	private JButton btnP;
	
	  
    // Just in case I decide to put filename on title bar
    private void setLastFileName(String string){ 
        lastFileName=string;
        if (string==null)string="Untitled Document";
        this.setTitle("RESISTIRE-"+string.substring(string.lastIndexOf(File.separatorChar)+1));

        /*
        final String title="3D Graph Explorer";
        if (parentFrame!=null) 
            if (string==null) parentFrame.setTitle(title+" v1.01");
            else parentFrame.setTitle(title+" - "+string.substring(string.lastIndexOf(File.separatorChar)+1));
        }
        */
    }
    
    /**
     * Exit
     */
    private void close() {
        if (!isDirty("Do you want to save before exit?")) 
        	        	System.exit(0);
    }
    
    /**
     *  erases everything
     */
    private void newFile() {
    	if (!isDirty("Do you want to save before creating a new file?")) {
    		initProject();
    	}
    }
    
    
	/**
	 * returns filename by showing Open/Save dialogue box
	 * @param save flag to select opn/save dialogue
	 * @param ext extention of file  (ext (like ".ser") is ignored for save=false)
	 * @return
	 */
	public String getFileName(boolean save,String ext,String title) {
	    final JFileChooser fileChooser=new JFileChooser();
	    fileChooser.setDialogTitle(title);
	    fileChooser.setCurrentDirectory(new File(lastDirectory==null?System.getProperty("user.dir"):lastDirectory));
	    int result;
	    if (save) result=fileChooser.showSaveDialog(this);
	    else result=fileChooser.showOpenDialog(this);
	    if (result==JFileChooser.APPROVE_OPTION) {
	        String fileName=fileChooser.getSelectedFile().getPath();
	        lastDirectory=fileName;
	        // it also works if the next line is commented out!
	        lastDirectory=lastDirectory.substring(0,lastDirectory.lastIndexOf(File.separatorChar));
	        if (save && fileName.indexOf('.')==-1) fileName+=ext;
	        if (save && (new File(fileName)).exists())
	            if (JOptionPane.showConfirmDialog(this,"File "+fileName+" already exists. Overwrite?","Warning",
	                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.NO_OPTION)
	                return null;
	        return fileName;
	    }
	    else return null;
	}
	
	/**
	 * Shows load dialog and loads file if dialogue is not canceled
	 */
	 private Object loadFromFile() {
	        return loadFromFile(getFileName(false,"tps","Open new file..."));
	 }
	 
	    
	 /**
	  * Loads file if exits and valid
	  * @param fileName file to be loaded
	  */
	 public Object loadFromFile(String fileName) {
		 
	        if (fileName==null) return null;
	        try {
	        	reporter.writeReport("Loading file "+fileName+'\n');
	        	FileLoader loader = LoaderFactory.create(fileName);
	        	Object loadedFile = loader.load(fileName);
	        	reporter.writeReport("file loaded"+'\n'+'\n');
	        	return loadedFile;
	        	
			 } catch (Exception e) {
				 e.printStackTrace();
				 JOptionPane.showMessageDialog(this,e.getMessage()+fileName,"Error",JOptionPane.ERROR_MESSAGE);
			 }
	        return null;
	 }
	 
	 /**
		 * Shows load dialog and loads file if dialogue is not canceled
		 */
		 private void loadProjectFromFile() {
		        loadProjectFromFile(getFileName(false,"ppf","Open new file..."));
		 }
		    
		 /**
		  * Loads file if exits and valid
		  * @param fileName file to be loaded
		  */
		 public void loadProjectFromFile(String fileName) {
		        if (fileName==null) return;
		        try {
		    		  ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
		    		  project = (ProjectSimpleElement) is.readObject();
		    		  ((TreeTableModel) treeTable.getTreeTableModel()).setRoot(project);
		    		  int countOfTabs = is.read();
		   		      for(int i=0; i<countOfTabs; i++){
		   		    	ArrayList<Element3D> elems = (ArrayList<Element3D>) is.readObject();
		   		    	DialogConfiguration conf = new DialogConfiguration();
		   		    	
		   		    	conf = (DialogConfiguration) is.readObject();
		   		    	
		   		    	this.addElement3D(elems.get(0), conf);
		   		    	LocalSettings settings = (LocalSettings) is.readObject();
		   		    	this.applySettings(tabsManager.getCurrentCanvas(),settings.getSettings(), false, true);
		   		    	
		   		    	// os.writeObject(tabsManager.getCanvasAt(i).getSceneManager().getElement3DList());
		   		      }
		    		  
//		    		  ArrayList<Element3D> list=(ArrayList<Element3D>) is.readObject();
//		    		  sceneManager.getElement3DList().clear();
//		    		  sceneManager.getElement3DList().addAll(list);
//		    		  Preferences preferences=(Preferences) is.readObject();
//		    		  preferences.setLookandFeel(Globalsettings.lookandFeel);
//		    		  applySettings(preferences,true,true);
		    		  //canvas3D.setScene(sceneManager.createScene(true));
		 	          //canvas3D.refresh();
		 	          updateTable();
		 	          setLastFileName(fileName);
		 	          dirty=false;
				 } catch (Exception e) {
					 
					 e.printStackTrace();
					  JOptionPane.showMessageDialog(this,e.getMessage()+fileName,"Error",JOptionPane.ERROR_MESSAGE);
				 }
		 }
	 
	 /**
	 * Shows save dialog optionally (for save as ... or when file is being saved for firsttime), and saves, returns true if succesful
	 * @param askName if new filename is to be chosen to save
	 * @return
	 */
	 private boolean saveToFile(boolean askName) {
        String fileName;
        //if (sceneManager.getElementCount()<1)return false;
        if (askName || lastFileName==null) fileName=getFileName(true,".ppf","Save file as ...");
        else fileName=lastFileName;
        if (fileName==null) return false;
        
   	    try {
   		      ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
   		      this.project.setName(fileName.substring(fileName.lastIndexOf("\\")+1, fileName.lastIndexOf(".")));
   		      os.writeObject(project);
   		      int countTabs = tabsManager.getCountOfTabs();
   		      
   		      os.write(countTabs);
   		      for(int i=0; i<countTabs; i++){
   		    	  os.writeObject(tabsManager.getCanvasAt(i).getSceneManager().getElement3DList());
   		    	  DialogConfiguration conf = new DialogConfiguration();
   		    	  conf.setTabTitle(tabsManager.getTitleAt(i));
   		    	  conf.setGraphPreferences(tabsManager.getCanvasAt(i).getSettings().getSettings());
   		    	  os.writeObject(conf);
   		    	  os.writeObject(tabsManager.getCanvasAt(i).getSceneManager().getSettings());
   		      }
   		      
	    	  dirty=false;
	    	  setLastFileName(fileName);
	    	  updateTable();
	 	} catch (IOException e) {
	 		e.printStackTrace();
			  JOptionPane.showMessageDialog(this,"Could not save to file "+fileName,"Warning",JOptionPane.WARNING_MESSAGE);
		      return false;  
		}
        return true;
    }
	 
	/**
	 * Updates table Enteries with elements in scenemanager Element3Dlist
	 * List is to be updated each time the Element3DList of sceneManager is updated
	 */
    private void updateTable(){
    	int countRows = sceneManager.getElementCount();
    	int acum = 0;
    	ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
    	
    	for (int i=0; i<countRows;i++){
    		Element3D elem = sceneManager.getElement3D(i);
    		ArrayList<Object> rowData = new ArrayList<Object>();
    		rowData.add(commonUtils.getobject3DIcon(elem));
    		rowData.add(elem.getName());
    		Checkbox checkbox = new Checkbox();
    		checkbox.setVisible(elem.isVisible());
    		rowData.add(checkbox);
    		data.add(rowData);


    	}
    	Object [][] arrayData = new Object[data.size()][];
		editorPane.updateUI();
		treeTable.updateUI();
    	dirty=true;
    }
    
    /**
     * Saves current Canvas Drawing in PNG Format
     */
    private void exportPNG() {
        String fileName=getFileName(true,".png","Export to image as....");
        if (fileName==null) return;
        try {
        	Canvas3D canvas3d = tabsManager.getCurrentCanvas();
	    	if(canvas3d != null){
	    		canvas3d.saveImage(fileName);
	    	}
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,"Could not export to file "+fileName,"Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
	
    /**
     * Apply settings to a specific canvas
     * @param canvas canvas where will be settings applicated
     * @param preferences preferences that will be applicated to canvas
     * @param skipGuiChanges set it true if gui is not to changed
     * @param reCreateScene set it true if scene is to be reCreated from scratch
     */
    public void applySettings(Canvas3D canvas,Preferences preferences, boolean skipGuiChanges, boolean reCreateScene){
		
		 this.tglAntialias.setSelected(preferences.isAntiAliasingEnabled());
		 canvas.getRenderer().setAntiAliasingEnabled(preferences.isAntiAliasingEnabled());
		 this.tglPerspective.setSelected(preferences.isPerspectiveEnabled());
		 canvas.getRenderer().setPerspectiveEnabled(preferences.isPerspectiveEnabled());
		 canvas.getInteractonHandler().setMouseInteractionAviable(preferences.getMouseInteractionAviable());
		 this.tgl3D.setSelected(preferences.isSteroscopyEnabled());
		 canvas.stereoMode=preferences.getSteroscopicMode();
		 canvas.stereoEnabled=preferences.isSteroscopyEnabled();
		 canvas.getRenderer().setFogEnabled(preferences.isFogEnabled());
		 canvas.getRenderer().setBackgroundColor(preferences.getBackColor());
		 Globalsettings.axisColor=preferences.getAxisColor();
		 bgColorIcon.setBackground(preferences.getBackColor());
		 canvas.getLights()[0].setEnabled(preferences.isLight1Enabled());
		 canvas.getLights()[1].setEnabled(preferences.isLight2Enabled());
		 canvas.getLights()[2].setEnabled(preferences.isLight3Enabled());
		 if (!skipGuiChanges){ 	
			fileToolbar.setVisible(preferences.isFileToolbarVisible());
		    editToolbar.setVisible(preferences.isObjectToolbarVisible());
			LookAndFeel current = UIManager.getLookAndFeel();
			if ((Globalsettings.fileToolbarVisible != preferences.isFileToolbarVisible())|| 
					(Globalsettings.objectToolbarVisible!= preferences.isObjectToolbarVisible())) {
				GridLayout layout;
				if (preferences.isObjectToolbarVisible()
						& preferences.isFileToolbarVisible())
					layout = new GridLayout(2, 1);
				else
					layout = new GridLayout(1, 1);

				if (!preferences.isFileToolbarVisible()) {
					this.pnlToolBar.remove(fileToolbar);
			} else {
					this.pnlToolBar.add(fileToolbar);
				}

				if (!preferences.isObjectToolbarVisible())
					this.pnlToolBar.remove(editToolbar);
				else
					this.pnlToolBar.add(editToolbar);
				pnlToolBar.setLayout(layout);
				
				// pnlToolBar.validate();
				this.validate();
			}
		    // this.editToolbar.setVisible(preferences.isObjectToolbarVisible());
		    this.statusBar.setVisible(preferences.isStatusbarVisible());
			if (!current.getName().equalsIgnoreCase(
					preferences.getLookandFeel())) {
				int choice = JOptionPane.showConfirmDialog(this,
						Messages.getString("dialog.laf.warning.text"),
						Messages.getString("dialog.laf.warning.title"),
						JOptionPane.YES_NO_CANCEL_OPTION);
				// check the user's choice
				if (choice == JOptionPane.YES_OPTION) {
					for (int i = 0; i < mnuLookAndFeel.getItemCount(); i++) {

						mnuLookAndFeel.getItem(i).setIcon(null);
						if (mnuLookAndFeel.getItem(i).getText()
								.equalsIgnoreCase(preferences.getLookandFeel())) {
							String className = mnuLookAndFeel.getItem(i)
									.getActionCommand().replace("laf+", "");
							try {
								// attempt to set the look and feel
								UIManager.setLookAndFeel(className);
								mnuLookAndFeel.getItem(i).setIcon(Icons.CHECK);
								// get the current windows open by this
								// application
								Window windows[] = Window.getWindows();
								// update the ui
								for (Window window : windows) {
									SwingUtilities
											.updateComponentTreeUI(window);
								}
								this.pack();
							} catch (Exception e) {
								System.out.println(e);
							}
							;
						}
					}
				}
			}
		} else{ //if skipguiUpdate =true
				//Save current Gui settings in preference object so that Globalsettings for gui remain unchanged
				preferences.setLookandFeel(Globalsettings.lookandFeel);
				preferences.setFileToolbarVisible(Globalsettings.fileToolbarVisible);
				preferences.setObjectToolbarVisible(Globalsettings.objectToolbarVisible);
				preferences.setStatusbarVisible(Globalsettings.statusbarVisible);
		}
		 canvas.getSettings().saveSettings(preferences);
//	  	Globalsettings.saveSettings(preferences); 
	    canvas.getSceneManager().setClip(new Clip(canvas.getSettings().mappedClipBox));
		canvas.setScene(canvas.getSceneManager().createScene(reCreateScene));
      canvas.refresh();    
    }
    
    
    /**
     * Apply Settings
     * @param skipGuiChanges set it true if gui is not to changed
     * @param recreateScene set it true if scene is to be reCreated from scratch
     */
    public void applySettings(Preferences preferences, boolean skipGuiChanges, boolean reCreateScene){
		
    	Canvas3D canvas3D = tabsManager.getCurrentCanvas();
		 this.tglAntialias.setSelected(preferences.isAntiAliasingEnabled());
		 canvas3D.getRenderer().setAntiAliasingEnabled(preferences.isAntiAliasingEnabled());
		 this.tglPerspective.setSelected(preferences.isPerspectiveEnabled());
		 canvas3D.getRenderer().setPerspectiveEnabled(preferences.isPerspectiveEnabled());
		 this.tgl3D.setSelected(preferences.isSteroscopyEnabled());
		 canvas3D.stereoMode=preferences.getSteroscopicMode();
		 canvas3D.stereoEnabled=preferences.isSteroscopyEnabled();
		 canvas3D.getRenderer().setFogEnabled(preferences.isFogEnabled());
		 canvas3D.getRenderer().setBackgroundColor(preferences.getBackColor());
		 canvas3D.getSettings().axisColor=preferences.getAxisColor();
		 bgColorIcon.setBackground(preferences.getBackColor());
		 canvas3D.getLights()[0].setEnabled(preferences.isLight1Enabled());
		 canvas3D.getLights()[1].setEnabled(preferences.isLight2Enabled());
		 canvas3D.getLights()[2].setEnabled(preferences.isLight3Enabled());
		 if (!skipGuiChanges){ 	
			fileToolbar.setVisible(preferences.isFileToolbarVisible());
		    editToolbar.setVisible(preferences.isObjectToolbarVisible());
			LookAndFeel current = UIManager.getLookAndFeel();
			if ((canvas3D.getSettings().fileToolbarVisible != preferences.isFileToolbarVisible())|| 
					(canvas3D.getSettings().objectToolbarVisible!= preferences.isObjectToolbarVisible())) {
				GridLayout layout;
				if (preferences.isObjectToolbarVisible()
						& preferences.isFileToolbarVisible())
					layout = new GridLayout(2, 1);
				else
					layout = new GridLayout(1, 1);

				if (!preferences.isFileToolbarVisible()) {
					this.pnlToolBar.remove(fileToolbar);
			} else {
					this.pnlToolBar.add(fileToolbar);
				}

				if (!preferences.isObjectToolbarVisible())
					this.pnlToolBar.remove(editToolbar);
				else
					this.pnlToolBar.add(editToolbar);
				pnlToolBar.setLayout(layout);
				
				// pnlToolBar.validate();
				this.validate();
			}
		    // this.editToolbar.setVisible(preferences.isObjectToolbarVisible());
		    this.statusBar.setVisible(preferences.isStatusbarVisible());
			if (!current.getName().equalsIgnoreCase(
					preferences.getLookandFeel())) {
				int choice = JOptionPane.showConfirmDialog(this,
						Messages.getString("dialog.laf.warning.text"),
						Messages.getString("dialog.laf.warning.title"),
						JOptionPane.YES_NO_CANCEL_OPTION);
				// check the user's choice
				if (choice == JOptionPane.YES_OPTION) {
					for (int i = 0; i < mnuLookAndFeel.getItemCount(); i++) {

						mnuLookAndFeel.getItem(i).setIcon(null);
						if (mnuLookAndFeel.getItem(i).getText()
								.equalsIgnoreCase(preferences.getLookandFeel())) {
							String className = mnuLookAndFeel.getItem(i)
									.getActionCommand().replace("laf+", "");
							try {
								// attempt to set the look and feel
								UIManager.setLookAndFeel(className);
								mnuLookAndFeel.getItem(i).setIcon(Icons.CHECK);
								// get the current windows open by this
								// application
								Window windows[] = Window.getWindows();
								// update the ui
								for (Window window : windows) {
									SwingUtilities
											.updateComponentTreeUI(window);
								}
								this.pack();
							} catch (Exception e) {
								System.out.println(e);
							}
							;
						}
					}
				}
			}
		} else{ //if skipguiUpdate =true
				//Save current Gui settings in preference object so that Globalsettings for gui remain unchanged
				preferences.setLookandFeel(canvas3D.getSettings().lookandFeel);
				preferences.setFileToolbarVisible(canvas3D.getSettings().fileToolbarVisible);
				preferences.setObjectToolbarVisible(canvas3D.getSettings().objectToolbarVisible);
				preferences.setStatusbarVisible(canvas3D.getSettings().statusbarVisible);
		}
//	  	Globalsettings.saveSettings(preferences);
		 
	    canvas3D.getSceneManager().setClip(new Clip(canvas3D.getSettings().mappedClipBox));
		canvas3D.setScene(sceneManager.createScene(reCreateScene));
         canvas3D.refresh();    
			
	}
	
	/**
	 * The main method; uses zero arguments in the args array.
	 * @param args the command line arguments
	 */
    public static final void main(String[] args) {
		// attempt to use the nimbus look and feel
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		        	Globalsettings.lookandFeel=UIManager.getLookAndFeel().getName();
			        break;
		        }
		    }
		} catch (Exception e) {
			// completely ignore the error and just use the default look and feel
		}
		
		
		// show the GUI on the EDT
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new CopyOfGui();
			}
		});
	}
	
	class TreeTableModel extends AbstractTreeTableModel{

		
		private String[] columnNames = {
                "Project item list"};
		
		ArrayList<SimpleElement> rootElements;
		
		public TreeTableModel(ProjectSimpleElement elem){
			super(elem);
		}
		
		public void removeElement(String name) {
			
			
		}

		public void reset() {
			root=null;
			
		}

		public void setRoot(ProjectSimpleElement project) {
			this.root = project;
			
		}

		public void addData(ArrayList<SimpleElement> list) {
			ProjectSimpleElement root = (ProjectSimpleElement)this.getRoot();
			root.addAllElement(list);
		}

		public void setData(ArrayList<SimpleElement> elems){
			ProjectSimpleElement root = (ProjectSimpleElement)this.getRoot();
			root.addAllElement(elems);
		}
		
		public void addRootElement(SimpleElement elem){
			ProjectSimpleElement root = (ProjectSimpleElement)this.getRoot();
			root.addElement(elem);
		}
		
		
		public int getColumnCount() {
			return columnNames.length;
		}
		
		 
		    public String getColumnName(int column){ 
		        return columnNames[column];
		    }

		
		public Object getValueAt(Object node, int col){ 
			if(!(node instanceof SimpleElement)){
				return "";
			}
			SimpleElement element = (SimpleElement)node;
			if(col == 0)
				return element.getName();
			return "b";
			
			
			
		}
		
		
		public boolean isLeaf(Object node){
			if(!(node instanceof ComposeSimpleElement) && node != root){
				return true;
			}
			return false;
			
		}
		
		 
		public Class getColumnClass(int c) {
        	if(c == 1)
        		return Boolean.class;
            return getValueAt(0, c).getClass();
        }
		
		
		public Object getChild(Object parent, int index){ 
			
			if(parent instanceof SimpleElement){
				ComposeSimpleElement element = (ComposeSimpleElement) parent;
				SimpleElement el = element.getAllElements().get(index);
				return element.getAllElements().get(index);
			}
			return ((ProjectSimpleElement)root).getAllElements().get(index);
		}

		
		public int getChildCount(Object parent){ 
			if(parent instanceof ComposeSimpleElement){
				ComposeSimpleElement element = (ComposeSimpleElement) parent;
				return element.getAllElements().size();
			}
			return ((ProjectSimpleElement)root).getAllElements().size();
		}

		
		public int getIndexOfChild(Object parent, Object child){ 
			ComposeSimpleElement container = (ComposeSimpleElement) parent;
			SimpleElement containded = (SimpleElement) child;
			return container.getAllElements().indexOf(containded);
			
		}
		
//		class TreeTableCustomElement {
//			SimpleElement element;
//			ArrayList<ContextMenuCommand> commands;
//			
//			/**
//			 * Constructor of TreeTableCustomElement class
//			 * @param element SimpleElement 
//			 * @param commands
//			 */
//			public TreeTableCustomElement(SimpleElement element,
//					ArrayList<ContextMenuCommand> commands) {
//				this.element = element;
//				this.commands = commands;
//			}
//
//			public SimpleElement getElement() {
//				return element;
//			}
//
//			public void setElement(SimpleElement element) {
//				this.element = element;
//			}
//
//			public ArrayList<ContextMenuCommand> getCommands() {
//				return commands;
//			}
//
//			public void setCommands(ArrayList<ContextMenuCommand> commands) {
//				this.commands = commands;
//			}
//
//		}
		
		
	}
	
	
	public void mouseEntered(MouseEvent arg0){ 
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
	public void mousePressed(MouseEvent arg0) {
		 
	}


	
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		TreeTableModel model = (TreeTableModel) treeTable.getTreeTableModel();
		 int i=treeTable.getSelectedRow();
		 if (i<0)return;
		 
		TreePath path = treeTable.getPathForRow(i);
		SimpleElement node = (SimpleElement) path.getLastPathComponent();

//		if(arg0.isMetaDown()){
//			JMenu menu = LeftTableMenuFactory.createMenu(node, this);
//			menu.setAlignmentX(arg0.getX());
//			menu.setAlignmentY(arg0.getY());
//			menu.show();
//			
//		}
	}
	
	class TreeCellRenderer extends DefaultTreeCellRenderer{

		
		public Component  getTreeCellRendererComponent(JTree tree, Object value, 
				boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus){
			
			if( value instanceof SimpleElement){
				this.setIcon(((SimpleElement) value).getIcon());
				this.setText(((SimpleElement)value).getName());
			}
			return this;
			
		}
	}
 
	public class Element3DTreeTableModel extends AbstractTreeTableModel {

		private Canvas3D canvas;
		
		private String[] columnNames = {
                "Element", 
                "Show"};
		
		public ArrayList<Element3D> rootElements;
		
		public Element3DTreeTableModel(ArrayList<Element3D> elems, Canvas3D canvas){
			super(new Object());
			this.canvas = canvas;
			rootElements = elems;
		}
		
		public void addData(ArrayList<Element3D> list) {
			rootElements.addAll(list);
			
		}

		public void setData(ArrayList<Element3D> elems){
			rootElements = elems;
		}
		
		public void addRootElement(Element3D elem){
			rootElements.add(elem);
		}
		
		
		public int getColumnCount() {
			return columnNames.length;
		}
		
		 
		    public String getColumnName(int column){ 
		        return columnNames[column];
		    }

		
		public Object getValueAt(Object node, int col){ 
			if(!(node instanceof Element3D)){
				return "";
			}
			Element3D element = (Element3D)node;
			switch(col){
			case 0:
				String name =element.getName();
		     	   return name;
			case 1: 
				return element.isVisible();
			}
			return "b";
			
			
			
		}

		/*
         * Don't need to implement this method unless your table's
         * editable.
         */
		
        public boolean isCellEditable(Object node, int col){ 
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (node instanceof Element3D && col == 1) {
                return true;
            } else {
                return false;
            }
        }
		
		
		public boolean isLeaf(Object node){
			if(node instanceof Element3D){
				Element3D elem = (Element3D) node;
				if(elem.isElementContainer())
					return false;
				return true;
			}
			return false;
			
		}
		
		 
		public Class getColumnClass(int c) {
        	if(c == 1)
        		return Boolean.class;
            return getValueAt(0, c).getClass();
        }
		
		
		public Object getChild(Object parent, int index){ 
			
			if(parent instanceof Element3D){
				Element3D element = (Element3D) parent;
				return element.getContainedElements().get(index);
			}
			return rootElements.get(index);
		}

		
		public int getChildCount(Object parent){ 
			if(parent instanceof Element3D){
				Element3D element = (Element3D) parent;
				return element.getContainedElements().size();
			}
			return rootElements.size();
		}

		
		public int getIndexOfChild(Object parent, Object child){ 
			Element3D container = (Element3D) parent;
			Element3D containded = (Element3D) child;
			return container.getContainedElements().indexOf(containded);
			
		}
		
		
		public void setValueAt(Object value, Object node, int col){
			if(col == 1 && node instanceof Element3D){
				Element3D elem = (Element3D) node;
				boolean val  = (Boolean)value;
				elem.setVisible(val);
        	    canvas.setScene(canvas.getSceneManager().createScene(false));
        	    Preferences preferences = Globalsettings.getSettings();
     	        preferences = Commons.setPreferences(sceneManager.getElement3DList());
     	        canvas.refresh();
     	        String name = elem.getName(); 
        	    System.out.println("Visibility of:" + elem.getName() +"="+(Boolean)val);
			}
		}		
	}

	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount()==2){
			//start extract treetable from panel
			JXTreeTable table = null;
			try{
				GraphicsPane graphicsPanel = (GraphicsPane) tabsManager.getCurrentTab();
				table = graphicsPanel.getTreeTable();
			}catch(Exception err){
				err.printStackTrace();
			}
			if(table == null ) return;
			int i = table.getSelectedRow();
			Element3D selectedNode = (Element3D) table.getPathForRow(i).getLastPathComponent();
			Element3DTreeTableModel model = (Element3DTreeTableModel) table.getTreeTableModel();
			Element3DCollection root = (Element3DCollection) model.rootElements.get(0);
			String title = EditElement3DDialog.show(this, root, i, tabsManager.getCurrentTitle());
			tabsManager.setCurrentTitle(title);
			Canvas3D current = tabsManager.getCurrentCanvas();
			Scene3D scene = current.getSceneManager().createScene(true);
			current.setScene(scene);
			current.refresh();	
		}		
	}

	public void addProcrustesAnalisys(ComposeSimpleElement result2, AnalysisConfiguration configuration, int index) {
		
		//Generate 3dElements
		result2.addAction(new ShowSimpleElementAction(
				"Show Element",
				"showpanel",
				this,
				result2
				));
		result2.addAction(new ShowSimpleElementAction(
				"Export Dataset",
				"exportDataset",
				this,
				result2
				));
		
		result2.addAction(new SimpleElementAction(
				"Remove",
				"remove",
				this,
				result2
				));
		
		int i=this.treeTable.getSelectedRow();
		TreePath path = treeTable.getPathForRow(i);
		ComposeSimpleElement selected = (ComposeSimpleElement) path.getLastPathComponent();
		result2.setIcon(Icons.DATASET);
		selected.addElement(result2);
		Element3DDataSet dataset3D = new Element3DDataSet(result2);

		if(configuration.getShowConsensus()){
			Element3DEntity consensus = new Element3DEntity((SampleSimpleElement)result2.getElementByKey("consensus"));
			dataset3D.add(consensus);
		}
		GraphicsPane graphicPane = (GraphicsPane) tabsManager.getTabAt(index);
		
//		Populate graphicsPane with elements
		Canvas3D newCanvas = this.createAndPopulateCanvas(dataset3D, configuration);
		ArrayList<Element3D> elementsList = new ArrayList<Element3D>();
		elementsList.add(dataset3D);
		graphicPane.addElements3D(elementsList, newCanvas);
		//this.addElement3D(dataset3D, configuration);
		tabsManager.setTitleTabAt(index, configuration.getTabTitle());
		ProcrustesFitDetalier detailer = new ProcrustesFitDetalier();
		reporter.writeReport("New procrustes fit analysis generated"+'\n');
		reporter.writeReport("Type of analysis: "+ (configuration.getType() == AnalysisConfiguration.MIN_SQUARES_FIT ? "Least squares fit" : "Robusts fit")+'\n');
		reporter.writeReport("Data Source: " + selected.getName() + '\n');
		reporter.writeReport(detailer.getDetails(result2)+'\n'+'\n'+'\n');
		
	}

	private Canvas3D createAndPopulateCanvas(Element3DCollection element3D, AnalysisConfiguration configuration) {
		ArrayList<Element3D> list = new ArrayList<Element3D>();
		list.add(element3D);
    	Preferences preferences;
		configuration.setGraphPreferences(Commons.setPreferences(list));
		preferences = configuration.getGraphPreferences();
		preferences.setLookandFeel(Globalsettings.lookandFeel);
		preferences.setBackColor(Color.WHITE);
	    Canvas3D newCanvas = this.createCanvas(new SceneManager(), new LocalSettings(preferences));
		newCanvas.getSceneManager().addElement(element3D);
		newCanvas.getSceneManager().setAxisVisible(true);
		newCanvas.getSceneManager().setSettings(new LocalSettings(preferences));
		applySettings(newCanvas,preferences,true,true);
        updateTable();
        dirty=false;
        newCanvas.setSettings(new LocalSettings(configuration.getGraphPreferences()));       
        
		return newCanvas;
	}


	public void stateChanged(ChangeEvent e) {
		Canvas3D canvas3d = this.tabsManager.getCurrentCanvas();
		if(canvas3d != null){
			SceneManager sceneManager = canvas3d.getSceneManager();
			
			this.tglGridXY.setSelected(sceneManager.isGridXYVisible());
			this.tglAxis.setSelected(sceneManager.isAxisVisible());
			this.tglBox.setSelected(sceneManager.isBoxVisible());
			bgColorIcon.setBackground(canvas3d.getRenderer().getBackgroundColor());
		}
	}

	public TabsManager getTabsManager() {
		return this.tabsManager;
	}
	 
}
