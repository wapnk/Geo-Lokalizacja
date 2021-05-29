package rest.geolocalization.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.geolocalization.models.Geo;
import rest.geolocalization.services.GeoService;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;


@RestController
public class GeoRestController
{
    @Autowired
    GeoService geoService;

    @GetMapping("getHistoryList")
    public String getHistoryList()
    {
        List<Geo> geoList=geoService.getGeoList();
        String result="";
        for(Geo tmp:geoList)
        {
            result+=tmp.toString()+"</br>";
        }
        return result;
       // return new Gson().toJson(geoList).toString();
    }
    @GetMapping(value = "getHistoryById",params = "id")
    public String getHistoryById(@RequestParam("id") int id)
    {
        Geo geo=geoService.getGeoById(id);
        return new Gson().toJson(geo);
    }
    @PostConstruct // todo to delete
    private void build()
    {
        geoService.saveGeo(new Geo("laptop","1111111",11.1,11.1,"city1",new Date()));//TODO to delete
        geoService.saveGeo(new Geo("komputer","2222222",22.2,22.2,"city2",new Date()));//TODO to delete
        geoService.saveGeo(new Geo("telefon","3333333",33.3,33.3,"city3",new Date()));//TODO to delete
    }
}