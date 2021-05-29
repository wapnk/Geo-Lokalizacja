package rest.geolocalization.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rest.geolocalization.models.Geo;

import java.util.List;

@Repository
public interface GeoRepository extends CrudRepository<Geo,Integer>
{
    @Query("select g from Geo g where g.id=:param")
    Geo getGeoById(@Param("param") int param);

    @Query("select g from Geo g")
    List<Geo> getGeoList();
}
