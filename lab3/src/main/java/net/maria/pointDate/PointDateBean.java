package net.maria.pointDate;

import net.maria.Model.Point;
import net.maria.services.BDClass;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@SessionScoped
@ManagedBean
public class PointDateBean implements Serializable {

    UUID session_id = UUID.randomUUID();

    private final BDClass bdClass = new BDClass();

    private Point newPoint;


    public List<Point> getPointsTable(){
        return bdClass.getPoints(this.session_id.toString());
    }

    public PointDateBean() {
        this.newPoint = new Point();
    }

    public void setLastR(){
        if(getPointsTable().size() != 0)
            newPoint.setR(getPointsTable().get(0).getR());
    }

    public void doCode(){
        newPoint.setSession_id(session_id.toString());
        newPoint.check();
        bdClass.addPointToTable(newPoint);
        newPoint = new Point();
        setLastR();
    }

    public void setNewPoint(Point newPoint) {
        this.newPoint = newPoint;
    }

    public Point getNewPoint() {
        return newPoint;
    }
}
