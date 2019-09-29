package com.monkey.api;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.sikuli.basics.Debug;
import org.sikuli.basics.Settings;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;

import com.monkey.core.driver.MonkeyDriver;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.utils.Utils;

public class MonkeyOCR {

    private final double DEFAULT_MIN_SIMILARITY = 0.8;
    private Point2D coordinate;

    /**
     * getCoords returns the coordinates of the FIRST element that matches the
     * specified
     *
     * @param baseImg       is the screenshot of the device
     * @param targetImgPath is the image of the element that you want to find
     * @return coordinates of the centre of the element found as Point2D
     */
    public Point2D getCoords(final BufferedImage baseImg, final String targetImgPath) {
        final Match m;
        final Finder f = new Finder(baseImg);
        Utils.isFileExisted(targetImgPath);
        final Point2D coords = new Point2D.Double(-1, -1);

        f.find(targetImgPath);
        if (f.hasNext()) {
            m = f.next();
            coords.setLocation(m.getTarget().getX(), m.getTarget().getY());
        }
        return coords;
    }

    public Point2D getCoords(final String targetImgPath) {
        final Match m;
        final Finder f = new Finder(this.takeScreenshot());
        final Point2D coords = new Point2D.Double(-1, -1);
        Utils.isFileExisted(targetImgPath);

        f.find(targetImgPath);
        if (f.hasNext()) {
            m = f.next();
            coords.setLocation(m.getTarget().getX(), m.getTarget().getY());
        }
        return coords;
    }

    public Point2D getCoords(final BufferedImage baseImg, final String targetImgPath, final double minSimilarityValue) {
        // set new minimum similarity
        Settings.MinSimilarity = minSimilarityValue;
        final Match m;
        final Finder f = new Finder(baseImg);
        final Point2D coords = new Point2D.Double(-1, -1);
        Utils.isFileExisted(targetImgPath);

        f.find(targetImgPath);
        if (f.hasNext()) {
            m = f.next();
            coords.setLocation(m.getTarget().getX(), m.getTarget().getY());
        }
        // revert to default similarity
        Settings.MinSimilarity = this.DEFAULT_MIN_SIMILARITY;
        return coords;
    }

    public Point2D getCoords(final String targetImgPath, final double minSimilarityValue) {
        // set new minimum similarity
        Settings.MinSimilarity = minSimilarityValue;
        final Match m;
        final Finder f = new Finder(this.takeScreenshot());
        final Point2D coords = new Point2D.Double(-1, -1);
        Utils.isFileExisted(targetImgPath);

        f.find(targetImgPath);
        if (f.hasNext()) {
            m = f.next();
            coords.setLocation(m.getTarget().getX(), m.getTarget().getY());
        }
        // revert to default similarity
        Settings.MinSimilarity = this.DEFAULT_MIN_SIMILARITY;
        return coords;
    }

    /**
     * getCoords returns a list of coordinates of all the matches found for the
     * element specified
     *
     * @param targetImgPath is the image of the element that you want to find
     * @return list of coordinates of the matches found for the element
     * specified
     */
    public List<Point2D> getCoordsForAllMatchingElements(final String targetImgPath, final double minSimilarityValue) {
        Settings.MinSimilarity = minSimilarityValue;
        final Finder f = new Finder(this.takeScreenshot());
        final List<Point2D> coordsList = new ArrayList<>();
        Match m;
        Utils.isFileExisted(targetImgPath);

        f.findAll(targetImgPath);
        while (f.hasNext()) {
            m = f.next();
            coordsList.add(new Point2D.Double(m.getTarget().getX(), m.getTarget().getY()));
        }
        Settings.MinSimilarity = this.DEFAULT_MIN_SIMILARITY;
        return coordsList;
    }

    /**
     * Convenience method that takes a screenshot of the device and returns a
     * BufferedImage for further processing.
     *
     * @return screenshot from the device as BufferedImage
     */
    public BufferedImage takeScreenshot() {
        Debug.setDebugLevel(3);
        final MonkeyDriver driver = ExecutionManager.getMonkeyDriver();
        final File scrFile = driver.getScreenshotAs(OutputType.FILE);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(scrFile);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    /**
     * Convenience method that returns true if the element is visible on the
     * screen. Used as an expected condition in waitUntilImageExists
     */
    public boolean isImageExists(final String targetImgPath, final double minSimilarityValue) {
        final Point2D coords;
        Utils.isFileExisted(targetImgPath);

        coords = this.getCoords(this.takeScreenshot(), targetImgPath, minSimilarityValue);
        return (coords.getX() >= 0) && (coords.getY() >= 0);
    }

    public Point2D getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(final Point2D coordinate) {
        this.coordinate = coordinate;
    }

}
