
package com.rps.app.resources;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;


import com.rps.utils.ImageUtils;

/**
 * Class storing all the icons used by the application.
 */
public class Icons {
	// sandbox icons
	public static Icon getColorIcon(Color color){
		return new ColorIcon(color);
	}
	
	/**app icon */
	public static final ImageIcon APP = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/app-icon.png");
		
	/** New icon */
	public static final ImageIcon NEW = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/new-icon.png");
	
	/** Open icon */
	public static final ImageIcon OPEN = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/open-icon.png");
	
	/** Save icon */
	public static final ImageIcon SAVE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/save-icon.png");

	/** Save As.. icon */
	public static final ImageIcon SAVEAS = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/saveas-icon.png");
	
	/** Export as Image icon */
	public static final ImageIcon EXPORT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/export-icon.png");
	
	/** Print icon */
	public static final ImageIcon PRINT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/print-icon.png");
	
	/** Look and feel icon */
	public static final ImageIcon LOOKANDFEEL = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/lookandfeel-icon.png");
	
	/** calculator icon */
	public static final ImageIcon CALCULATOR = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/calculator-icon.png");
	
	/** toolbar icon */
	public static final ImageIcon TOOLBAR = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/toolbar-icon.png");
	
	/** statusbar icon */
	public static final ImageIcon STATUSBAR = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/statusbar-icon.png");
	
	/** Prefereneces icon */
	public static final ImageIcon PREFERENCES = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/preferences-icon.png");
	
	/** Check icon */
    public static final ImageIcon CHECK = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/check-icon.png");

    /** Content icon */
    public static final ImageIcon CONTENT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/content-icon.png");

      /** Goto Home Page icon */
    public static final ImageIcon GOTOHOMEPAGE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/gotohomepage-icon.png");

    /** CheckforUpdates icon */
    public static final ImageIcon CHECKUPDATE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/checkupdate-icon.png");
    
    /** About icon */
    public static final ImageIcon ABOUT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/about-icon.png");

	/** Generic add icon */
	public static final ImageIcon ADDDATASET = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addDataset-icon.png");
	
	/** Generic remove icon */
	public static final ImageIcon REMOVE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/remove-icon.png");
	
    /** Zoom in icon */
	public static final ImageIcon ZOOM_IN = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/zoom-in-icon.png");
	
	/** Zoom out icon */
	public static final ImageIcon ZOOM_OUT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/zoom-out-icon.png");
	
	/**Reset Camera icon */
	public static final ImageIcon RESET = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/reset-icon.png");
	
	/**Toggle Axis icon */
	public static final ImageIcon AXIS = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/axis-icon.png");
	
	/**Toggle Bounding Box icon */
	public static final ImageIcon BOX = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/box-icon.png");
	
	/**Toggle GridXY  icon */
	public static final ImageIcon GRIDXY = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/gridxy-icon.png");
	
	/**Toggle Light icon */
	public static final ImageIcon LIGHT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/light-icon.png");
	
	/**Toggle Bounding Box icon */
	public static final ImageIcon STEREOSCOPE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/3D-icon.png");
	
	/**Toggle Bounding Box icon */
	public static final ImageIcon PERSPECTIVE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/perspective-icon.png");
	
	/**Group of landmarks icons*/
	public static final ImageIcon ENTITY = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/points-icon.png");
	
	/**Label DrawMode icon */
	public static final ImageIcon DRAWMODE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/drawmode-icon.png");
	
	/**Label Hidden Surface removal icon */
	public static final ImageIcon HSR = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/hsr-icon.png");
	
	/**Edt_ pointt Button */
	public static final ImageIcon POINT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/point-icon.png");
	
	/**Edt_ pointt Button */
	public static final ImageIcon VECTOR = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/vector-icon.png");
	
	/**Edt_ pointt Button */
	public static final ImageIcon LINE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/line-icon.png");
	
	/**Edt_ pointt Button */
	public static final ImageIcon CURVE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/curve-icon.png");
	
	/**Edt_ pointt Button */
	public static final ImageIcon CURVE2DIMPLICIT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/curve2dimplicit-icon.png");
	
	/**Edt_ pointt Button */
	public static final ImageIcon CURVE2DCARTESIAN = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/curve2dcartesian-icon.png");
	
	/**Edt_ pointt Button */
	public static final ImageIcon SURFACE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/surface-icon.png");
	
	/**Edt_ pointt Button */
	public static final ImageIcon SURFACEPARAMETRIC = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/surfaceparametric-icon.png");
	
	/**Edt_ pointt Button */
	public static final ImageIcon SURFACEIMPLICIT= ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/implicit-icon.png");
	
	/**Edit_ plane Button */
	public static final ImageIcon PLANE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/plane-icon.png");
	
	/**Edit_ plane Button */
	public static final ImageIcon POLYGON = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/polygon-icon.png");
	
	/**Edit_ primitive Button */
	public static final ImageIcon PREMITIVE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/premitive-icon.png");

	/**Edt_Add pointt Button */
	public static final ImageIcon ADDPOINT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addpoint-icon.png");
	
	/**Edt_Add pointt Button */
	public static final ImageIcon ADDVECTOR = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addvector-icon.png");
	
	/**Edt_Add pointt Button */
	public static final ImageIcon ADDLINE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addline-icon.png");
	
	/**Edt_Add pointt Button */
	public static final ImageIcon ADDCURVE3D = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addcurve3d-icon.png");
	
	/**Edt_Add pointt Button */
	public static final ImageIcon ADDCURVE2DCARTESIAN = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addcurve2dcartesian-icon.png");
	
	/**Edt_Add pointt Button */
	public static final ImageIcon ADDCURVE2DIMPLICIT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addcurve2dimplicit-icon.png");

	/**Edt_Add pointt Button */
	public static final ImageIcon ADDSURFACE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addsurface-icon.png");
	
	/**Edt_Add pointt Button */
	public static final ImageIcon ADDSURFACEPARAMETRIC = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addsurfaceparametric-icon.png");
	
	/**Edt_Add implcit function Button */
	public static final ImageIcon ADDSURFACEIMPLICIT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addimplicit-icon.png");

	/**Edit_Add plane Button */
	public static final ImageIcon ADDPLANE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addplane-icon.png");
	
	/**Edit_Add plane Button */
	public static final ImageIcon ADDPOLYGON = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addpolygon-icon.png");
	
	/**Edit_Add plane Button */
	public static final ImageIcon ADDPREMITIVE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addpremitive-icon.png");
	
	/**Edit_Add plane Button */
	public static final ImageIcon REMOVEELEMENT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/remove-icon.png");
	
	/**Edit_Antialias  Button */
	public static final ImageIcon ANTIALIAS = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/antialias-icon.png");
	
	/**Edit_Antialias  Button */
	public static final ImageIcon SCENESETTINGS = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/scenesettings-icon.png");
	
	/**Home Icon */
	public static final ImageIcon HOME = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/home-icon.png");

	/**Back navigation Icon */
	public static final ImageIcon BACK = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/back-icon.png");

	/**Forward navigation Icon */
	public static final ImageIcon FORWARD = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/forward-icon.png");

	public static final ImageIcon CM = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addProcrustesAnalysis-icon.png");
	
	public static final ImageIcon DISTANCE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/line-icon.png");
	
	public static final ImageIcon PROJECT = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/project.png");
	
	public static final ImageIcon DATASET = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/dataset.png");

	public static final ImageIcon EXPORTDATASET = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/exportDataset.png"); 
	
	public static final ImageIcon PROJECTION = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/addProjection-icon.png");;
	
	public static final ImageIcon CLOSE = ImageUtils.getIconFromClassPathSuppressExceptions("/com/rps/app/resources/close.png");
	
}
