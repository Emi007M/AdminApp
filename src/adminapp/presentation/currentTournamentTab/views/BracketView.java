/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminapp.presentation.currentTournamentTab.views;

import java.util.ArrayList;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import serializable.model.Chart;
import serializable.model.Node;

/**
 *
 * @author Emilia
 */
public class BracketView extends Region {

    private ArrayList<Node> matches;
    //  private Node current;

    private int vert_gap;
    private int match_width;
    private int top_offset;

    private String title;

    BracketView(ArrayList<Node> matches, String title) {
        this.matches = matches;
        this.title = title;
        this.setId("bracket-chart");

        vert_gap = 70;
        match_width = 220;

        top_offset = 50;

        initTitle();
        initBracket();
    }

    private void initTitle() {
        Label t = new Label(title);
        t.getStyleClass().add("title");
        t.relocate(50, 0);
        this.getChildren().add(t);
    }

    private void initBracket() {
        int lvl = matches.get(0).getChartLvl();
        int cur_lvl = lvl;
        int m_amount = matches.size();
        int j = 0;

        boolean firstColumn = true;

        for (int i = 0; i < m_amount; i++) {
            if (cur_lvl > matches.get(i).getChartLvl()) {
                cur_lvl = matches.get(i).getChartLvl();
                firstColumn = false;
                j = 0;
            }
            int tmp = lvl - cur_lvl;
            int posX = (tmp) * 300 + 30; //horizontal distance
            int posY = (int) (Math.pow(2, tmp) * vert_gap + j++ * 2 * vert_gap * (Math.pow(2, tmp)) + top_offset); // vertical distance + top shift

            this.addMatch(matches.get(i), posX, posY, firstColumn);

            if (cur_lvl < lvl) {
                addJoining(posX, posY, (int) ((Math.pow(2, tmp - 1)) * vert_gap));
            }

        }

        addDottedLine(lvl * 300 + 30, (int) (Math.pow(2, lvl) * vert_gap));

        addLvlNames();

    }

    private void addMatch(Node match, int posX, int posY, boolean isFirstLvl) {

        //prepare names
        String akaName = " ";
        String akaClub = " ";
        if (match.getAka() != null && match.getAka().getAthlete() != null) {
            akaName = match.getAka().getAthlete().getFullName().toUpperCase();
            akaClub = match.getAka().getAthlete().getClub();
        }
        String akaScore = (match.getScoreAka() != null) ? match.getScoreAka().toString() : "";

        String shiroName = " ";
        String shiroClub = " ";
        if (match.getShiro() != null && match.getShiro().getAthlete() != null) {
            shiroName = match.getShiro().getAthlete().getFullName().toUpperCase();
            shiroClub = match.getShiro().getAthlete().getClub();
        }
        String shiroScore = (match.getScoreShiro() != null) ? match.getScoreShiro().toString() : "";

        if (isFirstLvl && (" ".equals(akaName) || " ".equals(shiroName))) {
            akaName = shiroName = akaClub = shiroClub = " ";
        }

        //distances
        int distY = 50;
        int distClub = 20;
        int distScore = match_width;
        int icoMargin = 10;
        int icoSize = 20;
        int textShift = 15;

        //competitor color icons
        Rectangle akaIco = new Rectangle(icoSize, icoSize);
        akaIco.getStyleClass().add("redSquare");

        akaIco.relocate(posX, posY);
        Rectangle shiroIco = new Rectangle(icoSize, icoSize);
        shiroIco.getStyleClass().add("whiteSquare");
        shiroIco.relocate(posX, posY + distY);

        //setFill(Color.rgb(222, 222, 222)); //text color
        Label a1 = new Label(akaName);
        a1.relocate(posX + icoSize + icoMargin, posY - textShift);
        Label a2 = new Label(akaClub);
        a2.relocate(posX + icoSize + icoMargin, posY + distClub - textShift);
        a2.getStyleClass().add("club");
        Label a3 = new Label(akaScore);
        a3.relocate(posX + distScore, posY + 0 - textShift + 5);
        a3.getStyleClass().add("score");

        Label s1 = new Label(shiroName);
        s1.relocate(posX + icoSize + icoMargin, posY + distY - textShift);
        Label s2 = new Label(shiroClub);
        s2.relocate(posX + icoSize + icoMargin, posY + distClub + distY - textShift);
        s2.getStyleClass().add("club");
        Label s3 = new Label(shiroScore);
        s3.relocate(posX + distScore, posY + distY - textShift + 5);
        s3.getStyleClass().add("score");

        //make ranked names bold
        if (match.getAka() != null && match.getAka().getAthlete() != null && match.getAka().getAthlete().getRank() != 0) {
            a1.getStyleClass().add("ranked");
        }
        if (match.getShiro() != null && match.getShiro().getAthlete() != null && match.getShiro().getAthlete().getRank() != 0) {
            a1.getStyleClass().add("ranked");
        }

        Line l = new Line(posX, posY + distY - 15, posX + distScore + 20, posY + distY - 15);
        l.getStyleClass().add("line");

        //create match region and add it to the chart
        Pane m = new Pane();
        m.getChildren().setAll(akaIco, shiroIco, a1, a2, a3, s1, s2, s3, l);
        this.getChildren().addAll(m);

    }

    private void addJoining(int posX, int posY, int height) {
        int width = 20;
        int dist = height;

        posX -= width + 10;
        posY += 35;

        Line l_top = new Line(posX - width, posY - dist, posX, posY - dist);
        l_top.getStyleClass().add("line");

        Line l_bottom = new Line(posX - width, posY + dist, posX, posY + dist);
        l_bottom.getStyleClass().add("line");

        Line l_middle = new Line(posX, posY, posX + width, posY);
        l_middle.getStyleClass().add("line");

        Line l_vert = new Line(posX, posY - dist, posX, posY + dist);
        l_vert.getStyleClass().add("line");

        this.getChildren().addAll(l_top, l_bottom, l_middle, l_vert);
    }

    private Pane lvlNames = new Pane();

    private void addLvlNames() {
        int i = matches.get(0).getChartLvl();
        int j = 0;

        ArrayList<Label> labels = new ArrayList<>();
        for (; i >= 0; i--) {
            Label l = new Label(Chart.getLvlToString(i).toUpperCase());
            l.relocate(j++ * 300 + 30, 50);
            l.getStyleClass().add("lvl");
            l.getStyleClass().add("lvl-" + i);
            l.setStyle("-fx-min-width: " + (match_width + 50) + ";");
            labels.add(l);
        }

        lvlNames = new Pane();
        lvlNames.getChildren().addAll(labels);
        this.getChildren().addAll(lvlNames);

    }

    /**
     * adds line on bracket chart indicating half of the chart
     *
     * @param posX
     * @param posY
     */
    private void addDottedLine(int posX, int posY) {
        Line l = new Line(30, posY + 35 + top_offset, posX - 10, posY + 35 + top_offset);
        l.getStyleClass().add("line");
        l.getStyleClass().add("dotted-line");
        l.getStrokeDashArray().addAll(30d, 30d);

        this.getChildren().add(l);
    }

    /**
     * Sets names of levels for the bracket chart vertically fixed
     *
     * @param bracketScrollPane
     */
    void setScrollFixed(ScrollPane bracketScrollPane, Group scrollContent, StackPane zoomGroup) {
        zoomGroup.setMaxHeight(zoomGroup.getHeight());

        DoubleProperty scrollVvalue = bracketScrollPane.vvalueProperty();
        DoubleProperty contentHeight = new SimpleDoubleProperty();

        scrollContent.boundsInLocalProperty().addListener(
                (ObservableValue<? extends Bounds> observableValue, Bounds bounds, Bounds bounds2)
                -> {
            contentHeight.set(bounds2.getHeight());
        }
        );

        // fix lvlNames vertically
        lvlNames.layoutYProperty().bind(
                // to vertical scroll shift <0;1>
                scrollVvalue
                        // multiplied by (scrollableAreaHeight - visibleViewportHeight)
                        .multiply(
                                contentHeight
                                        .subtract(new ViewportHeightBinding(bracketScrollPane))
                        ).divide(zoomGroup.scaleYProperty())//.divide(new ZoomBinding(zoomGroup))
        );


        zoomGroup.setMinSize(10.0, 10.0);

        //
        //not sure yet if this will have to be 
        zoomGroup.setPrefSize(
                lvlNames.getParent().getParent().getLayoutX(),
                lvlNames.getParent().getParent().getLayoutY()
        );


    }

    private static class ViewportHeightBinding extends DoubleBinding {

        private final ScrollPane root;

        public ViewportHeightBinding(ScrollPane root) {
            this.root = root;
            super.bind(root.viewportBoundsProperty());
        }

        @Override
        protected double computeValue() {
            return root.getViewportBounds().getHeight();
        }
    }

}
