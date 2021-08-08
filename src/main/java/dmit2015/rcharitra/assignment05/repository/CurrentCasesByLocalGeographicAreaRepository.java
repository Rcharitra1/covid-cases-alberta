package dmit2015.rcharitra.assignment05.repository;

import dmit2015.rcharitra.assignment05.entity.CurrentCasesByLocalGeographicArea;
//import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.builder.DSL;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Polygon;
import org.geolatte.geom.codec.Wkt;
import org.geolatte.geom.crs.CoordinateReferenceSystems;


import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @Version 2021-04-03
 * @Author R Charitra
 * @Comments
 * This is a Repository class that extends Abstract Repository
 * It adds a method to find a covid19 details by location
 *
 * */

import java.util.Optional;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@ApplicationScoped
@Transactional
public class CurrentCasesByLocalGeographicAreaRepository extends AbstractJpaRepository<CurrentCasesByLocalGeographicArea, Long> {

    public CurrentCasesByLocalGeographicAreaRepository() {
        super(CurrentCasesByLocalGeographicArea.class);




    }

//    public CurrentCasesByLocalGeographicArea contains(double longitude, double latitude) {
//        String jpql = "SELECT a FROM CurrentCasesByLocalGeographicArea a WHERE contains(a.polygon, :pointValue) = true";
//        TypedQuery<CurrentCasesByLocalGeographicArea> query =
//                getEntityManager().createQuery(jpql, CurrentCasesByLocalGeographicArea.class);
////        Point<G2D> point = DSL.point(WGS84, DSL.g(-113.7779854, 52.2815365));
//        Point<G2D> point = DSL.point(WGS84, DSL.g(longitude, latitude));
//        query.setParameter("pointValue", point);
//        return query.getSingleResult();
//    }

    public Optional<CurrentCasesByLocalGeographicArea> contains(double longitude, double
            latitude) {
        Optional<CurrentCasesByLocalGeographicArea> optionalSingleResult =
                Optional.empty();
        final String jpql = "SELECT a FROM CurrentCasesByLocalGeographicArea a WHERE contains(a.polygon, :pointValue) = true";
        TypedQuery<CurrentCasesByLocalGeographicArea> query =
                getEntityManager().createQuery(jpql, CurrentCasesByLocalGeographicArea.class);
        Point<G2D> point = DSL.point(WGS84, DSL.g(longitude, latitude));
        query.setParameter("pointValue", point);
        try {
            CurrentCasesByLocalGeographicArea singleResult = query.getSingleResult();
            optionalSingleResult = Optional.of(singleResult);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optionalSingleResult;
    }



}