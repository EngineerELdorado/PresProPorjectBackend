package com.example.PresProProject;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public final class GeoUtils {


        public static Geometry wktToGeometry(String wellKnownText) throws ParseException {
            return new WKTReader().read(wellKnownText);
        }

}
